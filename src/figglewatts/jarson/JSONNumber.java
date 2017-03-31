/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figglewatts.jarson;

import java.util.Objects;

/**
 *
 * @author Sam Gibson
 */
public class JSONNumber extends JSONNode {
    private double data;
    
    public JSONNumber(double data) {
	this.data = data;
    }
    
    public JSONNumber(String data) {
	this.setValue(data);
    }
    
    @Override
    public String getValue() {
	return Double.toString(data);
    }

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
