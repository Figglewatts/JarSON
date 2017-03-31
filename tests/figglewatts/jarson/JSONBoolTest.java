package figglewatts.jarson;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam Gibson
 */
public class JSONBoolTest {
    
    public JSONBoolTest() {
    }

    /**
     * Test of getValue method, of class JSONBool.
     */
    @Test
    public void testGetValue() {
	System.out.println("getValue");
	JSONBool instance = new JSONBool(false);
	String expResult = "false";
	String result = instance.getValue();
	assertEquals(expResult, result);
    }

    /**
     * Test of setValue method, of class JSONBool.
     */
    @Test
    public void testSetValue() {
	System.out.println("setValue");
	String value = "false";
	JSONBool instance = new JSONBool(true);
	instance.setValue(value);
	assertEquals("false", instance.getValue());
    }

    /**
     * Test of equals method, of class JSONBool.
     */
    @Test
    public void testEquals() {
	System.out.println("equals");
	Object otherObject = new JSONString("false");
	Object otherJSONBool = new JSONBool("true");
	JSONBool instance = new JSONBool("false");
	JSONBool otherInstance = new JSONBool("false");
	assertTrue(instance.equals(otherInstance) 
		&& otherInstance.equals(instance));
	assertFalse(instance.equals(otherObject));
	assertFalse(instance.equals(otherJSONBool));
    }

    /**
     * Test of hashCode method, of class JSONBool.
     */
    @Test
    public void testHashCode() {
	System.out.println("hashCode");
	JSONBool instance = new JSONBool(false);
	JSONBool diffHash = new JSONBool(true);
	JSONBool sameHash = new JSONBool(false);
	assertTrue(instance.hashCode() == sameHash.hashCode());
	assertFalse(instance.hashCode() == diffHash.hashCode());
    }

    /**
     * Test of toString method, of class JSONBool.
     */
    @Test
    public void testToString() {
	System.out.println("toString");
	JSONBool instance = new JSONBool("true");
	String expResult = "true";
	String result = instance.toString();
	assertEquals(expResult, result);
    }
}
