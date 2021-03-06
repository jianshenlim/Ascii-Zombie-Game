package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
/**
 * Class implements the MamboMarie entity in the game.
 * 
 * This MamboMarie class with the ability to chant spells and wander around
 * @author Tri
 */
public class MamboMarie extends ZombieActor {
	/** MamboMariePortal that allows MamboMarie to appear and disappear */
	private MamboMariePortal portal;
	/** Turns attribute, it helps keep track of 
	 * how many turns left until the disappearance of the MamboMarie */
	private int turn = 0;
	/** Turns attribute, it helps keep track of 
	 * how many turns left until MamboMarie casts spell*/
	private int lifeCheckPoint = 0;
	/** Wander behaviour that allows wandering */
	private WanderBehaviour wanderBehaviour= new WanderBehaviour();
	/**
	 *MamboMarie Constructor.
	 *
	 * @param name - the name of the farmer
	 * @param portal - MamboMariePortal that allows MamboMarie to appear and disappear
	 */
	public MamboMarie(String name, MamboMariePortal portal) {
		super(name, 'M', 70, ZombieCapability.UNDEAD, AICapability.MAMBO);
		this.portal = portal;
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * At every 10th turn, MamboMarie casts a spell. Else
	 * It will wander around
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Farmer is
	 * @param display the Display
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// TODO Auto-generated method stub
		turn++;
		if (turn == 10) {
			turn = 0;
			lifeCheckPoint++;
			if (lifeCheckPoint == 3) {
				map.removeActor(this);
				portal.mamboDisappeared();
			}
			if(this.hasCapability(ZombieCapability.RESTRAINED)) {
				return new DoNothingAction();
			}
			return new ZombieChantingAction(name,5);

		}
		else {
			if(this.hasCapability(ZombieCapability.RESTRAINED)) {
				return new DoNothingAction();
			}
			return wanderBehaviour.getAction(this, map);
		}
	}
	@Override
	/**
	 * Do some damage to the MamboMarie.
	 *
	 * If the MamboMarie hitpoints go down to zero, it will signal the portal and 
	 * the potal will stop spawning new MamboMarie.
	 *
	 * @param points number of hitpoints to deduct.
	 */
	public void hurt(int points) {
		hitPoints -= points;
		if (hitPoints == 0) {
			portal.mamboKilled();
		}
		
	}
	
	@Override
	public String advancedHurt(GameMap map, int damage) {
		hurt(damage);
		return "";
	}

	@Override
	public void tick(Location currentLocation) {
		// TODO Auto-generated method stub	
	}
}
