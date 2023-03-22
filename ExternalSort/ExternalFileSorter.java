import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;

public class ExternalFileSorter {

    /**
     * Split file into mini-files of maximum of concurrentRecordsLimit lines each.
     * Each mini-file is sorted and saved as a temporary file
     * Returns a list of all mini-files to be merge-sorted later.
     * 
     * @param file                   source file
     * @param cmp                    comperator
     * @param concurrentRecordsLimit maximum lines per file
     * @return List<File> of temporary sorted files
     * @throws IOException
     */
    public static List<File> splitIntoSortedFiles(File file, Comparator<String> cmp, long concurrentRecordsLimit)
            throws IOException {
        System.out.println("Split the Input file into small Files with " + concurrentRecordsLimit + " rows");
        List<File> files = new ArrayList<File>();
        BufferedReader fbr = new BufferedReader(new FileReader(file));
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService<File> completionService = new ExecutorCompletionService<File>(executor);

        int numberOfFiles = 0;
        int currentRecords = 0;
        try {
            List<String> tmplist = new ArrayList<String>();
            String line = "";
            try {
                while (line != null) {
                    currentRecords = 0;
                    while ((currentRecords < concurrentRecordsLimit) && (line = fbr.readLine()) != null) {
                        tmplist.add(line);
                        currentRecords++;
                    }
                    List<String> tmpListCopy = new ArrayList<String>(tmplist);
                    completionService.submit(() -> sortAndSave(tmpListCopy, cmp, files));
                    numberOfFiles++;
                    tmplist.clear();
                }
            } catch (EOFException oef) {
                if (tmplist.size() > 0) {
                    List<String> tmpListCopy = new ArrayList<String>(tmplist);
                    completionService.submit(() -> sortAndSave(tmpListCopy, cmp, files));
                    numberOfFiles++;
                    tmplist.clear();
                }
            }
        } finally {
            fbr.close();
        }

        int completedJobs = 0;
        boolean errors = false;
        while (completedJobs < numberOfFiles && !errors) {
            try {
                Future<File> resultFuture = completionService.take();
                File result = resultFuture.get();
                completedJobs++;
            } catch (InterruptedException | ExecutionException e) {
                // log
                errors = true;
                System.err.println(e.getMessage());
            }
        }

        executor.shutdown();

        return files;
    }

    /**
     * sort lines and save in a temporary file
     * 
     * @param tmplist - lines to sort and save
     * @param cmp     - comperator
     * @param files   - files list to append the temporary file to
     * @return the created temporary file
     * @throws IOException
     */
    public static File sortAndSave(List<String> tmplist, Comparator<String> cmp, List<File> files) throws IOException {
        tmplist.sort(cmp); //
        File newtmpfile = File.createTempFile("sortInBatch", "flatfile");
        newtmpfile.deleteOnExit();
        BufferedWriter fbw = new BufferedWriter(new FileWriter(newtmpfile));
        try {
            for (String r : tmplist) {
                fbw.write(r);
                fbw.newLine();
            }
        } finally {
            fbw.close();
        }
        files.add(newtmpfile);
        return newtmpfile;
    }

    /**
     * Merge-sort a list of files into outputfile
     *
     * @param files                - list of files to merge-sort
     * @param outputfile           - output file to merge-sort into
     * @param cmp                  - comperator
     * @param queueInitialCapacity - initial capacity for the priority queue
     * @throws IOException
     */
    public static void mergeSortedFiles(List<File> files, File outputfile, final Comparator<String> cmp,
            int queueInitialCapacity) throws IOException {
        PriorityQueue<BinaryFileBuffer> pq = new PriorityQueue<BinaryFileBuffer>(queueInitialCapacity,
                new Comparator<BinaryFileBuffer>() {
                    public int compare(BinaryFileBuffer i, BinaryFileBuffer j) {
                        return cmp.compare(i.peek(), j.peek());
                    }
                });
        for (File f : files) {
            BinaryFileBuffer bfb = new BinaryFileBuffer(f);
            pq.add(bfb);
        }
        BufferedWriter fbw = new BufferedWriter(new FileWriter(outputfile));
        try {
            while (pq.size() > 0) {
                BinaryFileBuffer bfb = pq.poll();
                String r = bfb.pop();
                fbw.write(r);
                fbw.newLine();
                if (bfb.empty()) {
                    bfb.fbr.close();
                    if (!bfb.originalfile.delete()) {
                        System.err.println("File deletion failed");
                    }
                } else {
                    pq.add(bfb);
                }
            }
        } finally {
            fbw.close();
            for (BinaryFileBuffer bfb : pq)
                bfb.close();
        }
    }

