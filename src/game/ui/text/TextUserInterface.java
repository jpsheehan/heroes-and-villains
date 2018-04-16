package game.ui.text;

import game.ui.UserInterface;

public class TextUserInterface extends UserInterface {

	@Override
	public void showTitleScreen() {
		
		boolean keepLooping = true;
		
		while (keepLooping) {
		
			TextUserInterfaceHelpers.printTitleBlock(new String[] {
				"HEROES AND VILLAINS - CAMPUS EDITION",
				"by Manu Hamblyn & Jesse Sheehan",
				"Copyright (c) 2018"
			}, '#');
			
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
	public Integer showGameCreationScreen() {
		
		boolean keepLooping = true;
		
		int cityCount = 0;
		
		while (keepLooping) {
			
			TextUserInterfaceHelpers.printTitleBlock("CREATE A NEW GAME", '#');
			
			cityCount = TextUserInterfaceHelpers.getNumberWithBounds(3, 6, "How many villains do you want to fight (3-6)? ");
			
			keepLooping = !TextUserInterfaceHelpers.showYesNo(
				String.format("You have selected %d villains. Is this OK?", cityCount)
			);

		}
		
		return cityCount;
		
	}

	@Override
	public void showTeamCreationScreen() {
		
		
		
	}
	
	public static void main(String[] args) {
		TextUserInterface ui = new TextUserInterface();
		
		ui.showTitleScreen();
	}

}
