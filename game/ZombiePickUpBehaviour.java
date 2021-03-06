package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;


/**
 * Class that simulates the pickup behavior of zombies. Extends
 * PickUpBehaviour class. Allows the simulation of the different behaviors
 * based on the condition of the zombie's arms.
 * @author Jian Lim
 *
 */
public class ZombiePickUpBehaviour extends PickUpBehaviour {

	private Zombie currentZombie;
	
	/**
	 * ZombiePickUpBehaviour constructor
	 * @param current Current Zombie
	 */
	public ZombiePickUpBehaviour(Zombie current) {
		currentZombie = current;
	}

	/**
	 * Returns a pickup action if there is a valid pickup item on zombie's current
	 * position, checks to see if a zombie is allowed to pick up items based on condition of arms.
	 * And checks to see if item is not a food item.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (!currentZombie.getValidPickUp()) {
			return null;
		}else {
			List<Item> items = new ArrayList<Item>(map.locationOf(actor).getItems());
			if (!items.isEmpty()) {
				for (Item i :items) {
					if (i.getClass() != Food.class) {
						return i.getPickUpAction();
					}
				}
			}
			return null;
		}
	}
	
}
	
