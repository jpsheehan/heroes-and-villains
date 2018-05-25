package test.game.city;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.character.Villain;
import game.city.AreaType;
import game.city.VillainsLair;
import game.minigame.MinigameType;

class VillainsLairTest {

	private VillainsLair villainsLair;
	
	@BeforeEach
	void beforeEach() {
		villainsLair = new VillainsLair("Name", "Description", new Villain("Bob", "Taunt", MinigameType.ALL));
	}
	
	@Test
	void testGetName() {
		
		assertEquals("Name", villainsLair.getName());
		
	}
	
	@Test
	void testGetFlavourText() {
		
		assertEquals("Description", villainsLair.getFlavourText());
		
	}
	
	@Test
	void testGetType() {
		
		assertEquals(AreaType.VILLAINS_LAIR, villainsLair.getType());
		
	}
	
	@Test
	void testGetVillain() {
		
		assertEquals("Bob", villainsLair.getVillain().getName());
		
	}
	
	@Test
	void testGetBattleScreen() {
		
		assertNotNull(villainsLair.getBattleScreen());
		
	}

}
