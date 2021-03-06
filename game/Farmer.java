package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class implements the Farmer entity in the game.
 * 
 * This Farmer class with farming behaviour that allows for 
 * Specialization of farming
 * @author Tri
 */
public class Farmer extends Human{
	/** Farming behaviour that allows farming specialization */
	private Behaviour farmingBehaviour;
	/** Wander behaviour that allows wandering */
	private Behaviour wanderBehaviour = new WanderBehaviour();
	/** Eat behaviour that allows farmers to eat when they are low on hitpoints */
	private Behaviour eatBehaviour;
	/**
	 *Farmer Constructor.
	 *
	 * @param name - the name of the farmer
	 */
	public Farmer(String name) {
		// TODO Auto-generated constructor stub
		super(name, 'F', 50,AICapability.NPC);
		if(name == null) {
			throw new IllegalArgumentException("The Farmer name cannot be null/Farmer");
		}
		this.farmingBehaviour = new FarmingBehaviour(name);
		this.eatBehaviour = new EatBehaviour(name);
	}
	@Override
	/**
	 * If a Farmer can farm, it will. If not, if it is low on hitpoints, it will eat. Else
	 * It will wander around
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Farmer is
	 * @param display the Display
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if(this.hasCapability(ZombieCapability.RESTRAINED)) {
			return new DoNothingAction();
		}
		Action eatAction = eatBehaviour.getAction(this, map);
		if (this.hitPoints<25 && eatAction != null) {
			return eatAction;
		}
		Action farmingAction = farmingBehaviour.getAction(this, map);
		if (farmingAction != null) {
			return farmingAction;
		}
		Action wanderAction = wanderBehaviour.getAction(this, map);
		if(wanderAction != null) {
			return wanderBehaviour.getAction(this, map);
		}
		return new DoNothingAction();
		
	}

}

