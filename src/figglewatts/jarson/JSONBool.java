package figglewatts.jarson;

import java.util.Objects;

/**
 * Provides a representation of a boolean for putting in the
 * {@link JSONNode} tree.
 * 
 * @author Sam Gibson
 * 
 * @version 1.0
 * 
 * @see JSONNode
 */
public class JSONBool extends JSONNode {
    private boolean data;
    
    /**
     * Instantiate this {@code JSONBool} with a boolean value.
     * @param value The boolean value
     */
    public JSONBool(boolean value) {
	this.data = value;
    }
    
    /**
     * Instantiate this {@code JSONBool} with a {@link String} representation of 
     * a boolean.
     * @param value The boolean in {@link String} format
     */
    public JSONBool(String value) {
	this.setValue(value);
    }
    
    /**
     * @return This node's boolean value.
     */
    @Override
    public String getValue() {
	return Boolean.toString(data);
    }

    /**
     * Set this node's value to a given {@link String} representation of a 
     * boolean.
     * @param value The {@link String} representation of a boolean. 
     */
    @Override
    public final void setValue(String value) {
	this.data = Boolean.parseBoolean(value);
    }

    @Override
    public JSONNodeType getNodeType() {
	return JSONNodeType.BOOLEAN;
    }

    @Override
    public boolean equals(Object other) {
	if (other == this) return true;
	if (!(other instanceof JSONBool)) return false;
	
	JSONBool jsonBool = (JSONBool)other;
	return data == jsonBool.data;
    }

    @Override
    public int hashCode() {
	return Objects.hash(data);
    }

    @Override
    public String toString() {
	return data ? "true" : "false";
    }

    @Override
    public boolean isBoolean() {
	return true;
    }
}
