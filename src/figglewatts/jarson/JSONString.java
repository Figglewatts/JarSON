package figglewatts.jarson;

import java.util.Objects;

/**
 *
 * @author Sam Gibson
 */
public class JSONString extends JSONNode {
    private String data;
    
    public JSONString(String value) {
	this.data = value;
    }
    
    @Override
    public String getValue() {
	return data;
    }

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
