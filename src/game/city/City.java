package game.city;

import static game.GeneralHelpers.getString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import game.Describable;
import game.Nameable;
import game.character.Dialogue;
import game.character.InnKeeper;
import game.character.Villain;
import game.item.Item;
import game.randomevent.RandomEvent;

public class City implements Nameable, Describable, Serializable {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -5115206042729739277L;

	/**
	 * The name of the City.
	 */
	private String name;
	
	/**
	 * A map of Directions to Areas.
	 */
	private HashMap<Direction, Area> areas;
	
	/**
	 * The type of city.
	 */
	private CityType type;
	
	/**
	 * The random event that occurs at the start of this city.
	 */
	private RandomEvent randomEvent;

	/**
	 * Creates a new City.
	 * @param type The type of city to create.
	 */
	public City(CityType type, int cityIndex) {
		
		this.type = type;
		this.randomEvent = new RandomEvent();
		generate(cityIndex);
		
	}
	
	/**
	 * Creates a list of Areas a city has based on the type of city it is.
	 * @param type The type of city to get areas for.
	 * @return Returns a list of Areas in the City.
	 */
	private static ArrayList<Area> getAreaList(CityType type, int cityIndex) {
		
		String buildingName = type.getProperName();
		
		ArrayList<Area> areaList = new ArrayList<Area>();

		InnKeeper innKeeper = new InnKeeper(getString(String.format("%s.InnKeeper.Name", buildingName)), new Dialogue(type));
		
		ArrayList<Item> items = new ArrayList<Item>();
		
		String[] properNames = getString(String.format("%s.Shop.Items", type.getProperName())).split(",");
		
		for (String properName : properNames) {
			
			items.add(Item.fromStrings(properName.trim(), (cityIndex + 1) * 0.8f));
			
		}
		
		Villain villain = Villain.fromStringsFile(type);
		
		areaList.add(new Shop(getString(String.format("%s.Shop.Name", buildingName)), getString(String.format("%s.Shop.Flavour", buildingName)), items, innKeeper));
		areaList.add(new Hospital(getString(String.format("%s.Hospital.Name", buildingName)), getString(String.format("%s.Hospital.Flavour", buildingName))));
		areaList.add(new PowerUpDen(getString(String.format("%s.PowerUpDen.Name", buildingName)), getString(String.format("%s.PowerUpDen.Flavour", buildingName))));
		areaList.add(new VillainsLair(getString(String.format("%s.VillainsLair.Name", buildingName)), getString(String.format("%s.VillainsLair.Flavour", buildingName)), villain));
		areaList.add(new HomeBase(getString(String.format("%s.HomeBase.Name", buildingName)), getString(String.format("%s.HomeBase.Flavour", buildingName))));
		
		return areaList;
	}
	
	/**
	 * Gets the name of the City based on the CityType.
	 * @param type The type of City to get the name of.
	 * @return Returns the name of the City.
	 */
	private static String getCityName(CityType type) {
		
		return getString(type.getProperName() + ".Name");
		
	}
	
	/**
	 * Generates the City's name and areas.
	 */
	private void generate(int cityIndex) {
		
		// Set the city name
		this.name = City.getCityName(type);
		
		// Get the areas for this particular type of city
		ArrayList<Area> areaList = City.getAreaList(type, cityIndex);
		
		if (areaList.size() != 5) {
			
			throw new AssertionError("City must have exactly 5 areas!");
			
		}
		
		// Remove the home base from the areaList
		HomeBase home = null;
		
		for (int i = 0; i < areaList.size(); i++) {
			
			Area area = areaList.get(i);
			
			if (area.getType().equals(AreaType.HOME_BASE)) {
				areaList.remove(area);
				home = (HomeBase)area;
				break;
			}
			
		}
		
		if (home == null) {
			
			throw new AssertionError("City does not have a Home Base!");
			
		}

		// Create a new HashMap and put the random
		this.areas = new HashMap<Direction, Area>();
		
		// Add the home base to the centre.
		this.areas.put(Direction.CENTRE, home);
		
		// Randomize the other areas and assign them directions within the city.
		areaList = game.GeneralHelpers.shuffleArrayList(areaList);
		
		this.areas.put(Direction.NORTH, areaList.get(0));
		this.areas.put(Direction.WEST, areaList.get(1));
		this.areas.put(Direction.SOUTH, areaList.get(2));
		this.areas.put(Direction.EAST, areaList.get(3));
	}
	
	/**
	 * Gets the Area in the specified Direction.
	 * @param direction The Direction to get the Area of. 
	 * @return Returns the Area.
	 */
	public Area getArea(Direction direction) {
		
		return areas.get(direction);
		
	}
	
	/**
	 * Gets the area based on the AreaType.
	 * @param type The type of area to get.
	 * @return The Area object.
	 */
	public Area getArea(AreaType type) {
		
		for (Area area : areas.values()) {
			
			if (area.getType() == type) {
				
				return area;
				
			}
			
		}
		
		throw new AssertionError("Invalid area type.");
		
	}
	
	/**
	 * Gets the type of City.
	 * @return Returns the CityType.
	 */
	public CityType getType() {
		return type;
	}
	
	/**
	 * @return The City's name.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @return The city's description.
	 */
	@Override
	public String getFlavourText() {

		return getString(type.getProperName() + ".Flavour");
	}
	
	/**
	 * @return The city's random event.
	 */
	public RandomEvent getRandomEvent() {
		
		return this.randomEvent;
	}
}
