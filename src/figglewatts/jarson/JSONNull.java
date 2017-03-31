package figglewatts.jarson;

import java.util.Objects;

/**
 *
 * @author Sam Gibson
 */
public class JSONNull extends JSONNode {

    @Override
    public String getValue() {
	return null;
    }

    @Override
    public final void setValue(String value) { }

    @Override
    public JSONNodeType getNodeType() {
	return JSONNodeType.NULLVALUE;
    }

    @Override
    public boolean equals(Object other) {
	if (other == this) return true;
	return other instanceof JSONNull;
    }

    @Override
    public int hashCode() {
	return 0;
    }

    @Override
    public boolean getAsBoolean() {
	return false;
    }

    @Override
    public boolean isNull() {
	return true;
    }
}
