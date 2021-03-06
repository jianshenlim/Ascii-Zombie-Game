package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * Class that implement the eating behaviour that decides what a human 
 * needs to do to eat
 * It is an implementation of the Behaviour interface
 * @author Tri
 *
 */
public class EatBehaviour implements Behaviour{
	/** the name of the human that has this behaviour */
	private String humanName;
	/**
	 * EatBehaviour Constructor.
	 *
	 * @param humanName - the name of the human
	 */
	public EatBehaviour(String humanName) {
		// TODO Auto-generated constructor stub
		if(humanName == null) {
			throw new IllegalArgumentException("The Human name cannot be null");
		}
		this.humanName = humanName;
	}

	@Override
	/**
	 * Overridden method of getAction, modified to return an EatAction base on
	 * the availability of the food item in the inventory
	 * @param actor - the actor that possesses this behavior
	 * @param map - the map that the actor is currently in
	 * @return a new EatAction or a Null
	 */
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Item theFoodToEat = findFoodInInventory(actor);
		if (theFoodToEat != null) {
			return new EatAction(theFoodToEat);
		}
		Item theFoodOnTheGround = findFoodAtLocation(map.locationOf(actor));
		if (theFoodOnTheGround != null) {
			return new EatAction(theFoodOnTheGround);
		}
		return null;
	}
	
	private Item findFoodInInventory(Actor actor) {
		for(Item item: actor.getInventory()) {
			if (item.hasCapability(ItemCapability.EATABLE) == true) {
				return item;
			}
		}		
		return null;
	}
	private Item findFoodAtLocation(Location location) {
		for(Item item: location.getItems()) {
			if (item.hasCapability(ItemCapability.EATABLE) == true) {
				return item;
			}
		}
		return null;
	}

}
