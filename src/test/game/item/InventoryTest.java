package test.game.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.item.HealingItem;
import game.item.Inventory;
import game.item.ItemAbility;
import game.item.Map;
import game.item.PowerUpItem;
import game.minigame.MinigameType;

class InventoryTest {
	
	private Map map;
	private PowerUpItem powerUpItem;
	private HealingItem healingItem;
	
	public InventoryTest() {
		
		map = new Map("Old Map", "A dusty old map.", 10);
		healingItem = new HealingItem("Healing Potion", "It's red!", 10, 3, 3);
		powerUpItem = new PowerUpItem("Lucky Potion", "It's green!", 10, ItemAbility.INCREASE_GIFT_CHANCE, MinigameType.ALL);
		
	}

	@Test
	void testAdd() {
		
		Inventory inventory = new Inventory();
		assertEquals(0, inventory.size());
		
		assertEquals(0, inventory.getMaps().length);
		assertEquals(0, inventory.getHealingItems().length);
		assertEquals(0, inventory.getPowerUpItems().length);
		
		inventory.add(map);
		
		assertEquals(1, inventory.getMaps().length);
		assertEquals(0, inventory.getHealingItems().length);
		assertEquals(0, inventory.getPowerUpItems().length);
		assertEquals(map, inventory.getMaps()[0]);
		
		inventory.add(healingItem);
		
		assertEquals(1, inventory.getMaps().length);
		assertEquals(1, inventory.getHealingItems().length);
		assertEquals(0, inventory.getPowerUpItems().length);
		assertEquals(healingItem, inventory.getHealingItems()[0]);

		inventory.add(powerUpItem);
		
		assertEquals(1, inventory.getMaps().length);
		assertEquals(1, inventory.getHealingItems().length);
		assertEquals(1, inventory.getPowerUpItems().length);
		assertEquals(powerUpItem, inventory.getPowerUpItems()[0]);
		
		assertEquals(3, inventory.size());
		
		assertThrows(NullPointerException.class, () ->  inventory.add(null));
		assertThrows(IllegalArgumentException.class, () -> inventory.add(map));
		assertThrows(IllegalArgumentException.class, () -> inventory.add(healingItem));
		assertThrows(IllegalArgumentException.class, () -> inventory.add(powerUpItem));
		
	}
	
	@Test
	void testRemove() {
		
		Inventory inventory = new Inventory();
		inventory.add(map);
		inventory.add(healingItem);
		inventory.add(powerUpItem);

		inventory.remove(map);
		assertEquals(0, inventory.getMaps().length);
		assertThrows(IllegalArgumentException.class, () -> inventory.remove(map));
		
		inventory.remove(healingItem);
		assertEquals(0, inventory.getHealingItems().length);
		assertThrows(IllegalArgumentException.class, () -> inventory.remove(healingItem));
		
		inventory.remove(powerUpItem);
		assertEquals(0, inventory.getPowerUpItems().length);
		assertThrows(IllegalArgumentException.class, () -> inventory.remove(powerUpItem));
		
		assertThrows(NullPointerException.class, () -> inventory.remove(null));

		assertEquals(0, inventory.size());
		
	}

}
