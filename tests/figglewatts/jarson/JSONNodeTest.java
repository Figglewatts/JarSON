package figglewatts.jarson;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam
 */
public class JSONNodeTest {
    /**
     * The test data used for parsing a JSON string.
     */
    private final String parseTestData = "{\"widget\": {\n" +
"    \"debug\": \"on\",\n" +
"    \"window\": {\n" +
"        \"title\": \"Sample Konfabulator Widget\",\n" +
"        \"name\": \"main_window\",\n" +
"        \"width\": 500,\n" +
"        \"height\": 500\n" +
"    },\n" +
"	\"images\": [\n" +
"		{ \"src\": \"Images/Sun.png\",\n" +
"        \"name\": \"sun1\",\n" +
"        \"hOffset\": 250,\n" +
"        \"vOffset\": 250,\n" +
"        \"alignment\": \"center\" },\n" +
"		{ \"src\": \"Images/Earth.png\",\n" +
"        \"name\": \"earth1\",\n" +
"        \"hOffset\": 250,\n" +
"        \"vOffset\": 250,\n" +
"        \"alignment\": \"left\" },\n" +
"		{ \"src\": \"Images/Moon.png\",\n" +
"        \"name\": \"moon1\",\n" +
"        \"hOffset\": 250,\n" +
"        \"vOffset\": 250,\n" +
"        \"alignment\": \"right\" }\n" +
"	],\n" +
"    \"text\": {\n" +
"        \"data\": \"Click Here\",\n" +
"        \"size\": 36,\n" +
"        \"style\": \"bold\",\n" +
"        \"name\": \"text1\",\n" +
"        \"hOffset\": 250,\n" +
"        \"vOffset\": 100,\n" +
"        \"alignment\": \"center\",\n" +
"        \"onMouseUp\": \"sun1.opacity = (sun1.opacity / 100) * 90;\"\n" +
"    }\n" +
"}}";
    
    public JSONNodeTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getAsDouble method, of class JSONNode.
     */
    @Test
    public void testGetAsDouble() {
	System.out.println("getAsDouble");
	JSONNode instance = new JSONNumber(13.37);
	double expResult = 13.37;
	double result = instance.getAsDouble();
	assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of getAsDouble method, of class JSONNode.
     */
    @Test(expected = NumberFormatException.class)
    public void testGetAsDoubleException() {
	System.out.println("getAsDouble");
	JSONNode instance = new JSONString("test");
	double expResult = 13.37;
	double result = instance.getAsDouble();
    }

    /**
     * Test of getAsInt method, of class JSONNode.
     */
    @Test
    public void testGetAsInt() {
	System.out.println("getAsInt");
	JSONNode instance = new JSONNumber(10.07);
	int expResult = 10;
	int result = instance.getAsInt();
	assertEquals(expResult, result);
    }
    
    /**
     * Test of getAsInt method, of class JSONNode.
     */
    @Test(expected = NumberFormatException.class)
    public void testGetAsIntException() {
	System.out.println("getAsInt");
	JSONNode instance = new JSONString("test");
	int expResult = 10;
	int result = instance.getAsInt();
    }

    /**
     * Test of getAsFloat method, of class JSONNode.
     */
    @Test
    public void testGetAsFloat() {
	System.out.println("getAsFloat");
	JSONNode instance = new JSONNumber(10.5F);
	float expResult = 10.5F;
	float result = instance.getAsFloat();
	assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of getAsFloat method, of class JSONNode.
     */
    @Test(expected = NumberFormatException.class)
    public void testGetAsFloatException() {
	System.out.println("getAsFloat");
	JSONNode instance = new JSONString("test");
	float expResult = 10.5F;
	float result = instance.getAsFloat();
    }

    /**
     * Test of getAsBoolean method, of class JSONNode.
     */
    @Test
    public void testGetAsBoolean() {
	System.out.println("getAsBoolean");
	JSONNode instance = new JSONBool(true);
	boolean expResult = true;
	boolean result = instance.getAsBoolean();
	assertEquals(expResult, result);
    }
    
    /**
     * Test of getAsBoolean method, of class JSONNode.
     */
    @Test
    public void testGetAsBooleanFalse() {
	System.out.println("getAsBoolean");
	JSONNode instance = new JSONString("test");
	boolean expResult = false;
	boolean result = instance.getAsBoolean();
	assertEquals(expResult, result);
    }

    /**
     * Test of parse method, of class JSONNode.
     */
    @Test
    public void testParse() throws Exception {
	System.out.println("parse");
	
	JSONNode result = JSONNode.parse(parseTestData);
	assertEquals(4, result.get("widget").count());
	
	assertEquals("main_window", result.get("widget")
		.get("window")
		.get("name").getValue());
	
	assertEquals(3, result.get("widget")
		.get("images").count());
	
	assertEquals("earth1", result.get("widget")
		.get("images")
		.get(1).get("name")
		.getValue());
    }
    
}
