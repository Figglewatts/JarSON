package figglewatts.jarson;

import figglewatts.jarson.exceptions.JSONParseException;
import java.util.Stack;

/**
 * Provides an abstract root class for each leaf in the parsed JSON tree.
 * Every JSON element will be parsed into a {@code JSONNode} subclass.
 * 
 * @author Sam Gibson
 * 
 * @version 1.0
 */
public abstract class JSONNode {
    public JSONNode get(int index) { return null; }
    public JSONNode get(String key) { return null; }
    
    public void set(int index, JSONNode node) { }
    public void set(String key, JSONNode node) { }
    
    public String getValue() { return null; }
    public void setValue(String value) { };
    
    /**
     * @return The type of this {@code JSONNode}
     * @see JSONNodeType
     */
    public abstract JSONNodeType getNodeType();
    
    public int count() { return 0; }
    
    public boolean isNumber() { return false; }
    public boolean isString() { return false; }
    public boolean isBoolean() { return false; }
    public boolean isNull() { return false; }
    public boolean isArray() { return false; }
    public boolean isObject() { return false; }
    
    public void add(String key, JSONNode item) { }
    public void add(JSONNode item) { 
	this.add("", item); 
    }
    
    public JSONNode remove(String key) { return null; }
    public JSONNode remove(int index) { return null; }
    public JSONNode remove(JSONNode item) { return item; }
    
    @Override
    public String toString() {
	return "JSONNode";
    }
    
    public double getAsDouble() {
	return Double.parseDouble(this.getValue());
    }
    
    /**
     * Try to get this {@code JSONNode} as an integer.
     * @return The integer value of this node.
     * @throws NumberFormatException if the value of this node was not able
     * to be converted to an int.
     */
    public int getAsInt() throws NumberFormatException {
	return (int)getAsDouble();
    }
    
    /**
     * Try to get this {@code JSONNode} as a float.
     * @return The float value of this node.
     * @throws NumberFormatException if the value of this node was not able
     * to be converted to a float.
     */
    public float getAsFloat() {
	return Float.parseFloat(this.getValue());
    }
    
    /**
     * Get this {@code JSONNode} as a boolean.
     * @return {@code true} if the {@link String} value of this {@code JSONNode} 
     * is equal to {@code "true"} ignoring case, and {@code false} otherwise.
     */
    public boolean getAsBoolean() {
	return Boolean.parseBoolean(this.getValue());
    }
    
    @Override
    public abstract boolean equals(Object other);
    
    @Override
    public abstract int hashCode();
    
    // StringBuilder used for the escape() method
    private static final StringBuilder ESCAPE_BUILDER = new StringBuilder();
    // method used to correctly escape text in JSON
    protected static String escape(String text) {
	ESCAPE_BUILDER.setLength(0);
	char[] chars = text.toCharArray();
	for (char c : chars) {
	    switch (c) {
		case '\\':
		{
		    ESCAPE_BUILDER.append("\\\\");
		} break;
		case '\"':
		{
		    ESCAPE_BUILDER.append("\\\"");
		} break;
		case '\n':
		{
		    ESCAPE_BUILDER.append("\\n");
		} break;
		case '\r':
		{
		    ESCAPE_BUILDER.append("\\r");
		} break;
		case '\t':
		{
		    ESCAPE_BUILDER.append("\\t");
		} break;
		case '\b':
		{
		    ESCAPE_BUILDER.append("\\b");
		} break;
		case '\f':
		{
		    ESCAPE_BUILDER.append("\\f");
		} break;
		default:
		{
		    ESCAPE_BUILDER.append(c);
		} break;
	    }
	}
	return ESCAPE_BUILDER.toString();
    }
    
    // factory methods overloaded to create JSONNodes from any valid value type
    static JSONNode create(String s) {
	return new JSONString(s);
    }
    static JSONNode create(double n) {
	return new JSONNumber(n);
    }
    static JSONNode create(float n) {
	return new JSONNumber(n);
    }
    static JSONNode create(int n) {
	return new JSONNumber(n);
    }
    static JSONNode create(boolean b) {
	return new JSONBool(b);
    }
    
    // parses a JSON element into its correct data type
    static void parseElement(JSONNode ctx, String token, 
	    String tokenName, boolean quoted) {
	if (quoted) {
	    ctx.add(tokenName, create(token));
	    return;
	}
	String tmp = token.toLowerCase();
	if (tmp.equals("false") || tmp.equals("true"))
	    // it's a boolean
	    ctx.add(tokenName, create(Boolean.parseBoolean(tmp)));
	else if (tmp.equals("null"))
	    // it's a null value
	    ctx.add(tokenName, null);
	else
	{
	    double v;
	    try {
		v = Double.parseDouble(token);
		// it parses correctly, it's a number
		ctx.add(tokenName, create(v));
	    }
	    catch (NumberFormatException e) {
		// if not, it's just a regular old token
		ctx.add(tokenName, create(token));
	    }
	}
    }
    
