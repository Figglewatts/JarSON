package figglewatts.jarson;

import java.util.HashMap;
import java.util.Map;

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
}
