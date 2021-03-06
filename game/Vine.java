package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
/**
 * Class that implement Vine item, it allows player to craft Rope
 * It is an extension of the existing Item class
 * @author Tri
 *
 */
public class Vine extends Item {
	boolean craftingActionInList = false;
	/**
	    * Vine constructor	
		* create a Vine Item
	*/
	public Vine() {
		super("Vine", 'S', true);
		// TODO Auto-generated constructor stub
	}
	@Override 
	/**
	 * Overridden method of getAllowableActions. It will return a craftingAction object
	 * that allows human to craft it to a Rope
	 */
	public List<Action> getAllowableActions() {
		if (craftingActionInList == false) {
			Item newRope = new Rope();
			CraftingAction newCraftingAction = new CraftingAction(this, newRope);
			this.allowableActions.add(newCraftingAction);
			this.craftingActionInList = true;
		}	
		return this.allowableActions.getUnmodifiableActionList();
	}
}
