package game;

/**
 * Shotgun ammunition
 * @author Jian Lim
 *
 */
public class ShotgunShells extends Ammunition{
	
	/**
	 * ShotgunShells construct
	 * @param bulletCount
	 */
	public ShotgunShells(int bulletCount) {
		super("Box of shotgun shells",':', bulletCount, RangedWeaponCapability.SHELL);
		// TODO Auto-generated constructor stub
	}

}
