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
public class ZombieArm extends WeaponItem{
	/** this attributes whether the craftingAction is already in the list
	 * of allowable actions	*/
	private boolean craftingActionInList = false;
	/**
	* create a Zombie Arm
	* @param name - the name of this Item
	*/
	public ZombieArm(String name) {
		// TODO Auto-generated constructor stub
		super(name,'A',15,"slaps");
		if(name == null) {
			throw new IllegalArgumentException("Zombie Arm name cannot be null");
		}
	}
	@Override 
	/**
	 * Overridden method of getAllowableActions. It will return a craftingAction object
	 * that allows human to craft it to a zombie club
	 */
	public List<Action> getAllowableActions() {
		if (craftingActionInList == false) {
			ZombieClub newZombieClub = new ZombieClub("Zombie Club", this.damage() + 10, "clubs");
			CraftingAction newCraftingAction = new CraftingAction(this, newZombieClub);
			this.allowableActions.add(newCraftingAction);
			this.craftingActionInList = true;
		}	
		return this.allowableActions.getUnmodifiableActionList();
	}
}
