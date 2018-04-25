package game.ui.text;

import game.ui.UserInterface;
import static game.ui.text.TextUserInterfaceHelpers.*;

import java.util.ArrayList;

import game.GameEnvironment;
import game.GeneralHelpers;
import game.Team;
import game.TeamFullException;
import game.character.Hero;
import game.character.HeroType;
import game.city.Area;
import game.city.City;
import game.city.CityController;
import game.city.Direction;
import game.city.IllegalMoveException;

/**
 * Represents the user interface shown to the user in text format.
 * This is to run in the terminal/command line.
 * @author jesse
 *
 */
public class TextUserInterface extends UserInterface {
	
	/**
	 * The number of cities that the user wants to go to.
	 */
	private int cityCount = 3;
	
	/**
	 * A reference to the Team of heroes.
	 */
	private Team team;

	/**
	 * Create a new TextUserInterface object.
	 * @param env A reference to the parent GameEnvironment.
	 */
	public TextUserInterface(GameEnvironment env) {
		
		super(env);
		
	}
	
	/**
	 * Shows the title screen.
	 */
	public void showTitleScreen() {
		
		// The possible choices for the main menu.
		String[] options = new String[] {
			"New Game",
			"Load Game",
			"Quit"
		};
		
		// Run the rest of the function over and over, only exiting when the user decides to quit.
		while (true) {
			
			int choice = -1;
		
			// Print the title
			printTitleBlock(new String[] {
				"HEROES AND VILLAINS - CAMPUS EDITION",
				"by Manu Hamblyn & Jesse Sheehan",
				"Copyright (c) 2018"
			});
			
			printLineCentred("Hint: You can type 'c' for 'cancel' or 'q' for 'quit' at any time.\n");
			
			// Get some input from the user, handling the quit and cancel exceptions
			try {
				
				choice = showChoice("Select an option:", options);
				
			} catch (UserCancelException | UserQuitException e) {
				
				return;
				
			}
		
			// Make a decision based on what the user chose to do.
			switch (choice) {
			
				case 0:
					// new game
					
					try {
						
						// Create a new game, setting the CityController and Team in the parent GameEnvironment
						showNewGameScreen();
						
						this.getGameEnvironment().setCities(new CityController(cityCount));
						this.getGameEnvironment().setTeam(team);
						
						// Run the game loop (basically, plays the game proper).
						this.gameLoop();
						
					} catch (UserQuitException e) {
						
						return;
						
					} catch (UserCancelException e) {
						
						continue;
						
					}
					
					break;
					
				case 1:
					// Load game!
					
					// TODO: Implement loading of games from the main menu.
					System.out.println("Load an existing game!");
					break;
					
				case 2:
					// quit game
					return;
			
			}
		}
		
	}

	/**
	 * Shows a prompt for letting the user decide how many cities/villains to fight.
	 * @return
	 * @throws UserQuitException
	 * @throws UserCancelException
	 */
	public void showGetNumberOfVillainsScreen() throws UserQuitException, UserCancelException {
		
		String prompt = "How many villains do you want to fight? " + getInputOptions("3-6");
		
		printTitleBlock("New Game > Number of Villains");
		
		System.out.println(prompt);
		
		// Keep prompting the user a valid number.
		while (true) {
			
			try {
				
				this.cityCount = getNumberWithBounds(3, 6);
				return;
			
			} catch (UserContinueException e) {
				
				System.out.println(prompt);
				
			}

		}
		
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
					
					int oldCityCount = this.cityCount;
					
					showGetNumberOfVillainsScreen();
					
					// make sure that the number the user chose was valid. Otherwise restore the old cityCount.
					if (this.cityCount == 0) {
					
						this.cityCount = oldCityCount;
						
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
							
							showMessageDialog(String.format("You have added the hero \"%s\" (%s) to your team!", newHero.getName(), newHero.getType().toString()), "New Game > Hero Added"); 
							
						}
					}
					
					break;
					
				case 2:
					// remove a hero
					
