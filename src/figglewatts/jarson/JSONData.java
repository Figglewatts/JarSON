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
}
