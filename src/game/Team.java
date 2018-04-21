package game;
import java.util.ArrayList;

import game.character.Hero;

public class Team implements Nameable {

	/**
	 * The name of the team.
	 */
	private String name;
	
	/**
	 * The list of heroes.
	 */
	private ArrayList<Hero> heroes;
	
	/**
	 * The amount of money the heroes have.
	 */
	private int money;
	
	/**
	 * Creates a new Team.
	 * @param name The name of the team.
	 */
	public Team(String name) {
		
		// implement constraint (section 2.1.1)
		if (name.length() <= 2 || name.length() >= 10) {
			throw new IllegalArgumentException("name must be between 2 and 10 characters long.");
		}
		
		this.name = name;
		this.heroes = new ArrayList<Hero>();
		
	}
	
	/**
	 * Returns the list of heroes.
	 * @return
	 */
	public ArrayList<Hero> getHeroes() {
		
		return this.heroes;
		
	}
	
	/**
	 * Adds a new hero to the list.
	 * @param hero The hero to add.
	 * @throws TeamFullException
	 */
	public void addHero(Hero hero) throws TeamFullException {
		
		// implement constraint (section 2.1.3)
		if (this.heroes.size() >= 3) {
			
			throw new TeamFullException("Cannot add this Hero, there are too many Heroes in the Team.");
			
		}
		
		// make sure that the new Hero has a unique name
		for (Hero h : this.heroes) {
			
			if (hero.getName().equals(h.getName())) {
				
				throw new IllegalArgumentException("Could not add this Hero, Hero shares a name with another.");
				
			}
			
		}
		
		this.heroes.add(hero);
	}
	
	/**
	 * Remove a hero from the list.
	 * @param hero The hero to remove.
	 */
	public void removeHero(Hero hero) {
		
		if (!this.heroes.remove(hero)) {
			
			throw new IllegalArgumentException("Hero does not exist in the Team.");
			
		}
		
	}
	
	/**
	 * Remove a hero by its name.
	 * @param heroName The name of the hero to remove.
	 */
	public void removeHero(String heroName) {
		
		for (Hero hero : this.heroes) {
			
			if (hero.getName().equals(heroName)) {
				
				this.heroes.remove(hero);
				
			}
			
		}
		
		throw new IllegalArgumentException("Hero does not exist in the Team");
		
	}
	
	/**
	 * Returns the name of the team.
	 */
	@Override
	public String getName() {
		
		return this.name;
		
	}
	
	/**
	 * Returns the amount of money the heroes have.
	 * @return
	 */
	public int getMoney() {
		
		return this.money;
		
	}
	
	/**
	 * Gives the team an amount of money.
	 * @param amount
	 */
	public void giveMoney(int amount) {
		
		if (amount < 0 ) {
			
			throw new IllegalArgumentException("Amount cannot be less than zero.");
			
		}
		
		this.money += amount;
		
	}
	
	/**
	 * Removes some amount of money from the team's money count.
	 * @param amount
	 */
	public void spendMoney(int amount) {
		
		if (amount < 0) {
			
			throw new IllegalArgumentException("Amount cannot be less than zero.");
			
		}
		
		if (amount > this.money) {
			
			throw new IllegalArgumentException("Amount cannot be greater than the amount of money the team has.");
			
		}
		
		this.money -= amount;
		
	}
}
