package test.game.character;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.character.Villain;
import game.character.VillainDeadException;
import game.minigame.MinigameType;

class VillainTest {
	
	private Villain villain;

	@BeforeEach
	void beforeEach() {
		
		villain = new Villain("Name", "Taunt", MinigameType.ALL);
		
	}
	
	@Test
	void testGetTaunt() {
		
		assertEquals("Taunt", villain.getTaunt());
		
	}
	
	@Test
	void testGetName() {
		
		assertEquals("Name", villain.getName());
		
	}
	
	@Test
	void testGetFavouriteGame() {
		
		assertEquals(MinigameType.ALL, villain.getFavouriteGame());
		
	}
	
	@Test
	void testBeat() throws VillainDeadException {
		
		assertEquals(3, villain.getNumberOfWinsToDefeat());
		
		villain.beat();
		assertEquals(2, villain.getNumberOfWinsToDefeat());
		
		villain.beat();
		assertEquals(1, villain.getNumberOfWinsToDefeat());

		villain.beat();
		assertEquals(0, villain.getNumberOfWinsToDefeat());
		
		villain.beat();
		assertEquals(0, villain.getNumberOfWinsToDefeat());
		
	}
	
	@Test
	void testGetNumberOfRequiredWins() {
		
		assertEquals(3, Villain.getNumberOfRequiredWins());
		
	}

}
