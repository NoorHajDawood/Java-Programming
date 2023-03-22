package il.ac.shenkar.java.samples.iostreams;

public interface IFilesUtils {
    public void writeIntToFile(String file, int number) throws FilesUtilsException;
    public int readIntFromFile(String file) throws FilesUtilsException;
    public void writeObjectToFile(String file, Object ob) throws FilesUtilsException;
    public Object readObjectFromFile(String file) throws FilesUtilsException;
    public void copyTextualFile(String from, String to) throws FilesUtilsException;
    public String readTextFromURL(String address) throws FilesUtilsException;
}
