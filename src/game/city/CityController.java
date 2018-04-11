package game.city;

import java.util.ArrayList;

import game.GameWonException;

public class CityController {
	
	private City[] cities;
	private int cityIndex;
	private int numberOfCities;
	
	public CityController(int numberOfCities) {
		
		this.cityIndex = 0;
		this.numberOfCities = numberOfCities;
		
		generateCities();
		
	}
	
	private void generateCities() {
		
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
	
	public int getCityIndex() {
		
		return this.cityIndex;
		
	}
	
	public City getCurrentCity() {
		
		return this.cities[this.cityIndex];
		
	}
	
	public void goToNextCity() throws GameWonException {
		
		this.cityIndex++;
		
		if (this.cityIndex > this.cities.length) {
			
			throw new game.GameWonException();
			
		}
		
	}

}
