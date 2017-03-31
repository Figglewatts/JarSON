package figglewatts.jarson;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam Gibson
 */
public class JSONObjectTest {
    
    JSONObject o;
    JSONNumber weight;
    JSONBool kilos;
    JSONString id;
    JSONNull additional;
    
    public JSONObjectTest() {
    }
    
    @Before
    public void setUp() {
	o = new JSONObject();
	weight = new JSONNumber(12.4);
	kilos = new JSONBool(true);
	id = new JSONString("Mass of item");
	additional = new JSONNull();
	
	o.add("weight", weight);
	o.add("kilos", kilos);
	o.add("id", id);
	o.add("additional", additional);
    }

    /**
     * Test of equals method, of class JSONObject.
     */
    @Test
    public void testEquals() {
	System.out.println("equals");

	JSONObject otherSame = new JSONObject();
	otherSame.add("weight", weight);
	otherSame.add("kilos", kilos);
	otherSame.add("id", id);
	otherSame.add("additional", additional);
	
	JSONObject otherDiff = new JSONObject();
	otherDiff.add("weight", weight);
	otherDiff.add("lbs", kilos);
	otherDiff.add("id", id);
	otherDiff.add("additional", additional);
	
	assertTrue(o.equals(otherSame) && otherSame.equals(o));
	assertFalse(o.equals(otherDiff));
    }

    /**
     * Test of hashCode method, of class JSONObject.
     */
    @Test
    public void testHashCode() {
	System.out.println("hashCode");
	JSONObject sameHash = new JSONObject();
	sameHash.add("weight", weight);
	sameHash.add("kilos", kilos);
	sameHash.add("id", id);
	sameHash.add("additional", additional);
	
	JSONObject diffHash = new JSONObject();
	diffHash.add("weight", weight);
	diffHash.add("lbs", kilos);
	diffHash.add("id", id);
	diffHash.add("additional", additional);
	
	assertEquals(o.hashCode(), sameHash.hashCode());
	assertNotEquals(o.hashCode(), diffHash.hashCode());
    }

    /**
     * Test of remove method, of class JSONObject.
     */
    @Test
    public void testRemove_JSONNode() {
	System.out.println("remove");
	
	o.remove(weight);
	
	assertEquals(null, o.get("weight"));
    }

    /**
     * Test of remove method, of class JSONObject.
     */
    @Test
    public void testRemove_int() {
	System.out.println("remove");
	
	JSONNode node = o.get(1);
	o.remove(1);
	
	assertFalse(node.equals(o.get(1)));
    }

    /**
     * Test of remove method, of class JSONObject.
     */
    @Test
    public void testRemove_String() {
	System.out.println("remove");
	
	o.remove("weight");
	
	assertEquals(null, o.get("weight"));
    }

    /**
     * Test of add method, of class JSONObject.
     */
    @Test
    public void testAdd() {
	System.out.println("add");
	
	JSONString test = new JSONString("testNode");
	
	o.add("test", test);
	assertEquals("testNode", o.get("test").getValue());
    }

    /**
     * Test of count method, of class JSONObject.
     */
    @Test
    public void testCount() {
	System.out.println("count");

	assertEquals(4, o.count());
    }

    /**
     * Test of set method, of class JSONObject.
     */
    @Test
    public void testSet_String_JSONNode() {
	System.out.println("set");
	
	o.set("id", weight);
	assertEquals(o.get("weight"), o.get("id"));
    }

    /**
     * Test of set method, of class JSONObject.
     */
    @Test
    public void testSet_int_JSONNode() {
	System.out.println("set");
	
	JSONNode node = o.get(1);
	o.set(2, node);
	
	assertEquals(node, o.get(2));
    }

    /**
     * Test of get method, of class JSONObject.
     */
    @Test
    public void testGet_String() {
	System.out.println("get");
	
	assertEquals(weight, o.get("weight"));
    }
}
