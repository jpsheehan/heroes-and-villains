package game.ui.text;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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

}
