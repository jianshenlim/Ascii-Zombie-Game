package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * Class that summons the MamboMarie from the portal, extension of the existing Action class.
 * Allows for effects unique to only the player(for now)
 * @author Tri
 *
 */
public class SummonMamboMarie extends Action{
	/** The location that the MamboMarie will appear*/
	private Location spawnLocation;
	/** The portal of the MamboMarie*/
	private MamboMariePortal portal;
	/**
	 * SummonMamboMarie Constructor.
	 *
	 * @param spawnLocation - The location that the MamboMarie will appear
	 * @param portal - The portal of the MamboMarie
	 */
	public SummonMamboMarie(Location spawnLocation,MamboMariePortal portal) {
		// TODO Auto-generated constructor stub
		this.portal = portal;
		this.spawnLocation = spawnLocation;
	}

	@Override
	/**
	 * Overridden method of execute, modified to simulate the summoning  actions 
	 * @param actor - the actor that summons
	 * @param map - the map that the actor is currently in
	 * @return a new string indicates the action is executed successfully
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		map.addActor(new MamboMarie("Mambo Marie",portal), spawnLocation);
		portal.mamboAppeared();
		return actor.toString()+" has summoned the Mambo Marie";
	}

	@Override
	/**
	 * Overridden method of menuDescription. This method returns of string that would be displayed
	 * as one of the options on the menu
	 * @param actor - the actor that summons
	 * @return a new string that will be displayed on the menu

	 */
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Summon the Mambo Marie";
	}

}
