package game.ui.text;

import game.ui.UserInterface;

public class TextUserInterface extends UserInterface {

	@Override
	public void showTitleScreen() {
		
		boolean keepLooping = true;
		
		while (keepLooping) {
			
			int choice = -1;
		
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
			
			try {
				
				choice = TextUserInterfaceHelpers.showChoice("Select an option:", options);
				
			} catch (UserCancelException e) {
				
				
				
			} catch (UserQuitException e) {
				
				keepLooping = false;
				break;
				
			}
		
			switch (choice) {
			
			case 0:
				
				try {
					
					showGameCreationScreen();
					
				} catch (UserQuitException e) {
					
					keepLooping = false;
					
				}
				
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
	public Integer showGameCreationScreen() throws UserQuitException {
		
		boolean keepLooping = true;
		
		int cityCount = 0;
		
		TextUserInterfaceHelpers.printTitleBlock("CREATE A NEW GAME", '#');
		
		System.out.println("How many villains do you want to fight? " + TextUserInterfaceHelpers.getInputOptions("3-6"));
		
		while (keepLooping) {
			
			try {
				
				cityCount = TextUserInterfaceHelpers.getNumberWithBounds(3, 6);
				
				keepLooping = !TextUserInterfaceHelpers.showYesNo(
					String.format("You have selected %d villains. Is this OK?", cityCount)
				);
				
				if (keepLooping) {
					
					System.out.println("How many villains do you want to fight? " + TextUserInterfaceHelpers.getInputOptions("3-6"));
					
				}
			
			} catch (UserCancelException e) {
				
				break;
				
			} catch (UserContinueException e) {
				
				System.out.println("How many villains do you want to fight? " + TextUserInterfaceHelpers.getInputOptions("3-6"));
				
			}

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
