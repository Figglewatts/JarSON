package figglewatts.jarson;

import java.util.Objects;

/**
 * Provides a representation of a {@link JSONNode} which is a number.
 * 
 * @author Sam Gibson
 * 
 * @version 1.0
 * 
 * @see JSONNode
 */
public class JSONNumber extends JSONNode {
    private double data;
    
    /**
     * Instantiate this {@code JSONNumber} with a double value.
     * @param data The double value for this node to contain.
     */
    public JSONNumber(double data) {
	this.data = data;
    }
    
    /**
     * Instantiate this {@code JSONNumber} with a {@link String} representation
     * of a double.
     * @param data The {@link String} representation of a double.
     */
    public JSONNumber(String data) {
	this.setValue(data);
    }
    
    /**
     * Get the value of this node's double value as a {@link String}.
     * @return The {@link String} value of this node.
     */
    @Override
    public String getValue() {
	return Double.toString(data);
    }

    /**
     * Set this node's value to a {@link String} representation of a double.
     * Will set the value to 0 and print an error to {@code System.err} if
     * unable to convert value to double.
     * @param value The {@link String} representation of the double.
     */
    @Override
    public final void setValue(String value) {
	double v = 0;
	try {
	    v = Double.parseDouble(value);
	}
	catch (NumberFormatException e) {
	    System.err.println("Could not set JSONNumber to " + value);
	    e.printStackTrace(System.err);
	}
	this.data = v;
    }

    @Override
    public JSONNodeType getNodeType() {
	return JSONNodeType.NUMBER;
    }

    @Override
    public boolean equals(Object other) {
	if (this == other) return true;
	if (!(other instanceof JSONNumber)) return false;
	
	JSONNumber jsonNumber = (JSONNumber)other;
	return data == jsonNumber.data;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.data);
    }

    @Override
    public String toString() {
	return Double.toString(data);
    }

    @Override
    public boolean isNumber() {
	return true;
    }
}
