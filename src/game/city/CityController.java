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
	 * Creates a new CityController.
	 * @param numberOfCities The number of cities to have in this game.
	 */
	public CityController(int numberOfCities) {
		
		this.cityIndex = 0;
		this.numberOfCities = numberOfCities;
		
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
	 * Advances the Team to the next city.
	 * @throws GameWonException if the game has been won.
	 */
	public void goToNextCity() throws GameWonException {
		
		
		if (this.cityIndex + 1 >= this.cities.length) {
			
			throw new game.GameWonException();
			
		}

		this.cityIndex++;
	}

}
