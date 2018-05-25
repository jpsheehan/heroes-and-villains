package test.game.item;

import org.junit.jupiter.api.Test;

import game.item.Map;

class MapTest {

	Map map;
	
	@Test
	void beforeEach() {
		
		map = new Map("Old map", "It's torn", 10);
		
	}

}
