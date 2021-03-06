package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	private Behaviour behaviour = new WanderBehaviour();
	private Behaviour eatBehaviour;

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 50, ZombieCapability.ALIVE,AICapability.NPC);
		this.eatBehaviour = new EatBehaviour(name);
	}
	
	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints, AICapability ai) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE,ai);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// FIXME humans are pretty dumb, maybe they should at least run away from zombies?
		if(this.hasCapability(ZombieCapability.RESTRAINED)) {
			return new DoNothingAction();
		}
		Action eatAction = eatBehaviour.getAction(this, map);
		if (this.hitPoints<25 && eatAction != null) {
			return eatAction;
		}
		return behaviour.getAction(this, map);
	}

	@Override
	public void tick(Location currentLocation) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String advancedHurt(GameMap map, int damage) {
		this.hurt(damage);
		return "";}



}
