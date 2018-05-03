package test.game.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.item.HealingItem;

class HealingItemTest {

	@Test
	void testHealingItem() {
		
		new HealingItem("Name", "Description", 5, 1, 1);
		new HealingItem("Name", "Description", 5, 2, 1);
		new HealingItem("Name", "Description", 5, 3, 2000);
		new HealingItem("Name", "Description", 5, 4, 10);
		
		assertThrows(IllegalArgumentException.class, () -> new HealingItem("Name", "Description", 5, 0, 1));
		assertThrows(IllegalArgumentException.class, () -> new HealingItem("Name", "Description", 5, 5, 1));
		assertThrows(IllegalArgumentException.class, () -> new HealingItem("Name", "Description", 5, 2, 0));
		assertThrows(IllegalArgumentException.class, () -> new HealingItem("Name", "Description", 5, 0, -100));
		
	}
	
	@Test
	void testGetResorationLevel() {
		
		HealingItem item = new HealingItem("Name", "Description", 5, 2, 1);
		assertEquals(2, item.getRestorationLevel());
		
	}
	
	@Test
	void testGetApplicationTime() {
		
		HealingItem item = new HealingItem("Name", "Description", 5, 3, 100);
		assertEquals(100, item.getApplicationTime());
		
	}

}
