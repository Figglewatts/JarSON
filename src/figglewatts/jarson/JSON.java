package figglewatts.jarson;

import figglewatts.jarson.exceptions.JSONParseException;

/**
 *
 * @author Sam Gibson
 */
public class JSON {
    private JSON() { }
    
    public static JSONNode parse(String jsonString) throws JSONParseException {
	return JSONNode.parse(jsonString);
    }
}
