package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * Class that simulates curing actions, extension of the existing Action class.
 * Allows for effects unique to only the player(for now)
 * @author Tri
 *
 */
public class CuringAction extends Action {
	/** The item that needs to be deleted from the 
	 * inventory after the curing action executed*/
	private Item itemToRemove;
	/** the zombie that will be cured and have an extra CURED capability*/
	private Actor patient;
	/**
	 * CuringAction Constructor.
	 *
	 * @param itemToRemove - the item that needs to be removed
	 * @param patient - the Actor that needs to be cured
	 */
	public CuringAction(Actor patient,Item itemToRemove) {
		this.patient = patient;
		this.itemToRemove = itemToRemove;
	}
	@Override
	/**
	 * Overridden method of execute, modified to simulate the curing actions 
	 * @param actor - the actor that needs to craft
	 * @param map - the map that the actor is currently in
	 * @return a new string indicates the action is executed successfully
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Location location = map.locationOf(patient);
		patient.addCapability(ZombieCapability.CURED);
		actor.removeItemFromInventory(itemToRemove);
		return actor.toString()+" Cures "+patient.toString();
	}

	@Override
	/**
	 * Overridden method of menuDescription. This method returns of string that would be displayed
	 * as one of the options on the menu
	 * @param actor - the actor that cures
	 * @return a new string that will be displayed on the menu

	 */
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Cures "+patient.toString();
	}

}
