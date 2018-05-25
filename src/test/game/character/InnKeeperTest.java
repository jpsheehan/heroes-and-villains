package test.game.character;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import game.character.Dialogue;
import game.character.InnKeeper;
import game.city.CityType;

import org.junit.jupiter.api.Test;

class InnKeeperTest {

	private InnKeeper innKeeper;
	
	@BeforeEach
	void beforeEach() {
		innKeeper = new InnKeeper("Steve", new Dialogue(CityType.ENG_CORE));
	}
	
	@Test
	void testGetName() {

		assertEquals("Steve", innKeeper.getName());
		
	}
	
	@Test
	void testGetDialogue() {

		assertEquals("Nuts and Bolts you are", innKeeper.getDialogue().getGreeting());
		
	}

}
