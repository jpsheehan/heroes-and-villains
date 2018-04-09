package game.ui.text.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;

import game.ui.text.TextUserInterfaceHelpers;

class TextUserInterfaceHelpersTest extends TextUserInterfaceHelpers {
	
	private ByteArrayOutputStream outputStream;
	private ByteArrayInputStream inputStream;
	

	@Rule
	private final ExpectedException exception = ExpectedException.none();
	
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
	void testClear() {
		
		clear();
		
		assertEquals("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n", outputStream.toString());
		
	}
	
	@Test
	void testGetNumberWithBounds() {
		
		setInputStream("1\n");
		
		assertEquals(new Integer(1), getNumberWithBounds(1, 10));
		
		setInputStream("10\n");
		
		assertEquals(new Integer(10), getNumberWithBounds(1, 10));
		
		setInputStream("0500\n");
		
		assertEquals(new Integer(500), getNumberWithBounds(250, 750));
		
		assertThrows(IllegalArgumentException.class, () -> getNumberWithBounds(10, 1));
		
	}
	
	@Test
	void testReadLine() {
		
		setInputStream("Hello, World!\n");
		assertEquals("Hello, World!", readLine());
		
		setInputStream("\n");
		assertEquals("", readLine());
		
		setInputStream("1");
		assertEquals("1", readLine());
		
		setInputStream("");
		assertEquals("", readLine());
		
	}
	
	@Test
	void testShowChoice() {
		
		String[] options = new String[] {
			"One", "Two", "Three"
		};
		
		setInputStream("1\n");
		
		assertEquals(0, showChoice("Test Choice 1:", options));
		
	}
	
	@Test
	void testShowYesNo() {
		
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
