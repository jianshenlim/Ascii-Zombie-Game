package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

/**
 * Class that simulates the effect of aiming for ranged weapons
 * @author Jian Lim
 *
 */
public class Aim{
	
	private int aimRange = 0;
	private String aimDirection = "";
	private ZombieCapability targetType;
	private HashMap<String,String> directionStrings = new HashMap<String,String>();
	private int startXY;
	private int endXY;

	/**
	 * Constructor
	 * @param range interger value of max range
	 * @param direction String literal of aim direction
	 * @param targetCapability Capability of targets to be selected
	 * @throws Exception Exception thrown if invalid range or direction parameter entered
	 */
	public Aim(int range,String direction, ZombieCapability targetCapability)throws Exception{
		if (range < 0) {
			throw new Exception("Invalid range entered");
		}
		if (!direction.isEmpty() && directionStrings.containsKey(direction)) {
			throw new Exception("Invalid direction entered");
		}
		aimRange = range;
		aimDirection = direction;
		initalizeRangeVariable(direction);
		populateStringMap();
		targetType = targetCapability;
		
	}
	

	/**
	 * Returns an Arraylist of actors depending on the type parameters entered. If an empty string is input for direction, It returns all actors of the selected
	 * ZombieCapability within the input range. If a direction is input, instead returns all actors in range based on the direction that was input
	 * @param actor Current actor
	 * @param map Current GameMap of actor
	 * @return ArrayList of Actors
	 */
	public ArrayList<Actor> getTargets(Actor actor, GameMap map){
		String[] diagonal = {"NE","SE","NW","SW"};
		String[] vertical = {"N","S"};
		String[] horizontal = {"E","W"};
		if (this.aimDirection.isEmpty()) {return getTargetsInRange(actor,map);}
		else if (Arrays.asList(diagonal).contains(this.aimDirection)) {return getAllDiagonalTargets(actor,map);}
		else if (Arrays.asList(vertical).contains(this.aimDirection)) {return getAllVerticalTargets(actor,map);}
		else if (Arrays.asList(horizontal).contains(this.aimDirection)){return getAllHorizontalTargets(actor,map);}
		else {return new ArrayList();}
	} 
	
	/**
	 * Returns the full string string based on input string direction
	 * @return String of full direction
	 */
	public String getStringDirection() {
		return directionStrings.get(aimDirection);
	}
	
	/**
	 * Returns an ArrayList of Actors within the range of current Actor
	 * @param actor Current Actor
	 * @param map Current GameMap
	 * @return ArrayList of Actors
	 */
	private ArrayList<Actor> getTargetsInRange (Actor actor, GameMap map){
		ArrayList<Actor> targets = new ArrayList<Actor>();
		Location current = map.locationOf(actor);
		int currentX = current.x();
		int currentY = current.y();
		for (int x = currentX-aimRange; x <= currentX+ aimRange; x++) {
			for (int y = currentY-aimRange; y <= currentY+ aimRange; y++) {
				int validXLoc = this.checkXInBounds(x,map);
				int validYLoc = this.checkYInBounds(y, map);

				if (validXLoc != -1 && validYLoc != -1) {
					Location l = map.at(validXLoc,validYLoc);
					if (l.containsAnActor()) {
						Actor target = l.getActor();
						if (target.hasCapability(targetType)){targets.add(target);}}
					}
				}				
			}
		
		return targets;
	}
	
