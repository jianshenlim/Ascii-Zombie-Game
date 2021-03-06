package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Menu;

/**
 * Class to create submenus
 * @author Jian Lim
 *
 */
public class SubMenu extends Menu {

	private Display subMenuDisplay = new Display();
	
	/**
	 * Display a menu to the user and have them select an option.
	 * @param actor the Actor representing the player
	 * @param actions the Actions that the user can choose from
	 * @return the action selected by player
	 */
	public Action showMenu(Actor actor, Actions actions) {
		return super.showMenu(actor, actions, subMenuDisplay);
	}
	

	
	
}
