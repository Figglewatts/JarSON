package figglewatts.jarson.exceptions;

/**
 * Provides an exception for errors in parsing JSON.
 * 
 * @author Sam Gibson
 * 
 * @version 1.0
 */
public class JSONParseException extends Exception {

    public JSONParseException() {
    }

    public JSONParseException(String message) {
	super(message);
    }

    public JSONParseException(String message, Throwable cause) {
	super(message, cause);
    }
    
}
