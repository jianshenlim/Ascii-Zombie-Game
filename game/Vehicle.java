package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * Class that implement Vehicle item, it allows player to move between maps
 * It is an extension of the existing Item class
 * @author Tri
 *
 */
public class Vehicle extends Item {
	/**the location in town that the player will be transported to*/
	private Location locationInTown;
	/**the location in map that the player will be transported to*/
	private Location locationInMap;
	/**the default location in town that the player will be transported to*/
	private Location defaultLocationInMap;
	/**the default location in map that the player will be transported to*/
	private Location defaultLocationInTown;
	/**the boolean indicates whether the player is in town*/
	private boolean inTown = false;
	/**the boolean indicates whether the vehicle is in inventory*/
	private boolean inInventory = false;
	/**
	    * Vehicle constructor	
		* create a Vehicle Item
		* @param name - the name of the vehicle
		* @param locationInTown - the location in town that the player will be transported to
		* @param locationInMap - the location in map that the player will be transported to
	*/
	public Vehicle(String name,Location locationInTown, Location locationInMap) {
		super(name, 'V', true);
		this.locationInTown = locationInTown;
		this.locationInTown.setGround(new VehiclePark());
		this.defaultLocationInMap = locationInMap;
		this.defaultLocationInMap.setGround(new VehiclePark());
		this.defaultLocationInTown = locationInTown;
		this.locationInMap = locationInMap;
	}
	@Override 
	/**
	 * Overridden method of tick. It will determine the destination and if the vehicle is in inventory
	 */
	public void tick(Location currentLocation, Actor actor) {
		if (inTown == false) {
			this.locationInMap = currentLocation;
		}
		else {
			this.locationInTown = currentLocation;
		}
		this.inInventory = true;
	}
	@Override 
	/**
	 * Overridden method of tick. It will determine if the vehicle is in inventory
	 */
	public void tick(Location currentLocation) {
		this.inInventory = false;

	}

	@Override 
	/**
	 * Overridden method of getAllowableActions. It will return MoveToNewMapAction objects
	 * that allows player to move to a different map
	 */
	public List<Action> getAllowableActions() {
		this.allowableActions = new Actions();
		MoveToNewMapAction moveToMapAction;
		if(inTown) {
			if (locationInMap.containsAnActor() || !isInInventory()) {
				moveToMapAction = new MoveToNewMapAction(this.defaultLocationInMap,"Compound",this);
			}
			else {
				moveToMapAction = new MoveToNewMapAction(this.locationInMap,"Compound",this);
			}
			
		}
		else {
			if (locationInTown.containsAnActor() || !isInInventory()) {
				moveToMapAction = new MoveToNewMapAction(this.defaultLocationInTown,"Town",this);
			}
			else {
				moveToMapAction = new MoveToNewMapAction(this.locationInTown,"Town",this);
			}
		}
		this.allowableActions.add(moveToMapAction);
		return allowableActions.getUnmodifiableActionList();
	}
	protected boolean isInInventory() {
		return this.inInventory;
	}
	protected void changeMap() {
		this.inTown = !this.inTown;
	}
	

}
