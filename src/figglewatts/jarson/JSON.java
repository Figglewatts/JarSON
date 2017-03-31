package figglewatts.jarson;

import figglewatts.jarson.exceptions.JSONParseException;

/**
 * Provides a static method to parse a JSON string.
 * @author Sam Gibson
 * 
 * @version 1.0
 */
public class JSON {
    private JSON() { }
    
    /**
     * Parse a JSON string into a tree of {@code JSONNode} objects.
     * @param jsonString The JSON string.
     * @return The tree of {@code JSONNode} objects.
     * @throws JSONParseException When there was some error in parsing, i.e.
     * mismatched brackets or quotation marks.
     */
    public static JSONNode parse(String jsonString) throws JSONParseException {
	return JSONNode.parse(jsonString);
    }
}
