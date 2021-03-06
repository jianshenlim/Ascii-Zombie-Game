package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Capabilities;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
/**
 * A Zombie.
 * 
 * This Zombie class with limbs system that allows for different actions based on which limbs are destroyed.
 * 
 * @author ram, modified by Jian Lim
 * 
 *
 */
public class Zombie extends ZombieActor {
	private int curedTurns = 0;
	private Probability prob = new Probability();
	/**
	 * ArrayList of integer values 0-3 representing the 4 zombie limbs
	 */
	private ArrayList<Integer> limbList = new ArrayList<Integer>();
	/**
	 * Array of size 4 with boolean values, representing the presence of the 4 zombie limbs
	 */
	private boolean[] limbs = new boolean [4];
	
	/**
	 * boolean value indicating whether a zombie can move
	 */
	/**
	 * boolean value indicating whether a zombie can pick up items
	 */
	private boolean validPickUp = true;
		
	private Behaviour[] behaviours = {
			new ZombieAttackBehaviour(ZombieCapability.ALIVE,this),
			new ZombiePickUpBehaviour(this),
			new ZombieHuntBehaviour(Human.class, 10,this),
			new ZombieWanderBehaviour(this)
	};
	
	
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD,AICapability.NPC);
		for (int i = 0; i< 4;i++) {
			limbList.add(i);
			limbs[i] = true;
		}
	}
	
	
	/**
	 * Override of getIntrinsicWeapon in actor class, returns a bite or punch weapon
	 * based on probability which varies on limb condition. Different weapons have different
	 * hit chance probabilities
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		IntrinsicWeapon punch = new IntrinsicWeapon(10, "punches") ;
		IntrinsicWeapon bite = new IntrinsicWeapon(20, "bites");
		int punchProb;
		if (haveSingleArm()) {
			punchProb = 25;
		}else {punchProb = 50;
		}
		
		if (!haveBothArms()) {return bite;}
		else {
			if (prob.isSuccessful(punchProb)) {return punch;}
				else{return bite;}
		}
	}

	/**
	 * If a Zombie can attack, it will.  If not, if there is a weapon on the ground that it can pick up
	 * it will, if not it will chase any human within 10 spaces, barring that If no humans are close enough 
	 * it will wander randomly. Zombies also have a percentage chance to display a message to console display
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (prob.isSuccessful(10)){
			display.println(this.toString() + " " +"mutters Braaaaains......");
		}
		setActionCapability();
		if(this.hasCapability(ZombieCapability.RESTRAINED)) {
			return new DoNothingAction();
		}
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
	

	/**
	 * Method used to damage zombie actors, calls actor class hurt method, to reduce zombie health
	 * and calls class methods to destroy zombie limbs and insert the destroyed limbs as weapons 
	 * onto the ground
	 * @param map current game map
	 * @param damage damage to inflicted on zombie
	 * @return string literal of damage outcome description
	 */
	@Override
	public String advancedHurt(GameMap map, int damage){
		Location randomDropLocation = getDropLocation(this,map);
		String result = "";
		
		this.hurt(damage);
		if (prob.isSuccessful(25)){
			
			int limbNumber;
			try {	
				limbNumber = this.destroyLimb();
			}catch(Exception e) {
				System.out.println("Damaging Zombie failed. The message was " + e.getMessage());
				limbNumber = 4;
			}
			if (limbNumber <=1) {
				ZombieArm drop = new ZombieArm("zombie arm");
				result = "cuts off "+ this.toString() + "'s arm!";
				randomDropLocation.addItem(drop);
				
				if (!this.getInventory().isEmpty()) {
					if (prob.isSuccessful(50) || !this.haveBothArms()) {
						(this).dropAllInventory(this,map);
						result +=  System.lineSeparator() + this.toString() + "drops its weapon!";
					}	
				}								
			}else if (limbNumber >=2 && limbNumber <=3) {
				ZombieLeg drop = new ZombieLeg("zombie leg");
				result = "cuts off " + this.toString() + "'s leg!";
				randomDropLocation.addItem(drop);		
			}else {return result;}
		}
		return result;	
	}
	
	
	/**
	 * Method used to randomly destroy a zombie limb, returns a integer value
	 * of 0 or 1 to represent an arm, 2 or 3 to represent a leg and 4 to represents
	 * no limbs to damage
	 * @return integer value representing the corresponding limb
	 * @throws Exception if invalid limb number returned
	 */
	private int destroyLimb() throws Exception {
		int limbsSize = limbList.size();
		if (limbsSize > 0) {
			int index = prob.randomNumber(0,limbsSize);
			int limbNumber = limbList.get(index);
			
			if (limbNumber < 0 || limbNumber > 3) {
				throw new Exception("Invalid Limb Number to drop returned");
			}
			
			setLimb(limbNumber);
			limbList.remove(index);
			return limbNumber;
		}
		else {return 4;}
		
	}
	
	/**
	 * Method that sets the status of a zombie limb to destroyed
	 * @param limbindex integer value corresponding to limb number
	 */
	private void setLimb(int limbindex) {
		limbs[limbindex] = false;
	}
	
	/**
	 * Method that checks to see if zombie has both arms
	 * @return boolean value representing status of arms
	 */
	private boolean haveBothArms(){
		if (limbs[0] == true && limbs[1] == true) {
			return true;
		}else {return false;}
	}
	/**
	 * Method that checks to see if zombie has only one arm
	 * @return boolean value representing status of arm
	 */
	private boolean haveSingleArm(){
		if (limbs[0] == true ^ limbs[1] == true) {
			return true;
		}else {return false;}
	}
	/**
	 * Method that checks to see if zombie has only one leg
	 * @return boolean value representing status of leg
	 */
	private boolean haveSingleLeg(){
		if (limbs[2] == true ^ limbs[3] == true) {
			return true;
		}else {return false;}
	}
	
	/**
	 * Method that checks to see if the zombie has both legs
	 * @return boolean value representing status of legs
	 */
	private boolean haveBothLegs(){
		if (limbs[2] == true && limbs[3] == true) {
			return true;
		}else {return false;}
	}
	
	/**
	 * Method that drops all of the zombie's inventory
	 * @param target current zombie
	 * @param map current game map
	 */
	private void dropAllInventory(Actor target, GameMap map){
		Actions dropActions = new Actions();
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)		
			drop.execute(target, map);
		
	}
	
	/**
	 * Method that checks if a zombie is allowed to pick up items
	 * @return boolean value representing if zombie can pick up weapon
	 */
	public boolean getValidPickUp() {
		return validPickUp;
	}
	
	/**
	 * Method that sets the capability of a zombie's action based
	 * on the condition of the zombie's limbs
	 */
	private void setActionCapability() {
		if (haveSingleLeg()) {
			if(this.hasCapability(ZombieCapability.MOBILITY)) {
				this.removeCapability(ZombieCapability.MOBILITY);;
			}else {
				this.addCapability(ZombieCapability.MOBILITY);;
			}		}
		else if (haveBothLegs() ){
			if (this.hasCapability(ZombieCapability.MOBILITY) == false) {
				this.addCapability(ZombieCapability.MOBILITY);
			}
		}
		else if(!haveSingleLeg()&&!haveBothLegs()){
			if (this.hasCapability(ZombieCapability.MOBILITY) == true) {
				this.removeCapability(ZombieCapability.MOBILITY);
			}
		}
		if (!haveBothArms()) {
			validPickUp = false;}

	}
	
	/**
	 * Method the gets all available locations around current zombie
	 * @param actor Current zombie
	 * @param map Current game map
	 * @return Valid drop location
	 */
	private Location getDropLocation(Actor actor, GameMap map) {
		ArrayList<Location> dropLocations = new ArrayList<Location>();
		for (Exit exit : map.locationOf(actor).getExits()) {
	        Location destination = exit.getDestination();
	        if (destination.canActorEnter(actor)) {
	        	dropLocations.add(exit.getDestination());
	        }
		}
		if (!dropLocations.isEmpty()) {
			int noOfdDropLocations = dropLocations.size() - 1;
			try {
			return dropLocations.get(prob.randomNumber(0, noOfdDropLocations));
			} catch (Exception e){
				System.out.println("Dropping Item failed. The reason was "+ e.getMessage());
				
			};
			return map.locationOf(actor);
			}
		else {
			return map.locationOf(actor);
		}
	}


	@Override
	public void tick(Location currentLocation) {
		// TODO Auto-generated method stub
		if(this.hasCapability(ZombieCapability.CURED)) {
			this.curedTurns++;
		}
		if (curedTurns == 5) {
			currentLocation.map().removeActor(this);
			Actor newHuman = new Human("Human "+this.toString());
			for(Item item:inventory) {
				newHuman.addItemToInventory(item);
			}
			currentLocation.map().addActor(newHuman, currentLocation);
			
		}
	}
	


	
	
}
