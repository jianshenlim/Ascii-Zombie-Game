package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Zombie;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {
	
	/**
	 * Checks to see if the actor performed a focusing type action in the last turne
	 * @return boolean value if they did or did not
	 */
	public default boolean checkFocus() {return false;}
	
	/**
	 * Checks to see if actor got hit in the last turn
	 * @return boolean value if they did or did not
	 */
	public default boolean checkGotHit() {return false;}
	
	/**
	 * Resets the focusing counter of actor
	 */
	public default void resetFocused() {}
	
	/**
	 * Resets the hit counter of actor 
	 */
	public default void resetHit() {}
	
	/**
	 * Sets the focusing target actor of current actor
	 * @param target current Actor's target
	 */
	public default void setFocusTarget(Actor target) {}
	
	/**
	 * Resets the current actors focusing target
	 */
	public default void resetFocusTarget() {}
	
	/**
	 * Check to see if actor is targetting the same target in the last turn
	 * @param target currently targeted actor
	 * @return boolean value if it is or is not the same target
	 */
	public default boolean isFocusTarget(Actor target) {return false;}
	
	/**
	 * Advanged method of damaging actors which allows for effects to occur to the locations
	 * surrounding the targeted actor
	 * 
	 * @param map current GameMap of targetted actor
	 * @param damage Interger value of damage inflicted
	 * @return String literal of outcome to display
	 */
	public default String advancedHurt(GameMap map, int damage){
		return null;}

	public void tick(Location currentLocation);
}
