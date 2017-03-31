package figglewatts.jarson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Provides a container for multiple {@link JSONNode} elements.
 * The elements are accessed/stored with their token names as keys.
 * 
 * @author Sam Gibson
 * 
 * @version 1.0
 * 
 * @see JSONNode
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

    /**
     * Remove a given node from this object.
     * @param item The node to remove.
     * @return The node given in the argument.
     */
    @Override
    public JSONNode remove(JSONNode item) {
	map.values().remove(item);
	return item;
    }

    /**
     * Remove a node at a given index in this objects list of nodes.
     * @param index The index to remove at.
     * @return The removed node, or {@code null} if the index was too 
     * large/small.
     */
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

    /**
     * Remove a node with a given token name from this object.
     * @param key The token name to remove.
     * @return The removed node, or {@code null} if key was not present.
     */
    @Override
    public JSONNode remove(String key) {
	return map.remove(key);
    }

    /**
     * Add a node to this object with a given token name.
     * @param key The token name of this object.
     * @param item The node to add.
     */
    @Override
    public void add(String key, JSONNode item) {
	map.put(key, item);
    }

    @Override
    public boolean isObject() {
	return true;
    }
    
    /**
     * Return the number of nodes attached to this object.
     * @return 
     */
    @Override
    public int count() {
	return map.size();
    }

    /**
     * Replace a node with a given token name in this object.
     * Note that if the token name is not present then this will not add the
     * node to the object.
     * @param key The token name of the node.
     * @param node The node to replace it with.
     */
    @Override
    public void set(String key, JSONNode node) {
	map.replace(key, node);
    }

    /**
     * Replace a node at a given index in this object.
     * Note that if the index is out of range nothing will be replaced.
     * @param index The index of the node.
     * @param node The node to replace it with.
     */
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

    /**
     * Get a node from this object with its token name.
     * @param key The token name of the desired node.
     * @return The desired node.
     */
    @Override
    public JSONNode get(String key) {
	return map.get(key);
    }

    /**
     * Get a node from this object with its index in the object.
     * Note that the order in the map may not match the order in the JSON file.
     * @param index The index of the node to get.
     * @return The desired node.
     */
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
