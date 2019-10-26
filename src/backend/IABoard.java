package backend;

public class IABoard {

	private int rows, columns;
	private char[][] board;
	private boolean akedIfGameOver, gameOver, askedPlayerCanMove,
			playerCanMove;
	private int computerBlobs, playerBlobs;

	/**
	 * Constructor for the IABoard class.
	 * 
	 * @param rows
	 *            the amount of rows for the IABoard.
	 * @param columns
	 *            the amount of columns for the IABoard.
	 * @param computerBlobs
	 *            the amount of computer Blobs inside the IABoard.
	 * @param playerBlobs
	 *            the amount of player Blobs inside the IABoard.
	 */
	public IABoard(int rows, int columns, int computerBlobs, int playerBlobs) {
		this.rows = rows;
		this.columns = columns;
		this.board = new char[rows][columns];
		this.computerBlobs = computerBlobs;
		this.playerBlobs = playerBlobs;
	}

	/**
	 * Returns the amount of computer Blobs inside the IABoard.
	 * 
	 * @return the amount of computer Blobs inside the IABoard.
	 */
	public int computerBlobs() {
		return computerBlobs;
	}

	/**
	 * Returns the amount of player Blobs inside the IABoard.
	 * 
	 * @return the amount of player Blobs inside the IABoard.
	 */
	public int playerBlobs() {
		return playerBlobs;
	}

	/**
	 * Returns the amount of rows in the IABoard.
	 * 
	 * @return the amount of rows in the IABoard.
	 */
	public int rows() {
		return rows;
	}

	/**
	 * Returns the amount of columns in the IABoard.
	 * 
	 * @return the amount of columns in the IABoard.
	 */
	public int columns() {
		return columns;
	}

	/**
	 * Returns the Cell object at the i row, j columns position inside the
	 * Board.
	 * 
	 * @return the Cell object at the i row, j columns position inside the
	 *         Board.
	 */
	public char at(int i, int j) {
		return board[i][j];
	}

	/**
	 * Puts a char c in the i row, j column of the IABoard.
	 * 
	 */
	public void put(int i, int j, char c) {
		akedIfGameOver = false;
		askedPlayerCanMove = false;
		board[i][j] = c;
	}

	/**
	 * Converts all the Blobs at distance one from the Blob at(i, j).
	 * 
	 * @param i
	 *            the row number of the Blob that will convert the other ones.
	 * @param j
	 *            the column number of the Blob that will convert the other
	 *            ones.
	 */
	public void contaminate(int i, int j) {
		char c = board[i][j];
		int iLim = Math.min(i + 2, board.length);
		int jLim = Math.min(j + 2, board[0].length);
		for (int i1 = Math.max(i - 1, 0); i1 < iLim; i1++) {
			for (int j1 = Math.max(j - 1, 0); j1 < jLim; j1++) {
				convert(i1, j1, c);
			}
		}
	}

	/**
	 * Moves the char c to the position (i,j) if the IABoard.
	 * 
	 * @param i
	 *            the destination row number.
	 * @param j
	 *            the destination column number.
	 * @param c
	 *            the char to be moved.
	 */
	public void moveDistanceOne(int i, int j, char c) {
		put(i, j, c);
		if (c == 'p') {
			playerBlobs++;
		} else {
			computerBlobs++;
		}
	}

	/**
	 * Moves the char c from the position (iFrom, jFrom) to the position
	 * (iTo,jTo) if the IABoard.
	 * 
	 * @param iFrom
	 *            the initial row number in the Board.
	 * @param jFrom
	 *            the initial column number in the Board.
	 * @param iTo
	 *            the final row number in the Board.
	 * @param jTo
	 *            the final column number in the Board.
	 * @param c
	 *            the char to be moved.
	 */
	public void moveDistanceTwo(int iFrom, int jFrom, int iTo, int jTo, char c) {
		put(iTo, jTo, c);
		put(iFrom, jFrom, ' ');
	}

	private void convert(int i, int j, char c) {
		char aux = board[i][j];
		if (aux != c && aux != ' ') {
			if (aux == 'p') {
				board[i][j] = 'c';
				computerBlobs++;
				playerBlobs--;
			} else if (aux == 'c') {
				board[i][j] = 'p';
				playerBlobs++;
				computerBlobs--;
			}
		}
	}

	/**
	 * Makes a deep copy of the current IABoard.
	 * 
	 * @return a deep copy of the current IABoard.
	 */
	public IABoard clone() {
		IABoard ans = new IABoard(rows, columns, computerBlobs, playerBlobs);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				ans.put(i, j, board[i][j]);
			}
		}
		return ans;
	}

	/**
	 * Returns weather the game is over or not.
	 * 
	 * @return a boolean value that determines weather the game is over or not.
	 */
	public boolean gameOver() {
		if (!akedIfGameOver) {
			akedIfGameOver = true;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j] == 'p') {
						if (playerCanMove(i, j)) {
							return gameOver = false;
						}
					}
				}
			}
			return gameOver = true;
		} else {
			return gameOver;
		}
	}

	/**
	 * Returns a boolean value that determines weather the player has won or
	 * not.
	 * 
	 * @return a boolean value that determines weather the player has won or
	 *         not.
	 */
	public boolean playerHasWon() {
		return gameOver && playerBlobs - computerBlobs > 0;
	}

	/**
	 * Returns a representation of the IABoard as a String. The IABoard is
	 * represented with blank spaces for the Emptyness, ones for the player
	 * blobs and twos for the computer blobs.
	 * 
	 * @return a representation of the IABoard as a String.
	 */
	public String toString() {
		String ans = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				ans += board[i][j];
			}
			ans += "\n";
		}
		return ans;
	}

	/**
	 * Determines weather the Player can move in any direction inside the Board.
	 * 
	 * @return a boolean value that determines weather the Player can move in
	 *         any direction inside the Board.
	 */
	private boolean playerCanMove(int i, int j) {
		if (!askedPlayerCanMove) {
			askedPlayerCanMove = true;
			int aLim = Math.min(i + 3, board.length);
			int bLim = Math.min(j + 3, board[0].length);
			for (int a = Math.max(i - 2, 0); a < aLim; a++) {
				for (int b = Math.max(j - 2, 0); b < bLim; b++) {
					if (board[a][b] == ' ') {
						return playerCanMove = true;
					}
				}
			}
			return playerCanMove = false;
		}
		return playerCanMove;
	}
}