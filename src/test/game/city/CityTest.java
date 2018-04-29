package test.game.city;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.city.AreaType;
import game.city.City;
import game.city.CityType;
import game.city.Direction;

class CityTest {

	@Test
	void testCity() {
		
		assertThrows(NullPointerException.class, () -> new City(null));
		
		City city = new City(CityType.ERSKINE);
		assertEquals("Jack Erskine", city.getName());
		assertEquals(AreaType.HOME_BASE, city.getArea(Direction.CENTRE).getType());
		assertEquals(CityType.ERSKINE, city.getType());
		
	}

}
