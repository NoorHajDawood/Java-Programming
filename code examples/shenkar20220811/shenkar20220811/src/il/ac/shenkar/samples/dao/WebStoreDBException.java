package il.ac.shenkar.samples.dao;

public class WebStoreDBException extends Exception {

    public WebStoreDBException(String message) {
        super(message);
    }

    public WebStoreDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
