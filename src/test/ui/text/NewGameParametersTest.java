package test.ui.text;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import game.Team;
import game.ui.text.NewGameParameters;

class NewGameParametersTest {
	
	private static NewGameParameters params_1;
	private static NewGameParameters params_2;

	@BeforeAll
	static void beforeAll() {
		
		params_1 = new NewGameParameters(3, new Team("Team 1"));
		params_2 = new NewGameParameters(6, new Team("Team 2"));
		
	}
	
	@Test
	void testConstructor() {

		assertThrows(IllegalArgumentException.class, () -> new NewGameParameters(2, new Team("")));
		assertThrows(IllegalArgumentException.class, () -> new NewGameParameters(7, new Team("")));
		assertThrows(NullPointerException.class, () -> new NewGameParameters(6, null));
		
	}

	@Test
	void testGetCityCount() {
		
		assertEquals(3, params_1.getCityCount());
		assertEquals(6, params_2.getCityCount());
		
	}

	@Test
	void testGetTeam() {
		
		assertEquals("Team 1", params_1.getTeam().getName());
		assertEquals("Team 2", params_2.getTeam().getName());
		
	}

}
