package game;


/**
 * An interface for representing RangedWeapon which have an ammunition counter and 
 * can be reloaded
 * @author Jian Lim
 *
 */
public interface Reloadable {
	
	/**
	 * Method that decreases the current ammo count of Firearm
	 */
	public default void decrementAmmo() {
	}	
	
	/**
	 * Method that refills the current ammo count of Firearm
	 */
	public default void reload() {
	}
	
	/**
	 * Method that checks if current Firearm is reloadable
	 * @return Boolean value if it is or is not
	 */
	public default boolean reloadable() {
		return false;
	}
	
	/**
	 * Method that returns the current ammo count
	 * @return Integer value of current ammo count
	 */
	public default int getAmmoCount() {
		return 0;
	}
	

}


