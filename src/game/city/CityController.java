package game.city;

import java.io.Serializable;
import java.util.ArrayList;

import game.GameWonException;
import game.Settings;
import game.item.Map;

/**
 * Generates all cities in the game and holds references to them.
 * @author jesse
 *
 */
public class CityController implements Serializable {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -7347846701627560656L;

	/**
	 * A list of cities in the game.
	 */
	private City[] cities;
	
	/**
	 * The current city index.
	 */
	private int cityIndex;
	
	/**
	 * The total number of cities in this game.
	 */
	private int numberOfCities;
	
	/**
	 * The current direction that the player is in.
	 */
	private Direction direction;
	
	/**
	 * True if the team has used a map in this city.
	 */
	private boolean _hasUsedMap;
	
	/**
	 * A list of directions visited in this city.
	 */
	private ArrayList<Direction> visitedDirections;
	
	/**
	 * Creates a new CityController.
	 * @param numberOfCities The number of cities to have in this game.
	 */
	public CityController(int numberOfCities) {
		
		this.cityIndex = 0;
		this.numberOfCities = numberOfCities;
		this.direction = Direction.CENTRE;
		this.visitedDirections = new ArrayList<Direction>();
		this.visitedDirections.add(Direction.CENTRE);
		this._hasUsedMap = false;
		
		generateCities();
		
	}
	
	/**
	 * Randomly generates the order of the cities. Erskine is always last.
	 */
	private void generateCities() {
		
		// Validate the numberOfCities
		if (numberOfCities < Settings.getCitiesMin()) {
			
			throw new IllegalArgumentException("You need at least 3 cities.");
			
		}
		
		if (numberOfCities > Settings.getCitiesMax()) {
			
			throw new IllegalArgumentException("You can only have up to 6 cities.");
			
		}
		
		// Generate the cities (Erskine is always the final city).
		ArrayList<CityType> cityTypes = new ArrayList<CityType>();
		cityTypes.add(CityType.ENG_CORE);
		cityTypes.add(CityType.JAMES_HIGHT);
		cityTypes.add(CityType.LAW);
		cityTypes.add(CityType.MATARIKI);
		cityTypes.add(CityType.PSYCH);
		cityTypes.add(CityType.RUTHERFORD);
		
		ArrayList<CityType> shuffledCityTypes = game.GeneralHelpers.shuffleArrayList(cityTypes);
		
		this.cities = new City[this.numberOfCities];
		
		for (int i = 0; i < this.numberOfCities - 1; i++) {
			
			this.cities[i] = new City(shuffledCityTypes.get(i), i);
			((VillainsLair)this.cities[i].getArea(AreaType.VILLAINS_LAIR)).getBattleScreen().setCityIndex(i);
			
		}
		
		// Add Erskine to the end of the array
		this.cities[this.numberOfCities - 1] = new City(CityType.ERSKINE, this.numberOfCities - 1);
		
	}
	
	/**
	 * Gets the index of the current city.
	 * @return
	 */
	public int getCityIndex() {
		
		return this.cityIndex;
		
	}
	
	/**
	 * @return The current City.
	 */
	public City getCurrentCity() {
		
		return this.cities[this.cityIndex];
		
	}
	
	/**
	 * @return The current area in the current city.
	 */
	public Area getCurrentArea() {
		
		return this.cities[this.cityIndex].getArea(direction);
		
	}
	
	/**
	 * @return The current direction in the current city. 
	 */
	public Direction getDirection() {
		
		return this.direction;
		
	}
	
	/**
	 * Moves in a particular direction relative to the current area within the city.
	 * @param newDirection
	 * @throws IllegalMoveException 
	 */
	public void move(Direction newDirection) throws IllegalMoveException {

		Direction absoluteDirection;
		
		if (newDirection == null) {
			
			throw new NullPointerException("Direction cannot be null.");
			
		}
		
		if (newDirection == Direction.CENTRE) {
			
			throw new IllegalMoveException(this.direction, newDirection);
		}
		
		// If the current direction is in the centre then everything is valid.
		if (this.direction != Direction.CENTRE) {
			
			if (this.direction == Direction.NORTH && newDirection != Direction.SOUTH ||
					this.direction == Direction.WEST && newDirection != Direction.EAST ||
					this.direction == Direction.SOUTH && newDirection != Direction.NORTH ||
					this.direction == Direction.EAST && newDirection != Direction.WEST) {
				
				throw new IllegalMoveException(this.direction, newDirection);
				
			} else {
			
				absoluteDirection = Direction.CENTRE;
				
			}
			
		} else {
			
			absoluteDirection = newDirection;
			
		}
		
		this.goTo(absoluteDirection);
		
	}
	
	/**
	 * Advances the Team to the next city.
	 * @throws GameWonException if the game has been won.
	 */
	public void goToNextCity() throws GameWonException {
		
		
		if (this.cityIndex + 1 >= this.cities.length) {
			
			throw new game.GameWonException();
			
		}

		this.cityIndex++;
		this.direction = Direction.CENTRE;
		this.visitedDirections = new ArrayList<Direction>();
		this.visitedDirections.add(Direction.CENTRE);
		this._hasUsedMap = false;
		
	}
	
	/**
	 * @param direction The direction to check.
	 * @return True if the team has visited a particular direction in this city.
	 */
	public boolean hasVisitedDirection(Direction direction) {
		
		return this.visitedDirections.contains(direction) || this._hasUsedMap;
		
	}
	
	/**
	 * Uses the map in the current area.
	 * @param map The map to use.
	 */
	public void useMap(Map map) {
		
		if (map == null) {
			
			throw new IllegalArgumentException("Map cannot be null.");
			
		}
		
		if (this._hasUsedMap) {
			
			throw new AssertionError("Cannot use map while already using a map.");
			
		}
		
		this._hasUsedMap = true;
		
	}
	
	/**
	 * Check whether or not the map has been used in this city.
	 * @return True if the team has used a map in this city.
	 */
	public boolean hasUsedMap() {
		
		return this._hasUsedMap;
		
	}
	
	/**
	 * Sends the team to a specific location.
	 * @param direction The absolute direction to travel to.
	 */
	public void goTo(Direction direction) {
		
		this.direction = direction;
		
		if (!hasVisitedDirection(this.direction)) {
			
			this.visitedDirections.add(this.direction);
			
		}
		
	}
	
	/**
	 * @return True if this is the last city.
	 */
	public boolean isLastCity() {
		
		return this.cityIndex == this.cities.length - 1;
		
	}
}
