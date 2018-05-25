package test.game.city;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.city.AreaType;
import game.city.PowerUpDen;

class PowerUpDenTest {

	private PowerUpDen powerUpDen;
	
	@BeforeEach
	void beforeEach() {
		powerUpDen = new PowerUpDen("Name", "Description");
	}
	
	@Test
	void testGetName() {
		
		assertEquals("Name", powerUpDen.getName());
		
	}
	
	@Test
	void testGetFlavourText() {
		
		assertEquals("Description", powerUpDen.getFlavourText());
		
	}
	
	@Test
	void testGetType() {
		
		assertEquals(AreaType.POWER_UP_DEN, powerUpDen.getType());
		
	}

}
