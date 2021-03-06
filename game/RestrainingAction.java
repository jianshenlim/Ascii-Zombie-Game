package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
/**
 * Class that simulates restraining actors actions, extension of the existing Action class.
 * Allows for effects unique to only the player(for now)
 * @author Tri
 *
 */
public class RestrainingAction extends Action {
	/** The actor that the player will restrain*/
	private Actor restrainingActor;
	/** The item that the player uses to restrain*/
	private Item itemToRemove;
	/**
	 * RestrainingAction Constructor.
	 *
	 * @param restrainingActor - The actor that the player will restrain
	 * @param itemToRemove - The item that the player uses to restrain
	 */
	public RestrainingAction(Actor restrainingActor,Item itemToRemove) {
		this.restrainingActor = restrainingActor;
		this.itemToRemove = itemToRemove;
	}
	@Override
	/**
	 * Overridden method of execute, modified to simulate the restraining action
	 * @param actor - the actor that needs to craft
	 * @param map - the map that the actor is currently in
	 * @return a new string indicates the action is executed successfully
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		restrainingActor.addCapability(ZombieCapability.RESTRAINED);
		restrainingActor.removeCapability(ZombieCapability.RESTRAINABLE);
		actor.removeItemFromInventory(this.itemToRemove);
		return actor.toString()+" restrains "+restrainingActor.toString();	
	}

	@Override
	/**
	 * Overridden method of menuDescription. This method returns of string that would be displayed
	 * as one of the options on the menu
	 * @param actor - the actor that restrains
	 * @return a new string that will be displayed on the menu
	 */
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Restrain "+ restrainingActor.toString();
	}

}
