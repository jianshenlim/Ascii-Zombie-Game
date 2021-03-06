package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
/**
 * Class that simulates crafting actions, extension of the existing Action class.
 * Allows for effects unique to only the player(for now)
 * @author Tri
 *
 */
public class CraftingAction extends Action{
	/** the item that needs to be deleted from the 
	 * inventory after the crafting action executed*/
	private Item theItemToCraft;
	/** the item that needs to be added to the 
	 * inventory after the crafting action is executed*/
	private Item theItemCraftTo;
	/**
	 * CraftingAction Constructor.
	 *
	 * @param theItemToCraft - the item that needs to be crafted
	 * @param theItemCraftTo - the item that the action crafts to
	 */
	public CraftingAction(Item theItemToCraft, Item theItemCraftTo) {
		// TODO Auto-generated constructor stub
		if(theItemCraftTo == null || theItemToCraft == null) {
			throw new IllegalArgumentException("Invalid Weapon");
		}
		this.theItemToCraft = theItemToCraft;
		this.theItemCraftTo = theItemCraftTo;
	}
	@Override
	/**
	 * Overridden method of execute, modified to simulate the crafting actions 
	 * @param actor - the actor that needs to craft
	 * @param map - the map that the actor is currently in
	 * @return a new string indicates the action is executed successfully
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		actor.removeItemFromInventory(this.theItemToCraft);
		actor.addItemToInventory(theItemCraftTo);
		map.locationOf(actor).removeItem(this.theItemToCraft);
		return theItemCraftTo.toString() + " is crafted by "+actor.toString();
	}
	@Override
	/**
	 * Overridden method of menuDescription. This method returns of string that would be displayed
	 * as one of the options on the menu
	 * @param actor - the actor that needs to craft
	 * @return a new string that will be displayed on the menu

	 */
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor.toString() +" crafts a " + theItemCraftTo.toString();
	}

}
