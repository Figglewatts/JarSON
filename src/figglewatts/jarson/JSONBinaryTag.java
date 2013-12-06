package figglewatts.jarson;

public enum JSONBinaryTag {
	ARRAY(1),
	CLASS(2),
	VALUE(3),
	INT_VALUE(4),
	DOUBLE_VALUE(5),
	BOOL_VALUE(6),
	FLOAT_VALUE(7);
	
	private final int index;
	
	JSONBinaryTag(int index) {
		this.index = index;
	}
	
	public int index() {
		return index;
	}
	
	/**
	 * Returns the appropriate tag based on the index in the enum.
	 * @param n Index
	 * @return The JSONBinaryTag at the index
	 */
	public static JSONBinaryTag FromInt(int n) {
		switch (n) {
			case 1:
				return ARRAY;
			case 2:
				return CLASS;
			case 3:
				return VALUE;
			case 4:
				return INT_VALUE;
			case 5:
				return DOUBLE_VALUE;
			case 6:
				return BOOL_VALUE;
			case 7:
				return FLOAT_VALUE;
			default:
				return null;
		}
	}
}
