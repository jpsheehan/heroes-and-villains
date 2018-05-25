package test.game.city;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.city.AreaType;
import game.city.Hospital;;

class HospitalTest {

	private Hospital hospital;
	
	@BeforeEach
	void beforeEach() {
		hospital = new Hospital("Name", "Description");
	}
	
	@Test
	void testGetName() {
		
		assertEquals("Name", hospital.getName());
		
	}
	
	@Test
	void testGetFlavourText() {
		
		assertEquals("Description", hospital.getFlavourText());
		
	}
	
	@Test
	void testGetType() {
		
		assertEquals(AreaType.HOSPITAL, hospital.getType());
		
	}

}
