package figglewatts.jarson;

import java.util.Objects;

/**
 *
 * @author Sam Gibson
 */
public class JSONBool extends JSONNode {
    private boolean data;
    
    public JSONBool(boolean value) {
	this.data = value;
    }
    
    public JSONBool(String value) {
	this.setValue(value);
    }
    
    @Override
    public String getValue() {
	return Boolean.toString(data);
    }

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