    /**
     * Parse a JSON {@link String} into a tree of {@code JSONNode} objects.
     * @param json The JSON {@link String} to parse.
     * @return The tree of {@code JSONNode} objects.
     * @throws JSONParseException if brackets or quotation marks are mismatched
     */
    public static JSONNode parse(String json) throws JSONParseException {
	// store JSONNode elements in a stack while parsing to preserve
	// order in the tree
	Stack<JSONNode> stack = new Stack<>();
	
	// the current context, i.e. the node we should be attaching other
	// nodes to
	JSONNode ctx = null;
	
	// current position in the string
	int i = 0;
	
	// the current token being built as we parse it
	StringBuilder token = new StringBuilder();
	
	// the name of the token we are about to/currently parsing
	String tokenName = "";
	
	// true if we're inside quotation marks
	boolean quoteMode = false;
	boolean tokenIsQuoted = false;
	
	while (i < json.length()) {
	    char c = json.charAt(i);
	    switch(c) {
		case '{':
		{
		    if (quoteMode) {
			token.append(c);
			break;
		    }
		    
		    // it's a JSON object
		    stack.push(new JSONObject());
		    if (ctx != null) {
			ctx.add(tokenName, stack.peek());
		    }
		    tokenName = "";
		    token.setLength(0);
		    ctx = stack.peek();
		} break;
		case '[':
		{
		    if (quoteMode) {
			token.append(c);
			break;
		    }
		    
		    // it's a JSON array
		    stack.push(new JSONArray());
		    if (ctx != null) {
			ctx.add(tokenName, stack.peek());
		    }
		    tokenName = "";
		    token.setLength(0);
		    ctx = stack.peek();
		} break;
		case '}':
		case ']':
		{
		    if (quoteMode) {
			token.append(c);
			break;
		    }
		    if (stack.isEmpty()) {
			throw new JSONParseException(
				"JSON Parse: Too many closing brackets");
		    }
		    
		    // we've reached the end of an object or array, clean up
		    stack.pop();
		    if (token.length() > 0) {
			parseElement(ctx, token.toString(), 
				tokenName, tokenIsQuoted);
			tokenIsQuoted = false;
		    }
		    tokenName = "";
		    token.setLength(0);
		    if (stack.size() > 0) {
			ctx = stack.peek();
		    }
		} break;
		case ':':
		{
		    if (quoteMode)
		    {
			token.append(c);
			break;
		    }
		    
		    // we've reached the end of a token name, set the variable
		    // and clean up
		    tokenName = token.toString().trim();
		    token.setLength(0);
		    tokenIsQuoted = false;
		} break;
		case '"':
		{
		    // if it's already true turn it off, if not then turn it on
		    quoteMode ^= true;
		    tokenIsQuoted |= quoteMode;
		} break;
		case ',':
		{
		    if (quoteMode) {
			token.append(c);
			break;
		    }
		    
		    // parse the next item in a sequence
		    if (token.length() > 0) {
			parseElement(ctx, token.toString(), 
				tokenName, tokenIsQuoted);
			tokenIsQuoted = false;
		    }
		    tokenName = "";
		    token.setLength(0);
		    tokenIsQuoted = false;
		} break;
		case '\r':
		case '\n':
		    break;
		case ' ':
		case '\t':
		{
		    if (quoteMode) {
			token.append(c);
		    }
		} break;
		
		case '\\':
		{
		    ++i;
		    if (quoteMode) {
			// if we're in quotes, deal with escape characters
			char ch = json.charAt(i);
			switch (ch) {
			    case 't':
			    {
				token.append('\t');
			    } break;
			    case 'r':
			    {
				token.append('\r');
			    } break;
			    case 'n':
			    {
				token.append('\n');
			    } break;
			    case 'b':
			    {
				token.append('\b');
			    } break;
			    case 'f':
			    {
				token.append('\f');
			    } break;
			    default:
			    {
				token.append(ch);
			    } break;
			}
		    }
		} break;
		
		default:
		{
		    // otherwise, just append to the current token
		    token.append(c);
		} break;
	    }
	    ++i;
	}
	if (quoteMode) {
	    throw new JSONParseException("JSON Parse: Quotation mark mismatch");
	}
	return ctx;
    }
}
