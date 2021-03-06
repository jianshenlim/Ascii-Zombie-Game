package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for non zombie Actor attacking Zombies.
 * 
 * Modified: Jian Lim
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Probability prob = new Probability();
	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	/**
	 * Method has been modified to damage zombies using a created method, instead of calling hurt().
	 * allows for the damaging of zombie limbs and the display of appropriate outcome based on 
	 * success of events.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();		

		
		if (prob.isSuccessful(20)) {
			return actor + " misses " + actor + ".";
		}
		int damage = weapon.damage();

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		String outcome = target.advancedHurt(map,damage);	
			
		if (!outcome.isEmpty()) {
			result += System.lineSeparator() + actor +" " + outcome;
		}		
		
		if (!target.isConscious()) {
			Item corpse = new PortableItem("dead " + target, '^');
			map.locationOf(target).addItem(corpse);	
			dropAllInventory(target,map);
			map.removeActor(target);	

			result += System.lineSeparator() + target + " is killed.";
		}
		
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
	
	/**
	 * Method that drops all of attack action target's inventory on the ground
	 * @param target Actor target of attack action
	 * @param map Current game map
	 */
	protected void dropAllInventory(Actor target, GameMap map){
		Actions dropActions = new Actions();
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)		
			drop.execute(target, map);

	}
	

	
}
