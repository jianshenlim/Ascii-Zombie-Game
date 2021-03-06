package game;

/**
 * Interface that implements the ability to increment a counter/focus level to implement effects
 * @author Jian Lim
 *
 */
public interface FocusLevel {
	
	/**
	 * Method that increments the current focus level
	 */
	public default void incrementFocus() {};
	
	/**
	 * Method that resets current focus level
	 */
	public default void resetFocus() {};
	
	/**
	 * Method that returns the current focus level
	 * @return Integer value of current focus level
	 */
	public default int getFocus() {return 0;}

}
