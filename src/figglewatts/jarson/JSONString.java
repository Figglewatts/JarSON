package figglewatts.jarson;

import java.util.Objects;

/**
 * Provides a representation of a {@link JSONNode} that is a {@link String}.
 * 
 * @author Sam Gibson
 * 
 * @version 1.0
 * 
 * @see JSONNode
 */
public class JSONString extends JSONNode {
    private String data;
    
    /**
     * Instantiate this {@code JSONString} with a {@link String} value.
     * @param value 
     */
    public JSONString(String value) {
	this.data = value;
    }
    
    /**
     * @return The {@link String} stored in this node.
     */
    @Override
    public String getValue() {
	return data;
    }

    /**
     * Set the {@link String} stored in this node to a given value.
     * @param value The value to set the {@link String} to.
     */
    @Override
    public void setValue(String value) {
	this.data = value;
    }

    @Override
    public JSONNodeType getNodeType() {
	return JSONNodeType.STRING;
    }

    @Override
    public boolean equals(Object other) {
	if (other == this) return true;
	if (!(other instanceof JSONString)) return false;
	
	JSONString jsonString = (JSONString)other;
	return Objects.equals(data, jsonString.data);
    }

    @Override
    public int hashCode() {
	return Objects.hash(data);
    }

    @Override
    public boolean isString() {
	return true;
    }

    @Override
    public String toString() {
	return "\"" + escape(this.data) + "\"";
    }
}
