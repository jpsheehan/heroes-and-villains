package test.game.character;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.character.Hero;
import game.character.HeroDeadException;
import game.character.HeroType;

class HeroDeadExceptionTest {
	
	private HeroDeadException e;
	
	@BeforeEach
	public void beforeEach() {
		e = new HeroDeadException(new Hero("Steve", HeroType.ARTS_STUDENT));
	}

	@Test
	void testGetHero() {
		
		assertEquals("Steve", e.getHero().getName());
		
	}

}
