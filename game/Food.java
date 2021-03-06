package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
/**
 * Class that implement food item, it allows human to heal and restore hitpoints
 * It is an extension of the existing Item class
 * @author Tri
 *
 */
public class Food extends Item{
	/**
	* create a Food Item
	*/
	public Food() {
		// TODO Auto-generated constructor stub
		super("Food",'O', true);
		addCapability(ItemCapability.EATABLE);
	}
	@Override
	/**
	 * Overridden method of getAllowableActions. It will determine which actions to return
	 */
	public List<Action> getAllowableActions() { 
		this.allowableActions = new Actions();
		EatAction newEatAction = new EatAction(this);
		this.allowableActions.add(newEatAction);
		return allowableActions.getUnmodifiableActionList();
	}

}
