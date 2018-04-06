package game;
import java.util.ArrayList;

public class Team implements Nameable {

	private String name;
	private ArrayList<Hero> heroes;
	
	public Team(String name) {
		
		// implement constraint (section 2.1.1)
		if (name.length() <= 2 || name.length() >= 10) {
			throw new IllegalArgumentException("name must be between 2 and 10 characters long.");
		}
		
		this.name = name;
		this.heroes = new ArrayList<Hero>();
	}
	
	public ArrayList<Hero> getHeroes() {
		return this.heroes;
	}
	
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
	
	public void removeHero(Hero hero) {
		if (!this.heroes.remove(hero)) {
			throw new IllegalArgumentException("Hero does not exist in the Team.");
		}
	}
	
	public void removeHero(String heroName) {
		for (Hero hero : this.heroes) {
			if (hero.getName().equals(heroName)) {
				this.heroes.remove(hero);
			}
		}
		
		throw new IllegalArgumentException("Hero does not exist in the Team");
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
