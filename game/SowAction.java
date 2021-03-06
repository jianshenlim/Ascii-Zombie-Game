package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
/**
 * Class that implements sowing action, it allows farmer to sow when
 * the there is dirt around
 * It is an extension of the existing Action class
 * @author Tri
 *
 */
public class SowAction extends Action{
	/** the location that the sowing action takes place */
	private Location sowLocation;
	/** the new Ground that the sowing action replaces with */
	private Ground newSow;
	/**
	 * SowAction Constructor.
	 *
	 * @param sowLocation - the location that the sowing action takes place
	 * @param newSow - the new Ground that the sowing action replaces with
	 */
	public SowAction(Location sowLocation, Ground newSow) {
		// TODO Auto-generated constructor stub
		if(sowLocation == null) {
			throw new IllegalArgumentException("Location for Sow Action cannot be null");
		}
		this.sowLocation = sowLocation;
		this.newSow = newSow;
	}

	@Override
	/**
	 * Overridden method of execute, modified to simulate the sow action
	 * @param actor - the actor that needs to sow
	 * @param map - the map that the actor is currently in
	 * @return a new string indicates the action is executed successfully
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		this.sowLocation.setGround(this.newSow);
		return actor.toString() + " sows new "+ this.newSow.toString();
	}

	@Override
	/**
	 * Overridden method of menuDescription. This method returns of string that would be displayed
	 * as one of the options on the menu
	 * @param actor - the actor that needs to sow
	 * @return a new string that will be displayed on the menu
	 */
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Sow new " + this.newSow.toString();
	}

}
