package figglewatts.jarson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author Sam Gibson
 */
public class JSONArray extends JSONNode implements Iterable<JSONNode> {
    private final ArrayList<JSONNode> list = new ArrayList<>();

    @Override
    public JSONNode get(int index) {
	return this.list.get(index);
    }

    @Override
    public void set(int index, JSONNode node) {
	if (node == null) node = new JSONNull();
	if (index < 0 || index > list.size()) list.add(node);
	else list.set(index, node);
    }

    @Override
    public JSONNode remove(JSONNode item) {
	list.remove(item);
	return item;
    }

    @Override
    public JSONNode remove(int index) {
	return list.remove(index);
    }

    @Override
    public void add(JSONNode item) {
	list.add(item);
    }

    @Override
    public void add(String key, JSONNode item) {
	add(item);
    }
    
    @Override
    public int count() {
	return list.size();
    }

    @Override
    public JSONNodeType getNodeType() {
	return JSONNodeType.ARRAY;
    }

    @Override
    public boolean equals(Object other) {
	if (other == this) return true;
	if (!(other instanceof JSONArray)) return false;
	
	JSONArray jsonArray = (JSONArray)other;
	return Objects.equals(list, jsonArray.list);
    }

    @Override
    public int hashCode() {
	return Objects.hash(list);
    }

    @Override
    public Iterator<JSONNode> iterator() {
	return list.iterator();
    }

    @Override
    public boolean isArray() {
	return true;
    }
    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("[ ");
	for (JSONNode n : list) {
	    if (sb.length() > 2)
		sb.append(", ");
	    sb.append(n.toString());
	}
	sb.append(" ]");
	return sb.toString();
    }
}
