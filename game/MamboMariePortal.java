package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

/**
 * Class that implement MamboMariePortal, it allows MamboMarie to appear and disappear on the map
 * It is an extension of the existing Ground class
 * @author Tri
 *
 */
public class MamboMariePortal extends Ground {
	/** A boolean that indicates the mambo is currently on the map */
	private boolean mamboInTheMap = false;
	/** A boolean that indicates the mambo is dead */
	protected boolean mamboKilled = false;
	/**
	 * MamboMariePortal Constructor.
	 */
	public MamboMariePortal() {
		super('X');
		addCapability(GroundCapability.MAMBOALIVE);
		// TODO Auto-generated constructor stub
	}
	@Override
	/**
	 * Overridden method of tick to simulate the appearance of MamboMarie
	 * @param location - the location of the MamboMariePortal		
	 */
	public void tick(Location location) {
		if (!this.mamboInTheMap && !this.mamboKilled) {
			Probability probabilityGenerator = new Probability();
			boolean spawn = probabilityGenerator.isSuccessful(5);
			if (spawn) {
				Location spawnSpot = decideSpawnSpot(location.map());
				MamboMarie newMamboMarie = new MamboMarie("Mambo Marie",this);
				location.map().addActor(newMamboMarie, spawnSpot);
				this.mamboInTheMap = true;
			}
		}
		else if (this.mamboKilled) {
			location.setGround(new Dirt());
		}
		
	}
	@Override
	/**
	 * Overridden method of allowableActions. If the player stands next to the portal
	 * he/she can choose to summon the Mambo if it is still alive that is
	 * @param   actor - the actor that wants to interact with the crop
	 * @return  an Actions object that contains all the valid actions that the actor can
	 * do the crop
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
		if(actor.hasCapability(AICapability.PLAYER) && !mamboInTheMap && !mamboKilled) {
			Location spawnSpot = decideSpawnSpot(location.map());
			actions.add(new SummonMamboMarie(spawnSpot, this));
		}
		return actions;
	}
	
	private Location decideSpawnSpot(GameMap map) {
		Probability probabilityGenerator = new Probability();
		ArrayList<Location> candidates = getValidSpotForSpawn(map);
		if (candidates.size()>1) {
			try {
				return candidates.get(probabilityGenerator.randomNumber(0,candidates.size()-1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (candidates.size() == 1) {
			return candidates.get(0);
		}
		return null;
		
	}
	protected void mamboKilled() {
		this.mamboKilled = true;
		this.removeCapability(GroundCapability.MAMBOALIVE);
	}
	protected void mamboAppeared() {
		this.mamboInTheMap = true;	
	}
	protected void mamboDisappeared() {
		this.mamboInTheMap = false;
	}
	private ArrayList<Location> getValidSpotForSpawn(GameMap map) {
		NumberRange width = map.getXRange();
		NumberRange length = map.getYRange();
		ArrayList<Integer> yBorderValues = new ArrayList<Integer>();
		yBorderValues.add(length.min());
		yBorderValues.add(length.max());
		ArrayList<Integer> xBorderValues = new ArrayList<Integer>();
		xBorderValues.add(width.min());
		xBorderValues.add(width.max());

		ArrayList<Location> validSpot = new ArrayList<Location>();
		for (int x: width) {
			for(int y: yBorderValues) {
				Location candidate = map.at(x, y);
				MamboMarie dummy = new MamboMarie("Dummy",this);
				boolean itCanBeThere = candidate.canActorEnter(dummy);
				if(itCanBeThere) {
					validSpot.add(candidate);
				}
			}
		}
		for (int x: xBorderValues) {
			for(int y: length) {
				if (y == length.min() || y == length.max()) {
					continue;
				}
				else {
					Location candidate = map.at(x, y);
					MamboMarie dummy = new MamboMarie("Dummy",this);
					boolean itCanBeThere = candidate.canActorEnter(dummy);
					if(itCanBeThere) {
						validSpot.add(candidate);
					}
				}
			}
		}
		return validSpot;
		
	}
}
