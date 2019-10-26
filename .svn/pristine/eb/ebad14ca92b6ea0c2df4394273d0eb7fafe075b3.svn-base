package backend;

public class Computer extends Blob {

	/**
	 * Constructor for the Computer class
	 * 
	 * @param board
	 *            a reference to the game Board.
	 * @param row
	 *            a row number position in the Board.
	 * @param column
	 *            a column number position in the Board.
	 */
	public Computer(Board board, int row, int column) {
		super(board, row, column);
	}

	/**
	 * Returns a representation of the Computer Blob as a String. The Computer
	 * Blob is represented with the string '2'.
	 * 
	 * @return a representation of the Computer Blob as a String.
	 */
	public String toString() {
		return "2";
	}

	/**
	 * Returns the opposite of the current Blob.
	 * 
	 * @param blob
	 *            The blob into which the current Blob may be converted.
	 * @return a blob that is the exact opposite of the current blob.
	 */
	public Blob convert(Blob blob) {
		if (blob.getClass() != getClass()) {
			remove(board());
			Player py = new Player(board(), row(), column());
			py.add(board());
			return py;
		}
		return this;
	}

	/**
	 * Adds the current Blob into the ComputerBlob collection, contained in the
	 * Board class.
	 * 
	 * @param board
	 *            a reference to the game Board.
	 */
	@Override
	public void add(Board board) {
		board.computerBlobs().add(this);
	}

	/**
	 * Removes the current Blob from the ComputerBlob collection, contained in
	 * the Board class.
	 * 
	 * @param board
	 *            a reference to the game Board.
	 */
	@Override
	public void remove(Board board) {
		board.remove(this);
	}

	/**
	 * Makes a deep copy of the current Computer Blob. It needs a reference to
	 * the game Board though.
	 * 
	 * @param a
	 *            reference to the game Board.
	 * 
	 * @return a deep copy of the current Computer Blob.
	 */
	@Override
	public Blob clone(Board board) {
		return new Computer(board, row(), column());
	}

	/**
	 * Returns a representation of the Computer Blob as a char. The Computer
	 * Blob is represented with the string 'c'. If the boolean value willSwap
	 * that is received as a parameter is true, then the method will return a
	 * 'p'.
	 * 
	 * @param willSwap
	 *            a boolean value that will determine weather the method will
	 *            return a 'c' character (if false) or a 'p' character (if
	 *            true).
	 * 
	 * @return a representation of the Computer Blob as a char.
	 */
	@Override
	public char toChar(boolean willSwap) {
		if (willSwap) {
			return 'p';
		}
		return 'c';
	}
}