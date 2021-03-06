package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {
	
	private Menu menu = new Menu();
	private boolean gotHit = false;
	private boolean focused = false;
	private Actor focusTarget = null;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints,AICapability.PLAYER);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if(this.hasCapability(ZombieCapability.RESTRAINED)) {
			return new DoNothingAction();
		}
		if (lastAction.getNextAction() != null && !gotHit) {
			Action last = lastAction.getNextAction();
			if (last.getClass() == FocusTargetAction.class || lastAction.getClass() == FocusTargetAction.class) {
				focused = true;
			}else {
				focused = false;
			}
			actions.add(lastAction.getNextAction()); 
		}else {
			focused = false;
		}
		if (map.locationOf(this).getGround().hasCapability(GroundCapability.SOWABLE)){
			Action newSowAction = new SowAction(map.locationOf(this),new Herb());
			actions.add(newSowAction);
			Action newSowAction2 = new SowAction(map.locationOf(this),new Tree());
			actions.add(newSowAction2);

		}
		actions.add(new QuitGameAction());
		Action chosenAction = menu.showMenu(this, actions, display);
		return chosenAction;
}
	
	@Override
	public void setFocusTarget(Actor target) {
		focusTarget = target;
	}
	
	@Override
	public boolean isFocusTarget(Actor target) {
		if (target != focusTarget || focusTarget == null) {
			setFocusTarget(target);
			return false;
		}else {return true;}
		
	}
	@Override
	public void resetFocusTarget() {
			focusTarget = null;
	}
	
	@Override
	public boolean checkFocus() {
		return focused;
	}
	
	public boolean checkGotHit() {
		return gotHit;
	}
	

	@Override 
	public void hurt(int points) {
		hitPoints -= points;
		focused = false;
		gotHit = true;
	}
	
	@Override
	public void resetHit() {
		gotHit = false;
	}


}
	