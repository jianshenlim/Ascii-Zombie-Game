package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * Class that implement the eat action that allows human to heal
 * It is an extension of the existing Action class
 * @author Tri
 *
 */
public class EatAction extends Action {
	/** Food object that will be eaten and removed from the inventory */
	private Item foodToEat;
	
	/**
	 * EatAction Constructor.
	 *
	 * @param foodToEat - the Food item that needs to be eaten
	 */
	public EatAction(Item foodToEat) {
		// TODO Auto-generated constructor stub
		if(foodToEat == null) {
			throw new IllegalArgumentException("The Food To Eat cannot be null");
		}
		if(foodToEat.hasCapability(ItemCapability.EATABLE ) == false) {
			throw new IllegalArgumentException("The Food has to be edible");
		}
		this.foodToEat = foodToEat;
	}
	
	@Override
	/**
	 * Overridden method of execute, modified to simulate the eating process
	 * @param actor - the actor that needs to eat
	 * @param map - the map that the actor is currently in
	 * @return a new string indicates the action is executed successfully
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		actor.heal(20);
		actor.removeItemFromInventory(foodToEat);
		List<Exit> exits = map.locationOf(actor).getExits();
		for (Exit exit : exits) {
			Location candidate = exit.getDestination();
			candidate.removeItem(this.foodToEat);
		}
		Location locationOfActor = map.locationOf(actor);
		locationOfActor.removeItem(this.foodToEat);
		return actor.toString() + " ate food and recovered health";
	}
	/**
	 * Overridden method of menuDescription. This method returns of string that would be displayed
	 * as one of the options on the menu
	 * @param actor - the actor that needs to eat
	 * @return a new string that will be displayed on the menu

	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor.toString()+ " eats food to recover health";
	}

}
