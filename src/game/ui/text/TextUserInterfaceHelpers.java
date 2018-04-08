package game.ui.text;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Scanner;

public class TextUserInterfaceHelpers {
	
	public static void showMessage(String message) {
		
		System.out.println(message);
		
	}
	
	public static boolean showYesNo(String question) {
		return false;
	}
	
	public static int showChoice(String message, String[] options) {
		
		System.out.println(message);
		
		for (int i = 0; i < options.length; i++) {
			
			System.out.println(String.format("  %d) %s", i + 1, options[i]));
			
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("> ");
		int choice = scanner.nextInt();
		
		while (choice < 1 || choice >= options.length) {

			System.out.print("> ");
			choice = scanner.nextInt();
			
		}
		
		return choice - 1;
		
	}
	
	/**
	 * Returns one line of text from the standard input.
	 * @return
	 */
	public static String readLine() {
		
		// Create a new buffer of a particular size. This size denotes how many characters of input can be accepted.
		// 1024 is probably overkill and 32 would probably suffice.
		int BUFFER_SIZE = 1024;
		byte[] buffer = new byte[BUFFER_SIZE];
		
		try {
			
			// Read the bytes from the System.in
			int bytesRead = System.in.read(buffer);
			
			// Create a ByteBuffer from the byte array and convert to a UTF-8 CharBuffer
			ByteBuffer byteBuffer = ByteBuffer.wrap(buffer, 0, bytesRead);
			CharBuffer charBuffer = Charset.forName("UTF-8").decode(byteBuffer);
			
			// Return the resulting String
			return charBuffer.toString().trim();
			
		} catch (IOException e) {
			
			// If an error occurs, just return an empty String
			return "";
			
		}
		
	}
	
	/**
	 * Clears the screen.
	 */
	public static void clear() {
		// the terminal will likely be 25 lines high
		// TODO: find a reliable method for detecting console height
		System.out.println(repeatString("\n", 25));
	}
	
	/**
	 * Returns a copy of a String repeated n times.
	 * @param str The String to repeat.
	 * @param n The number of times to repeat the String.
	 * @return
	 */
	public static String repeatString(String str, Integer n) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			builder.append(str);
		}
		
		return builder.toString();
	}
	
}
