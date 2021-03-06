package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * Class that implement Zombie Egg. It simulates the transition from a dead
 * body to a new zombie
 * It is an extension of the existing Item class
 * @author Tri
 *
 */
public class ZombieEgg extends Item{
	
	/** Turns attribute, it helps keep track of 
	 * how many turns left until the zombie egg turns into a zombie */
	private int turns = 0;
	private String humanName;
	/**
	* create a ZombieEgg Item
	* @param killedHumanName - the name of the dead human
	*/
	public ZombieEgg(String killedHumanName) {
		// TODO Auto-generated constructor stub
		super("Zombie Egg", 'E', false);
		if(killedHumanName == null) {
			throw new IllegalArgumentException("The killed Human name cannot be null");
		}
		humanName = killedHumanName;
	}

	@Override
	/**
	 * Overridden method of tick to simulate the growing of crop, after 5 turns, the egg 
	 * will turn to a zombie
	 * @param currentLocation - the current location of the egg	
	 */
	public void tick(Location currentLocation) {
		turns ++;
		Zombie newZombie = new Zombie(humanName);
		if(turns > 10 && currentLocation.canActorEnter(newZombie)) {
			currentLocation.addActor(newZombie);
			currentLocation.removeItem(this);
		}
		else {
			newZombie = null;
		}
	}

}
