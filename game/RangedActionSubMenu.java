package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class that displayes all avaliable actions to perform on a chosen target
 * @author Jian Lim
 *
 */
public class RangedActionSubMenu extends DisplaySubMenu{
	
	private Action chosenAction;
	
	/**
	 * RangedActionSubMenu constructor
	 * @param currentTarget Current actor target
	 * @param current Actors current RangedWeapon
	 */
	public RangedActionSubMenu(Actor currentTarget, RangedWeapon current) {
		super("targets "+ currentTarget);
		this.addAction(new FocusTargetAction(currentTarget,current));
		this.addAction(new ShootTargetAction(currentTarget,current));
				
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		
		chosenAction = submenu.showMenu(actor, allActions);
		
		return chosenAction.execute(actor,map);
	}
	
	
	@Override
	public Action getNextAction() {
		return chosenAction.getNextAction();
	}


}
