package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * Class that simulates moving between different maps, extension of the existing Action class.
 * Allows for effects unique to only the player(for now)
 * @author Tri
 *
 */
public class MoveToNewMapAction extends Action {
	/** Location in the other map the player will travel to*/
	private Location locationToMoveTo;
	/** The name of the map the player will travel to*/
	private String newMapName;
	/** The vehicle the player uses to travel*/
	private Vehicle vehicleUsed;
	/**
	 * CuringAction Constructor.
	 *
	 * @param locationToMoveTo - Location in the other map the player will travel to
	 * @param newMapName - The name of the map the player will travel to
	 * @param vehicleUsed - The vehicle the player uses to travel
	 */
	public MoveToNewMapAction(Location locationToMoveTo, String newMapName, Vehicle vehicleUsed) {
		this.locationToMoveTo = locationToMoveTo;
		this.newMapName = newMapName;
		this.vehicleUsed = vehicleUsed;
	}
	@Override
	/**
	 * Overridden method of execute, modified to simulate the movement between different maps
	 * @param actor - the actor that needs to move
	 * @param map - the map that the actor is currently in
	 * @return a new string indicates the action is executed successfully
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		if(!vehicleUsed.isInInventory()) {
			Location currentLocation = map.locationOf(actor);
			currentLocation.removeItem(vehicleUsed);
			locationToMoveTo.addItem(vehicleUsed);
		}
		vehicleUsed.changeMap();
		GameMap mapToMoveTo = locationToMoveTo.map();
		map.removeActor(actor);
		mapToMoveTo.addActor(actor, locationToMoveTo);
		return "Player just moves to "+newMapName;
	}

	@Override
	/**
	 * Overridden method of menuDescription. This method returns of string that would be displayed
	 * as one of the options on the menu
	 * @param actor - the actor that needs to travel
	 * @return a new string that will be displayed on the menu

	 */
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		
		return "Move to "+newMapName;
	}

}
