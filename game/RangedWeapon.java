package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Base class for creating ranged weapons, extends WeaponItem and implements the Reloadable interface, allowing the the ability to track and reload an ammunition count and 
 * FocusLevel interface, allowing to build, track and reset a weapons focus level to cause different effects
 * @author Jian lim
 *
 */
public abstract class RangedWeapon extends WeaponItem implements Reloadable,FocusLevel{
	
	private int maxRange;
	private int rDamage;
	private int hitChance;
	private String rangedVerb;
	
	/**
	 * RangedWeapon constructor
	 * @param name String literal of RangedWeapon name
	 * @param displayChar Char of RangedWeapon to display
	 * @param damage Integer value of RangedWeapon's melee attack damage
	 * @param rangedDamage Integer value of RangedWeapon's ranged attack damage
	 * @param range Interger value of max range of RangedWeapon
	 * @param hit Interger value of RangedWeapon's base hit chance
	 * @param verb String literal of RangedWeapon's melee attack verb
	 * @param rVerb String literal of rangedWeapon's ranged attack verb
	 */
	public RangedWeapon(String name, char displayChar, int damage,int rangedDamage,int range,int hit, String verb, String rVerb) {
		super(name, displayChar, damage, verb);
		rDamage = rangedDamage;
		maxRange = range;
		rangedVerb = rVerb;
		hitChance = hit;
		
	}
	
	/**
	 * Returns the max range of current RangedWeapon
	 * @return Interger value representing the max range
	 */
	public int getMaxRange() {
		return maxRange;
	}
	/**
	 * Returns the ranged damage of current RangedWeapon
	 * @return Integer value of RangedWeapon damage
	 */
	public int getRangedDamage() {
		return rDamage;
	}
	
	/**
	 * Returns the base hit chance of current RangedWeapon
	 * @return Integer value of hit chance
	 */
	public int getRangedHitChance() {
		return hitChance;
	}
	
	/**
	 * Returns verb of current RangedWeapon ranged attack
	 * @return String literal of RangedWeapon's ranged attack verb
	 */
	public String rangedVerb() {
		return rangedVerb;
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		Reloadable.super.reload();
	}

	@Override
	public boolean reloadable() {
		// TODO Auto-generated method stub
		return Reloadable.super.reloadable();
	}
		
	
}