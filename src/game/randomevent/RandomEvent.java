package game.randomevent;

import java.io.Serializable;

import game.GeneralHelpers;
import game.Team;
import game.character.Hero;
import game.character.HeroType;
import game.item.Item;

/**
 * Represents a random event such as the Team getting robbed or gifted an item.
 * @author jesse
 */
public class RandomEvent implements Serializable {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -6349351122715786991L;

	/**
	 * The probability that any event occurs when entering a new City.
	 */
	private static float probability = 0.4f;
	
	/**
	 * Whether or not an event actually took place.
	 */
	private boolean occurred;
	
	/**
	 * Indicates whether or not the event has been performed.
	 */
	private boolean performed;
	
	/**
	 * The type of event that occurred (or null).
	 */
	private RandomEventType type;
	
	/**
	 * Creates a new instance of RandomEvent.
	 * Also calculates whether or not the event happens.
	 */
	public RandomEvent() {
		
		this.performed = false;
		
		this.occurred = (GeneralHelpers.getRandom().nextInt(100) <= probability * 100f);
		
		if (occurred()) {
			
			if (GeneralHelpers.getRandom().nextBoolean()) {
				
				type = RandomEventType.GIFT;
				
			} else {
				
				type = RandomEventType.ROBBERY;
				
			}
			
		} else {
			
			type = null;
			
		}
		
	}
	
	/**
	 * @return True if any event occurred. False otherwise.
	 */
	public boolean occurred() {
		
		return this.occurred;
		
	}
	
	/**
	 * @return The type of random event that occurred. Or null if no event occurred.
	 */
	public RandomEventType getEventType() {
		
		return type;
		
	}
	
	/**
	 * Performs the random event.
	 * @throws RobberyPreventedException When a law student is in the team and the team was robbed.
	 * @throws MoneyRobbedException When the team has no items to rob.
	 * @throws ItemRobbedException When the team is robbed.
	 * @throws ItemGiftedException When the team is gifted an item.
	 */
	public void performEvent(Team team) throws RobberyPreventedException, MoneyRobbedException, ItemRobbedException, ItemGiftedException {
		
		if (performed) {
			throw new AssertionError("Cannot perform the same random event twice (because then it's not random, but constant)");
		}
		
		performed = true;
		
		if (type == null) {
			
			return;
			
		}
		
		switch (type) {
		
			case GIFT:
				performGifting(team);
				break;
				
			case ROBBERY:
				performRobbery(team);
				break;
				
		}
		
	}
	
	/**
	 * Performs the robbery.
	 * @throws RobberyPreventedException When a law student is on the team.
	 * @throws MoneyRobbedException When the team has no items in their inventory.
	 * @throws ItemRobbedException When an item is taken from the team.
	 */
	private void performRobbery(Team team) throws RobberyPreventedException, MoneyRobbedException, ItemRobbedException {
		
		Hero lawStudent = null;
		
		// check if there is a law student in the team
		for (Hero hero : team.getHeroes()) {
			if (hero.getType() == HeroType.LAW_STUDENT && hero.isAlive()) {
				lawStudent = hero;
				break;
			}
		}
		
		if (team.getInventory().getAllItems().length == 0) {
			
			// if the team has no items then take half of their money instead.
			
			if (team.getMoney() == 0) {
				
				// if the team has no money either, do nothing.
				return;
				
			}
			
			int moneyTaken = team.getMoney() / 2;
			
			if (lawStudent != null) {
				
				// not implementing this exception class or anything because we don't want to be too pedantic.
				// just pretend that the team never got robbed in the first place!
				// throw new MoneyRobberyPreventedException(lawStudent, moneyTaken);
				return;
				
			}
			
			team.spendMoney(moneyTaken);
			throw new MoneyRobbedException(moneyTaken);
			
		}
		
		// select a random item from the team's inventory.
		Item itemToRemove = team.getInventory().getAllItems()[GeneralHelpers.getRandom().nextInt(team.getInventory().size())];

		if (lawStudent != null) {
			throw new RobberyPreventedException(lawStudent, itemToRemove);
		}
		
		// remove it
		team.getInventory().remove(itemToRemove);
		throw new ItemRobbedException(itemToRemove);
		
	}
	
	/**
	 * Attempts to gift the team an item.
	 * @throws ItemGiftedException When the team is gifted an item.
	 */
	private void performGifting(Team team) throws ItemGiftedException {
		
		// select a random item from the item pool
		Item[] itemPool = GeneralHelpers.getItemPool();
		
		if (itemPool.length > 0) {
			
			Item selectedItem = itemPool[GeneralHelpers.getRandom().nextInt(itemPool.length)];
			team.getInventory().add(selectedItem);
			throw new ItemGiftedException(selectedItem);
			
		}
		
	}
	
}
