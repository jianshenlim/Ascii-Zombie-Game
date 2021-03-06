package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

/**
 * Class that creates RangedActionSubMenus for each target in range
 * @author Jian Lim
 *
 */
public class RangedTargetSubMenu extends DisplaySubMenu{
	
	private RangedWeapon currentRWeapon;
	private Aim aim;
	
	/**
	 * RangedTargetSubMenu constructor
	 * @param current Actors current RangedWeapon
	 */
	public RangedTargetSubMenu(RangedWeapon current) {
		super("selects target with "+ current);
		currentRWeapon = current;
		try {
			aim = new Aim(this.currentRWeapon.getMaxRange(),"",ZombieCapability.UNDEAD);
		}catch (Exception e) {
			System.out.println("Invalid aiming parameters intered. The message was " + e.getMessage());
		}
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		this.allActions.clear();
		getTargets(actor,map);
		chosenAction = submenu.showMenu(actor, allActions);
		return chosenAction.execute(actor,map);
	}
	
	private void getTargets(Actor actor, GameMap map) {
		ArrayList<Actor> targets = aim.getTargets(actor, map);
		for (Actor a : targets) {
			this.addAction(new RangedActionSubMenu(a,this.currentRWeapon));	
		}
		this.addAction(new DoNothingAction());
		
		
	}
	

	@Override
	public Action getNextAction() {
		return chosenAction.getNextAction();
	}
	
}
