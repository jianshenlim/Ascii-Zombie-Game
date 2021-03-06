package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;


/**
 * Class that simulates a zombie's attack behavior, extension of AttackBehaviour class. 
 * uses the ZombieAttackAction class to generate new zombie attacks
 * 
 * @author Jian Lim
 *
 */
public class ZombieAttackBehaviour extends AttackBehaviour{
	
	private Zombie currentZombie;
	
	public ZombieAttackBehaviour(ZombieCapability attackableTeam, Zombie current) {
		super(attackableTeam);
		currentZombie = current;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Overridden getAction method that now returns a ZombieAttackAction if possible,
	 * Modified to return new ZombieAttackActions
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there an attackable Actor next to me?
		if (currentZombie.hasCapability(ZombieCapability.AGGRESSIVE)) {
			List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
			Collections.shuffle(exits);
			
			for (Exit e: exits) {
				if (!(e.getDestination().containsAnActor()))
					continue;
				if (e.getDestination().getActor().hasCapability(super.getAttackableTeam())) {
					return new ZombieAttackAction(e.getDestination().getActor(),currentZombie);
				}
			}
		}
		return null;
		
		
	}
}
