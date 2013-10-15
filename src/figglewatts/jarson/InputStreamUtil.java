package figglewatts.jarson;

import java.io.FileInputStream;
import java.io.IOException;

public class InputStreamUtil {
	private InputStreamUtil() { } // static constructor
	
	// these are zero-indexed
	private final static int INT32_LENGTH = 3;
	
	public static byte[] ReadInt32(FileInputStream in) throws IOException {
		byte[] b = new byte[INT32_LENGTH]; // you know things are getting serious when you see byte[]
		in.read(b);
		return b;
	}
}
