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

/**
 * Game Environment controls the game.
 * @author Manu
 * @version 1.0
 * 
 * @author Jessee
 * @version 2.0
 * Adds variables and methods for loading / saving games
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
	private transient long timeSaved;
	
	/**
	 * The time the game environment was loaded/created.
	 */
	private transient long timeStarted;
	
	/**
	 * The time (in seconds) since the game was started/loaded. This is used only for loading and saving.
	 */
	private long timeTakenTemp;
	
	/**
	 * Creates a new instance of GameEnvironment.
	 */
	public GameEnvironment() {
		
		this.timeStarted = (new Date()).getTime();
		
	}
	
	/**
	 * @return The city controller.
	 */
	public CityController getCityController() {
		
		return this.cityController;
		
	}
	
	/**
	 * @return The team.
	 */
	public Team getTeam() {
		
		return this.team;
		
	}
	
	/**
	 * Sets the cities. To be called from the UserInterface.
	 * @param cities The city controller to use.
	 */
	public void setCityController(CityController cities) {
		
		this.cityController = cities;
		
	}
	
	/**
	 * Sets the team. To be called from the UserInterface.
	 * @param team The team to use.
	 */
	public void setTeam(Team team) {
		
		this.team = team;
		
	}
	
	/**
	 * For a unique issue dealing with the "Are you sure you want to enter" confirm dialog appearing when loading into a Villains Lair.
	 */
	private boolean ignoreRoomPrompt;
	
	/**
	 * Saves the state of the game in a particular location.
	 * @throws IOException If something went wrong.
	 */
	public void saveState() throws IOException {
		
		File saveDir = new File(Settings.getSaveDirectory());
		
		if (!saveDir.isDirectory()) {
			
			saveDir.mkdir();
			
		}
		
		Path path = Paths.get(Settings.getSaveDirectory(), String.format("%d.ser", (new Date()).getTime()));
		
		String filename = path.toString();
		
		// get the random state
		this.randomStateTemp = GeneralHelpers.getRandom();
		this.timeTakenTemp = (new Date()).getTime() - timeStarted;
		
		FileOutputStream fileOut = new FileOutputStream(filename);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(this);
		objOut.close();
		fileOut.close();
		
	}
	
	/**
	 * Loads the game state from a predetermined folder on the filesystem
	 * @return A new Game Environment.
	 * @throws IOException When the serialized data could not be read.
	 * @throws ClassNotFoundException When a class hasn't been marked as Serializable.
	 */
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
		
		// Set some metadata related to the loading of the game.
		env.setTimeSaved(latest);
		env.ignoreRoomPrompt = true;
		GeneralHelpers.setRandom(env.randomStateTemp);

		(new File(filename)).delete();
		
		return env;
		
	}
	
	/**
	 * Gets an array of file objects found in the save directory.
	 * @return An array of Files.
	 */
	public static File[] getSaveStates() {
		
		File saveDir = new File(Settings.getSaveDirectory());
		
		// Return an empty array if the directory doesn't exist
		if (!saveDir.isDirectory()) {
			
			return new File[0];
			
		}
		
		// Read the files into an ArrayList
		ArrayList<File> files = new ArrayList<File>();
		
		for (File file : saveDir.listFiles()) {
			if (file.getName().indexOf(".ser") >= 0) {

				try {
					
					// Get the filename and add it to the ArrayList if it has the correct filename format.
					String filename = file.getName().substring(0, file.getName().indexOf(".ser"));
					Long.parseLong(filename);
					files.add(file);
					
				} catch (NumberFormatException e) {
					// sometimes the filename might not be in the correct format
					// ignore these files and continue
					continue;
				}
			}
		}
		
		// Convert the files list into an array
		File[] filesArray = new File[files.size()];
		for (int i = 0; i < files.size(); i++) {
			filesArray[i] = files.get(i);
		}
		
		return filesArray;
		
	}
	
	/**
	 * Gets whether or not any save states exist.
	 * @return True if at least one save state exists. False otherwise.
	 */
	public static boolean doesSaveStateExist() {
		
		return getSaveStates().length > 0;
		
	}
	
	/**
	 * Sets the time when the game was saved.
	 * @param time The time in milliseconds since the epoch.
	 */
	private void setTimeSaved(long time) {
		
		this.timeSaved = time;
		this.timeStarted = (new Date()).getTime() - timeTakenTemp;
		
	}
	
	/**
	 * Gets the time when the game was saved.
	 * @return The time in (milliseconds since the epoch) when the game was saved.
	 */
	public long getTimeSaved() {
		
		return this.timeSaved;
		
	}
	
	/**
	 * Gets whether the game should ignore the room prompt.
	 * This is set to true internally when the game is loaded.
	 * This is to fix a very specific edge case where the player saves the game from within a Villain's Lair.
	 * Also sets the internal flag to false for subsequent calls.
	 * @return True if the game should ignore the room prompt. False otherwise.
	 */
	public boolean getIgnoreRoomPrompt() {
		
		boolean wasTrue = ignoreRoomPrompt;
		ignoreRoomPrompt = false;
		return wasTrue;
		
	}
	
	/**
	 * @return The number of seconds since the game began.
	 */
	public long getNumberOfSeconds() {
		return ((new Date()).getTime() - this.timeStarted) / 1000;
		
	}

}
 

