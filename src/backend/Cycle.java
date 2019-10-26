package backend;

public class Cycle {
	int cycle = 0;
	int total = 0;

	/**
	 * Increments the cycle value by one unit.
	 */
	public void increment() {
		cycle++;
		total++;
	}

	/**
	 * A getter for the current cycle value.
	 * 
	 * @return an int value that represents the cycle value.
	 */
	public int cycle() {
		return cycle;
	}

	/**
	 * The Cycle object has two values: the amount of cycles, and the total
	 * cycles made. The first one will be 0 when the reset() method is called,
	 * and the second one will never be changed, maintaining this way the total
	 * amount of cycles made.
	 * 
	 * @return the total amount of cycles made.
	 */
	public int total() {
		return total;
	}

	/**
	 * Returns a String representation of the total amount of cycles made in the
	 * whole course of the current object's life.
	 * 
	 * @return the total amount of cycles made in the whole course of the
	 *         current object's life, represented by a String.
	 */
	public String toString() {
		return "" + total;
	}

	/**
	 * Sets the current cycles amount to 0, without changing the total amount of
	 * cycles made by this object in its entire life.
	 */
	public void reset() {
		cycle = 1;
	}
}