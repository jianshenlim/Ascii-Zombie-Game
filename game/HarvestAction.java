package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * Class that implements harvesting action, it allows farmer to harvest when
 * the crop is ripe
 * It is an extension of the existing Action class
 * @author Tri
 *
 */
public class HarvestAction extends Action{
	/** the location that the harvesting action takes place */
	private Location harvestLocation; 
	/** the new Item that would be added to the actor's inventory */
	private Item newItem;
	/**
	 * HarvestAction Constructor.
	 *
	 * @param harvestLocation - the location that the harvest action takes place
	 * @param newItem - the new Item that would be added to the actor's inventory
	 */
	public HarvestAction(Location harvestLocation, Item newItem) {
		// TODO Auto-generated constructor stub
		if(harvestLocation == null) {
			throw new IllegalArgumentException("Location for Harvest Action cannot be null");
		}
		this.harvestLocation = harvestLocation;
		this.newItem = newItem;
	}

	@Override
	/**
	 * Overridden method of execute, modified to simulate the harvest action
	 * @param actor - the actor that needs to harvest
	 * @param map - the map that the actor is currently in
	 * @return a new string indicates the action is executed successfully
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		
		harvestLocation.setGround(new Dirt());
		if(!actor.hasCapability(AICapability.PLAYER)) {
			harvestLocation.addItem(newItem);
		}
		else {
			actor.addItemToInventory(newItem);
		}
		return actor.toString() + " harvests for" + newItem.toString();
	}

	@Override
	/**
	 * Overridden method of menuDescription. This method returns of string that would be displayed
	 * as one of the options on the menu
	 * @param actor - the actor that needs to harvest
	 * @return a new string that will be displayed on the menu
	 */
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return  actor.toString() + " harvests for " + newItem.toString();
	}

}
