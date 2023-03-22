package il.ac.shenkar.java.project;

/**
 * exception class for the project
 */
public class CostsManagerException extends Exception{
    /**
     * constructor with only message
     * @param message
     */
    public CostsManagerException(String message) {
        super(message);
    }

    /**
     * constructor with message and cause
     * @param message
     * @param cause
     */
    public CostsManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
