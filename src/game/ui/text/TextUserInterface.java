package game.ui.text;

import game.ui.UserInterface;
import static game.ui.text.TextUserInterfaceHelpers.*;

import java.util.ArrayList;

import game.GameEnvironment;
import game.Team;
import game.TeamFullException;
import game.character.Hero;
import game.character.HeroType;
import game.city.CityController;

public class TextUserInterface extends UserInterface {
	
	private int cityCount = 3;
	private Team team;

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
					// new game
					
					try {
						
						
						showNewGameScreen();
						
						this.getGameEnvironment().setCities(new CityController(cityCount));
						this.getGameEnvironment().setTeam(team);
						
						this.gameLoop();
						
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
					// quit game
					System.out.println("Quit the game.");
					keepLooping = false;
					break;
			
			}
		}
		
		
	}

	public Integer showGetNumberOfVillainsScreen() throws UserQuitException, UserCancelException {
		
		boolean keepLooping = true;
		
		int cityCount = 0;
		
		printTitleBlock("New Game > Number of Villains");
		
		System.out.println("How many villains do you want to fight? " + getInputOptions("3-6"));
		
		while (keepLooping) {
			
			try {
				
				cityCount = getNumberWithBounds(3, 6);
				
				break;
			
			} catch (UserContinueException e) {
				
				System.out.println("How many villains do you want to fight? " + getInputOptions("3-6"));
				
			}

		}
		
		return cityCount;
		
	}

	public void showNewGameScreen() throws UserQuitException, UserCancelException {
		
		boolean keepLooping = true;
		ArrayList<Hero> heroes = new ArrayList<Hero>();
		String teamName = "";

		String[] options = new String[] {
			"Change the number of Villains",
			"Add a Hero",
			"Remove a Hero",
			"Name the Team",
			"Ready!"
		};
		
		while (keepLooping) {
			
			int choice = 0;
			
			printTitleBlock("New Game");
			
			System.out.println(String.format("You will be fighting %d villains.\n", cityCount));
			
			if (teamName.equals("")) {
				
				System.out.println("Your team has not been named yet.\n");
				
			} else {
				
				System.out.println(String.format("Your team name is \"%s\".\n", teamName));
				
			}
			
			System.out.println(String.format("You have %d hero%s in your team%s", heroes.size(), heroes.size() == 1 ? "" : "es", heroes.size() == 0 ? "." : ":"));
			
			if (heroes.size() > 0) {
				
				System.out.println(getPrettyHeroesString(heroes));
				
			}
			
			System.out.println();
			
			choice = showChoice("Select an option:", options);
			
			switch (choice) {
			
				case 0:
					// change the number of Villains
					int number = showGetNumberOfVillainsScreen();
					
					if (number != 0) {
					
						cityCount = number;
						
					}
					
					break;
				
				case 1:
					// add a hero
					
					if (heroes.size() >= 3) {
						
						showMessageDialog("You can only have three heroes in your team!", "New Game > Error");
						
					} else {
						
						Hero newHero = showHeroCreationMenu(heroes);
						
						if (newHero != null) {
							
							heroes.add(newHero);
							
						}
					}
					
					break;
					
				case 2:
					// remove a hero
					
					if (heroes.size() == 0) {
						
						showMessageDialog("There are no heroes in your team to remove!", "New Game > Error");
						
					} else {
						
						Hero deleteHero = showHeroDeletionMenu(heroes);
						
						if (deleteHero != null) {
							
							heroes.remove(deleteHero);
							
						}
					}
					
					break;
					
				case 3:
					// change the team name
					String input = showInputDialog("Enter the team name:", "New Game > Team Name");
					
					if (!input.equals("")) {
						
						teamName = input;
						options[3] = "Rename the Team";
						
					}
					
					break;
					
					
				case 4:
					// ready!
					if (heroes.size() < 1) {
						
						showMessageDialog("You need at least one hero!", "New Game > Error");
						
					} else {
						
						if (heroes.size() > 3) {
							
							showMessageDialog("You cannot have more than three heroes!", "New Game > Error");
							
						} else {
							
							if (teamName.equals("")) {
								
								showMessageDialog("The team name cannot be empty!", "New Game > Error");
								
							} else {
								
								team = new Team(teamName);
								
								for (Hero hero : heroes) {
									
									try {
										
										team.addHero(hero);
										
									} catch (TeamFullException e) {
										
										throw new AssertionError("The ui should check if too many heroes have been added.");
										
									}
									
								}
								
								if (showConfirmGameScreen()) {
									
									return;
									
								}
								
							}
							
						}
						
					}
					
					break;
					
				default:
					throw new AssertionError("Invalid option.");
			
			}
			
		}
		
	}
	
	private Hero showHeroCreationMenu(ArrayList<Hero> heroes) {
		
		String name = "Test Hero";
		HeroType type = HeroType.ARTS_STUDENT;
		
		
		
		for (Hero hero : heroes) {
			
			if (hero.getName().toLowerCase().equals(name.toLowerCase())) {
				
				return null; // TODO: reprompt for name
				
			}
			
		}
		
		return new Hero(name, type);
	}
	
	private Hero showHeroDeletionMenu(ArrayList<Hero> heroes) {
		return heroes.remove(0);
	}
	
	private boolean showConfirmGameScreen() throws UserQuitException {
		
		String heroString = getPrettyHeroesString((team.getHeroes()));

		return showYesNoDialog(
				String.format(
						"You will be fighting %d villains. Your team name is \"%s\". You have %d hero%s:\n%s\n\nIs this ok?",
						cityCount, team.getName(), team.getHeroes().size(), team.getHeroes().size() == 1 ? "" : "es", String.join("\n", heroString)),
				"New Game > Confirm");
		
	}
	
	public static Hero showHeroSelectionMenu(String message, String title, Hero[] heroes) throws UserQuitException {
		
		boolean keepLooping = true;
		int choice = -1;
		
		String[] heroStrings = new String[heroes.length];
		
		for (int i = 0; i < heroes.length; i++) {
			
			heroStrings[i] = String.format("%s (%s)", heroes[i].getName(), heroes[i].getType().toString());
			
		}
		
		while (keepLooping) {
			
			printTitleBlock(title + " > Hero Selection");
			
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
	
	private static String getPrettyHeroesString(Iterable<Hero> heroes) {
		
		ArrayList<String> heroStrings = new ArrayList<String>();
		
		for (Hero hero : heroes) {
			
			heroStrings.add(String.format(" - %s (%s)", hero.getName(), hero.getType().toString()));
			
		}
		
		return String.join("\n", heroStrings);
	}

	@Override
	public void start() {
		
		this.showTitleScreen();
		
	}

	private void gameLoop() throws UserQuitException {
		
		showMessageDialog("Main game should run now...");
		
	}
}
