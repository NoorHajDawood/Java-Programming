package il.ac.shenkar.java.fraction;

public class FractionException extends Exception{
    public FractionException(String message) {
        super(message);
    }

    public FractionException(String message, Throwable cause) {
        super(message, cause);
    }
}
