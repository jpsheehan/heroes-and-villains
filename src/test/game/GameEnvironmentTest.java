package test.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.GameEnvironment;
import game.Team;
import game.city.CityController;

class GameEnvironmentTest {

	@Test
	void testCityController() {
		
		GameEnvironment env = new GameEnvironment();
		CityController cc = new CityController(3);
		
		env.setCityController(cc);
		assertEquals(cc, env.getCityController());
		
	}
	
	@Test
	void testTeam() {
		
		GameEnvironment env = new GameEnvironment();
		Team team = new Team("Team Name");
		
		env.setTeam(team);
		assertEquals(team, env.getTeam());
		
	}

}
