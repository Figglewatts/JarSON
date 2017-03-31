package figglewatts.jarson;

import figglewatts.jarson.exceptions.JSONParseException;
import java.util.Stack;

/**
 *
 * @author Sam Gibson
 */
public abstract class JSONNode {
    public JSONNode get(int index) { return null; }
    public JSONNode get(String key) { return null; }
    
    public void set(int index, JSONNode node) { }
    public void set(String key, JSONNode node) { }
    
    public String getValue() { return null; }
    public void setValue(String value) { };
    
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
	double v = 0;
	try {
	    v = Double.parseDouble(this.getValue());
	}
	catch (NumberFormatException e) {
	    System.err.println("Could not get as double!");
	    e.printStackTrace(System.err);
	}
	return v;
    }
    
    public int getAsInt() {
	int v = 0;
	try {
	    v = Integer.parseInt(this.getValue());
	}
	catch (NumberFormatException e) {
	    System.err.println("Could not get as int!");
	    e.printStackTrace(System.err);
	}
	return v;
    }
    
    public float getAsFloat() {
	float v = 0;
	try {
	    v = Float.parseFloat(this.getValue());
	}
	catch (NumberFormatException e) {
	    System.err.println("Could not get as float!");
	    e.printStackTrace(System.err);
	}
	return v;
    }
    
    public boolean getAsBoolean() {
	return Boolean.parseBoolean(this.getValue());
    }
    
    @Override
    public abstract boolean equals(Object other);
    
    @Override
    public abstract int hashCode();
    
    private static final StringBuilder ESCAPE_BUILDER = new StringBuilder();
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
    
    static void parseElement(JSONNode ctx, String token, 
	    String tokenName, boolean quoted) {
	if (quoted) {
	    ctx.add(tokenName, create(token));
	    return;
	}
	String tmp = token.toLowerCase();
	if (tmp.equals("false") || tmp.equals("true"))
	    ctx.add(tokenName, create(Boolean.parseBoolean(tmp)));
	else if (tmp.equals("null"))
	    ctx.add(tokenName, null);
	else
	{
	    double v;
	    try {
		v = Double.parseDouble(token);
		ctx.add(tokenName, create(v));
	    }
	    catch (NumberFormatException e) {
		ctx.add(tokenName, create(token));
	    }
	}
    }
    
    public static JSONNode parse(String json) throws JSONParseException {
	Stack<JSONNode> stack = new Stack<>();
	JSONNode ctx = null;
	int i = 0;
	StringBuilder token = new StringBuilder();
	String tokenName = "";
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
