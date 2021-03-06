package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

/**
 * A class that generates an AttackAction if the current Actor is standing
 * next to an Actor that they can attack.
 * 
 * @author ram
 *
 */
public class AttackBehaviour implements Behaviour {
	private ZombieCapability attackableTeam;
	private Actor player = null;
	
	/**
	 * Constructor.
	 * 
	 * Sets the team (i.e. ZombieCapability) that the owner of this
	 * Behaviour is allowed to attack.
	 * 
	 * @param attackableTeam Team descriptor for ZombieActors that can be attacked
	 */
	public AttackBehaviour(ZombieCapability attackableTeam) {
		this.attackableTeam = attackableTeam;
	}

	/**
	 * Returns an AttackAction that attacks an adjacent attackable Actor.
	 * 
	 * Actors are attackable if their ZombieCapability matches the 
	 * "undeadness status" set 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there an attackable Actor next to me?
		if (actor.hasCapability(ZombieCapability.AGGRESSIVE))
		{
			List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
			Collections.shuffle(exits);
			
			for (Exit e: exits) {
				if (!(e.getDestination().containsAnActor()))
					continue;
				if (e.getDestination().getActor().hasCapability(attackableTeam)) {
					return new AttackAction(e.getDestination().getActor());
				}
			}
		}
		
		return null;
		
	}
	
	public ZombieCapability getAttackableTeam() {
		return attackableTeam;
	}
	
//	private Actor getPlayer(GameMap map) {
//		NumberRange xCoord = map.getXRange();
//		NumberRange yCoord = map.getYRange();
//		for (int x : xCoord) {
//			for (int y : yCoord) {
//				Location currentLoc = map.at(x, y);
//				if (map.isAnActorAt(currentLoc)) {
//					Actor currentActor = map.getActorAt(currentLoc);
//					if (currentActor.hasCapability(AICapability.PLAYER)) {
//						return currentActor;
//					}	
//				}	
//			}
//		}
//		return null;	
//	}



}
