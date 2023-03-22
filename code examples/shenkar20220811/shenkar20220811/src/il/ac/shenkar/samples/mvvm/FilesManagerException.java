package il.ac.shenkar.samples.mvvm;

public class FilesManagerException extends Exception{
    public FilesManagerException(String message) {
        super(message);
    }

    public FilesManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
