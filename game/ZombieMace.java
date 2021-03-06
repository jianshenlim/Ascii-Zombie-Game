package game;
import edu.monash.fit2099.engine.WeaponItem;
/**
 * Class that implement Zombie Mace, it is a kind of weapon
 * it allows human to hit zombie with it
 * It is an extension of the existing WeaponItem class
 * @author Tri
 *
 */
public class ZombieMace extends WeaponItem{
	/**
	* create a Zombie Mace
	* @param name - the name of this Item
	* @param damage - the damage the zombie mace can deal
	* @param verb - what it sounds when i smack you with it
	*/
	public ZombieMace(String name,int damage, String verb)  {
		// TODO Auto-generated constructor stub
		super(name, '%', damage, verb);
		if(name == null || verb == null) {
			throw new IllegalArgumentException("Invalid input for Zombie Mace");
		}
	}
	/**
	* create a Zombie Mace
	* @param name - the name of this Item
	*/
	public ZombieMace(String name)  {
		// TODO Auto-generated constructor stub
		super(name, '%', 25, "smashes");
		if(name == null) {
			throw new IllegalArgumentException("Zombie Mace name cannot be null");
		}
	}

}
