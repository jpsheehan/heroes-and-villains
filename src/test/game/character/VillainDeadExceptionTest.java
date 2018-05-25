package test.game.character;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.character.Villain;
import game.character.VillainDeadException;
import game.minigame.MinigameType;

class VillainDeadExceptionTest {

	VillainDeadException e;
	
	@BeforeEach
	void beforeEach() {
		e = new VillainDeadException(new Villain("Bob", "Taunt", MinigameType.GUESS_THE_NUMBER), 10);
	}
	
	@Test
	void testGetVillain() {
		
		assertEquals("Bob", e.getVillain().getName());
		
	}
	
	@Test
	void testGetReward() {
		
		assertEquals(10, e.getReward());
		
	}

}
