package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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
	 * Required for implementing the Serializable interface.
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
	private Random randomStateTemp;
	
	/**
	 * The timestamp when the game was saved (or 0 if it was this is not a saved game)
	 */
	private long timeSaved;
	
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
	
	/**
	 * For a unique issue dealing with the "Are you sure you want to enter" confirm dialog appearing when loading into a Villains Lair.
	 */
	private boolean ignoreRoomPrompt;
	
	public void saveState() throws IOException {
		
		File saveDir = new File(Settings.getSaveDirectory());
		
		if (!saveDir.isDirectory()) {
			
			saveDir.mkdir();
			
		}
		
		Path path = Paths.get(Settings.getSaveDirectory(), String.format("%d.ser", (new Date()).getTime()));
		
		String filename = path.toString();
		
		// get the random state
		this.randomStateTemp = GeneralHelpers.getRandom();
		FileOutputStream fileOut = new FileOutputStream(filename);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(this);
		objOut.close();
		fileOut.close();
		
	}
	
	public static GameEnvironment loadState() throws IOException, ClassNotFoundException {
		
		String filename = null;
		long latest = 0;
		
		for (File file : getSaveStates()) {
			if (file.getName().indexOf(".ser") >= 0) {

				try {
					long timestamp = Long.parseLong(file.getName().substring(0, file.getName().indexOf(".ser")));
					
					if (timestamp > latest) {
						latest = timestamp;
						filename = file.getAbsolutePath();
					}
				} catch (NumberFormatException e) {
					
				}
			}
		}
		
		if (filename == null) {
			throw new IOException("No save states found.");
		}
		
		GameEnvironment env = null;
		
		FileInputStream fileIn = new FileInputStream(filename);
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		env = (GameEnvironment)objIn.readObject();
		fileIn.close();
		objIn.close();
		
		env.setTimeSaved(latest);
		env.ignoreRoomPrompt = true;
		GeneralHelpers.setRandom(env.randomStateTemp);

		(new File(filename)).delete();
		
		return env;
		
	}
	
	private static File[] getSaveStates() {
		
		File saveDir = new File(Settings.getSaveDirectory());
		
		if (!saveDir.isDirectory()) {
			
			return new File[0];
			
		}
		
		ArrayList<File> files = new ArrayList<File>();
		
		for (File file : saveDir.listFiles()) {
			if (file.getName().indexOf(".ser") >= 0) {

				try {
					
					String filename = file.getName().substring(0, file.getName().indexOf(".ser"));
					Long.parseLong(filename);
					files.add(file);
					
					
				} catch (NumberFormatException e) {
					
				}
			}
		}
		
		File[] filesArray = new File[files.size()];
		for (int i = 0; i < files.size(); i++) {
			filesArray[i] = files.get(i);
		}
		
		return filesArray;
		
	}
	
	public static boolean doesSaveStateExist() {
		
		return getSaveStates().length > 0;
		
	}
	
	private void setTimeSaved(long time) {
		
		this.timeSaved = time;
		
	}
	
	public long getTimeSaved() {
		
		return this.timeSaved;
		
	}
	
	public boolean getIgnoreRoomPrompt() {
		
		boolean wasTrue = ignoreRoomPrompt;
		ignoreRoomPrompt = false;
		return wasTrue;
		
	}

}
 

