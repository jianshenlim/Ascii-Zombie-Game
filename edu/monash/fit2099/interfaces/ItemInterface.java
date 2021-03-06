package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Weapon;
import game.FirearmWeapon;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {
	
	/**
	 * Method that reloads the current item
	 */
	public default void reload() {}
	
	/**
	 * Method that checks to see if current item is reloadable
	 * @return boolean value if it is or is not
	 */
	public default boolean reloadable() {return false;}
	
	
}
