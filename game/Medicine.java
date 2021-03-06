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
 * Class that implement Medicine item, it allows player to turn zombie back to human
 * It is an extension of the existing Item class
 * @author Tri
 *
 */
public class Medicine extends Item {
	/**the potential zombies that can be cured*/
	private ArrayList<Actor>patients;
	/**
    * Medicine constructor	
	* create a Medicine Item
	*/
	public Medicine() {
		super("Medicine", 'K', true);
		// TODO Auto-generated constructor stub
	}
	@Override
	/**
	 * Overridden method of tick. It will determine which actors can be cured
	 */
	public void tick(Location currentLocation, Actor actor) {
		findTargetsForCuring(currentLocation);

	}
	@Override
	/**
	 * Overridden method of tick. It will determine which actors can be cured
	 */
	public void tick(Location currentLocation) {
		findTargetsForCuring(currentLocation);

	}
	@Override 
	/**
	 * Overridden method of getAllowableActions. It will return curingAction objects
	 * that allows player to cure zombies
	 */
	public List<Action> getAllowableActions() {
		this.allowableActions = new Actions();
		for(Actor target:this.patients) {
			CuringAction newCuringAction = new CuringAction(target,this);
			this.allowableActions.add(newCuringAction);
		}
		return this.allowableActions.getUnmodifiableActionList();
	}
	private void findTargetsForCuring(Location currentLocation) {
		this.patients = new ArrayList<Actor>();
		for (Exit exit : currentLocation.getExits()) {
			Location destination = exit.getDestination();

			// Game rule. You don't get to interact with tWphe ground if someone is standing
			// on it.
			if (destination.containsAnActor()) {
				Actor actor = destination.getActor();
				if(actor.hasCapability(ZombieCapability.RESTRAINED)&&
					actor.hasCapability(ZombieCapability.UNDEAD)){					
					this.patients.add(destination.getActor());
				}
			} 
		}
	}

}
