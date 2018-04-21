package game.city;

import static game.GeneralHelpers.getString;
import java.util.ArrayList;
import java.util.HashMap;

import game.character.Dialogue;
import game.character.InnKeeper;
import game.character.Villain;
import game.character.VillainType;
import game.item.Item;
import game.minigame.MinigameType;

public class City implements game.Nameable {
	
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
	 * Creates a new City.
	 * @param type The type of city to create.
	 */
	public City(CityType type) {
		
		this.type = type;
		
		generate();
		
	}
	
	/**
	 * Creates a list of Areas a city has based on the type of city it is.
	 * @param type The type of city to get areas for.
	 * @return Returns a list of Areas in the City.
	 */
	private static ArrayList<Area> getAreaList(CityType type) {
		
		ArrayList<Area> areaList = new ArrayList<Area>();
		
		switch (type) {
		
		case RUTHERFORD:
			// TODO: complete the areas for Rutherford
			InnKeeper innKeeper = new InnKeeper(getString("Rutherford.InnKeeper.Name"), new Dialogue());
			ArrayList<Item> items = new ArrayList<Item>();
			Villain villain = new Villain(getString("Rutherford.Villain.Name"), VillainType.RORY_THE_BUILDER, MinigameType.ALL, 3);
			
			areaList.add(new Shop(getString("Rutherford.Shop.Name"), items, innKeeper));
			areaList.add(new Hospital(getString("Rutherford.Hospital.Name")));
			areaList.add(new PowerUpDen(getString("Rutherford.PowerUpDen.Name")));
			areaList.add(new VillainsLair(getString("Rutherford.VillainsLair.Name"), villain, -1));
			areaList.add(new HomeBase(getString("Rutherford.HomeBase.Name")));
			break;
			
		case ENG_CORE:
			// TODO: create the areas for Engineering Core
			break;
			
		case ERSKINE:
			// TODO: create the areas for Jack Erskine
			break;
			
		case JAMES_HIGHT:
			// TODO: create the areas for James Hight
			break;
			
		case LAW:
			// TODO: create the areas for Law and Business
			break;
			
		case MATARIKI:
			// TODO: create the areas for Matariki
			break;
			
		case PSYCH:
			// TODO: create the areas for Psychology/Sociology
			break;
			
		default:
			throw new AssertionError("Invalid CityType to generate.");
		
		}
		
		return areaList;
	}
	
	/**
	 * Gets the name of the City based on the CityType.
	 * @param type The type of City to get the name of.
	 * @return Returns the name of the City.
	 */
	private static String getCityName(CityType type) {
		
		switch (type) {
		
		case ENG_CORE:
			return "Engineering Core";
		
		case ERSKINE:
			return "Jack Erskine";
			
		case JAMES_HIGHT:
			return "James Hight";
			
		case LAW:
			return "Law & Business";
			
		case MATARIKI:
			return "Matariki";
			
		case PSYCH:
			return "Psychology/Sociology";
			
		case RUTHERFORD:
			return "Ernest Rutherford";
			
		default:
			throw new AssertionError("Invalid CityType enum for name.");
		
		}
		
	}
	
	/**
	 * Generates the City's name and areas.
	 */
	private void generate() {
		
		// Set the city name
		this.name = City.getCityName(type);
		
		// Get the areas for this particular type of city
		ArrayList<Area> areaList = City.getAreaList(type);
		
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
	 * Gets the type of City.
	 * @return Returns the CityType.
	 */
	public CityType getType() {
		return type;
	}
	
	/**
	 * Returns the City's name.
	 */
	@Override
	public String getName() {
		return name;
	}
	
}