    /**
     * build comparator for specific field type
     * 
     * @return CSV Comparator
     */
    private static Comparator<String> createCsvComparator(int indexOfColumn) {
        String regex = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";
        Pattern patternNumber = Pattern.compile(regex);
        String datePattern = "yyyy-MM-dd";

        return new Comparator<String>() {
            public int compare(String r1, String r2) {

                String[] row1 = r1.split(",");
                String[] row2 = r2.split(",");

                if (patternNumber.matcher(row1[indexOfColumn]).matches()
                        && patternNumber.matcher(row2[indexOfColumn]).matches()) {
                    double n1 = Double.parseDouble(row1[indexOfColumn]);
                    double n2 = Double.parseDouble(row2[indexOfColumn]);
                    return Double.compare(n1, n2);

                } else {
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat(datePattern);
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat(datePattern);
                    try {
                        Date d1 = dateFormat1.parse(row1[indexOfColumn]);
                        Date d2 = dateFormat2.parse(row2[indexOfColumn]);
                        return d1.compareTo(d2);
                    } catch (ParseException e) {
                        return row1[indexOfColumn].compareTo(row2[indexOfColumn]);
                    }
                }
            }
        };
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Please provide input file and key field index and x (number of records in memory)");
            return;
        }
        String inputfile = args[0];
        String outputfile = "sorted-" + inputfile;
        int indexOfColumn = Integer.parseInt(args[1]);
        int concurrentRecordsLimit = Integer.parseInt(args[2]);

        String headers;
        BufferedReader fbr = new BufferedReader(new FileReader(inputfile));
        headers = fbr.readLine();
        fbr.close();
        String[] headersSplited = headers.split(",");
        if (indexOfColumn >= headersSplited.length) {
            System.err.println("Header number is out of range of the file headers");
            return;
        }

        Comparator<String> comparator = createCsvComparator(indexOfColumn);

        List<File> tmpFiles = splitIntoSortedFiles(new File(inputfile), comparator, concurrentRecordsLimit);
        final int numberOfTmpFiles = tmpFiles.size();
        System.out.println("Number of tmp files: " + numberOfTmpFiles);

        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Threads number: " + numberOfThreads);
        int filesPerThread = numberOfTmpFiles / numberOfThreads;
        if (filesPerThread < 2) {
            filesPerThread = 2;
        }
        if (filesPerThread > concurrentRecordsLimit) {
            filesPerThread = concurrentRecordsLimit;
        }
        System.out.println("Files Per Thread: " + filesPerThread);
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        List<File> threadFiles = new ArrayList<File>();

        final int FILESPERTHREAD = filesPerThread;

        class MyRunnable implements Runnable {
            final int start;
            final int end;
            final int index;

            public MyRunnable(int start, int end, int index) {
                this.start = start;
                this.end = end;
                this.index = index;

            }

            @Override
            public void run() {
                try {
                    mergeSortedFiles(tmpFiles.subList(start, end), threadFiles.get(index), comparator, FILESPERTHREAD);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        int offset = 0;
        int threadFileIndex = 0;

        System.out.println("Merge Sort started");
        while (offset < numberOfTmpFiles) {
            File newtmpfile = File.createTempFile("ThreadOutputFiles_", "csv");
            newtmpfile.deleteOnExit();
            threadFiles.add(newtmpfile);
            int fromIndex = offset;
            int toIndex = offset + FILESPERTHREAD;
            if (toIndex > numberOfTmpFiles) {
                toIndex = numberOfTmpFiles;
            }
            service.submit(new MyRunnable(fromIndex, toIndex, threadFileIndex));
            offset += FILESPERTHREAD;
            threadFileIndex++;
        }

        service.shutdown();
        boolean terminationResult = false;
        try {
            terminationResult = service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            if (!terminationResult) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
            System.err.println(e.getMessage());
        }
        if (terminationResult) {
            System.out.println("Last Merge Sort started");
            mergeSortedFiles(threadFiles, new File(outputfile), comparator, FILESPERTHREAD);
        } else {
            System.err.println("Thread pool termination failure.");
        }
    }

}

/**
 * Allows reading line by line from file
 * while providing the option to peek last read line without reading the next
 * line
 */
class BinaryFileBuffer {
    public static int BUFFERSIZE = 4096;
    public BufferedReader fbr;
    public File originalfile;
    private String cache;
    private boolean empty;

    public BinaryFileBuffer(File f) throws IOException {
        originalfile = f;
        fbr = new BufferedReader(new FileReader(f), BUFFERSIZE);
        reload();
    }

    public boolean empty() {
        return empty;
    }

    private void reload() throws IOException {
        try {
            empty = (this.cache = fbr.readLine()) == null;
        } catch (EOFException oef) {
            empty = true;
            cache = null;
        }
    }

    public void close() throws IOException {
        fbr.close();
    }

    public String peek() {
        if (empty())
            return null;
        return cache;
    }

    public String pop() throws IOException {
        String answer = peek();
        reload();
        return answer;
    }
}