package test.game.city;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.city.AreaType;
import game.city.HomeBase;

class HomeBaseTest {

	private HomeBase homeBase;
	
	@BeforeEach
	void beforeEach() {
		homeBase = new HomeBase("Name", "Description");
	}
	
	@Test
	void testGetName() {
		
		assertEquals("Name", homeBase.getName());
		
	}
	
	@Test
	void testGetFlavourText() {
		
		assertEquals("Description", homeBase.getFlavourText());
		
	}
	
	@Test
	void testGetType() {
		
		assertEquals(AreaType.HOME_BASE, homeBase.getType());
		
	}

}
