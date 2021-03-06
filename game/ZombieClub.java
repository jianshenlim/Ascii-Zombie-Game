package game;
import edu.monash.fit2099.engine.WeaponItem;
/**
 * Class that implement Zombie Club, it is a kind of weapon
 * it allows human to hit zombie with it
 * It is an extension of the existing WeaponItem class
 * @author Tri
 *
 */
public class ZombieClub extends WeaponItem{
	/**
	* create a Zombie Mace
	* @param name - the name of this Item
	* @param damage - the damage the zombie club can deal
	* @param verb - what it sounds when i smack you with it
	*/
	public ZombieClub(String name,int damage, String verb) {
		// TODO Auto-generated constructor stub
		super(name, '?', damage, verb);
		if(name == null || verb == null) {
			throw new IllegalArgumentException("Invalid input for Zombie Club");
		}
	}
	/**
	* create a Zombie Mace
	* @param name - the name of this Item
	*/
	public ZombieClub(String name) {
		// TODO Auto-generated constructor stub
		super(name, '?', 25, "smashes");
		if(name == null) {
			throw new IllegalArgumentException("Invalid name for Zombie Club");
		}
	}

}
