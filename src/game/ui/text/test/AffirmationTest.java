package game.ui.text.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.ui.text.Affirmation;

class AffirmationTest {

	@Test
	void testFromString() {

		assertEquals(Affirmation.YES, Affirmation.fromString("YES"));
		assertEquals(Affirmation.YES, Affirmation.fromString("yes"));
		assertEquals(Affirmation.YES, Affirmation.fromString("y"));
		assertEquals(Affirmation.YES, Affirmation.fromString("yeah"));
		assertEquals(Affirmation.YES, Affirmation.fromString("yup"));
		assertEquals(Affirmation.YES, Affirmation.fromString("1"));

		assertEquals(Affirmation.NO, Affirmation.fromString("NO"));
		assertEquals(Affirmation.NO, Affirmation.fromString("no"));
		assertEquals(Affirmation.NO, Affirmation.fromString("n"));
		assertEquals(Affirmation.NO, Affirmation.fromString("nope"));
		assertEquals(Affirmation.NO, Affirmation.fromString("nah"));
		assertEquals(Affirmation.NO, Affirmation.fromString("0"));

		assertEquals(Affirmation.UNKNOWN, Affirmation.fromString("Maybe"));
		assertEquals(Affirmation.UNKNOWN, Affirmation.fromString("nopes"));
		assertEquals(Affirmation.UNKNOWN, Affirmation.fromString("heck no"));
		assertEquals(Affirmation.UNKNOWN, Affirmation.fromString("yarp"));
		
	}

}
