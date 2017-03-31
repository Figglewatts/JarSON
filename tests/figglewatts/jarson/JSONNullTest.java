package figglewatts.jarson;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam Gibson
 */
public class JSONNullTest {
    
    public JSONNullTest() {
    }

    /**
     * Test of getValue method, of class JSONNull.
     */
    @Test
    public void testGetValue() {
	System.out.println("getValue");
	JSONNull instance = new JSONNull();
	String expResult = "null";
	String result = instance.getValue();
	assertEquals(expResult, result);
    }

    /**
     * Test of setValue method, of class JSONNull.
     */
    @Test
    public void testSetValue() {
	System.out.println("setValue");
	String value = "";
	JSONNull instance = new JSONNull();
	instance.setValue(value);
	assertEquals("null", instance.getValue());
    }

    /**
     * Test of equals method, of class JSONNull.
     */
    @Test
    public void testEquals() {
	System.out.println("equals");
	Object otherObject = new JSONString("test");
	JSONNull instance = new JSONNull();
	JSONNull otherNull = new JSONNull();
	assertTrue(instance.equals(otherNull) && otherNull.equals(instance));
	assertFalse(instance.equals(otherObject));
    }

    /**
     * Test of hashCode method, of class JSONNull.
     */
    @Test
    public void testHashCode() {
	System.out.println("hashCode");
	JSONNull instance = new JSONNull();
	JSONNull sameHash = new JSONNull();
	JSONString diffHash = new JSONString("hash");
	assertTrue(instance.hashCode() == sameHash.hashCode());
	assertFalse(instance.hashCode() == diffHash.hashCode());
    }

    /**
     * Test of getAsBoolean method, of class JSONNull.
     */
    @Test
    public void testGetAsBoolean() {
	System.out.println("getAsBoolean");
	JSONNull instance = new JSONNull();
	boolean expResult = false;
	boolean result = instance.getAsBoolean();
	assertEquals(expResult, result);
    }
}
