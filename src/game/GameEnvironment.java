package game;

import game.city.CityController;
import game.ui.UserInterface;
import game.ui.text.TextUserInterface;

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
	 * The team of heroes.
	 */
	private Team team;
	
	/**
	 * The city controller.
	 */
	private CityController cityController;
	
	/**
	 * The user interface to use.
	 */
	private UserInterface ui;
	
	@SuppressWarnings("rawtypes")
	public GameEnvironment(Class uiClass) {
		
		switch (uiClass.getName()) {
		
			case "game.ui.text.TextUserInterface":
				this.ui = new TextUserInterface(this);
				break;
			
			default:
				throw new IllegalArgumentException("No such ui class: " + uiClass.getName());
		
		}
		
		//call ui gameCreation (returns number of cities), call cityController with number of cities
//		cityCount = ui.showGameCreationScreen();
//		CityController cities = new CityController(cityCount);
		
		//call the team creation screen, returns a team of heroes
		// team creation will have sorted out hero creation
		// team = ui.showTeamCreationScreen();
		
		//now move round the city. Where does team start? Some randomised index.
		// ui.showNavigationScreen();
		
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
	
	/**
	 * Starts the game.
	 */
	public void run() {

		this.ui.start();
		
	}
	
	/**
	 * Gets the user interface.
	 * @return
	 */
	public UserInterface getUserInterface() {
		
		return this.ui;
		
	}
	
	/**
	 * Gets the city controller.
	 * @return
	 */
	public CityController getCityController() {
		
		return this.cityController;
		
	}
	
	/**
	 * Gets the team.
	 * @return
	 */
	public Team getTeam() {
		
		return this.team;
		
	}
	
	/**
	 * Sets the cities. To be called from the UserInterface.
	 * @param cities
	 */
	public void setCities(CityController cities) {
		
		this.cityController = cities;
		
	}
	
	/**
	 * Sets the team. To be called from the UserInterface.
	 * @param team
	 */
	public void setTeam(Team team) {
		
		this.team = team;
		
	}
	

}
 

