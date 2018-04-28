package game.ui.text.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Simplifies running tests on a text-based user interface.
 * @author jesse
 *
 */
public class StreamHelpers {
	
	/**
	 * The output stream to use for System.out
	 */
	private static ByteArrayOutputStream outputStream;
	
	/**
	 * The input stream to use for System.in
	 */
	private static ByteArrayInputStream inputStream;
	
	/**
	 * Creates a new output stream to hold anything written to System.out.
	 * Should be called in the @BeforeEach method.
	 */
	public static void setupOutputStream() {
		
		outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		
	}
	
	/**
	 * Sets System.in and System.out back to their normal values.
	 * Should be called in the @AfterEach method.
	 */
	public static void restoreStreams() {
		
		System.setOut(System.out);
		System.setIn(System.in);
		
	}

	/**
	 * Sets the input stream to a string.
	 * @param input The string to be read from System.in.
	 */
	public static void setInputStream(String input) {
		
		inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);
		
	}


	/**
	 * Gets the output stream as a string using UTF-8 encoding.
	 * @return The string written to System.out.
	 */
	public static String getOutputStream() {
		
		return getOutputStream("UTF-8");
		
	}
	
	/**
	 * Gets the output stream as a string.
	 * @param encoding The encoding to decode with.
	 * @return The string written to System.out.
	 */
	public static String getOutputStream(String encoding) {
		
		// Convert the output stream into a byte array and then a ByteBuffer.
		byte[] bArray = outputStream.toByteArray();
		ByteBuffer bBuffer = ByteBuffer.wrap(bArray, 0, bArray.length);
		
		// Convert the ByteBuffer into a CharBuffer.
		CharBuffer cBuffer = Charset.forName(encoding).decode(bBuffer);
		
		// Clears the output stream.
		setupOutputStream();
		
		return cBuffer.toString();
		
	}
	
}
