package game;

import game.city.CityController;
import game.ui.text.TextUserInterface;
import game.character.*;
import java.util.ArrayList;

//20180416 to do
// call the ui
// handle exceptions
// ??

/**
 * Game Environment controls the game.
 * Calls the user interface for initial setups
 * Calls the CityController
 * Calls the user interface for navigation
 *  Calls areas
 * @author Manu
 *
 */
public class GameEnvironment {
	
	/**
	 * 
	 */
	private int cityCount = 0;
	
	/**
	 * 
	 */
	private ArrayList<Hero> heroes;
	
	/**
	 * 
	 */
	private String teamName = "";
	
	/**
	 * 
	 */
	private Team team;
	
	//create the ui, show the title-screen
	TextUserInterface ui = new TextUserInterface();
	ui.showTitleScreen();
	
	//call ui gameCreation (returns number of cities), call cityController with number of cities
	cityCount = ui.showGameCreationScreen();
	CityController cities = new CityController(cityCount);
	
	//call the team creation screen, returns a team of heroes
	// team creation will have sorted out hero creation
	team = ui.showTeamCreationScreen();
	
	//now move round the city. Where does team start? Some randomised index.
	ui.showNavigationScreen();
	
	// while something (cityCounter not greater than the number of cities?) 
	//
	// while something (no navigation?), check Area.
	//
	//if VillainsLair start battlescreen
	//  if villain defeated go to next level. Break, increment cityCounter, otherwise stay in battle screen.
	//if Hospital offer to: check health apply Healing Items etc
	//if Shop start Innkeeper and Dialogue and offer to: purchase & add Items to Inventory
	//if HomeBase offer to: view hero status and attributes, use map
	//if PowerUpDen offer to: apply PowerUp Items
	
	}

}
 

