package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.WeaponItem;
/**
 * Class that implement Zombie Arm, it is a kind of weapon
 * it allows human to hit zombie with it
 * It is an extension of the existing WeaponItem class
 * @author Tri
 *
 */
public class ZombieLeg extends WeaponItem{
	/** this attributes whether the craftingAction is already in the list
	 * of allowable actions	*/
	private boolean craftingActionInList = false;
	/**
	* create a Zombie Leg
	* @param name - the name of this Item
	*/
	public ZombieLeg(String name) {
		// TODO Auto-generated constructor stub
		super(name,'L',20,"belts");
		if(name == null) {
			throw new IllegalArgumentException("Zombie Leg name cannot be null");
		}
	}
	@Override 
	/**
	 * Overridden method of getAllowableActions. It will return a craftingAction object
	 * that allows human to craft it to a zombie mace
	 */
	public List<Action> getAllowableActions() {
		if (craftingActionInList == false) {
			ZombieMace newZombieMace = new ZombieMace("Zombie Mace", this.damage() + 10, "smashes");
			CraftingAction newCraftingAction = new CraftingAction(this, newZombieMace);
			this.allowableActions.add(newCraftingAction);
			this.craftingActionInList = true;
		}	
		return this.allowableActions.getUnmodifiableActionList();
	}
}
