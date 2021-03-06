package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Base class for performing ranged attacks
 * @author Jian Lim
 *
 */
public class RangedAttackAction extends AttackAction{
	
	/**
	 * Aim class object 
	 */
	protected Aim aim;
	
	/**
	 * RangedAttackAction constructor
	 * @param target Current actor target
	 * @param range Integer value of RangedAttackAction range
	 * @param direction String literal of Aim direction
	 * @param targetCapability Capability of actor target
	 */
	public RangedAttackAction(Actor target,int range, String direction,ZombieCapability targetCapability) {
		super(target);
		try {
			aim = new Aim (range,direction,targetCapability);
		}catch(Exception e) {
			System.out.println("Invalid aiming parameters intered. The message was " + e.getMessage());
		}
	}
	
	
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		ArrayList<Actor> targets = aim.getTargets(actor, map);
		for (Actor a : targets) {
			result = actor.toString() + " makes a ranged attack on " + target;
			target.hurt(10);
			return result;
			
		}
		return result;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor.toString()+ " makes a ranged attack on " + target;

	}


}
