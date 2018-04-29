package test.game.city;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.GameWonException;
import game.city.AreaType;
import game.city.CityController;
import game.city.CityType;
import game.city.Direction;
import game.city.IllegalMoveException;
import game.item.Map;

class CityControllerTest {

	@Test
	void testCityController() {
		
		assertThrows(IllegalArgumentException.class, () -> new CityController(2));
		assertThrows(IllegalArgumentException.class, () -> new CityController(7));
		
		new CityController(3);
		new CityController(4);
		new CityController(5);
		new CityController(6);
		
	}
	
	@Test
	void testGenerateCities() throws GameWonException {
		
		CityController ctrl = new CityController(3);
		
		ctrl.goToNextCity();
		ctrl.goToNextCity();
		
		assertEquals(CityType.ERSKINE, ctrl.getCurrentCity().getType());
		assertEquals(Direction.CENTRE, ctrl.getDirection());
		assertEquals(AreaType.HOME_BASE, ctrl.getCurrentArea());
		
	}

	@Test
	void testGetCityIndex() throws GameWonException {
		
		CityController ctrl = new CityController(3);
		assertEquals(0, ctrl.getCityIndex());
		
		ctrl.goToNextCity();
		assertEquals(1, ctrl.getCityIndex());
		
		ctrl.goToNextCity();
		assertEquals(2, ctrl.getCityIndex());
		
		assertThrows(GameWonException.class, () -> ctrl.goToNextCity());
		
		assertEquals(2, ctrl.getCityIndex());
		
	}
	
	@Test
	void testMap() {
		
		Map map = new Map("Cool Map", "Description", 10);
		CityController ctrl = new CityController(4);
		
		assertFalse(ctrl.hasUsedMap());

		assertTrue(ctrl.hasVisitedDirection(Direction.CENTRE));
		assertFalse(ctrl.hasVisitedDirection(Direction.EAST));
		assertFalse(ctrl.hasVisitedDirection(Direction.NORTH));
		assertFalse(ctrl.hasVisitedDirection(Direction.SOUTH));
		assertFalse(ctrl.hasVisitedDirection(Direction.WEST));
		
		assertThrows(IllegalArgumentException.class, () -> ctrl.useMap(null));
		
		ctrl.useMap(map);
		assertTrue(ctrl.hasUsedMap());
		
		assertThrows(AssertionError.class, () -> ctrl.useMap(map));

		assertTrue(ctrl.hasVisitedDirection(Direction.CENTRE));
		assertTrue(ctrl.hasVisitedDirection(Direction.EAST));
		assertTrue(ctrl.hasVisitedDirection(Direction.NORTH));
		assertTrue(ctrl.hasVisitedDirection(Direction.SOUTH));
		assertTrue(ctrl.hasVisitedDirection(Direction.WEST));
		
	}
	
	@Test
	void testMove() throws IllegalMoveException {
		
		CityController ctrl = new CityController(5);
		assertEquals(Direction.CENTRE, ctrl.getDirection());
		
		ctrl.move(Direction.NORTH);
		assertEquals(Direction.NORTH, ctrl.getDirection());
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.NORTH));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.WEST));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.EAST));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.CENTRE));
		ctrl.move(Direction.SOUTH);
		
		ctrl.move(Direction.SOUTH);
		assertEquals(Direction.SOUTH, ctrl.getDirection());
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.SOUTH));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.WEST));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.EAST));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.CENTRE));
		ctrl.move(Direction.NORTH);

		ctrl.move(Direction.WEST);
		assertEquals(Direction.WEST, ctrl.getDirection());
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.SOUTH));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.WEST));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.NORTH));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.CENTRE));
		ctrl.move(Direction.EAST);

		ctrl.move(Direction.EAST);
		assertEquals(Direction.EAST, ctrl.getDirection());
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.SOUTH));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.EAST));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.NORTH));
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.CENTRE));
		ctrl.move(Direction.WEST);
		
		assertEquals(Direction.CENTRE, ctrl.getDirection());
		assertThrows(IllegalMoveException.class, () -> ctrl.move(Direction.CENTRE));
		
		assertThrows(NullPointerException.class, () -> ctrl.move(null));
		
	}
	
}
