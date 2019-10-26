package backend;

public class Player extends Blob {

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
	public Player(Board board, int row, int column) {
		super(board, row, column);
	}

	/**
	 * Returns a representation of the Player Blob as a String. The Player Blob
	 * is represented with the string '1'.
	 * 
	 * @return a representation of the Player Blob as a String.
	 */
	public String toString() {
		return "1";
	}

	/**
	 * Returns a boolean value that represents weather the player can move
	 * distance one or two from its current position.
	 * 
	 * @return a boolean value that represents weather the player can move
	 *         distance one or two from its current position.
	 */
	public boolean canMove() {
		int i1 = Math.max(row() - 2, 0);
		int j1 = Math.max(column() - 2, 0);
		int iLim = Math.min(row() + 3, board().rows());
		int jLim = Math.min(column() + 3, board().cols());
		for (int i = i1; i < iLim; i++) {
			for (int j = j1; j < jLim; j++) {
				if (canMoveTo(i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Adds the current Blob into the PlayerBlob collection, contained in the
	 * Board class.
	 * 
	 * @param board
	 *            a reference to the game Board.
	 */
	@Override
	public void add(Board board) {
		board.playerBlobs().add(this);
	}

	/**
	 * Returns the opposite of the current Blob.
	 * 
	 * @param blob
	 *            The blob into which the current Blob may be converted.
	 * @return a blob that is the exact opposite of the current blob.
	 */
	@Override
	public Blob convert(Blob blob) {
		if (blob.getClass() != getClass()) {
			remove(board());
			Computer pc = new Computer(board(), row(), column());
			pc.add(board());
			return pc;
		}
		return this;
	}

	/**
	 * Determines weather the current Blob is a PlayerBlob or not.
	 * 
	 * @return a boolean value that determines weather the current Blob is a
	 *         PlayerBlob or not.
	 */
	@Override
	public boolean isPlayer() {
		return true;
	}

	/**
	 * Removes the current Blob from the PlayerBlob collection, contained in the
	 * Board class.
	 * 
	 * @param board
	 *            a reference to the game Board.
	 */
	@Override
	public void remove(Board board) {
		board.remove(this);
	}

	/**
	 * Makes a deep copy of the current Player Blob. It needs a reference to the
	 * game Board though.
	 * 
	 * @param a
	 *            reference to the game Board.
	 * 
	 * @return a deep copy of the current Player Blob.
	 */
	@Override
	public Blob clone(Board board) {
		return new Player(board, row(), column());
	}

	/**
	 * Returns a representation of the Player Blob as a char. The Player Blob is
	 * represented with the string 'p'. If the boolean value willSwap that is
	 * received as a parameter is true, then the method will return a 'c'.
	 * 
	 * @param willSwap
	 *            a boolean value that will determine weather the method will
	 *            return a 'p' character (if false) or a 'c' character (if
	 *            true).
	 * 
	 * @return a representation of the Player Blob as a char.
	 */

	@Override
	public char toChar(boolean willSwap) {
		if (willSwap) {
			return 'c';
		}
		return 'p';
	}
}