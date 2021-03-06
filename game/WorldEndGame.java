package game;

import java.util.HashMap;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

/**
 * Class representin the game world, extends World class, modified to allow for different game ending scenarios and different 
 * end game display messages
 * @author Jian Lim
 *
 */
public class WorldEndGame extends World{
	
	private HashMap<String,String> endGameConditions = new HashMap<String,String>();
	private String endGameCondition;
	private boolean displayMamboWarning = false;
	
	/**
	 * WorldEndGame constructor
	 * @param display the Display that will display this World.
	 */
	public WorldEndGame(Display display) {
		super(display);
		generateEndGameString();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (stillRunning()) {
					
			
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning()) {
					processActorTurn(actor);
					actor.tick(actorLocations.locationOf(actor));
					actor.resetHit();}
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}

		}
		display.println(endGameConditionMsg());
		display.println(endGameMessage());
	}
	
	
	private String endGameConditionMsg() {
		return endGameConditions.get(endGameCondition);
	};
	
	
	private void generateEndGameString() {
		endGameConditions.put("win01","All zombies and Mambo Marie are dead! You Win!");
		endGameConditions.put("lose01","All humans have died! You lose!");
		endGameConditions.put("lose02","You have died! You Lose!");
	}
	
	
	@Override
	public boolean stillRunning() {
		if (!checkMapsForZombie()&&!checkMapsForMamboMarie()) {
			endGameCondition = "win01";
			return false;
		}
		if (!checkMapsForHuman()) {
			endGameCondition = "lose01";
			return false;
		}

		if (!checkMapsForPlayer()){
			endGameCondition = "lose02";
			return false;
		}
		return true;
		
	} 
	
	private boolean checkMapsForHuman() {
		for (Actor actor : actorLocations) {
			if (actor.hasCapability(ZombieCapability.ALIVE) && actor.hasCapability(AICapability.NPC)) {return true;}
		}
		return false;
	}
	
	private boolean checkMapsForZombie() {
		for (Actor actor : actorLocations) {
			if (actor.hasCapability(ZombieCapability.UNDEAD)) {return true;}
		}
		return false;
	}
	
	private boolean checkMapsForPlayer() {
		if (actorLocations.contains(player)) {return true;}
		else {return false;}
	}
	private boolean checkMapsForMamboMarie() {
		for(GameMap map:gameMaps) {
			for (int x: map.getXRange()) {
				for(int y: map.getYRange()) {
					Location candidate = map.at(x, y);
					if(candidate.getGround().hasCapability(GroundCapability.MAMBOALIVE)) {
						if(!displayMamboWarning) {
							display.println("You feel an evil presence still...its not over yet!");
							displayMamboWarning = true;
						}
						return true;
					}
				}
			}
		}
		return false;
	}

}
