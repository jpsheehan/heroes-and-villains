package test.game.city;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.city.Direction;
import game.city.IllegalMoveException;

class IllegalMoveExceptionTest {

	IllegalMoveException e;
	
	@BeforeEach
	void beforeEach() {
		
		e = new IllegalMoveException(Direction.EAST, Direction.SOUTH);
		
	}
	
	@Test
	void testCurrentDirection() {
		
		assertEquals(Direction.EAST, e.getCurrentDirection());
		
	}
	
	@Test
	void testFailedDirection() {
		
		assertEquals(Direction.SOUTH, e.getFailedDirection());
		
	}

}
