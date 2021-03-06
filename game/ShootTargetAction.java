package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Action that allows actors to shoot targets with firearms
 * @author Jian Lim
 *
 */
public class ShootTargetAction extends RangedAttackAction{
	
	private RangedWeapon currentRWeapon;

	/**
	 * ShootTargetAction constructor
	 * @param target Current actor target
	 * @param current Curret RangedWeapon of actor
	 */
	public ShootTargetAction(Actor target, RangedWeapon current) {
		super(target,0, "", ZombieCapability.UNDEAD);
		currentRWeapon = current;
	}
	
	/**
	 * Overriden method that deals different shooting effects based on current ranged weapons level of focus
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";

		if (!actor.isFocusTarget(target)) {
			currentRWeapon.resetFocus();
		}
		if (actor.checkGotHit() || !actor.checkFocus()) {
			currentRWeapon.resetFocus();
		}
		int damage = currentRWeapon.getRangedDamage();
		int hitChance = currentRWeapon.getRangedHitChance();
		
		if (prob.isSuccessful(hitChance)) {
			result += System.lineSeparator() + actor.toString() + " " + currentRWeapon.rangedVerb() + " "+ target.toString() + " for " + damage + " damage!";
			
			target.advancedHurt(map,currentRWeapon.getRangedDamage());
			
			if (!target.isConscious()) {
				Item corpse = new PortableItem("dead " + target, '^');
				map.locationOf(target).addItem(corpse);	
				dropAllInventory(target,map);
				map.removeActor(target);	
	
				result += System.lineSeparator() + target + " is killed.";
			}
			
			currentRWeapon.decrementAmmo();
			result+= System.lineSeparator() + currentRWeapon.getAmmoCount() +" shots remaining";
		}else {
			currentRWeapon.decrementAmmo();
			result+= System.lineSeparator() + currentRWeapon.getAmmoCount() +" shots remaining";
			result += System.lineSeparator() + actor.toString() +" misses "+ target;
		}
		
		currentRWeapon.resetFocus();
		return result;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor.toString() + " " + currentRWeapon.rangedVerb() + " "+ target.toString() ;
	}
	
	
	@Override
	public Action getNextAction() {
		return null;
	}

}
