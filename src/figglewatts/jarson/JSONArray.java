package figglewatts.jarson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * Provides a storage container for multiple {@link JSONNode} elements.
 * The elements are accessed through indexes instead of keys.
 * 
 * @author Sam Gibson
 * 
 * @version 1.0
 * 
 * @see JSONNode
 */
public class JSONArray extends JSONNode implements Iterable<JSONNode> {
    private final ArrayList<JSONNode> list = new ArrayList<>();

    /**
     * Gets a {@link JSONNode} from this array.
     * @param index The index of the element.
     * @return The {@link JSONNode} to get.
     */
    @Override
    public JSONNode get(int index) {
	return this.list.get(index);
    }

    /**
     * Sets a {@link JSONNode} in this array.
     * If {@code index < 0 || index > list.size()} then it adds it to the array.
     * @param index The index of the element to change.
     * @param node The node to put at the given index.
     */
    @Override
    public void set(int index, JSONNode node) {
	if (node == null) node = new JSONNull();
	if (index < 0 || index > list.size()) list.add(node);
	else list.set(index, node);
    }

    /**
     * Remove a given item from this array.
     * @param item The item to remove.
     * @return The removed {@link JSONNode}
     */
    @Override
    public JSONNode remove(JSONNode item) {
	list.remove(item);
	return item;
    }

    /**
     * Remove an element at a given index from the array.
     * @param index The index of the item to remove.
     * @return The removed {@link JSONNode}
     * @throws IndexOutOfBoundsException if the given index is out of bounds
     * of the list
     */
    @Override
    public JSONNode remove(int index) {
	return list.remove(index);
    }

    /**
     * Add an element to the array.
     * @param item The element to add
     */
    @Override
    public void add(JSONNode item) {
	list.add(item);
    }

    /**
     * Add an element to the array.
     * @param key Key is not used in this method, it is provided for 
     * polymorphism from {@link JSONNode} instances
     * @param item The element to add
     */
    @Override
    public void add(String key, JSONNode item) {
	add(item);
    }
    
    /**
     * @return The number of items in the array
     */
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
	return list.equals(jsonArray.list);
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
