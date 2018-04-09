package game.ui.text.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.ui.text.TextUserInterfaceHelpers;

class TextUserInterfaceHelpersTest extends TextUserInterfaceHelpers {

	@Test
	void testRepeatString() {
		
		String str = "Hi";
		
		assertEquals(repeatString(str, -1), "");
		
		assertEquals(repeatString(str, 0), "");
		
		assertEquals(repeatString(str, 1), "Hi");
		
		assertEquals(repeatString(str, 2), "HiHi");
		
		assertEquals(repeatString(str, 5), "HiHiHiHiHi");
		
	}
	
	@Test
	void testPadLeft() {
		
		assertEquals(padLeft("1", ' ', 4), "   1");
		
		assertEquals(padLeft("7", '0', 3), "007");
		
		assertEquals(padLeft("> Important!", '=', 20), "========> Important!");
		
	}
	
	@Test
	void testPadRight() {
		
		assertEquals(padRight("It's Over 9", '0', 14), "It's Over 9000");
		
	}

}
