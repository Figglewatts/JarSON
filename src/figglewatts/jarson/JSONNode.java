package figglewatts.jarson;

import java.util.List;
import java.util.Stack;

import figglewatts.jarson.exceptions.JSONParseException;

public class JSONNode {
	private List<JSONNode> nodeList;
	
	public void Add(String key, JSONNode item) { }
	public void Add(JSONNode item) {
		Add("", item);
	}
	public void Add(String key) {
		Add(key, new JSONNode());
	}
	public JSONNode Remove(String key) {
		return null;
	}
	public JSONNode Remove(int index) {
		return null;
	}
	public JSONNode Remove(JSONNode item) {
		return item;
	}
	@Override
	public String toString() {
		return "JSONNode";
	}
	public String toString(String prefix) {
		return "JSONNode";
	}
	
	static String Escape(String text) {
		String result = "";
		for (char c : text.toCharArray())
		{
			switch (c) {
				case '\\' :
					result += "\\\\";
					break;
				case '\"' :
					result += "\\\"";
					break;
				case '\n' :
					result += "\\n";
					break;
				case '\r' :
					result += "\\r";
					break;
				case '\t' :
					result += "\\t";
					break;
				case '\b' :
					result += "\\b";
					break;
				case '\f' :
					result += "\\f";
					break;
				default :
					result += c;
					break;
			}
		}
		return result;
	}
	
	public static JSONNode Parse(String JSON) throws JSONParseException {
		Stack<JSONNode> stack = new Stack<JSONNode>();
		JSONNode ctx = null;
		int i = 0;
		String token = "";
		String tokenName = "";
		boolean QuoteMode = false;
		char[] JSONCharArray = JSON.toCharArray();
		while (i < JSON.length()) {
			switch (JSONCharArray[i]) {
				case '{' : // it's the start of a class
					if (QuoteMode) {
						token += JSONCharArray[i];
						break;
					}
					stack.push(new JSONClass());
					if (ctx != null) {
						tokenName = tokenName.trim();
						if (ctx.getClass() == JSONArray.class) {
							ctx.Add(stack.peek());
						} else if (tokenName != "") {
							ctx.Add(tokenName, stack.peek());
						}
					}
					tokenName = "";
					token = "";
					ctx = stack.peek();
					break;
					
				case '[' : // it's the start of an array
					if (QuoteMode) {
						token += JSONCharArray[i];
						break;
					}
					stack.push(new JSONArray());
					if (ctx != null) {
						tokenName = tokenName.trim();
						if (ctx.getClass() == JSONArray.class) {
							ctx.Add(stack.peek());
						} else if (tokenName != "") {
							ctx.Add(tokenName, stack.peek());
						}
					}
					tokenName = "";
					token = "";
					ctx = stack.peek();
					break;
					
				case '}' :
				case ']' : // closing brackets... self explanatory
					if (QuoteMode) {
						token += JSONCharArray[i];
						break;
					}
					if (stack.size() == 0) {
						throw new JSONParseException("Too many closing brackets!");
					}
					stack.pop();
					if (token != "") {
						tokenName = tokenName.trim();
						if (ctx.getClass() == JSONArray.class) {
							ctx.Add(token);
						} else if (tokenName != "") {
							ctx.Add(tokenName); // IF YOU'RE GETTING MYSTERIOUS BUGS LATER, THIS IS THE CAUSE
						}
					}
					tokenName = "";
					token = "";
					if (stack.size() > 0) {
						ctx = stack.peek();
					}
					break;
					
				case ':' :
					if (QuoteMode) {
						token += JSONCharArray[i];
						break;
					}
					tokenName = token;
					token = "";
					break;
					
				case '"' :
					QuoteMode ^= true; // assigns the result of the logical XOR
					break;
					
				case ',' :
					if (QuoteMode) {
						token += JSONCharArray[i];
						break;
					}
					if (token != "") {
						if (ctx.getClass() == JSONArray.class) {
							ctx.Add(token);
						} else if (tokenName != "") {
							ctx.Add(tokenName);
						}
					}
					tokenName = "";
					token = "";
					break;
					
				case '\r' :
				case '\n' : // newline or carraige return, skip this line and move to next
					break;
				
				case ' ' :
				case '\t' :
					if (QuoteMode) {
						token += JSONCharArray[i];
					}
					break;
					
				case '\\':
					++i;
					if (QuoteMode) {
						char C = JSONCharArray[i];
						switch (C) {
							case 't' : token += '\t'; break;
							case 'r' : token += '\r'; break;
							case 'n' : token += '\n'; break;
							case 'b' : token += '\b'; break;
							case 'f' : token += '\f'; break;
							case 'u' :
								String s = JSON.substring(i+1, 4);
								token += (char)Integer.parseInt(s);
								i += 4;
								break;
							default : token += C; break;
						}
					}
					break;
					
				default :
					token += JSONCharArray[i];
					break;
			}
			i++;
		}
		if (QuoteMode) {
			throw new JSONParseException("Quotation marks are inconsistent. You done goof'd.");
		}
		return ctx;
	}
	
	// TODO: add serialize() methods
	// TODO: add deserialize() methods
}
