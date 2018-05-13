package game;

import game.city.CityController;
import game.ui.UserInterface;
import game.ui.gui.GraphicalUserInterface;
import game.ui.text.TextUserInterface;
import game.ui.textgui.TextGraphicalUserInterface;

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
			
			case "game.ui.gui.GraphicalUserInterface":
				this.ui = new GraphicalUserInterface(this);
				break;
				
			case "game.ui.textgui.TextGraphicalUserInterface":
				this.ui = new TextGraphicalUserInterface(this);
				break;
				
			default:
				throw new IllegalArgumentException("No such ui class: " + uiClass.getName());
		
		}

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
	public void setCityController(CityController cities) {
		
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
 

