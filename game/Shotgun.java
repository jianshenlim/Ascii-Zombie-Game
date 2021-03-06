package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.WeaponItem;
/**
 * A shotgun firearm weapon
 * @author Jian Lim
 *
 */
public class Shotgun extends FirearmWeapon {
	
	/**
	 * Shotgun constructor
	 * @param ammo Shotgun current ammo
	 * @param max Shotgun max ammo
	 */
	public Shotgun(int ammo, int max) {
		super("Shotgun",'=',40,0,75,"blasts",ammo,max);
		this.addCapability(RangedWeaponCapability.SHELL);		
		allowableActions.add(new ShotgunSubMenu(this));
	}
	

	
	@Override 

	public List<Action> getAllowableActions() {
		if (getAmmoCount() == 0) {
			return new Actions().getUnmodifiableActionList();
		}else {
		return this.allowableActions.getUnmodifiableActionList();
		}
	}
}
	
	
	
