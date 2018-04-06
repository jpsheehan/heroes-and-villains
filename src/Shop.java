
import java.util.ArrayList;

/**
 * The Shop class as described in section 2.3.2 of the specification.
 *
 */
public class Shop extends Area {

	/**
	 * The list of Items the shop sells.
	 */
	private ArrayList<Item> items;
	
	/**
	 * The InnKeeper this shop has. Every shop must have an InnKeeper.
	 */
	private InnKeeper innKeeper;
	
	/**
	 * Creates a new Shop.
	 * @param name The name of the Shop.
	 * @param items The items the Shop sells.
	 * @param innKeeper The InnKeeper sells the Items.
	 */
	public Shop(String name, ArrayList<Item> items, InnKeeper innKeeper) {
		super(name, AreaType.SHOP);
		
		this.items = items;
		this.innKeeper = innKeeper;
	}
	
	/**
	 * Returns the list of Items to sell.
	 */
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	/**
	 * Returns the InnKeeper.
	 */
	public InnKeeper getInnKeeper() {
		return this.innKeeper;
	}

}
