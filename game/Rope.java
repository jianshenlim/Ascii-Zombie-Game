package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * Class that implement Rope item, it allows player to restrain actors
 * It is an extension of the existing Item class
 * @author Tri
 *
 */
public class Rope extends Item {
	/**the actors that can be restrained
	 */
	private ArrayList<Actor>restrainingTargets;
	/**
	    * Rope constructor	
		* create a Rope Item
		*/
	public Rope() {
		super("Rope", 'R', true);
		// TODO Auto-generated constructor stub
	}
	@Override
	/**
	 * Overridden method of tick. It will determine which actors can be restrained
	 */
	public void tick(Location currentLocation, Actor actor) {
		findTargetsForRestraining(currentLocation);
	}
	@Override
	/**
	 * Overridden method of tick. It will determine which actors can be restrained
	 */
	public void tick(Location currentLocation) {
		findTargetsForRestraining(currentLocation);
	}
	@Override 
	/**
	 * Overridden method of getAllowableActions. It will return restrainingAction objects
	 * that allows player to restrain actors
	 */
	public List<Action> getAllowableActions() {
		this.allowableActions = new Actions();
		for(Actor target:this.restrainingTargets) {
			RestrainingAction newRestrainingAction = new RestrainingAction(target,this);
			this.allowableActions.add(newRestrainingAction);
		}
		
		return this.allowableActions.getUnmodifiableActionList();
	}
	private void findTargetsForRestraining(Location currentLocation) {
		this.restrainingTargets = new ArrayList<Actor>();
		for (Exit exit : currentLocation.getExits()) {
			Location destination = exit.getDestination();

			// Game rule. You don't get to interact with tWphe ground if someone is standing
			// on it.
			if (destination.containsAnActor()) {
				if(destination.getActor().hasCapability(ZombieCapability.RESTRAINABLE)){
					this.restrainingTargets.add(destination.getActor());
				}
			} 
		}
	}

}
