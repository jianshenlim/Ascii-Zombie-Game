package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;

/**
 * Base class for Firearm weapons, which extends ranged weapons, 
 * @author Jian Lim
 *
 */
public abstract class FirearmWeapon extends RangedWeapon {
	/**
	 * Maximun ammo count of FirearmWeapon
	 */
	private int maxAmmo;
	/**
	 * 	Current ammo count of FirearmWeapon
	 */
	private int ammoCount;
	
	/**
	 * FirearmWeapon constructor
	 * @param name String literal of FirearmWeapon name
	 * @param displayChar Char of FirearmWeapon diplayed to screen
	 * @param rangedDamage Integer value of FirearmWeapons ranged damage
	 * @param range Interger value of FirearmWeapons range
	 * @param hitchance Integer value of FirearmWeapons base hit chance
	 * @param rVerb String literal of FirearmWeapons ranged verb
	 * @param startAmmo Integer value of starting ammo count
	 * @param max Integer value of Firearm Weapons maximum ammo capacity
	 */
	public FirearmWeapon(String name, char displayChar, int rangedDamage, int range,int hitchance,String rVerb, int startAmmo,int max) {
		super(name, displayChar,15, rangedDamage, range,hitchance,"gun butts", rVerb);
		this.addCapability(RangedWeaponCapability.RELOADABLE);
		if (startAmmo < 0) {
			ammoCount = 0;
		}
		ammoCount = startAmmo;
		maxAmmo = max;
	}

	@Override
	public void decrementAmmo() {
		if (ammoCount != 0) {
			ammoCount--;
		}else {
			ammoCount = 0;
		}
	}

	@Override
	public void reload() {
		if (ammoCount < maxAmmo) {
			ammoCount++;
		}
	}

	@Override
	public boolean reloadable() {
		if (ammoCount < maxAmmo) {return true;}
		else return false;
	}

	@Override
	public int getAmmoCount() {
		return ammoCount;
	}
	
	
	/**
	 * Overridden method to return the actions of object, if ammo count is 0 returns an empty list of actions to represent 
	 * running out of ammo, else the list of avaliable actions normally
	 */
	@Override
	public List<Action> getAllowableActions() {
		if (ammoCount == 0) {
			return new Actions().getUnmodifiableActionList();
		}else {
		return this.allowableActions.getUnmodifiableActionList();
		}
	}
}
