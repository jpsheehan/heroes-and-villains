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

/**
 * Represents the user interface shown to the user in text format.
 * This is to run in the terminal/command line.
 * @author jesse
 *
 */
public class TextUserInterface extends UserInterface {

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
						
						// Create a new game, and set the team and city controller in the game environment.
						NewGameParameters params = showNewGameScreen();
						
						this.getGameEnvironment().setCityController(new CityController(params.getCityCount()));
						this.getGameEnvironment().setTeam(params.getTeam());
						
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
	public int showGetNumberOfVillainsScreen() throws UserQuitException, UserCancelException {
		
		int cityCount = 0;
		
		String prompt = "How many villains do you want to fight? " + getInputOptions("3-6");
		
		printTitleBlock("New Game > Number of Villains");
		
		System.out.println(prompt);
		
		// Keep prompting the user a valid number.
		while (true) {
			
			try {
				
				cityCount = getNumberWithBounds(3, 6);
				return cityCount;
			
			} catch (UserContinueException e) {
				
				System.out.println(prompt);
				
			}

		}
		
	}

	/**
	 * Displays a menu that is used to start a new game. Offers the user options of setting the number of villains, team name and adding/removing heroes.
	 * Upon returning, the game is started.
	 * @throws UserQuitException
	 * @throws UserCancelException
	 */
	public NewGameParameters showNewGameScreen() throws UserQuitException, UserCancelException {
		
		// The number of cities that the user wants to go to.
		int cityCount = 3;
		
		ArrayList<Hero> heroes = new ArrayList<Hero>();
		String teamName = "";

		String[] options = new String[] {
			"Change the number of Villains",
			"Add a Hero",
			"Remove a Hero",
			"Name the Team",
			"Ready!"
		};
		
		while (true) {
			
			int choice = 0;
			
			printTitleBlock("New Game");
			
			// Print some information about villains.
			System.out.println(String.format("You will be fighting %d villains.\n", cityCount));
			
			// Print some information about the team name.
			if (teamName.equals("")) {
				
				System.out.println("Your team has not been named yet.\n");
				
			} else {
				
				System.out.println(String.format("Your team name is \"%s\".\n", teamName));
				
			}
			
			// Print some information about the number of heroes in your team.
			System.out.println(String.format("You have %d hero%s in your team%s", heroes.size(), heroes.size() == 1 ? "" : "es", heroes.size() == 0 ? "." : ":"));
			
			// Print a list of heroes in your team.
			if (heroes.size() > 0) {
				
				System.out.println(getPrettyHeroesString(heroes));
				
			}
			
			System.out.println();
			
			// Display a list of options and switch on the user's choice.
			choice = showChoice("Select an option:", options);
			
			switch (choice) {
			
				case 0:
					// change the number of Villains
					
					int oldCityCount = cityCount;
					
					cityCount = showGetNumberOfVillainsScreen();
					
					// make sure that the number the user chose was valid. Otherwise restore the old cityCount.
					if (cityCount == 0) {
					
						cityCount = oldCityCount;
						
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
					
					if (!input.isEmpty()) {
						
						// Validate the input
						if (input.length() < 2 || input.length() >= 10) {
							
							showMessageDialog("Sorry, the team name must be between 2 and 10 characters long.", "New Game > Change Team Name > Error");
							
						} else {
							
							teamName = input;
							
							// change the menu string from "Name the Team" to "Rename the Team"
							options[3] = "Rename the Team";
							
						}
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
								
								Team team = new Team(teamName);
								
								for (Hero hero : heroes) {
									
									try {
										
										team.addHero(hero);
										
									} catch (TeamFullException e) {
										
										throw new AssertionError("The ui should check if too many heroes have been added.");
										
									}
									
								}
								
								// Create the parameters for the new game and confirm that this is what the user wants to start the game with.
								NewGameParameters params = new NewGameParameters(cityCount, team);
								
								if (showConfirmGameScreen(params)) {
									
									return params;
									
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
		
		//can get them into a string buffer
		String[] heroesArray; // = new String[];
		StringBuffer hereosBuffer = new StringBuffer();
		for(HeroType c : HeroType.values()) {
			hereosBuffer.append(c);
			hereosBuffer.append(", ");
			//heroesArray.add(toString(c));
		}
		// if not empty remove the last comma
			if (hereosBuffer.length() != 0) {
				hereosBuffer.deleteCharAt(hereosBuffer.length()-2);
			}
		System.out.println(hereosBuffer); //only for testing
		
		System.out.println(""); //testing
		
		// this works for printing them out
		for(HeroType heroType : HeroType.values()){
			System.out.println("Hero Type: " + heroType);
		}
		System.out.println(""); //only for testing

		/*
		for(HeroType heroType : HeroType.values()){
			heroesArray = heroType.toString();
		}
		*/
		
		// get the enum values into an array of strings attempt 3
		String[] typesOfHeroes = new String[HeroType.values().length];
		for (int i = 0; i < HeroType.values().length ; i++) {
			typesOfHeroes[i] = HeroType.values().toString();
			}
		System.out.println(typesOfHeroes); //only for testing
		
		 /*
		 * for (i = 0; i < numberHeroTypes ; i++) {
		 * 	
		 * 	print out the hero type and ability
		 *  
		 * }
		 */
		
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
	 * @return Hero
	 */
	private Hero showHeroDeletionMenu(ArrayList<Hero> heroes) throws UserQuitException {
		
		Hero[] heroArray = new Hero[heroes.size()];
		
		//get each hero from the ArrayList into an Array.
		// loop through ArrayList (containing Hero) add each Hero into Array (containing hero) 
		for (int i = 0; i<heroes.size(); i++ ) {
			heroArray[i] = heroes.get(i);
		}
		return (showHeroSelectionMenu("Select a hereo to remove from the team", "New Game > Remove Hero", heroArray));
	}
	
	/**
	 * Displays a confirmation dialog to the user. Should give a general summary of the game settings and the team.
	 * @return Returns true if the user wants to play the game, false otherwise.
	 * @throws UserQuitException
	 */
	private boolean showConfirmGameScreen(NewGameParameters params) throws UserQuitException {
		
		String heroString = getPrettyHeroesString((params.getTeam().getHeroes()));

		return showYesNoDialog(
				String.format(
						"You will be fighting %d villains. Your team name is \"%s\". You have %d hero%s:\n%s\n\nIs this ok?",
						params.getCityCount(), params.getTeam().getName(), params.getTeam().getHeroes().size(),
						params.getTeam().getHeroes().size() == 1 ? "" : "es", String.join("\n", heroString)), // pluralise the word "hero" if there is more than one hero in the team
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
			
			printTitleBlock(title);
			
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
	private void drawMap() throws UserQuitException {
		
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
		
		// set the character in the centre of each room to either be the letter legend of the room (if the team has visited the room)
		// or the '?' symbol (if the team hasn't visited the room).
		Character northChar = this.getGameEnvironment().getCityController().hasVisitedDirection(Direction.NORTH) ? getAreaLetter(city.getArea(Direction.NORTH)) : '?';
		Character westChar = this.getGameEnvironment().getCityController().hasVisitedDirection(Direction.WEST) ? getAreaLetter(city.getArea(Direction.WEST)) : '?';
		Character centreChar = this.getGameEnvironment().getCityController().hasVisitedDirection(Direction.CENTRE) ? getAreaLetter(city.getArea(Direction.CENTRE)) : '?';
		Character eastChar = this.getGameEnvironment().getCityController().hasVisitedDirection(Direction.EAST) ? getAreaLetter(city.getArea(Direction.EAST)) : '?';
		Character southChar = this.getGameEnvironment().getCityController().hasVisitedDirection(Direction.SOUTH) ? getAreaLetter(city.getArea(Direction.SOUTH)) : '?';
		
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
		
		// print an open "door" if the team has visited the north area 
		if (this.getGameEnvironment().getCityController().hasVisitedDirection(Direction.NORTH)) {
			
			sb.append(tl + repeatString(ht.toString(), 3) + "   " + repeatString(ht.toString(), 3) + tr);
			
		} else {

			sb.append(tl + repeatString(ht.toString(), 9) + tr);
			
		}
		
		sb.append(repeatString(ht.toString(), 9) + tr + '\n');
		sb.append(repeatString(" ", leftMargin));
		
		sb.append(vl + (direction == Direction.WEST ? occupiedAcrossTop : unoccupiedAcross));		
		sb.append(vl + (direction == Direction.CENTRE ? occupiedAcrossTop : unoccupiedAcross) + vr);
		sb.append((direction == Direction.EAST ? occupiedAcrossTop : unoccupiedAcross) + vr + '\n');
		
		sb.append(repeatString(" ", leftMargin));
		sb.append(vl + " " + (direction == Direction.WEST ? occupiedUp : unoccupiedUp) + "  " + westChar + "  " + (direction == Direction.WEST ? occupiedUp : unoccupiedUp) + " ");
		
		// print an open "door" if the team has visited the west area
		if (this.getGameEnvironment().getCityController().hasVisitedDirection(Direction.WEST)) {
			
			sb.append(" ");
			
		} else {
			
			sb.append(vl);
			
		}
		
		sb.append(" " + (direction == Direction.CENTRE ? occupiedUp : unoccupiedUp) + "  " + centreChar + "  " + (direction == Direction.CENTRE ? occupiedUp : unoccupiedUp) + " ");
		
		// print an open "door" if the team has visited the east area
		if (this.getGameEnvironment().getCityController().hasVisitedDirection(Direction.EAST)) {
			
			sb.append(" ");
			
		} else {
			
			sb.append(vr);
			
		}
		
		sb.append(" " + (direction == Direction.EAST ? occupiedUp : unoccupiedUp) + "  " + eastChar + "  " + (direction == Direction.EAST ? occupiedUp : unoccupiedUp) + " " + vr + "\n");
		
		sb.append(repeatString(" ", leftMargin));
		sb.append(vl + (direction == Direction.WEST ? occupiedAcrossBottom : unoccupiedAcross));
		sb.append(vl + (direction == Direction.CENTRE ? occupiedAcrossBottom : unoccupiedAcross) + vr);
		sb.append((direction == Direction.EAST ? occupiedAcrossBottom : unoccupiedAcross) + vr + '\n');
		
		sb.append(repeatString(" ", leftMargin));
		sb.append(bl + repeatString(hb.toString(), 9));
		
		// print the "door" if the team has visited the area 
		if (this.getGameEnvironment().getCityController().hasVisitedDirection(Direction.NORTH)) {

			sb.append(bl + repeatString(hb.toString(), 3) + "   " + repeatString(hb.toString(), 3)  + br);
			
		} else {

			sb.append(bl + repeatString(hb.toString(), 9) + br);
			
		}
		
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
		
		printLineCentred("Legend:");
		System.out.println();
		
		printLineCentred("B - Home Base \u2503 S - Shop \u2503 H - Hospital \u2503 P - Power Up Den \u2503 V - Villain's Lair");
		System.out.println();
		
		printLineCentred("Press <Enter> to continue...");
		
		try {
			readLine();
		} catch (UserCancelException | UserContinueException e) {}
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
		ge.setCityController(new CityController(3));
		
		// ge.getCityController().useMap(new Map("Cool Map", "It's made of paper!", 10));
		
		try {
			ui.drawMap();
		} catch (UserQuitException e) {
			
		}
	}
}
