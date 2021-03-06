package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
/**
 * Class that implements Herb, it allows player to sow and harvest for Medicine when it is mature.
 * It has an extra PERENNIAL capability
 * It is an extension of the existing Ground class
 * @author Tri
 *
 */
public class Herb extends Ground {
	/** Turns attribute, it helps keep track of 
	 * how many turns left until the Herb turns mature */
	private int age;
	/**
	 * Herb Constructor.
	 */
	public Herb() {
		super('i');
		// TODO Auto-generated constructor stub
	}
	@Override
	/**
	 * Overridden method of tick to simulate the growing of Herb, after 20 turns, crop will turn 
	 * mature and the display char will change
	 * @param location - the location of the herb		
	 */
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = '|';
		if (age == 20) {
			displayChar = '$';
			addCapability(GroundCapability.HARVESTABLE);
		}
	}
	@Override
	/**
	 * Overridden method of allowableActions. It will decide which actions to return
	 * @param   actor - the actor that wants to interact with the herb
	 * @return  an Actions object that contains all the valid actions that the actor can
	 * do to the Herb
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){
		if (actor.hasCapability(AICapability.PLAYER) && this.hasCapability(GroundCapability.HARVESTABLE)) {
			Actions allowableActions = new Actions();
			HarvestAction newHarvestAction = new HarvestAction(location,new Medicine());
			allowableActions.add(newHarvestAction);
			return allowableActions;
		}

		Actions allowableActions = new Actions();
		return allowableActions;
	}
	/**
	 * Returns the Ground name
	 * 
	 */
	public String toString() {
		return "Herb";
	}
	
}
