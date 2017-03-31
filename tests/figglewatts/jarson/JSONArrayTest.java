package figglewatts.jarson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam
 */
public class JSONArrayTest {
    
    private JSONArray array;
    JSONNumber a1;
    JSONBool a2;
    JSONString a3;
    JSONNull a4;
    
    public JSONArrayTest() {
    }
    
    @Before
    public void setUp() {
	array = new JSONArray();
	a1 = new JSONNumber(12.4);
	a2 = new JSONBool(true);
	a3 = new JSONString("Array pos 3");
	a4 = new JSONNull();
	array.add(a1);
	array.add(a2);
	array.add(a3);
	array.add(a4);
    }
    
    @After
    public void tearDown() {
	array = null;
	a1 = null;
	a2 = null;
	a3 = null;
	a4 = null;
    }

    /**
     * Test of get method, of class JSONArray.
     */
    @Test
    public void testGet() {
	System.out.println("get");
	assertEquals(a3, array.get(2));
    }

    /**
     * Test of set method, of class JSONArray.
     */
    @Test
    public void testSet() {
	System.out.println("set");
	array.set(2, a4);
	assertEquals(a4, array.get(2));
    }

    /**
     * Test of remove method, of class JSONArray.
     */
    @Test
    public void testRemove_JSONNode() {
	System.out.println("remove");
	array.remove(a3);
	assertFalse(array.get(2) instanceof JSONString);
    }

    /**
     * Test of remove method, of class JSONArray.
     */
    @Test
    public void testRemove_int() {
	System.out.println("remove");
	array.remove(2);
	assertFalse(array.get(2) instanceof JSONString);
    }

    /**
     * Test of add method, of class JSONArray.
     */
    @Test
    public void testAdd_JSONNode() {
	System.out.println("add");
	JSONNumber a5 = new JSONNumber(13.37);
	array.add(a5);
	assertEquals("13.37", array.get(4).getValue());
    }

    /**
     * Test of add method, of class JSONArray.
     */
    @Test
    public void testAdd_String_JSONNode() {
	System.out.println("add");
	JSONNumber a5 = new JSONNumber(13.37);
	array.add("test", a5);
	assertEquals("13.37", array.get(4).getValue());
    }

    /**
     * Test of count method, of class JSONArray.
     */
    @Test
    public void testCount() {
	System.out.println("count");
	assertEquals(4, array.count());
    }

    /**
     * Test of equals method, of class JSONArray.
     */
    @Test
    public void testEquals() {
	System.out.println("equals");
	
	JSONArray sameArray = new JSONArray();
	sameArray.add(a1);
	sameArray.add(a2);
	sameArray.add(a3);
	sameArray.add(a4);
	
	JSONArray diffArray = new JSONArray();
	diffArray.add(a1);
	diffArray.add(a3);
	diffArray.add(a4);
	
	assertTrue(array.equals(sameArray) && sameArray.equals(array));
	assertFalse(array.equals(diffArray));
    }

    /**
     * Test of hashCode method, of class JSONArray.
     */
    @Test
    public void testHashCode() {
	JSONArray sameHash = new JSONArray();
	sameHash.add(a1);
	sameHash.add(a2);
	sameHash.add(a3);
	sameHash.add(a4);
	
	JSONArray diffHash = new JSONArray();
	diffHash.add(a1);
	diffHash.add(a3);
	diffHash.add(a4);
	
	assertTrue(array.hashCode() == sameHash.hashCode());
	assertFalse(array.hashCode() == diffHash.hashCode());
    }

    /**
     * Test of toString method, of class JSONArray.
     */
    @Test
    public void testToString() {
	System.out.println("toString");
	String expResult = "[ 12.4, true, \"Array pos 3\", null ]";
	String result = array.toString();
	System.out.println(result);
	assertEquals(expResult, result);
    }
    
}
