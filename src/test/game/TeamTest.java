package test.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import game.Settings;
import game.Team;
import game.TeamFullException;
import game.character.Hero;
import game.character.HeroType;

class TeamTest {
	
	private static Team team_1;
	private static Team team_2;
	
	@BeforeAll
	static void beforeAll() throws TeamFullException {
		
		team_1 = new Team("Team Name!");
		team_2 = new Team("ok");
		
		Hero bob = new Hero("Bob", HeroType.COMMERCE_STUDENT);
		
		team_1.addHero(new Hero("Alice", HeroType.ARTS_STUDENT));
		assertThrows(IllegalArgumentException.class, () -> team_1.addHero(new Hero("Alice", HeroType.COMPUTER_SCIENCE_STUDENT)));
		team_1.addHero(bob);
		team_1.addHero(new Hero("Charlie", HeroType.LAW_STUDENT));
		assertThrows(TeamFullException.class, () -> team_1.addHero(new Hero("Eve", HeroType.MATHS_STUDENT)));
		
		assertEquals(3, team_1.getHeroes().length);
		assertEquals(0, team_2.getHeroes().length);
		
		assertThrows(IllegalArgumentException.class, () -> team_2.removeHero(bob));
		assertThrows(IllegalArgumentException.class, () -> team_1.removeHero("Eve"));
		
		assertEquals(3, team_1.getHeroes().length);
		assertEquals(0, team_2.getHeroes().length);
		
		team_1.removeHero("Alice");
		team_1.removeHero(bob);
		
		assertEquals(1, team_1.getHeroes().length);
		assertEquals("Charlie", team_1.getHeroes()[0].getName());
	}

	@Test
	void testTeam() {

		assertThrows(IllegalArgumentException.class, () -> new Team(""));
		assertThrows(IllegalArgumentException.class, () -> new Team("1"));
		assertThrows(IllegalArgumentException.class, () -> new Team("Team Name 1"));
		
	}
	
	@Test
	void testGetName() {
		
		assertEquals("Team Name!", team_1.getName());
		assertEquals("ok", team_2.getName());
		
	}

	@Test
	void testMoney() {
		
		int startMoney = Settings.getTeamStartMoney();
		
		team_1.giveMoney(20);
		assertThrows(IllegalArgumentException.class, () -> team_1.giveMoney(-10));
		assertEquals(startMoney + 20, team_1.getMoney());
		
		assertEquals(startMoney, team_2.getMoney());
		
		team_1.spendMoney(5);
		assertEquals(startMoney + 15, team_1.getMoney());
		
		team_2.giveMoney(49);
		assertThrows(IllegalArgumentException.class, () -> team_2.spendMoney(100));
		assertEquals(startMoney + 49, team_2.getMoney());
		
		team_2.giveMoney(1);
		team_2.spendMoney(50);
		assertEquals(startMoney, team_2.getMoney());
		
	}

}
