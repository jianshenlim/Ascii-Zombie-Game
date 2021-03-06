package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Allows for a ranged attack that affects all actors in an area
 * @author Jian Lim
 *
 */
public class AttackAreaAction extends RangedAttackAction{
	
	private String shootDirection;
	private RangedWeapon currentRWeapon;

	/**
	 * AttackAreaAction constructor
	 * @param target current target
	 * @param current Current ranged weapon
	 * @param direction Strint of attack direction, takes in "N,S,E,W,NE,SE,NE,NW"
	 */
	public AttackAreaAction(Actor target, RangedWeapon current, String direction) {
		super(target,0,direction, ZombieCapability.UNDEAD);
		shootDirection = direction;
		currentRWeapon = current;
	}
	
	
	/**
	 * Overriden methods damages all actors in the area range
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = actor.toString() + " " + currentRWeapon.rangedVerb()+" the " + currentRWeapon + " " + aim.getStringDirection();
		
		ArrayList<Actor> targets = aim.getTargets(actor, map);
			
		if (!targets.isEmpty()) {
			for (Actor a : targets) {
				if (prob.isSuccessful(currentRWeapon.getRangedHitChance())) {	
					String outcome = a.advancedHurt(map,currentRWeapon.getRangedDamage());		

					if (!outcome.isEmpty()) {

						result += System.lineSeparator() + actor +" " + outcome;
					}								
					result += System.lineSeparator() + a.toString() + " is hit for " + Integer.toString(currentRWeapon.getRangedDamage()) + " damage";
					
					if (!a.isConscious()) {
						Item corpse = new PortableItem("dead " + a, '^');
						map.locationOf(a).addItem(corpse);	
						dropAllInventory(a,map);
						map.removeActor(a);	
						result += System.lineSeparator() + a + " is killed.";
					}
				}
				else {
					result += System.lineSeparator() + "The shot misses " + a.toString();
					}
				}
			
		}
		currentRWeapon.decrementAmmo();
		result+= System.lineSeparator() + Integer.toString(currentRWeapon.getAmmoCount()) +" shots remaining";
		
		return result;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor.toString()+ " shoots the " + currentRWeapon.toString() + " " + aim.getStringDirection();

	}

}
