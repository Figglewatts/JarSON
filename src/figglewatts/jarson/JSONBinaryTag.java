package figglewatts.jarson;

public enum JSONBinaryTag {
	Array(1),
	Class(2),
	Value(3),
	IntValue(4),
	DoubleValue(5),
	BoolValue(6),
	FloatValue(7);
	
	private int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	JSONBinaryTag(int value) {
		this.setValue(value);
	}
}
