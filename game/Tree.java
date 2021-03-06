package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A tree that starts as a sapling and grows into a large tree.
 * 
 * @author ram
 *
 */
public class Tree extends Ground {
	private int age = 0;
	public Tree() {
		super('+');

	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20) {
			displayChar = 'T';
			addCapability(GroundCapability.HARVESTABLE);
		}
	}
	@Override
	/**
	 * Overridden method of allowableActions. It will decide which actions to return
	 * @param   actor - the actor that wants to interact with the tree
	 * @return  an Actions object that contains all the valid actions that the actor can
	 * do to the tree
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){
		if (actor.hasCapability(AICapability.PLAYER)&&this.hasCapability(GroundCapability.HARVESTABLE)) {
			Actions allowableActions = new Actions();
			HarvestAction newHarvestAction = new HarvestAction(location,new Vine());
			allowableActions.add(newHarvestAction);
			return allowableActions;
		}
		return new Actions();
	}
	@Override
	public String toString() {
		return "Tree";
	}
}
