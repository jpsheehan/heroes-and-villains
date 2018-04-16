package game.character.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import game.character.Hero;
import game.character.HeroAbility;
import game.character.HeroType;

import org.junit.jupiter.api.BeforeEach;


class HeroTest {
	
	private Hero hero_1, hero_2, hero_3, hero_4, hero_5, hero_6, hero_7;
	
	@BeforeEach
	void setup() {
		hero_1 = new Hero("Sir Bob the Brave", HeroType.MATHS_STUDENT);
		hero_2 = new Hero("Hero 2", HeroType.ARTS_STUDENT);
		hero_3 = new Hero("Hero 3", HeroType.COMMERCE_STUDENT);
		hero_4 = new Hero("Hero 4", HeroType.COMPUTER_SCIENCE_STUDENT);
		hero_5 = new Hero("Hero 5", HeroType.ENGINEERING_STUDENT);
		hero_6 = new Hero("Hero 6", HeroType.LAW_STUDENT);
		hero_7 = new Hero("Hero 7", HeroType.SCIENCE_STUDENT);
	}

	@Test
	void testGetType() {
		assertEquals(HeroType.MATHS_STUDENT, hero_1.getType());
		assertEquals(HeroType.ARTS_STUDENT, hero_2.getType());
		assertEquals(HeroType.COMMERCE_STUDENT, hero_3.getType());
		assertEquals(HeroType.COMPUTER_SCIENCE_STUDENT, hero_4.getType());
		assertEquals(HeroType.ENGINEERING_STUDENT, hero_5.getType());
		assertEquals(HeroType.LAW_STUDENT, hero_6.getType());
		assertEquals(HeroType.SCIENCE_STUDENT, hero_7.getType());
	}

	@Test
	void testGetAbility() {
		assertEquals(HeroAbility.IMPROVED_ODDS, hero_1.getAbility());
		assertEquals(HeroAbility.WITTY_PHRASES, hero_2.getAbility());
		assertEquals(HeroAbility.CHEAPER_ITEMS, hero_3.getAbility());
		assertEquals(HeroAbility.TELEPORT, hero_4.getAbility());
		assertEquals(HeroAbility.VILLAINS_LESS_20_HEALTH, hero_5.getAbility());
		assertEquals(HeroAbility.PREVENTS_ROBBERY, hero_6.getAbility());
		assertEquals(HeroAbility.INCREASED_RECOVERY_RATE, hero_7.getAbility());
	}

	@Test
	void testGetRecoveryRate() {
		
		fail("Not yet implemented");
		
	}

	@Test
	void testGetHealth() {
	
		fail("Not yet implemented");
	}

	@Test
	void testGetName() {
		assertEquals("Sir Bob the Brave", hero_1.getName());
		assertEquals("Hero 2", hero_2.getName());
		assertEquals("Hero 3", hero_3.getName());
		assertEquals("Hero 4", hero_4.getName());
		assertEquals("Hero 5", hero_5.getName());
		assertEquals("Hero 6", hero_6.getName());
		assertEquals("Hero 7", hero_7.getName());
	}

}
