package game;

import edu.monash.fit2099.engine.Actor;

/**
 * Sniper FirearmWeapon
 * @author Jian Lim
 *
 */
public class Sniper extends FirearmWeapon{
	
	private int focus = 0;
	
	/**
	 * Sniper constructor
	 * @param ammo Sniper starting ammo
	 * @param max Sniper max ammo capacity
	 * @param range Sniper max range
	 */
	public Sniper(int ammo, int max,int range) {
		super("Sniper Rifle",'!',30,range,75,"snipes",ammo,max);
		this.addCapability(RangedWeaponCapability.BULLET);

		allowableActions.add(new RangedTargetSubMenu(this));
	}
	
	@Override
	public int getRangedHitChance() {
		if (focus == 0) {
			return 75;
		} else if (focus == 1) {
			return 90;
		}else {
			return 100;
		}
	}
	
	@Override
	public void incrementFocus() {
		if (focus <2) {
		focus ++;
		}
	}
	
	@Override
	public void resetFocus() {
		focus = 0;
	} 
	
	@Override
	public int getFocus(){
		return focus;
	}
		
	@Override
	public int getRangedDamage() {
		if (focus == 1) {
			return super.getRangedDamage() * 2;
		}else if (focus == 2) {
			return super.getRangedDamage()*1000;
		}else {return super.getRangedDamage();}
	}
	

	
	

}
