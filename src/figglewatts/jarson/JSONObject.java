package figglewatts.jarson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Sam Gibson
 */
public class JSONObject extends JSONNode implements Iterable<JSONNode> {
    private HashMap<String, JSONNode> map = new HashMap<>();
    
    @Override
    public JSONNodeType getNodeType() {
	return JSONNodeType.OBJECT;
    }

    @Override
    public boolean equals(Object other) {
	if (other == this) return true;
	if (!(other instanceof JSONObject)) return false;
	
	JSONObject jsonObject = (JSONObject)other;
	return Objects.equals(map, jsonObject.map);
    }

    @Override
    public int hashCode() {
	return Objects.hash(map);
    }

    @Override
    public Iterator<JSONNode> iterator() {
	return map.values().iterator();
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("{ ");
	for (Map.Entry<String, JSONNode> e : map.entrySet()) {
	    String key = e.getKey();
	    JSONNode node = e.getValue();
	    if (sb.length() > 3)
		sb.append(", ");
	    sb.append("\"");
	    sb.append(escape(key));
	    sb.append("\":");
	    sb.append(node);
	}
	sb.append(" }");
	return sb.toString();
    }

    @Override
    public JSONNode remove(JSONNode item) {
	map.values().remove(item);
	return item;
    }

    @Override
    public JSONNode remove(int index) {
	int i = 0;
	for (JSONNode n : map.values()) {
	    if (i == index) {
		map.values().remove(n);
		return n;
	    }
	    i++;
	}
	return null;
    }

    @Override
    public JSONNode remove(String key) {
	return map.remove(key);
    }

    @Override
    public void add(String key, JSONNode item) {
	map.put(key, item);
    }

    @Override
    public boolean isObject() {
	return true;
    }

    @Override
    public int count() {
	return map.size();
    }

    @Override
    public void set(String key, JSONNode node) {
	map.replace(key, node);
    }

    @Override
    public void set(int index, JSONNode node) {
	int i = 0;
	for (String k : map.keySet()) {
	    if (i == index) {
		map.replace(k, node);
	    }
	    i++;
	}
    }

    @Override
    public JSONNode get(String key) {
	return map.get(key);
    }

    @Override
    public JSONNode get(int index) {
	int i = 0;
	for(JSONNode n : map.values()) {
	    if (i == index) {
		return n;
	    }
	    i++;
	}
	return null;
    }
}
