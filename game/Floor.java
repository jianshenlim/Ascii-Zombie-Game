package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
/**
 * Class that implement Floor, it can be entered by every actors
 * It is an extension of the existing Ground class
 * @author Tri
 *
 */
public class Floor extends Ground {
	/**
	 * Floor Constructor.
	 */
	public Floor() {
		super('_');
		// TODO Auto-generated constructor stub
	}
	@Override
	/**
	 * It can be entered by every actors
	 *
	 * @param actor the Actor to check
	 * @return true
	 */
	public boolean canActorEnter(Actor actor) {		
		return true;

	}
	
	@Override
	/**
	 * Floor does not block thrown objects
	 * @return false
	 */
	public boolean blocksThrownObjects() {
		return false;
	}

}
