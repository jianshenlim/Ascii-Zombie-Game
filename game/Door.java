package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
/**
 * Class that implement Door, it allows Human to enter but not Zombie
 * It is an extension of the existing Ground class
 * @author Tri
 *
 */
public class Door extends Ground {
	/**
	 * Door Constructor.
	 */
	public Door() {
		super('D');
		// TODO Auto-generated constructor stub
	}
	@Override
	/**
	 * It is a impassable terrain for Zombie and it is only passable if it is Human .
	 *
	 * @param actor the Actor to check
	 * @return true or false
	 */
	public boolean canActorEnter(Actor actor) {
		if(actor.hasCapability(ZombieCapability.ALIVE)) {
			return true;
		}
		return false;
	}
	
	@Override
	/**
	 * Door blocks thrown objects
	 * @return true
	 */
	public boolean blocksThrownObjects() {
		return true;
	}

}
