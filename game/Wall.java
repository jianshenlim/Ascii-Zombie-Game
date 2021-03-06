package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
/**
 * Class that implement Wall, it allows no actor to enter
 * It is an extension of the existing Ground class
 * @author Tri
 *
 */
public class Wall extends Ground {
	/**
	 * Wall Constructor.
	 */
	public Wall() {
		super('W');
		// TODO Auto-generated constructor stub
	}
	@Override
	/**
	 * It is a impassable terrain 
	 *
	 * @param actor the Actor to check
	 * @return false
	 */
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	/**
	 * Wall blocks thrown objects
	 * @return true
	 */
	public boolean blocksThrownObjects() {
		return true;
	}

}
