package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Class that simulates the pickup behavior of actors
 * @author Jian Lim
 *
 */
public class PickUpBehaviour implements Behaviour {
	
	
	/**
	 * returns a pickup action if there is a valid pickup item on actor 
	 * position
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Item> items = new ArrayList<Item>(map.locationOf(actor).getItems());
		
		if (!items.isEmpty()) {
			for (Item i :items) {
				return new PickUpItemAction(i);
			}
		}
		return null;
	}
}
