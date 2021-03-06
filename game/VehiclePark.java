package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
/**
 * Class that implement VehiclePark, it guarantees that the player will always have somewhere to go to
 * It is an extension of the existing Ground class
 * @author Tri
 *
 */
public class VehiclePark extends Ground {

	/**
	 * VehiclePark Constructor.
	 */
	public VehiclePark() {
		super('P');
		// TODO Auto-generated constructor stub
	}
	@Override
	/**
	 * It is an impassable terrain for any actor but the player .
	 *
	 * @param actor the Actor to check
	 * @return true or false
	 */
	public boolean canActorEnter(Actor actor) {	
		if (actor.hasCapability(AICapability.PLAYER)) {
			return true;
		}
		return false;

	}
	
	@Override
	/**
	 * VehiclePark does not block thrown objects
	 * @return false
	 */
	public boolean blocksThrownObjects() {
		return false;
	}

}
