package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;


/**
 * Class that simulates all zombie attack actions, extension of existing AttackAction class.
 * Allows for effects unique to only zombies
 * @author Jian
 *
 */
public class ZombieAttackAction extends AttackAction{
	
	private Probability prod = new Probability();
	
	/**
	 * Stores reference to current zombie performing attack
	 */
	private Actor currentZombie;
	
	/**
	 * ZombieAttackAction constructor
	 * @param target Actor target of attack
	 * @param current Current Zombie performing attack
	 */
	public ZombieAttackAction(Actor target, Zombie current) {
		super(target);
		currentZombie = current;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Overridden method of execute, modified to simulate different hit chances based on the
	 * returned intrinsic weapon, also allows zombies to heal if it successfully hits a bite attack
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		Weapon weapon = actor.getWeapon();
		String result = "";
		
		int hitChance = getHitChance(weapon);
		
		if (prod.isSuccessful(hitChance)) {
			return actor + " misses " + target + ".";
		}
		
		result += evaluateAttack(actor,weapon);

		if (!target.isConscious()) {
			
			ZombieEgg corpse = new ZombieEgg(target.toString());
			map.locationOf(target).addItem(corpse);
			dropAllInventory(target,map);
			map.removeActor(target);

			result += System.lineSeparator() + target + " is killed.";
			

			
			
			
		}

		return result;
	}
	
	
	/**
	 * Calculates the miss chance based on returned weapon, bite attacks 
	 * have a lower hit chance than other attacks
	 * @param weapon current zombie weapon
	 * @return integer value representing probability chance
	 */
	private int getHitChance(Weapon weapon) {
		if (weapon.verb() == "bites") {return 20;}
		else {return 50;}
		
	}
	
	
	/**
	 * Evaluates the outcome of attack, returns string literal detailing events occured
	 * @param actor Actor targeted
	 * @param weapon Current weapon used
	 * @return String literal describing events
	 */
	private String evaluateAttack(Actor actor, Weapon weapon) {
		int damage = weapon.damage();
		int healAmount = 5;
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		
		if (weapon.verb() =="bites") {
			currentZombie.heal(healAmount);
			result += System.lineSeparator() + actor + " heals " + healAmount+ " health from a bite.";
		}
		target.hurt(damage);
		return result;
	}

	
	
	
}

