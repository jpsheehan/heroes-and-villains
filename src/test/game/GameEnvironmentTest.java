package test.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.GameEnvironment;
import game.Team;
import game.city.CityController;
import game.ui.gui.GraphicalUserInterface;
import game.ui.text.TextUserInterface;
import game.ui.textgui.TextGraphicalUserInterface;

class GameEnvironmentTest {

	@Test
	void testCityController() {
		
		GameEnvironment env = new GameEnvironment(TextUserInterface.class);
		CityController cc = new CityController(3);
		
		env.setCityController(cc);
		assertEquals(cc, env.getCityController());
		
	}
	
	@Test
	void testTeam() {
		
		GameEnvironment env = new GameEnvironment(GraphicalUserInterface.class);
		Team team = new Team("Team Name");
		
		env.setTeam(team);
		assertEquals(team, env.getTeam());
		
	}
	
	@Test
	void testUserInterface() {
		
		GameEnvironment env = new GameEnvironment(TextGraphicalUserInterface.class);
		
		assertEquals(TextGraphicalUserInterface.class, env.getUserInterface().getClass());
		
	}
	
	@Test
	void testConstructor() {
		
		assertThrows(IllegalArgumentException.class, () -> new GameEnvironment(Object.class));
		
	}

}
