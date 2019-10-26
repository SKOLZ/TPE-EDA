package backend;

public class Emptyness extends Blob {

	/**
	 * Constructor for the Emptyness class.
	 * 
	 * @param board
	 *            a reference to the game Board.
	 * @param row
	 *            a row number position in the Board.
	 * @param column
	 *            a column number position in the Board.
	 */
	public Emptyness(Board board, int row, int column) {
		super(board, row, column);
	}

	/**
	 * Returns a representation of the Emptyness Blob as a String. The Emptyness
	 * Blob is represented with the string ' '.
	 * 
	 * @return a representation of the Emptyness Blob as a String.
	 */
	public String toString() {
		return " ";
	}

	/**
	 * Returns the opposite of the current Blob. In this case, the opposite of
	 * the Emptyness Blob is itself.
	 * 
	 * @param blob
	 *            The blob into which the current Blob may be converted.
	 * @return a reference to itself.
	 */
	public Blob convert(Blob blob) {
		return this;
	}

	/**
	 * Returns a representation of the Emptyness Blob as a char. The Emptyness
	 * Blob is represented with the string ' '. If the boolean value willSwap
	 * that is received as a parameter is true, then the method will return a
	 * ' '.
	 * 
	 * @param willSwap
	 *            the value of this parameter is of no importance for this
	 *            class.
	 * 
	 * 
	 * @return a representation of the Emptyness Blob as a char.
	 */
	public char toChar(boolean playerMovesFirst) {
		return ' ';
	}

	/**
	 * Returns a boolean value that determines weather this is an Emptyness Blob
	 * instance.
	 * 
	 * @return true;
	 */
	public boolean isEmpty() {
		return true;
	}

	/**
	 * Because it has no sense at all to move the Emptyness Blob, the move
	 * method doesn't move the current Blob, and returns false.
	 * 
	 * @param i
	 *            a row position in the board.
	 * @param j
	 *            a column position in the board.
	 * @return false;
	 * 
	 */
	@Override
	public boolean move(int i, int j) {
		return false;
	}

	/**
	 * Because we don't care much about the status of every Emptyness Blob in
	 * the game, when this method is called, it doesn't do anything.
	 * 
	 * @param board
	 *            A reference to the game board.
	 * 
	 */
	@Override
	public void add(Board board) {
		return;
	}

	/**
	 * Because we don't care much about the status of every Emptyness Blob in
	 * the game, when this method is called, it doesn't do anything.
	 * 
	 * @param board
	 *            A reference to the game board.
	 * 
	 */
	@Override
	public void remove(Board board) {
		return;
	}

	/**
	 * Makes a deep copy of the current Emptyness Blob. It needs a reference to
	 * the game Board though.
	 * 
	 * @param a
	 *            reference to the game Board.
	 *            
	 * @return a deep copy of the current Emptyness Blob.
	 */
	@Override
	public Blob clone(Board board) {
		return new Emptyness(board, row(), column());
	}
}