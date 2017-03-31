/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figglewatts.jarson.exceptions;

/**
 *
 * @author Sam Gibson
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