					if (heroes.size() == 0) {
						
						showMessageDialog("There are no heroes in your team to remove!", "New Game > Error");
						
					} else {
						
						Hero deletedHero = showHeroDeletionMenu(heroes);
						
						if (deletedHero != null) {
							
							heroes.remove(deletedHero);
							
							showMessageDialog(String.format("You have removed the hero \"%s\" (%s) from your team!", deletedHero.getName(), deletedHero.getType().toString()), "New Game > Hero Removed"); 
							
						}
					}
					
					break;
					
				case 3:
					// change the team name
					
					String input = showInputDialog("Enter the team name:", "New Game > Change Team Name");
					
					if (!input.equals("")) {
						
						teamName = input;
						options[3] = "Rename the Team"; // change the menu string from "Name the Team" to "Rename the Team"
						
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
								
								// Everything seems to be in order.
								// Assemble the team.
								
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
					throw new AssertionError("Invalid option. This should never be reached!");
			
			}
			
		}
		
	}
	
	/**
	 * Returns the Hero that is to be added to the Team. Doesn't actually add the Hero to the Team, just returns it.
	 * @param heroes A list of heroes in the Team. Can be used to check that the new hero's name is unique.
	 * @return
	 */
	private Hero showHeroCreationMenu(ArrayList<Hero> heroes) {
		
		// Create a test hero.
		String name = "Test Hero";
		HeroType type = HeroType.ARTS_STUDENT;
		
		// Checks if the hero's name is unique.
		for (Hero hero : heroes) {
			
			if (hero.getName().toLowerCase().equals(name.toLowerCase())) {
				
				return null; // TODO: reprompt for name
				
			}
			
		}
		
		// Return the new Hero
		return new Hero(name, type);
	}
	
	/**
	 * Returns the Hero object to remove from the Team. Doesn't actually remove the Hero from the Team, just returns it.
	 * @param heroes A list of heroes to choose from.
	 * @return
	 */
	private Hero showHeroDeletionMenu(ArrayList<Hero> heroes) {
		return heroes.remove(0);
	}
	
	/**
	 * Displays a confirmation dialog to the user. Should give a general summary of the game settings and the team.
	 * @return Returns true if the user wants to play the game, false otherwise.
	 * @throws UserQuitException
	 */
	private boolean showConfirmGameScreen() throws UserQuitException {
		
		String heroString = getPrettyHeroesString((team.getHeroes()));

		return showYesNoDialog(
				String.format(
						"You will be fighting %d villains. Your team name is \"%s\". You have %d hero%s:\n%s\n\nIs this ok?",
						cityCount, team.getName(), team.getHeroes().size(),
						team.getHeroes().size() == 1 ? "" : "es", String.join("\n", heroString)), // pluralise the word "hero" if there is more than one hero in the team
				"New Game > Confirm");
		
	}
	
	/**
	 * Displays a list of heroes to choose from. Returns the hero that the user chose.
	 * @param message A message to prompt the user with.
	 * @param title The title of the dialog
	 * @param heroes A list of heroes to choose from.
	 * @return
	 * @throws UserQuitException
	 */
	public static Hero showHeroSelectionMenu(String message, String title, Hero[] heroes) throws UserQuitException {
		
		int choice = -1;
		
		String[] heroStrings = new String[heroes.length];
		
		for (int i = 0; i < heroes.length; i++) {
			
			heroStrings[i] = String.format("%s (%s)", heroes[i].getName(), heroes[i].getType().toString());
			
		}
		
		while (true) {
			
			printTitleBlock(title + " > Hero Selection");
			
			try {
				
				choice = showChoice(message, heroStrings);
				break;
				
			} catch (UserCancelException e) { }
			
		}
		
		// return the Hero the user chose or null if something went wrong.
		if (choice > -1) {
			
			return heroes[choice];
			
		} else {
			
			return null;
			
		}
	}
	
	/**
	 * Returns a string with the heroes prettily formatted.
	 * @param heroes The list or array of heroes to display.
	 * @return
	 */
	private static String getPrettyHeroesString(Iterable<Hero> heroes) {
		
		ArrayList<String> heroStrings = new ArrayList<String>();
		
		for (Hero hero : heroes) {
			
			heroStrings.add(String.format(" - %s (%s)", hero.getName(), hero.getType().toString()));
			
		}
		
		return String.join("\n", heroStrings);
	}

	/**
	 * Starts the user interface.
	 */
	@Override
	public void start() {
		
		this.showTitleScreen();
		
	}

	/**
	 * Runs the main game loop. Calling the function to display the area and handling any exceptions that come from it.
	 * @throws UserQuitException
	 */
	private void gameLoop() throws UserQuitException {
		
		try {
		
			while (true) {
				
				displayAreaScreen();
				
			}
		
//		} catch (GameWonException e) {
//			
//			showMessageDialog("Congratulations! You have won the game!");
//			
//		} catch (GameOverException e) {
//			
//			showMessageDialog("Sorry! You lost the game!");
		
		} catch (UserQuitException e) {
			
			throw e; // Bubble this back up
			
		} catch (Exception e) {
			
			// Handle all errors that shouldn't happen here:
			showMessageDialog(e.getMessage(), "Critical Application Error");
			
		}
		
	}

	/**
	 * Displays an area on the screen, presenting the user with information and a list of options.
	 * @throws UserQuitException
	 */
	private void displayAreaScreen() throws UserQuitException {
		
		City city = this.getGameEnvironment().getCityController().getCurrentCity();
		Area area = this.getGameEnvironment().getCityController().getCurrentArea();
		
		// Just a test: In reality we need to offer the user the chance to enter some input
		showMessageDialog(area.getFlavourText(), String.format("%s > %s (%s)",
				city.getName(),
				area.getName(),
				area.getType().toString()));
		
	}
	
	/**
	 * Prints the map of the current city to the screen. Displays any areas that have already been discovered or if the player has used a Map.
	 */
	private void drawMap() {
		
		int leftMargin = (getConsoleWidth() - 31) / 2;
		
		StringBuilder sb = new StringBuilder();
		
		// Setup some constants to use to draw the lines.
		Character tl = '\u259B'; // top-left corner
		Character tr = '\u259C'; // top-right corner
		Character bl = '\u2599'; // bottom-left corner
		Character br = '\u259F'; // bottom-right corner
		Character vl = '\u258C'; // vertical left bar
		Character vr = '\u2590'; // vertical right bar
		Character ht = '\u2580'; // horizontal top bar
		Character hb = '\u2584'; // horizontal bottom bar

		City city = this.getGameEnvironment().getCityController().getCurrentCity();
		Direction direction = this.getGameEnvironment().getCityController().getDirection();
		
		Character northChar = getAreaLetter(city.getArea(Direction.NORTH));
		Character westChar = getAreaLetter(city.getArea(Direction.WEST));
		Character centreChar = getAreaLetter(city.getArea(Direction.CENTRE));
		Character eastChar = getAreaLetter(city.getArea(Direction.EAST));
		Character southChar = getAreaLetter(city.getArea(Direction.SOUTH));
		
		String occupiedAcrossTop = " \u250F" + repeatString("\u2501", 5) + "\u2513 ";
		String occupiedAcrossBottom = " \u2517" + repeatString("\u2501", 5) + "\u251B ";
		String occupiedUp = "\u2503";
		String unoccupiedAcross = repeatString(" ", 9);
		String unoccupiedUp = " ";
		
		// north room
		sb.append(repeatString(" ", 10 + leftMargin));
		sb.append(tl + repeatString(ht.toString(), 9) + tr + '\n');
		sb.append(repeatString(" ", 10 + leftMargin));
		sb.append(vl + (direction == Direction.NORTH ? occupiedAcrossTop : unoccupiedAcross) + vr + '\n');
		sb.append(repeatString(" ", 10 + leftMargin));
		sb.append(vl + " " + (direction == Direction.NORTH ? occupiedUp : unoccupiedUp) + "  " + northChar + "  " + (direction == Direction.NORTH ? occupiedUp : unoccupiedUp) + " " + vr + '\n');
		sb.append(repeatString(" ", 10 + leftMargin));
		sb.append(vl + (direction == Direction.NORTH ? occupiedAcrossBottom : unoccupiedAcross) + vr + '\n');
		
		// west, centre and east rooms
		sb.append(repeatString(" ", leftMargin));
		sb.append(tl + repeatString(ht.toString(), 9));
		sb.append(tl + repeatString(ht.toString(), 9) + tr);
		sb.append(repeatString(ht.toString(), 9) + tr + '\n');
		
		sb.append(repeatString(" ", leftMargin));
		sb.append(vl + (direction == Direction.WEST ? occupiedAcrossTop : unoccupiedAcross));
		sb.append(vl + (direction == Direction.CENTRE ? occupiedAcrossTop : unoccupiedAcross) + vr);
		sb.append((direction == Direction.EAST ? occupiedAcrossTop : unoccupiedAcross) + vr + '\n');
		
		sb.append(repeatString(" ", leftMargin));
		sb.append(vl + " " + (direction == Direction.WEST ? occupiedUp : unoccupiedUp) + "  " + westChar + "  " + (direction == Direction.WEST ? occupiedUp : unoccupiedUp) + " ");
		sb.append(vl + " " + (direction == Direction.CENTRE ? occupiedUp : unoccupiedUp) + "  " + centreChar + "  " + (direction == Direction.CENTRE ? occupiedUp : unoccupiedUp) + " " + vr);
		sb.append(" " + (direction == Direction.EAST ? occupiedUp : unoccupiedUp) + "  " + eastChar + "  " + (direction == Direction.EAST ? occupiedUp : unoccupiedUp) + " " + vr + "\n");
		
		sb.append(repeatString(" ", leftMargin));
		sb.append(vl + (direction == Direction.WEST ? occupiedAcrossBottom : unoccupiedAcross));
		sb.append(vl + (direction == Direction.CENTRE ? occupiedAcrossBottom : unoccupiedAcross) + vr);
		sb.append((direction == Direction.EAST ? occupiedAcrossBottom : unoccupiedAcross) + vr + '\n');
		
		sb.append(repeatString(" ", leftMargin));
		sb.append(bl + repeatString(hb.toString(), 9));
		sb.append(bl + repeatString(hb.toString(), 9) + br);
		sb.append(repeatString(hb.toString(), 9) + br + '\n');
		
		// south room
		sb.append(repeatString(" ", 10 + leftMargin));
		sb.append(vl + (direction == Direction.SOUTH ? occupiedAcrossTop : unoccupiedAcross) + vr + '\n');
		sb.append(repeatString(" ", 10 + leftMargin));
		sb.append(vl + " " + (direction == Direction.SOUTH ? occupiedUp : unoccupiedUp) + "  " + southChar + "  " + (direction == Direction.SOUTH ? occupiedUp : unoccupiedUp) + " " + vr + '\n');
		sb.append(repeatString(" ", 10 + leftMargin));
		sb.append(vl + (direction == Direction.SOUTH ? occupiedAcrossBottom : unoccupiedAcross) + vr + '\n');
		sb.append(repeatString(" ", 10 + leftMargin));
		sb.append(bl + repeatString(hb.toString(), 9) + br + '\n');
		
		// Print everything to the terminal
		printTitleBlock("Map > " + city.getName());
		
		System.out.println(sb.toString());
		
		printLineCentred("Key:");
		System.out.println();
		
		printLineCentred("B - Home Base \u2503 S - Shop \u2503 H - Hospital \u2503 P - Power Up Den \u2503 V - Villain's Lair");
		System.out.println();
		
		printLineCentred("Press <Enter> to continue...");
		
		try {
			readLine();
		} catch (UserCancelException | UserQuitException | UserContinueException e) {}
	}
	
	/**
	 * Returns the letter that corresponds to the area (for the drawMap method).
	 * @param area The area to get the letter of.
	 * @return
	 */
	private Character getAreaLetter(Area area) {
		
		switch (area.getType()) {
			
			case HOME_BASE: return 'B';
			case HOSPITAL: return 'H';
			case POWER_UP_DEN: return 'P';
			case SHOP: return 'S';
			case VILLAINS_LAIR: return 'V';
			default: return 'X';
		
		}
	}

	public static void main(String[] args) {
		
		GeneralHelpers.setIsRunningInEclipse(true);
		GameEnvironment ge = new GameEnvironment(TextUserInterface.class);
		TextUserInterface ui = new TextUserInterface(ge);
		ge.setCities(new CityController(3));
		
//		try {
//			ge.getCityController().move(Direction.SOUTH);
//		} catch (IllegalMoveException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ui.drawMap();
	}
}
