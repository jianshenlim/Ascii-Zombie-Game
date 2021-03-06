package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;


/**
 * Class for creating ammunition objects to reload firearm weapons, takes in a RangedWeaponCapability corresponding to the 
 * bullet type/gun that the ammunition reloads
 * @author Jian Lim
 *
 */
public class Ammunition extends PortableItem {
	/**
	 * Number of rounds the ammunition can reload
	 */
	private int rounds;
	/**
	 * The type of gun the ammunition can reload
	 */
	private RangedWeaponCapability bulletType;
	
	/**
	 * Constructor
	 * @param name String literal of ammunition name
	 * @param displayChar Char of ammunition object printed on display
	 * @param bulletCount Integer value of ammunition capacity
	 * @param ammoBulletType Capability of Ammunition to reload specific FirearmWeapons
	 */
	public Ammunition(String name, char displayChar,int bulletCount, RangedWeaponCapability ammoBulletType) {
		super(name, displayChar);
		rounds = bulletCount;
		bulletType = ammoBulletType;
	}
	
	/**
	 * Method that checks if Ammunition object is able to reload a FirearmWeapon
	 * @return Boolean value of result
	 */
	public boolean areRoundsRemaining() {
		if (rounds > 0) {return true;}
		else {return false;}
	}
	
	/**
	 * Method that decrements the number of rounds remaining of ammunition object
	 */
	public void decrementRounds() {
		rounds--;
	}
	
	
	/**
	 * Methods that returns the current remaining number of rounds
	 * @return Integer value of number of rounds remaining
	 */
	public int getRemainingRounds() {
		return rounds;
	}
	
	
	/**
	 * Overridden method that returns a list of actions, containing a ReloadAction
	 */
	@Override
	public List<Action> getAllowableActions() { 
		allowableActions = new Actions();
		this.allowableActions.add(new ReloadAction(this,bulletType));
		return allowableActions.getUnmodifiableActionList();
	}
	
}
