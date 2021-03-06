package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Quit game action for player
 * @author Jian Lim
 *
 */
public class QuitGameAction extends Action {
	
	/**
	 * QuitGameAction constructor
	 */
	public QuitGameAction () {};
	

	
	
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		map.removeActor(actor);
		return actor.toString() + " has given up and ends it all...";
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Quit Game";
	}

}
	

