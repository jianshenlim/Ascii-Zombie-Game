package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
/**
 * Class that implement the farming behaviour that decides what farmers 
 * need to do to farm
 * It is an implementation of the Behaviour interface
 * @author Tri
 *
 */
public class FarmingBehaviour implements Behaviour {
	/** the name of the farmer that has this behaviour */
	private String farmerName = null;
	/**
	 * FarmingBehaviour Constructor.
	 *
	 * @param farmerName - the name of the farmer
	 */
	public FarmingBehaviour(String farmerName) {
		// TODO Auto-generated constructor stub
		if(farmerName == null) {
			throw new IllegalArgumentException("The Farmer name cannot be null/Farming Behaviour");
		}
		this.farmerName = farmerName;
	}

	@Override
	/**
	 * Overridden method of getAction, modified to return an Action base on
	 * the environment surrounds the farmer
	 * @param actor - the actor that needs to farm
	 * @param map - the map that the actor is currently in
	 * @return a new Action or a Null
	 */
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Probability newProbability = new Probability();
		Location actorLocation = map.locationOf(actor);
		Location dirtAround = getDirtAround(actorLocation);
		if (dirtAround != null && newProbability.isSuccessful(33)) {
			return new SowAction(dirtAround, new Crop());
		}
		Ground groundType = map.locationOf(actor).getGround();
		if (groundType.hasCapability(GroundCapability.FERTILIZABLE)) {
			return new FertilizeAction(actorLocation);
		}
		Action newHarvestAction = getHarvestActionAround(actor, actorLocation);
		if (newHarvestAction != null) {
			return newHarvestAction;
		}
		return null;
	}
	private Location getDirtAround(Location actorLocation) {
		List<Exit> exits = actorLocation.getExits();
		for (Exit exit : exits) {
			Location candidate = exit.getDestination();
			if (candidate.getGround().hasCapability(GroundCapability.SOWABLE)) {
				return candidate;
			}
		}
		return null;
	}
	private Action getHarvestActionAround(Actor actor,Location actorLocation) {
		List<Exit> exits = actorLocation.getExits();
		for (Exit exit : exits) {
			Location candidate = exit.getDestination();
			if (candidate.getGround().hasCapability(GroundCapability.HARVESTABLE) &&
					candidate.getGround().hasCapability(GroundCapability.FARMERENABLE)) {
				return candidate.getGround().allowableActions(actor, actorLocation, exit.getName()).get(0);
			}
		}
		if (actorLocation.getGround().hasCapability(GroundCapability.HARVESTABLE)&&
				actorLocation.getGround().hasCapability(GroundCapability.FARMERENABLE)) {
			return actorLocation.getGround().allowableActions(actor, actorLocation, null).get(0);
		}
		return null;
	}
	

}
