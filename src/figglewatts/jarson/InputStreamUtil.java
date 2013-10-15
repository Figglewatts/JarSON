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
	
	public static String ReadString(FileInputStream in) throws IOException {
		StringBuilder builder = new StringBuilder();
		int ch;
		while ((ch = in.read()) != -1) {
			// 0x0D is carraige return
			// 0x0A is line feed
			if (ch == 0x0D || ch == 0x0A) {
				break; // newline or carraige return has been found, break out of the loop
			}
			builder.append((char)ch);
		}
		return builder.toString();
	}
}
