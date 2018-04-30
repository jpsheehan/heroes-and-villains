package test.game.city;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.city.AreaType;

class AreaTypeTest {

	@Test
	void testToString() {
		
		assertEquals("Home Base", AreaType.HOME_BASE.toString());
		assertEquals("Villain's Lair", AreaType.VILLAINS_LAIR.toString());
		assertEquals("Shop", AreaType.SHOP.toString());
		assertEquals("Hospital", AreaType.HOSPITAL.toString());
		assertEquals("Power Up Den", AreaType.POWER_UP_DEN.toString());
		
	}
	
	@Test
	void testToProperString() {
		
		assertEquals("HomeBase", AreaType.HOME_BASE.toProperString());
		assertEquals("VillainsLair", AreaType.VILLAINS_LAIR.toProperString());
		assertEquals("Shop", AreaType.SHOP.toProperString());
		assertEquals("Hospital", AreaType.HOSPITAL.toProperString());
		assertEquals("PowerUpDen", AreaType.POWER_UP_DEN.toProperString());
		
	}

}
