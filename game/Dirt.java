package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
		addCapability(GroundCapability.SOWABLE);
	}
	@Override
	/**
	 * Overridden method of allowableActions. It will decide which actions to return
	 * @param   actor - the actor that wants to interact with the crop
	 * @return  an Actions object that contains all the valid actions that the actor can
	 * do the crop
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){

		return new Actions();
	}
}
