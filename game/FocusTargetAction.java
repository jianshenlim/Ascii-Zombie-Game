package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * Action that allows actors to focus on a particular target
 * @author Jian Lim
 *
 */
public class FocusTargetAction extends Action{
	
	private RangedWeapon currentRWeapon;
	private Actor target;
	
	/**
	 * FocusTargetAction constructor
	 * @param actor Current actor target
	 * @param current Current RangedWeapon of actor
	 */
	public FocusTargetAction(Actor actor, RangedWeapon current) {
		target =  actor;
		currentRWeapon = current;
	}
	

	@Override
	public String execute(Actor actor, GameMap map) {
		
		if (!actor.isFocusTarget(target)) {
			currentRWeapon.resetFocus();
			actor.setFocusTarget(target);
			currentRWeapon.incrementFocus();
		}else {
			currentRWeapon.incrementFocus();
		}
		return actor.toString() + " focuses on " + target;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor.toString()+ " focuses aim at " + target;

	}
	
	/**
	 * Returns a new focusTargetAction of current weapon focus level is less than 2, else return a new shootTargetAction
	 */
	@Override
	public Action getNextAction() {
		if (currentRWeapon.getFocus()<2) {
			return new FocusTargetAction(target,currentRWeapon);
		}
		else {
		return new ShootTargetAction(target,currentRWeapon);
		}
	}

}
