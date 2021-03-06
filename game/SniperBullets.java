package game;

/**
 * Ammunition for Sniper
 * @author Jian Lim
 *
 */
public class SniperBullets extends Ammunition{
	/**
	 * SniperBullets Constructor
	 * @param bulletCount SniperBullets starting ammo count
	 */
	public SniperBullets(int bulletCount) {
		super("Box of sniper bullets", '"', bulletCount, RangedWeaponCapability.BULLET);
	}

}
