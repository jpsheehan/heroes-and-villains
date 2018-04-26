package game.city;

import java.util.ArrayList;

import game.GameWonException;

/**
 * Generates all cities in the game and holds references to them.
 * @author jesse
 *
 */
public class CityController {
	
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
		
		generateCities();
		
	}
	
	/**
	 * Randomly generates the order of the cities. Erskine is always last.
	 */
	private void generateCities() {
		
		// Validate the numberOfCities
		if (numberOfCities < 3) {
			
			throw new IllegalArgumentException("You need at least 3 cities.");
			
		}
		
		if (numberOfCities > 6) {
			
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
			
			this.cities[i] = new City(shuffledCityTypes.get(i));
			
		}
		
		// Add Erskine to the end of the array
		this.cities[this.numberOfCities - 1] = new City(CityType.ERSKINE);
		
	}
	
	/**
	 * Gets the index of the current city.
	 * @return
	 */
	public int getCityIndex() {
		
		return this.cityIndex;
		
	}
	
	/**
	 * Gets the current City.
	 */
	public City getCurrentCity() {
		
		return this.cities[this.cityIndex];
		
	}
	
	/**
	 * Returns the current area in the current city.
	 * @return
	 */
	public Area getCurrentArea() {
		
		return this.cities[this.cityIndex].getArea(direction);
		
	}
	
	/**
	 * Returns the current direction in the current city. 
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
		
		this.direction = absoluteDirection;
		
		if (!hasVisitedDirection(this.direction) ) {
			
			this.visitedDirections.add(this.direction);
			
		}
		
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
	}
	
	/**
	 * Returns true if the team has visited a particular direction in this city.
	 * @param direction The direction to check.
	 * @return
	 */
	public boolean hasVisitedDirection(Direction direction) {
		
		return this.visitedDirections.contains(direction);
		
	}

}
