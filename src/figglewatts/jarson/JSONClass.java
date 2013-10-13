package figglewatts.jarson;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import figglewatts.jarson.exceptions.JSONAccessException;

public class JSONClass extends JSONNode {
	private Map<String, JSONNode> children = new HashMap<String, JSONNode>();
	
	@Override
	public void setNode(JSONNode node, String key) throws JSONAccessException {
		if (children.containsKey(key)) {
			children.put(key, node);
		} else {
			String exceptionString = "";
			String.format("Child with key {0} does not exist!", key);
			throw new JSONAccessException(exceptionString);
		}
	}
	@Override
	public JSONNode getNode(String key) throws JSONAccessException {
		if (children.containsKey(key)) {
			return children.get(key);
		} else {
			String exceptionString = "";
			String.format("Child with key {0} does not exist!", key);
			throw new JSONAccessException(exceptionString);
		}
	}
	
	@Override
	public int size() {
		return children.size();
	}
	
	@Override
	public void Add(String key, JSONNode item) {
		if (key != null) {
			children.put(key, item);
		} else {
			children.put(UUID.randomUUID().toString(), item);
		}
	}
	
	@Override
	public JSONNode Remove(String key) {
		if (!children.containsKey(key)) {
			return null; // if the class has no child with matching key, return null
		}
		return children.remove(key);
	}
	
	@Override
	public String toString() {
		String result = "{";
		for(Map.Entry<String, JSONNode> entry : children.entrySet()) {
			if (result.length() > 2) {
				result += ", ";
			}
			result += "\"" + Escape(entry.getKey()) + "\":" + entry.getValue().toString();
		}
		result += "}";
		return result;
	}
	
	@Override
	public String toString(String prefix) {
		String result = "{";
		for(Map.Entry<String, JSONNode> entry : children.entrySet()) {
			if (result.length() > 3) {
				result += ", ";
			}
			result += "\n" + prefix + "   ";
			result += "\"" + Escape(entry.getKey()) + "\":" + entry.getValue().toString(prefix + "   ");
		}
		result += "\n" + prefix + "}";
		return result;
	}
	
	// TODO: add serialize() method
}
