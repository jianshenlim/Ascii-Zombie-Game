package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;



/**
 *Class that simulates a zombie's hunt behavior, extends existing HuntBehaviour.
 *Modified to allow a zombie's limb condition to affect the behavior
 * @author Jian
 *
 */
public class ZombieHuntBehaviour extends HuntBehaviour{
	
	private Zombie currentZombie;

	/**
	 * ZombieHuntBehaviour constructor
	 * @param cls Attackable class
	 * @param range Integer value of zombies hunt range
	 * @param current Current Zombie
	 */
	public ZombieHuntBehaviour(Class<?> cls, int range, Zombie current) {
		super(cls, range);
		currentZombie = current;
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Overriden getAction method, modified to allow leg condition to affect the 
	 * zombie's behavior. if both legs are destroyed the zombie cannot hunt, if a single leg is
	 * destroyed, returns an alternating hunt action every game turn.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		if (currentZombie.hasCapability(ZombieCapability.MOBILITY)) {
			return super.getAction(actor, map);		
		}else {
			return null;	
		}
		
	
	
}

}
