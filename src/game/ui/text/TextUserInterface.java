package game.ui.text;

import game.ui.UserInterface;

public class TextUserInterface extends UserInterface {

	@Override
	public void showTitleScreen() {
		
		boolean keepLooping = true;
		
		while (keepLooping) {
		
			TextUserInterfaceHelpers.clear();
			
			TextUserInterfaceHelpers.printHorizontalRule();
			TextUserInterfaceHelpers.printLineCentred("HEROES AND VILLAINS - CAMPUS EDITION");
			TextUserInterfaceHelpers.printLineCentred("by Manu Hamblyn & Jesse Sheehan");
			TextUserInterfaceHelpers.printHorizontalRule();
			
			System.out.println("");
			
			String[] options = new String[] {
				"New Game",
				"Load Game",
				"Quit"
			};
			
			int choice = TextUserInterfaceHelpers.showChoice("Select an option:", options);
		
			switch (choice) {
			
			case 0:
				showGameCreationScreen();
				break;
				
			case 1:
				// Load game!
				System.out.println("Load an existing game!");
				break;
				
			case 2:
				System.out.println("Quit the game.");
				keepLooping = false;
				break;
			
			}
		}
		
		
	}

	@Override
	public void showGameCreationScreen() {
		
		TextUserInterfaceHelpers.clear();
		TextUserInterfaceHelpers.printHorizontalRule();
		TextUserInterfaceHelpers.printLineCentred("CREATE A NEW GAME");
		TextUserInterfaceHelpers.printHorizontalRule();
		
		System.out.println();
		int cityCount = TextUserInterfaceHelpers.getNumberWithBounds(3, 6, "How many villains do you want to fight? (select between 3 and 6)");
		System.out.println(String.format("You have selected %d villains.", cityCount));
		
		TextUserInterfaceHelpers.readLine("Press <Enter> to continue...");
		
		
	}

	@Override
	public void showTeamCreationScreen() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		TextUserInterface ui = new TextUserInterface();
		
		ui.showTitleScreen();
	}

}
