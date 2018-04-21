package game.ui.text;

import game.ui.UserInterface;
import static game.ui.text.TextUserInterfaceHelpers.*;

import java.util.ArrayList;

import game.GameEnvironment;
import game.Team;
import game.TeamFullException;
import game.character.Hero;

public class TextUserInterface extends UserInterface {

	public TextUserInterface(GameEnvironment env) {
		
		super(env);
		
	}
	
	public void showTitleScreen() {
		
		boolean keepLooping = true;
		
		String[] options = new String[] {
			"New Game",
			"Load Game",
			"Quit"
		};
		
		while (keepLooping) {
			
			int choice = -1;
		
			printTitleBlock(new String[] {
				"HEROES AND VILLAINS - CAMPUS EDITION",
				"by Manu Hamblyn & Jesse Sheehan",
				"Copyright (c) 2018"
			});
			
			printLineCentred("Hint: You can type 'c' for 'cancel' or 'q' for 'quit' at any time.");
			System.out.println();
			
			try {
				
				choice = showChoice("Select an option:", options);
				
			} catch (UserCancelException | UserQuitException e) {
				
				keepLooping = false;
				break;
				
			}
		
			switch (choice) {
			
			case 0:
				
				try {
					
					int cityCount = showGameCreationScreen();
					
					Team team = showTeamCreationScreen();
					
					boolean confirm = showConfirmGameScreen(cityCount, team);
					
					return;
					
				} catch (UserQuitException e) {
					
					keepLooping = false;
					
				} catch (UserCancelException e) {
					
					continue;
					
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

	public Integer showGameCreationScreen() throws UserQuitException, UserCancelException {
		
		boolean keepLooping = true;
		
		int cityCount = 0;
		
		printTitleBlock("CREATE A NEW GAME");
		
		System.out.println("How many villains do you want to fight? " + getInputOptions("3-6"));
		
		while (keepLooping) {
			
			try {
				
				cityCount = getNumberWithBounds(3, 6);
				
				keepLooping = !showYesNo(
					String.format("You have selected %d villains. Is this OK?", cityCount)
				);
				
				if (keepLooping) {
					
					System.out.println("How many villains do you want to fight? " + getInputOptions("3-6"));
					
				}
			
			} catch (UserContinueException e) {
				
				System.out.println("How many villains do you want to fight? " + getInputOptions("3-6"));
				
			}

		}
		
		return cityCount;
		
	}

	public Team showTeamCreationScreen() throws UserQuitException, UserCancelException {
		
		boolean keepLooping = true;
		ArrayList<Hero> heroes = new ArrayList<Hero>();

		String[] options = new String[] {
			"Add a Hero",
			"Remove a Hero",
			"Ready!"
		};
		
		while (keepLooping) {
			
			int choice = 0;
			
			printTitleBlock("CREATE A TEAM");
			
			if (heroes.size() > 0) {
				
				System.out.println(String.format("You have %d heroes in your team:", heroes.size()));
				
				for (Hero hero : heroes) {
					
					System.out.println(" - " + hero.getName());
					
				}
				
				System.out.println();
				
			}
			
			choice = showChoice("Select an option:", options);
			
			switch (choice) {
				
				case 0:
					// add a hero
					Hero newHero = showHeroCreationMenu();
					
					if (newHero != null) {
						
						heroes.add(newHero);
						
					}
					
					break;
					
				case 1:
					// remove a hero
					Hero deleteHero = showHeroDeletionMenu();
					
					if (deleteHero != null) {
						
						heroes.remove(deleteHero);
						
					}
					
					break;
					
				case 2:
					// ready!
					if (heroes.size() < 1) {
						
						showMessageDialog("You need at least one hero!", "Error");
						
					} else {
						
						if (heroes.size() > 3) {
							
							showMessageDialog("You cannot have more than three heroes!", "Error");
							
						} else {
							
							// TODO: Get the team name.
							Team team = new Team("");
							
							for (Hero hero : heroes) {
								
								try {
									
									team.addHero(hero);
									
								} catch (TeamFullException e) {
									
									throw new AssertionError("The ui should check if too many heroes have been added.");
									
								}
								
							}
							
							return team;
							
						}
						
					}
					
					break;
					
				default:
					throw new AssertionError("Invalid option.");
			
			}
			
		}
		
		return null;
		
	}
	
	private Hero showHeroCreationMenu() {
		return null;
	}
	
	private Hero showHeroDeletionMenu() {
		return null;
	}
	
	private boolean showConfirmGameScreen(int cityCount, Team team) throws UserQuitException {
		
		// TODO: Do properly!
		showMessageDialog("Confirm", "Information");
		
		return false;
		
	}
	
	public Hero showHeroSelectionMenu(String message, Hero[] heroes) throws UserQuitException {
		
		return showHeroSelectionMenu(message, "HERO SELECTION", heroes);
		
	}
	
	public static Hero showHeroSelectionMenu(String message, String title, Hero[] heroes) throws UserQuitException {
		
		boolean keepLooping = true;
		int choice = -1;
		
		String[] heroStrings = new String[heroes.length];
		
		for (int i = 0; i < heroes.length; i++) {
			
			heroStrings[i] = heroes[i].toString();
			
		}
		
		while (keepLooping) {
			
			printTitleBlock(title);
			
			try {
				
				choice = showChoice(message, heroStrings);
				keepLooping = false;
				
			} catch (UserCancelException e) { }
			
		}
		
		if (choice > -1) {
			
			return heroes[choice];
			
		} else {
			
			return null;
			
		}
	}

	@Override
	public void start() {
		
		this.showTitleScreen();
		
	}

}
