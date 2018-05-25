package test.game.city;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.character.Dialogue;
import game.character.InnKeeper;
import game.city.AreaType;
import game.city.CityType;
import game.city.Shop;
import game.item.Item;
import game.item.Map;

class ShopTest {

	Shop shop;
	
	@BeforeEach
	void beforeEach() {
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Map("Map", "Description", 0));
		shop = new Shop("Name", "Flavour", items, new InnKeeper("Steve", new Dialogue(CityType.ENG_CORE)));
	}
	
	@Test
	void testGetName() {
		
		assertEquals("Name", shop.getName());
		
	}
	
	@Test
	void testGetFlavourText() {
		
		assertEquals("Flavour", shop.getFlavourText());
		
	}
	
	@Test
	void testGetType() {
		
		assertEquals(AreaType.SHOP, shop.getType());
		
	}
	
	@Test
	void testGetInnKeeper() {
		
		assertEquals("Steve", shop.getInnKeeper().getName());
		
	}
	
	@Test
	void testGetInventory() {
		
		assertEquals(0, shop.getInventory().size());
		
	}

}
