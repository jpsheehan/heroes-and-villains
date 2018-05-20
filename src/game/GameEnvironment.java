package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import game.city.CityController;

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
public class GameEnvironment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3013740228796469981L;

	/**
	 * The team of heroes.
	 */
	private Team team;
	
	/**
	 * The city controller.
	 */
	private CityController cityController;
	
	/**
	 * Used to temporarily store the random seed when saving state.
	 * Do not rely on this as the actual seed value, instead use game.GeneralHelpers.getSeed()
	 */
	private long randomSeedTemp;
	
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
	
	public void saveState(String filename) throws IOException {
		
		// get the random value
		// TODO: RETHINK THIS PROBLEM!!!
		this.randomSeedTemp = GeneralHelpers.getSeed();
		FileOutputStream fileOut = new FileOutputStream(filename);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(this);
		objOut.close();
		fileOut.close();
		
	}
	
	public static GameEnvironment loadState(String filename) throws IOException, ClassNotFoundException {
		
		GameEnvironment env = null;
		
		FileInputStream fileIn = new FileInputStream(filename);
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		env = (GameEnvironment)objIn.readObject();
		fileIn.close();
		objIn.close();
		
		GeneralHelpers.seedRandom(env.randomSeedTemp);
		
		return env;
		
	}
	

}
 

