package game;

import java.util.List;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * Class that creates reload actions for ranged weapons
 * @author Jian Lim
 *
 */
public class ReloadAction extends Action {
	
	private Ammunition currentAmmoBox;
	private RangedWeaponCapability boxBulletType;
	
	/**
	 * ReloadAction constructor
	 * @param currentAmmo Ammunition object
	 * @param bulletType Capability of current Ammunition object
	 */
	public ReloadAction(Ammunition currentAmmo,RangedWeaponCapability bulletType) {
		currentAmmoBox = currentAmmo;
		boxBulletType = bulletType;
		
	}
	
	/**
	 * Overriden method that checks actor for RangedWeapon that is reloadable and has the same bullet type (RangedWeaponCapability) as the Ammunition object
	 * reloads the RangedWeapon to full or until the Ammunition object runs out of bullets and is removed from the actors inventory
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		Item current = getGun(actor);
		if (checkForGun(actor) && current.reloadable()) {
			int roundCount = 0;
			
			while (current.reloadable() && currentAmmoBox.areRoundsRemaining()) {
				current.reload();
				currentAmmoBox.decrementRounds();
				roundCount++;
			}
			
			if (current.reloadable()) {result = actor + " reloaded " + current +" for " + roundCount + "rounds";}
			else {
				result = current + " fully reloaded" + System.lineSeparator() + currentAmmoBox.getRemainingRounds() +" "+ boxBulletType.toString().toLowerCase() +"s remaining in box" ;
			}
			
			if (!currentAmmoBox.areRoundsRemaining()) {
				result += System.lineSeparator() + actor + " finished a box of ammunition";
				updateInventory(actor);
				Location locationOfActor = map.locationOf(actor);
				locationOfActor.removeItem(currentAmmoBox);
						
			}
		} else {result = currentAmmoBox.getRemainingRounds() +" "+ boxBulletType.toString().toLowerCase() +"s remaining in box";}
			
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		if (checkForGun(actor)) {
			Item current = getGun(actor);
			if (current.reloadable()) {
				return actor + " reloads "+ current.toString();	
				}
		}
		return actor + " counts remaining " + boxBulletType.toString().toLowerCase() +"s";
	}
	
	/**
	 * Check actors inventory for a RangedWeapon that is reloadable and has the same RangedWeaponCapability type ie same bullets as Ammunition object
	 * @param actor Current actor
	 * @return Boolean value
	 */
	private boolean checkForGun(Actor actor) {
		List<Item> inventory = actor.getInventory();
		for (Item i : inventory) {
			if (i.hasCapability(RangedWeaponCapability.RELOADABLE) && i.hasCapability(boxBulletType)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the item that is both reloadable and has the same RangedWeaponCapability type ie same bullets as Ammunition object
	 * @param actor current actor
	 * @return Item 
	 */
	private Item getGun(Actor actor) {
		List<Item> inventory = actor.getInventory();
		for (Item i : inventory) {
			if (i.hasCapability(RangedWeaponCapability.RELOADABLE) && i.hasCapability(boxBulletType)) {
				return i;
			}
		}
		return null;
		
	}
	
	private void updateInventory(Actor actor) {
		actor.removeItemFromInventory(currentAmmoBox);
	}

}
