package game.ui.text;

import java.util.Scanner;

public final class TextUserInterfaceHelpers {
	
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
	 * Clears the screen.
	 */
	public static void clear() {
		
	}
	
	/**
	 * Returns a copy of a String repeated n times.
	 * @param str The String to repeat.
	 * @param n The number of times to repeat the String.
	 * @return
	 */
	public String repeatString(String str, Integer n) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			builder.append(str);
		}
		
		return builder.toString();
	}
	
}
