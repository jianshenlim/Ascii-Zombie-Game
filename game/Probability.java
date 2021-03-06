package game;

import java.util.Random;
/**
 * Class the simulates the probability of an event occurring
 * @author Jian Lim
 *
 */
class Probability {
	private Random random;
	
	public Probability() {
			random = new Random();
	}
	
	/**
	 * Method that takes a value 0 - 100 representing success chance, and returns a boolean value based
	 * on the probability of success
	 * @param percentage integer value of the success percentage
	 * @return boolean value if event is successful
	 */
	public boolean isSuccessful(int percentage) {
		int randomNumber =random.nextInt(101);
		if (percentage < 0) {
			percentage = 0;
		} 
		if (percentage > 100) {
			percentage = 100;
		}	
		if (randomNumber <= percentage) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Method that returns a random integer value in a range between two integer values, inclusive
	 * @param rangeStart integer value of beginning of the number range
	 * @param rangeEnd integer value of the end of the number range
	 * @return random integer value in the range
	 * @throws Exception Exception if integer value returned is not in the range
	 */
	public int randomNumber(int rangeStart,int rangeEnd) throws Exception {
		int result = random.nextInt(rangeEnd - rangeStart) + rangeStart;
		
		if (result < rangeStart || result > rangeEnd) {
			throw new Exception("Random number returned out of range"); 
		}
		
		
		return result;
	}

}
