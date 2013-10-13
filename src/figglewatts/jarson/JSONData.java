package figglewatts.jarson;

public class JSONData extends JSONNode {
	private String data;
	
	@Override
	public String getValue() {
		return data;
	}
	
	@Override
	public void setValue(String data) {
		this.data = data;
	}
	
	public JSONData(String data) {
		this.data = data;
	}
	public JSONData(int data) {
		this.setAsInt(data);
	}
	public JSONData(float data) {
		this.setAsFloat(data);
	}
	public JSONData(double data) {
		this.setAsDouble(data);
	}
	public JSONData(boolean data) {
		this.setAsBoolean(data);
	}
	
	@Override
	public String toString() {
		return "\"" + Escape(data) + "\"";
	}
	
	@Override
	public String toString(String prefix) {
		return "\"" + Escape(data) + "\"";
	}
	
	// TODO: add serialize() method
}
