package figglewatts.jarson;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam Gibson
 */
public class JSONNumberTest {
    
    public JSONNumberTest() {
    }

    /**
     * Test of getValue method, of class JSONNumber.
     */
    @Test
    public void testGetValue() {
	System.out.println("getValue");
	JSONNumber instance = new JSONNumber(13.37);
	String expResult = "13.37";
	String result = instance.getValue();
	assertEquals(expResult, result);
    }

    /**
     * Test of setValue method, of class JSONNumber.
     */
    @Test
    public void testSetValue() {
	System.out.println("setValue");
	String value = "13.37";
	JSONNumber instance = new JSONNumber(10.01);
	instance.setValue(value);
	assertEquals(value, instance.getValue());
    }

    /**
     * Test of equals method, of class JSONNumber.
     */
    @Test
    public void testEquals() {
	System.out.println("equals");
	Object otherValue = new JSONNumber(10.03);
	Object otherString = new JSONString("test");
	JSONNumber instanceA = new JSONNumber(13.37);
	JSONNumber instanceB = new JSONNumber(13.37);
	assertTrue(instanceA.equals(instanceB) && instanceB.equals(instanceA));
	assertFalse(instanceA.equals(otherValue));
	assertFalse(instanceA.equals(otherString));
    }

    /**
     * Test of hashCode method, of class JSONNumber.
     */
    @Test
    public void testHashCode() {
	System.out.println("hashCode");
	JSONNumber instance = new JSONNumber(13.37);
	JSONNumber sameHash = new JSONNumber(13.37);
	JSONNumber diffHash = new JSONNumber(13.38);
	assertTrue(instance.hashCode() == sameHash.hashCode());
	assertFalse(instance.hashCode() == diffHash.hashCode());
    }

    /**
     * Test of toString method, of class JSONNumber.
     */
    @Test
    public void testToString() {
	System.out.println("toString");
	JSONNumber instance = new JSONNumber(13.37);
	assertEquals("13.37", instance.toString());
    }
}
