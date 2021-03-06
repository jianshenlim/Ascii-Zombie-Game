package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;
/**
 * Class that simulates the zombie spell casting actions, extension of the existing Action class.
 * Allows for effects unique to only the MamboMarie(for now)
 * @author Tri
 *
 */
public class ZombieChantingAction extends Action {
	/** The number of zombie has been spawned by casting spell*/
	private static int numOfZomBieSpawn = 1;
	/** The name of the actor who casts the spell*/
	private String name;
	/** The number of zombie to be spawn*/
	private int numOfZomBie;
	/**
	 * ZombieChantingAction Constructor.
	 *
	 * @param whoCastThisSpell - The name of the actor who casts the spell
	 * @param numOfZombie - he number of zombie to be spawn
	 */
	public ZombieChantingAction(String whoCastThisSpell, int numOfZombie) {
		this.name = whoCastThisSpell;
		this.numOfZomBie = numOfZombie;
	}
	@Override
	/**
	 * Overridden method of execute, modified to simulate the casting zombie spell 
	 * @param actor - the actor that casts spell
	 * @param map - the map that the actor is currently in
	 * @return a new string indicates the action is executed successfully
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		ArrayList<Location> validSpotForSpawn = getValidSpotForSpawn(map);
		Probability newProbGenerator = new Probability();
		if (validSpotForSpawn.size() == 0) {
			return name + " tries to cast a Zombie Spawn Spell but there are no spots to spawn Zombie";
		}
		int numOfNewZombie = 0;
		while(validSpotForSpawn.size()!= 1 && numOfNewZombie !=this.numOfZomBie) {
			try {
				int index = newProbGenerator.randomNumber(0, validSpotForSpawn.size()-1);
				Location spotToSpawn = validSpotForSpawn.get(index);
				Zombie newZombie = new Zombie("Spawn Zombie "+ZombieChantingAction.numOfZomBieSpawn);
				spotToSpawn.addActor(newZombie);
				validSpotForSpawn.remove(index);
				numOfNewZombie++;
				ZombieChantingAction.numOfZomBieSpawn++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (validSpotForSpawn.size()== 1 && numOfNewZombie == this.numOfZomBie - 1) {
			int index = 0;
			Location spotToSpawn = validSpotForSpawn.get(index);
			Zombie newZombie = new Zombie("Spawn Zombie "+ZombieChantingAction.numOfZomBieSpawn);
			spotToSpawn.addActor(newZombie);
			validSpotForSpawn.remove(index);
			ZombieChantingAction.numOfZomBieSpawn++;
		}
		return this.name+" just casted a Zombie Spawn Spell Y'all!! Run!!!";
	}

	@Override
	/**
	 * Overridden method of menuDescription. This method returns of string that would be displayed
	 * as one of the options on the menu
	 * @param actor - the actor that casts the spell
	 * @return a new string that will be displayed on the menu

	 */
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return name + " casts a Zombie Spawn";
	}
	private ArrayList<Location> getValidSpotForSpawn(GameMap map) {
		NumberRange width = map.getXRange();
		NumberRange length = map.getYRange();
		ArrayList<Location> validSpot = new ArrayList<Location>();
		for (int x: width) {
			for(int y: length) {
				Location candidate = map.at(x, y);
				Zombie dummy = new Zombie("Dummy");
				boolean itCanBeThere = candidate.canActorEnter(dummy);
				if(itCanBeThere) {
					validSpot.add(candidate);
				}
			}
		}
		return validSpot;
		
	}

}
