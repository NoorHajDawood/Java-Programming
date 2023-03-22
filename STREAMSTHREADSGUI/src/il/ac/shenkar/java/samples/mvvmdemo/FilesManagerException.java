package il.ac.shenkar.java.samples.mvvmdemo;

public class FilesManagerException extends Exception{
    public FilesManagerException(String message) {
        super(message);
    }

    public FilesManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
