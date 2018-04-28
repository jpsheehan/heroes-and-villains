package game.ui.text.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import game.ui.text.TextUserInterfaceHelpers;
import game.ui.text.UserCancelException;
import game.ui.text.UserContinueException;
import game.ui.text.UserQuitException;
import static game.ui.text.test.StreamHelpers.*;

class TextUserInterfaceHelpersTest extends TextUserInterfaceHelpers {
	
	@BeforeEach
	void beforeEach() {
		setupOutputStream();
	}
	
	@AfterEach
	void afterEach() {
		restoreStreams();
	}
	
	
	@Test
	void testSetConsoleWidth() {
		
		setConsoleWidth(40);
		assertEquals(40, getConsoleWidth());

		assertThrows(IllegalArgumentException.class, () -> setConsoleWidth(-1));
		assertThrows(IllegalArgumentException.class, () -> setConsoleWidth(0));
		
		setConsoleWidth(80);
		assertEquals(80, getConsoleWidth());
		
	}
	
	@Test
	void testSetConsoleHeight() {
		
		setConsoleHeight(10);
		assertEquals(10, getConsoleHeight());

		assertThrows(IllegalArgumentException.class, () -> setConsoleHeight(-1));
		assertThrows(IllegalArgumentException.class, () -> setConsoleHeight(0));
		
		setConsoleHeight(25);
		assertEquals(25, getConsoleHeight());
		
	}
	
	@Test
	void testPrintHorizontalRule() {
		
		setConsoleWidth(10);
		
		printHorizontalRule();
		assertEquals("=========\n", getOutputStream());
		
		printHorizontalRule('@');
		assertEquals("@@@@@@@@@\n", getOutputStream());
		
		setConsoleWidth(80);
		
	}
	
	@Test
	void testPrintLineCentred() {
		
		setConsoleWidth(3);
		printLineCentred("J");
		assertEquals(" J \n", getOutputStream());
		
		setConsoleWidth(4);
		printLineCentred("J");
		assertEquals(" J \n", getOutputStream());
		
		setConsoleWidth(80);
		
	}
		
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
	
	@Test
	void testGetNumberWithBounds() throws UserCancelException, UserQuitException, UserContinueException {
		
		setInputStream("1\n");
		assertEquals(new Integer(1), getNumberWithBounds(1, 10));
		
		setInputStream("10\n");
		assertEquals(new Integer(10), getNumberWithBounds(1, 10));
		
		setInputStream("0500\n");
		assertEquals(new Integer(500), getNumberWithBounds(250, 750));
		
		assertThrows(IllegalArgumentException.class, () -> getNumberWithBounds(10, 1));
		
		setInputStream("What?!\n5\n");
		assertEquals(new Integer(5), getNumberWithBounds(1, 10));
		
		setInputStream("Really?\nNot a number!\n7\n");
		assertEquals(new Integer(7), getNumberWithBounds(1, 10));
		
	}
	
	@Test
	void testReadLine() throws UserCancelException, UserQuitException, UserContinueException {
		
		setInputStream("Hello, World!\n");
		assertEquals("Hello, World!", readLine());
		
		setInputStream("\n");
		assertEquals("", readLine());
		
		setInputStream("1");
		assertEquals("1", readLine());
		
		setInputStream("");
		assertEquals("", readLine());
		
		setInputStream("First Line\nSecond Line\nThird Line");
		assertEquals("First Line", readLine());
		assertEquals("Second Line", readLine());
		assertEquals("Third Line", readLine());
		
		setInputStream("c\n");
		assertThrows(UserCancelException.class, () -> readLine());
		
		setInputStream("q\ny");
		assertThrows(UserQuitException.class, () -> readLine());
		
		setInputStream("q\nn");
		assertThrows(UserContinueException.class, () -> readLine());
		
		setInputStream("First Line\nSecond Line");
		assertEquals("First Line", readLine());
		setInputStream("Cool Beans!");
		assertEquals("Cool Beans!", readLine());
		
	}
	
	@Test
	void testShowChoice() throws UserCancelException, UserQuitException {
		
		String[] options = new String[] {
			"One", "Two", "Three"
		};
		
		setInputStream("1\n");
		assertEquals(0, showChoice("Test Choice 1:", options));
		
		setInputStream("q\nn\n2");
		assertEquals(1, showChoice("Test Choice 2:", options));
		
	}
	
	@Test
	void testShowYesNo() throws UserQuitException, UserCancelException {
		
		setInputStream("y\n");
		assertTrue(showYesNo("Test 1"));
		
		setInputStream("yes\n");
		assertTrue(showYesNo("Test 2"));
		
		setInputStream("n\n");
		assertFalse(showYesNo("Test 3"));
		
		setInputStream("no\n");
		assertFalse(showYesNo("Test 4"));
		
		setInputStream("???\nyes");
		assertTrue(showYesNo("Test 5"));
		
		setInputStream("q\ny\n");
		assertThrows(UserQuitException.class, () -> showYesNo("Test 6"));
		
		setInputStream("q\ny\n");
		assertFalse(showYesNo("Test 9", true));
		
		setInputStream("q\nn\ny");
		assertTrue(showYesNo("Test 7"));
		
		setInputStream("c\n");
		assertThrows(UserCancelException.class, () -> showYesNo("Test 8"));
		
		setInputStream("c\n");
		assertFalse(showYesNo("Test 10", true));
		
		setInputStream("???\nc\n");
		assertThrows(UserCancelException.class, () -> showYesNo("Test 11"));
		
		setInputStream("???\nc\n");
		assertFalse(showYesNo("Test 12", true));
		
		setInputStream("???\nq\ny\n");
		assertThrows(UserQuitException.class, () -> showYesNo("Test 13"));
		
		setInputStream("???\nq\ny\n");
		assertFalse(showYesNo("Test 15", true));
		
		setInputStream("???\nq\nn\ny");
		assertTrue(showYesNo("Test 14"));
	}
	
}
