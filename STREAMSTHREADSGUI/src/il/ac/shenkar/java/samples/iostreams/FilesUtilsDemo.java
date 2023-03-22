package il.ac.shenkar.java.samples.iostreams;

public class FilesUtilsDemo {

    public static void main(String args[]) {

        IFilesUtils utils = new SimpleFilesUtils();
        try {
            //utils.writeIntToFile("data.bin",123);
            // System.out.println(utils.readIntFromFile("data.bin"));
            //Rectangle rec = new Rectangle(3,4);
            //utils.writeObjectToFile("data",rec);
            // System.out.println(utils.readObjectFromFile("data"));
            // utils.copyTextualFile("hello.txt","other.txt");
            String text = utils.readTextFromURL("http://www.zindell.com/index.php");
            System.out.println(text);

        } catch (FilesUtilsException e) {
            e.printStackTrace();
        }


    }
}
