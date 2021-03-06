package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class that creates DisplaySubMenu actions
 * @author Jian Lim
 *
 */
public abstract class DisplaySubMenu extends Action{
	
	/**
	 * Submenu object that holds actions that belong in the submenu
	 */
	protected SubMenu submenu = new SubMenu();
	/**
	 * Actions object containing an empty list of actions
	 */
	protected Actions allActions = new Actions();
	/**
	 * String literal of the menu shown on display
	 */
	protected String menuDescription;
	/**
	 * Action chosen by player
	 */
	protected Action chosenAction;
	
	
	/**
	 * DisplaySubMenu constructor
	 * @param menuDes String literal of submenu displayed
	 */
	public DisplaySubMenu(String menuDes) {
		menuDescription = menuDes;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		
		chosenAction = submenu.showMenu(actor, allActions);
		
		return chosenAction.execute(actor,map);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor.toString() + " " + menuDescription;
	}

	public void addAction(Action newAction) {
		allActions.add(newAction);
	}

}
