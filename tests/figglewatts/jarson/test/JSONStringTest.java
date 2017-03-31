package figglewatts.jarson.test;

import figglewatts.jarson.JSONBool;
import figglewatts.jarson.JSONNodeType;
import figglewatts.jarson.JSONString;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam Gibson
 */
public class JSONStringTest {
    
    public JSONStringTest() {
    }

    /**
     * Test of getValue method, of class JSONString.
     */
    @Test
    public void testGetValue() {
	System.out.println("getValue");
	JSONString instance = new JSONString("test");
	String expResult = "test";
	String result = instance.getValue();
	assertEquals(expResult, result);
    }

    /**
     * Test of setValue method, of class JSONString.
     */
    @Test
    public void testSetValue() {
	System.out.println("setValue");
	String value = "";
	JSONString instance = new JSONString("test");
	instance.setValue("testSetting");
	String expResult = "testSetting";
	String result = instance.getValue();
	assertEquals(expResult, result);
    }

    /**
     * Test of getNodeType method, of class JSONString.
     */
    @Test
    public void testGetNodeType() {
	System.out.println("getNodeType");
	JSONString instance = new JSONString("test");
	JSONNodeType expResult = JSONNodeType.STRING;
	JSONNodeType result = instance.getNodeType();
	assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class JSONString.
     */
    @Test
    public void testEqualsNegativeSameType() {
	System.out.println("equalsSameType");
	Object other = new JSONString("test2");
	JSONString instance = new JSONString("test");
	boolean expResult = false;
	boolean result = instance.equals(other);
	assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class JSONString.
     */
    @Test
    public void testEqualsNegativeDiffType() {
	System.out.println("equalsDiffType");
	Object other = new JSONBool(false);
	JSONString instance = new JSONString("test");
	boolean expResult = false;
	boolean result = instance.equals(other);
	assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class JSONString.
     */
    @Test
    public void testEqualsPositive() {
	System.out.println("equalsPositive");
	Object other = new JSONString("test");
	JSONString instance = new JSONString("test");
	boolean expResult = true;
	boolean result = instance.equals(other);
	assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class JSONString.
     */
    @Test
    public void testHashCodePositive() {
	System.out.println("hashCodePositive");
	JSONString instance = new JSONString("test");
	JSONString sameHash = new JSONString("test");
	assertTrue(instance.hashCode() == sameHash.hashCode());
    }
    
    /**
     * Test of hashCode method, of class JSONString.
     */
    @Test
    public void testHashCodeNegative() {
	System.out.println("hashCodeNegative");
	JSONString instance = new JSONString("test");
	JSONString sameHash = new JSONString("test1");
	assertFalse(instance.hashCode() == sameHash.hashCode());
    }

    /**
     * Test of isString method, of class JSONString.
     */
    @Test
    public void testIsString() {
	System.out.println("isString");
	JSONString instance = new JSONString("test");
	boolean expResult = true;
	boolean result = instance.isString();
	assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class JSONString.
     */
    @Test
    public void testToString() {
	System.out.println("toString");
	JSONString instance = new JSONString("test");
	String expResult = "\"test\"";
	String result = instance.toString();
	assertEquals(expResult, result);
    }
    
}
