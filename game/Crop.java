package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Class that implement crop, it allows farmer to sow, fertilize and harvest when it is ripe
 * It is an extension of the existing Ground class
 * @author Tri
 *
 */
public class Crop extends Ground{
	/** Turns attribute, it helps keep track of 
	 * how many turns left until the crop turns ripe */
	private int turns = 0;
	/** Ripe attribute indicates whether the crop is ripe*/
	/**
	 * Crop Constructor.
	 */
	public Crop() {
		// TODO Auto-generated constructor stub
		super(']');
		addCapability(GroundCapability.FERTILIZABLE);
		addCapability(GroundCapability.FARMERENABLE);

	}
	
	@Override
	/**
	 * Overridden method of tick to simulate the growing of crop, after 20 turns, crop will turn 
	 * ripe and the display char will change
	 * @param map - the location of the crop		
	 */
	public void tick(Location location) {
		this.turns ++;
		if (this.turns == 20) {
			this.displayChar = '}';
			addCapability(GroundCapability.HARVESTABLE);
			removeCapability(GroundCapability.FERTILIZABLE);
		}
	}
	@Override
	/**
	 * Overridden method of allowableActions. It will decide which actions to return
	 * @param   actor - the actor that wants to interact with the crop
	 * @return  an Actions object that contains all the valid actions that the actor can
	 * do the crop
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){
		if (this.hasCapability(GroundCapability.HARVESTABLE)) {
			Actions allowableActions = new Actions();
			HarvestAction newHarvestAction = new HarvestAction(location,new Food());
			allowableActions.add(newHarvestAction);
			return allowableActions;
		}
		return new Actions();
	}
	@Override
	/**
	 * Returns the Ground name
	 * 
	 */
	public String toString() {
		return "Crop";
	}
	/**
	 * Increase the number of turns past to simulate the fertilization 	
	 */
	protected void fertilize() {
		this.turns += 10;
		if (this.turns >= 20) {
			this.displayChar = '}';
			this.addCapability(GroundCapability.HARVESTABLE);
		}
	}
}
