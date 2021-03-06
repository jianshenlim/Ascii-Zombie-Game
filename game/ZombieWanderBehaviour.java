package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;



/**
 * Class that simulates a zombie's wander behavior. Extends WanderBehaviour class.
 * Allows us to simluate different behavior based on condition of zombie legs.
 * 
 * @author Jian Lim
 *
 */
public class ZombieWanderBehaviour extends WanderBehaviour {
	
	private Zombie currentZombie;
	
	/**
	 * ZombieWanderBehaviour constructor,
	 * @param current Current Zombie
	 */
	public ZombieWanderBehaviour(Zombie current) {
		currentZombie = current;
	}
	
	
	/**
	 * Method that checks status of zombie's legs and returns a valid
	 * move action based on the leg's condition
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		if (currentZombie.hasCapability(ZombieCapability.MOBILITY)) {
			return super.getAction(actor, map);		
		}else {
			return null;	
		}
		
	

}
}