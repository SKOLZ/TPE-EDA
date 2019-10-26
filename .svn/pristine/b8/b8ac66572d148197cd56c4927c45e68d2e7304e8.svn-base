package backend;

public abstract class Blob {

	private int row, column;
	private Board board;

	/**
	 * Constructor for the Blob class.
	 * 
	 * @param board
	 *            A reference to the game board.
	 * @param row
	 *            the row position in the board.
	 * 
	 * @param column
	 *            the column position in the board.
	 */
	public Blob(Board board, int row, int column) {
		this.board = board;
		this.row = row;
		this.column = column;
	}

	/**
	 * Returns a boolean value that determines weather a Blob can move to the i
	 * row, j column of the board.
	 * 
	 * @param i
	 *            a row position in the board.
	 * @param j
	 *            a column position in the board.
	 * @return boolean that determines weather a Blob can move to the i row, j
	 *         column of the board.
	 * 
	 */
	public boolean move(int i, int j) {
		if (canMoveTo(i, j)) {
			if (i - row >= -1 && i - row <= 1 && j - column >= -1
					&& j - column <= 1) {
				moveDistanceOne(i, j);
				return true;
			} else if (i - row >= -2 && i - row <= 2 && j - column >= -2
					&& j - column <= 2) {
				moveDistanceTwo(i, j);
				return true;
			}
		}
		return false;
	}

	/**
	 * To avoid asking for the type of each Blob, when a Blob duplicates itself,
	 * the new Blob adds itself to the board, so it knows where to put it:
	 * weather in the computer blobs group or the player blob group.
	 * 
	 * @param board
	 *            A reference to the game board.
	 * 
	 */
	public abstract void add(Board board);

	/**
	 * To avoid asking for the type of each Blob, when a Blob has to disappear,
	 * the new Blob removes itself from the board, so it knows what to remove:
	 * weather a computer blob or a player blob.
	 * 
	 * @param board
	 *            A reference to the game board.
	 * 
	 * @return a deep copy of the current Blob.
	 * 
	 */
	public abstract void remove(Board board);

	/**
	 * Returns a boolean value that determines weather a Blob can move to the i
	 * row, j column of the board.
	 * 
	 * @param i
	 *            a row position in the board.
	 * @param j
	 *            a column position in the board.
	 * 
	 * @return a boolean value that determines weather a Blob can move to the i
	 *         row, j column of the board.
	 * 
	 */
	public boolean canMoveTo(int i, int j) {
		return board.at(i, j).isEmpty();
	}

	/**
	 * Returns a boolean value that determines weather this is an Emptyness Blob
	 * instance.
	 * 
	 * @return a boolean value that determines weather this is an Emptyness blob
	 *         or not.
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Moves the current Blob one distance from its original position in the
	 * board, duplicating itself. Afterwards, it contaminates the surroundings.
	 * 
	 * @param i
	 *            A row position in the board.
	 * @param j
	 *            A column position in the board.
	 */
	public void moveDistanceOne(int i, int j) {
		Blob blob = this.clone(board);
		blob.row = i;
		blob.column = j;
		board.put(new Cell(blob));
		blob.contaminate();
	}

	/**
	 * Moves the current Blob two distances from its original position in the
	 * board, jumping, without any kind of duplication. Afterwards, it
	 * contaminates the surroundings.
	 * 
	 * @param i
	 *            A row position in the board.
	 * @param j
	 *            A column position in the board.
	 */
	public void moveDistanceTwo(int i, int j) {
		board.put(new Cell(new Emptyness(board, row, column)));
		row = i;
		column = j;
		board.put(new Cell(this.clone(board)));
		contaminate();
	}

	/**
	 * Returns a deep copy of the current Blob.
	 * 
	 * @param board
	 *            A reference to the game board.
	 * @return a deep copy of the current Blob.
	 */
	public abstract Blob clone(Board board);

	/**
	 * Returns a char representation of the current Blob. The boolean parameter
	 * specifies weather the player moves first or not: this is because of the
	 * internal implementation of the IA.
	 * 
	 * @param boolean A value that represents weather the player moves first or
	 *        not.
	 * 
	 * @return a char representation of the current Blob.
	 */
	public abstract char toChar(boolean willSwap);

	/**
	 * This method converts all the surrounding blobs at distance one to the
	 * current blob.
	 */
	private void contaminate() {
		int i1 = Math.max(row - 1, 0);
		int j1 = Math.max(column - 1, 0);
		int iLim = Math.min(row + 2, board().rows());
		int jLim = Math.min(column + 2, board().cols());
		for (int i = i1; i < iLim; i++) {
			for (int j = j1; j < jLim; j++) {
				board.at(i, j).convert(this);
			}
		}
	}

	/**
	 * Returns the opposite of the current Blob.
	 * 
	 * @param blob
	 *            The blob into which the current Blob may be converted.
	 * @return a blob that is the exact opposite of the current blob.
	 */
	public abstract Blob convert(Blob blob);

	/**
	 * Getter for the row position of the blob in the board.
	 * 
	 * @return the index of the row position of the blob in the board.
	 */
	public int row() {
		return row;
	}

	/**
	 * Getter for the column position of the blob in the board.
	 * 
	 * @return the index of the column position of the blob in the board.
	 */
	public int column() {
		return column;
	}

	/**
	 * Getter for the Board.
	 * 
	 * @return a reference to a Board.
	 */
	public Board board() {
		return board;
	}

	/**
	 * Determines weather the current Blob is able to move in any direction
	 * 
	 * @return a boolean value that represents weather the current Blob is able
	 *         to move in any direction.
	 */
	public boolean canMove() {
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blob other = (Blob) obj;
		if (board == null) {
			if (other.board != null)
				return false;
		}
		return row == other.row && column == other.column;
	}

	/**
	 * Determines weather the current Blob is a PlayerBlob or not.
	 * 
	 * @return a boolean value that determines weather the current Blob is a
	 *         PlayerBlob or not.
	 */
	public boolean isPlayer() {
		return false;
	}
}