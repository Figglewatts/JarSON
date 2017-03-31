package figglewatts.jarson;

/**
 * Provides a representation of a null JSON node.
 * 
 * @author Sam Gibson
 * 
 * @version 1.0
 * 
 * @see JSONNode
 */
public class JSONNull extends JSONNode {

    /**
     * @return {@code null}, as it represents a null value.
     */
    @Override
    public String getValue() {
	return "null";
    }
    
    /**
     * Doesn't set anything, as this is a null value. This method is retained
     * for polymorphism with {@link JSONNode}.
     * @param value Unused, but retained for polymorphism.
     */
    @Override
    public final void setValue(String value) { }

    @Override
    public JSONNodeType getNodeType() {
	return JSONNodeType.NULLVALUE;
    }

    @Override
    public boolean equals(Object other) {
	if (other == this) return true;
	return other instanceof JSONNull;
    }

    @Override
    public int hashCode() {
	return 0;
    }

    @Override
    public boolean getAsBoolean() {
	return false;
    }

    @Override
    public boolean isNull() {
	return true;
    }
    
    @Override
    public String toString() {
	return "null";
    }
}
