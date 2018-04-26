package game.ui.text.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import game.ui.text.TextUserInterfaceHelpers;
import game.ui.text.UserCancelException;
import game.ui.text.UserContinueException;
import game.ui.text.UserQuitException;

class TextUserInterfaceHelpersTest extends TextUserInterfaceHelpers {
	
	private ByteArrayOutputStream outputStream;
	private ByteArrayInputStream inputStream;
	
	
	@BeforeEach
	void setupOutputStream() {
		outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
	}
	
	@AfterEach
	void restoreStreams() {
		System.setOut(System.out);
		System.setIn(System.in);
	}
	
	void setInputStream(String input) {
		inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);
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
		try {
			readLine();
			assertFalse(true, "Should have caught a UserCancelException");
		} catch (UserCancelException e) {
			assertTrue(true, "Caught the UserCancelException.");
		} catch (Exception e) {
			assertTrue(false, "Caught some other Exception.");
		}
		
		setInputStream("q\ny");
		try {
			readLine();
			assertFalse(true, "Did not catch the UserQuitException.");
		} catch (UserQuitException e) {
			assertTrue(true, "Caught the UserQuitException.");
		} catch (Exception e) {
			assertTrue(false, "Caught some other Exception.");
		}
		
		setInputStream("q\nn");
		try {
			readLine();
			assertFalse(true, "Did not catch the userCancelException.");
		} catch (UserContinueException e) {
			assertTrue(true, "Caught the UserCancelException.");
		} catch (Exception e) {
			assertTrue(false, "Caught some other Exception.");
		}
		
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
		
	}
	
}
