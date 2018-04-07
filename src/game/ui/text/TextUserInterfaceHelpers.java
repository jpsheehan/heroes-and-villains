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
	
}
