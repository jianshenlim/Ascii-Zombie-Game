package game;

import edu.monash.fit2099.engine.DoNothingAction;

/**
 * Submenu for shotgun attacks
 * @author Jian Lim
 *
 */
public class ShotgunSubMenu extends DisplaySubMenu{
	
	/**
	 * Shotgun submenu constructor
	 * @param current Actor's current shotgun
	 */
	public ShotgunSubMenu(Shotgun current) {
		super("shoots shotgun");
		this.addAction(new AttackAreaAction(null,current,"N"));
		this.addAction(new AttackAreaAction(null,current,"NE"));
		this.addAction(new AttackAreaAction(null,current,"E"));
		this.addAction(new AttackAreaAction(null,current,"SE"));
		this.addAction(new AttackAreaAction(null,current,"S"));
		this.addAction(new AttackAreaAction(null,current,"SW"));
		this.addAction(new AttackAreaAction(null,current,"W"));
		this.addAction(new AttackAreaAction(null,current,"NW"));
		this.addAction(new DoNothingAction());

	}

}
