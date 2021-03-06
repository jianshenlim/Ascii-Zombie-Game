package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
/**
 * Class that implements fertilizing action, it allows farmer to fertilize when
 * the crop is unripe
 * It is an extension of the existing Action class
 * @author Tri
 *
 */
public class FertilizeAction extends Action{
	/** the location that the harvesting action takes place */
	private Location fertilizeLocation; 
	/**
	 * HarvestAction Constructor.
	 *
	 * @param fertilizeLocation - the location that the fertilize action takes place
	 */
	public FertilizeAction(Location fertilizeLocation) {
		// TODO Auto-generated constructor stub
		if(fertilizeLocation == null) {
			throw new IllegalArgumentException("Location for Fertilize Action cannot be null");
		}
		this.fertilizeLocation = fertilizeLocation;
	}

	@Override
	/**
	 * Overridden method of execute, modified to simulate the fertilize action
	 * @param actor - the actor that needs to fertilize
	 * @param map - the map that the actor is currently in
	 * @return a new string indicates the action is executed successfully
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Crop theCrop = (Crop) fertilizeLocation.getGround();
		theCrop.fertilize();
		return  actor.toString() + " fertilizes the unripe crop";
	}

	@Override
	/**
	 * Overridden method of menuDescription. This method returns of string that would be displayed
	 * as one of the options on the menu
	 * @param actor - the actor that needs to fertilize
	 * @return a new string that will be displayed on the menu
	 */
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor.toString() + " fertilizes unripe crop";
	}

}
