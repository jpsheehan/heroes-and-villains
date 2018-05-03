package test.game.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.item.ItemAbility;
import game.item.PowerUpItem;
import game.minigame.MinigameType;

class PowerUpItemTest {

	@Test
	void testPowerUpItem() {
		
		new PowerUpItem("Cool PowerUpItem", "it's pretty", 10, ItemAbility.DAMAGE_PROTECTION, MinigameType.DICE_ROLLS);

		assertThrows(NullPointerException.class, () -> new PowerUpItem("Cool PowerUpItem", "it's pretty", 10, null, MinigameType.DICE_ROLLS));
		assertThrows(NullPointerException.class, () -> new PowerUpItem("Cool PowerUpItem", "it's pretty", 10, ItemAbility.DECREASE_ROBBERY_CHANCE, null));
		
	}
	
	@Test
	void testGetAbility() {
		
		PowerUpItem item = new PowerUpItem("Cool PowerUpItem", "it's pretty", 10, ItemAbility.DAMAGE_PROTECTION, MinigameType.DICE_ROLLS);
		assertEquals(ItemAbility.DAMAGE_PROTECTION, item.getAbility());
		
	}
	
	@Test
	void testGetAppliesTo() {
		
		PowerUpItem item = new PowerUpItem("Cool PowerUpItem", "it's pretty", 10, ItemAbility.DAMAGE_PROTECTION, MinigameType.DICE_ROLLS);
		assertEquals(MinigameType.DICE_ROLLS, item.getAppliesTo());
		
	}

}