	/**
	 * Returns an ArrayList of Actors to the left or right of current Actor in a 90degree cone of range 3
	 * @param actor Current Actor
	 * @param map Current GameMap
	 * @return ArrayList of Actors
	 */
	private ArrayList<Actor> getAllHorizontalTargets(Actor actor, GameMap map){
		ArrayList<Actor> targets = new ArrayList<Actor>();
		Location current = map.locationOf(actor);
		int currentX = current.x();
		int currentY = current.y();
		int tempStartXY = this.startXY;
		int offsetStart;
		int offsetEnd;	
		while(currentX + tempStartXY <= currentX + this.endXY) {
			if (this.aimDirection == "E") {
				offsetStart = -tempStartXY;
				offsetEnd = tempStartXY;
			}else if (this.aimDirection == "W") {
				offsetStart = tempStartXY;
				offsetEnd = -tempStartXY;
			}else {return targets;}
			for (int y = currentY + offsetStart; y <= currentY + offsetEnd; y++) {
				int validXLoc = this.checkXInBounds(currentX+tempStartXY,map);
				int validYLoc = this.checkYInBounds(y, map);
				if (validXLoc != -1 && validYLoc != -1) {
					Location l = map.at(validXLoc,validYLoc);
					if (l.containsAnActor()) {
						Actor target = l.getActor();
						targets.add(target);}
					}
				}
				tempStartXY++;			
			}
		return targets;
	}
	
	
	/**
	 * Returns an ArrayList of Actors to the up or down of current Actor in a 90degree cone of range 3
	 * @param actor Current Actor
	 * @param map Current GameMap
	 * @return ArrayList of Actors
	 */
	private ArrayList<Actor> getAllVerticalTargets(Actor actor, GameMap map){
		ArrayList<Actor> targets = new ArrayList<Actor>();
		Location current = map.locationOf(actor);
		int currentX = current.x();
		int currentY = current.y();
		int tempStartXY = this.startXY;
		int offsetStart;
		int offsetEnd;
		while(currentY + tempStartXY <= currentY+ this.endXY) {
			if (this.aimDirection == "N") {
				offsetStart = tempStartXY;
				offsetEnd = -tempStartXY;
			}else if (this.aimDirection == "S") {
				offsetStart = -tempStartXY;
				offsetEnd = tempStartXY;
			}else {return targets;}
			
			for (int x = currentX + offsetStart; x <= currentX + offsetEnd; x++) {
				int validXLoc = this.checkXInBounds(x,map);
				int validYLoc = this.checkYInBounds(currentY+tempStartXY, map);
				if (validXLoc != -1 && validYLoc != -1) {
					Location l = map.at(validXLoc,validYLoc);
					if (l.containsAnActor()) {
						Actor target = l.getActor();
						targets.add(target);}
					}
			}
			tempStartXY++;
		}
		return targets;	
	}
	
		
	
	/**
	 * Returns an ArrayList of Actors in the area diagonal of current Actor in a 3x3 square based on chosen direction
	 * @param actor Current Actor
	 * @param map Current GameMap
	 * @return ArrayList of Actors
	 */
	private ArrayList<Actor> getAllDiagonalTargets(Actor actor, GameMap map){
		ArrayList<Actor> targets = new ArrayList<Actor>();
		Location current = map.locationOf(actor);
		int currentX = current.x();
		int currentY = current.y();
		int offsetStart;
		int offsetEnd;
		
		if (this.aimDirection == "NE" || this.aimDirection == "SE") {
			offsetStart = 1;
			offsetEnd = 3;
		}else if (this.aimDirection == "NW" || this.aimDirection == "SW") {
			offsetStart = -3;
			offsetEnd = -1;
		}else {return targets;}
		
		for (int y = currentY + this.startXY; y<= currentY + this.endXY; y++) {
			for (int x = currentX + offsetStart; x <= currentX + offsetEnd; x++) {
				int validXLoc = this.checkXInBounds(x,map);
				int validYLoc = this.checkYInBounds(y, map);
				if (validXLoc != -1 && validYLoc != -1) {
					Location l = map.at(validXLoc,validYLoc);
					if (l.containsAnActor()) {

						Actor target = l.getActor();
						targets.add(target);}
					}
				}		
			}
		return targets;
	}
	
	
	private void initalizeRangeVariable(String direction) {
		String[] northAndWestDirections = {"N","NE","NW","W"};
		String[] southAndEastDirections = {"S","SE","SW","E"};
		
		if (Arrays.asList(northAndWestDirections).contains(direction)) {
			this.startXY = -3;
			this.endXY = -1;
			}
		else if (Arrays.asList(southAndEastDirections).contains(direction)){
				this.startXY = 1;
				this.endXY = 3;
			}
		else {}
	}
	
	private int checkXInBounds(int x, GameMap map) {
		NumberRange xRange = map.getXRange();
		int minX = xRange.min();
		int maxX = xRange.max();
		
		if (x < minX) {return -1;}
		else if (x > maxX) {return -1;}
		else {return x;}
	}
	
	private int checkYInBounds(int y, GameMap map) {
		NumberRange yRange = map.getYRange();
		int minY = yRange.min();
		int maxY = yRange.max();
		
		if (y < minY) {return -1;}
		else if (y > maxY) {return -1;}
		else {return y;}
	}
	
	private void populateStringMap () {
		this.directionStrings.put("N","North");
		this.directionStrings.put("S","South");
		this.directionStrings.put("E","East");
		this.directionStrings.put("W","West");
		this.directionStrings.put("NE","North-East");
		this.directionStrings.put("SE","South-East");
		this.directionStrings.put("NW","North-West");
		this.directionStrings.put("SW","South-West");
	}
	
	
}
