package il.ac.shenkar.java.samples.mvvmdemo.model;

import il.ac.shenkar.java.samples.mvvmdemo.FilesManagerException;
import java.io.*;

public class SimpleFilesManagerModel implements IFilesManagerModel {
    @Override
    public String[] getFilesAndFoldersNames(String directory) throws FilesManagerException {
        File file = new File(directory);
        return file.list();
    }

    @Override
    public String getTextualFileContent(String file) throws FilesManagerException {
        FileInputStream fis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);
            String text = dis.readUTF();
            return text;
        } catch(IOException e) {
            throw new FilesManagerException("problem reading content of textual file",e);
        } finally {
            if(dis!=null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
