package backend;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

	private int rows;
	private int columns;
	private Cell[][] board;
	private ArrayList<Player> playerBlobs = new ArrayList<Player>();
	private ArrayList<Computer> computerBlobs = new ArrayList<Computer>();
	private Game game;

	/**
	 * Constructor for the Blob class.
	 * 
	 * @param rows
	 *            The amount of rows in the board.
	 * @param columns
	 *            the amount of columns in the board.
	 */
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		board = new Cell[rows][columns];
	}

	/**
	 * This method uses the current Board object to build a simpler version: the
	 * answer is an IABoard object, that is modeled through a char matrix. This
	 * has been implemented so the AI can use it for its needs, in a more
	 * efficient way.
	 * 
	 * @param playerPlaysFirst
	 *            The construction of the IABoard will depend on the value of
	 *            this parameter.
	 * @return an IABoard based on the current Board.
	 */
	public IABoard iaBoard(boolean willSwap) {
		IABoard iaBoard = null;
		if (willSwap) {
			iaBoard = new IABoard(rows, columns, playerBlobs.size(),
					computerBlobs.size());
		} else {
			iaBoard = new IABoard(rows, columns, computerBlobs.size(),
					playerBlobs.size());
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				iaBoard.put(i, j, at(i, j).toChar(willSwap));
			}
		}
		return iaBoard;
	}

	/**
	 * Every Board object has been modeled, so it knows a Game: when a movement
	 * in the board has been made, the board asks to the game if the game has
	 * not ended yet for example. This is a setter for such Game object.
	 * 
	 * @param game
	 *            A reference to a Game object.
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Getter for the Game.
	 * 
	 * @return a reference to the Game.
	 */
	public Game game() {
		return game;
	}

	/**
	 * Given a certain Cell, the boards puts in the Cell row, and Cell column
	 * the Cell object.
	 * 
	 * @param cell
	 *            the Cell object to be placed in the Board.
	 */
	public void put(Cell cell) {
		if (board[cell.row()][cell.column()] != null) {
			remove(board[cell.row()][cell.column()].blob());
		}
		board[cell.row()][cell.column()] = cell;
	}

	/**
	 * Puts in every place in the Board a Cell with an Emptyness Blob.
	 */
	public void initialize() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				put(new Cell(new Emptyness(this, i, j)));
			}
		}
	}

	/**
	 * Returns a deep copy of the current Board.
	 * 
	 * @return a deep copy of the current Board.
	 */
	public Board clone() {
		Board ans = new Board(rows, columns);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Blob blob = board[i][j].blob().clone(ans);
				ans.board[i][j] = new Cell(blob);
			}
		}
		ans.game = new Game(ans);
		return ans;
	}

	/**
	 * Returns a Collection of all the Computer Blobs: it can be used to avoid
	 * parsing the whole board in search of them.
	 * 
	 * @return a Collection of all the Computer Blobs.
	 */
	public ArrayList<Computer> computerBlobs() {
		return computerBlobs;
	}

	/**
	 * Returns a Collection of all the Player Blobs: it can be used to avoid
	 * parsing the whole board in search of them.
	 * 
	 * @return a Collection of all the Player Blobs.
	 */
	public ArrayList<Player> playerBlobs() {
		return playerBlobs;
	}

	/**
	 * Adds a Blob to the corresponding collection: Player Blobs or Computer
	 * Blobs, depending on the type of the Blob. This has been solved with
	 * double-dispatching.
	 * 
	 * @param blob
	 *            the Blob object to be added to the corresponding collection.
	 */
	void add(Blob blob) {
		blob.add(this);
	}

	/**
	 * Removes a Blob from the corresponding collection: Player Blobs or
	 * Computer Blobs, depending on the type of the Blob. This has been solved
	 * with double-dispatching.
	 * 
	 * @param blob
	 *            the Blob object to be removed from the corresponding
	 *            collection.
	 */
	void remove(Blob blob) {
		blob.remove(this);
	}

	/**
	 * Removes a Blob from the Computer Blobs collection. This method should be
	 * called from the Computer class, when the remove(Blob blob) reaches it.
	 * 
	 * @param computer
	 *            the Computer object to be removed from the corresponding
	 *            collection.
	 */
	void remove(Computer computer) {
		computerBlobs.remove(computer);
	}

	/**
	 * Removes a Blob from the Player Blobs collection. This method should be
	 * called from the Player class, when the remove(Blob blob) reaches it.
	 * 
	 * @param player
	 *            the Player object to be removed from the corresponding
	 *            collection.
	 */
	void remove(Player player) {
		playerBlobs.remove(player);
	}

	/**
	 * Getter for the amount of rows in the Board.
	 * 
	 * @return the amount of rows in the Board.
	 */
	public int rows() {
		return rows;
	}

	/**
	 * Getter for the amount of columns in the Board.
	 * 
	 * @return the amount of columns in the Board.
	 */
	public int cols() {
		return columns;
	}

	/**
	 * Determines weather the Player can move in any direction inside the Board.
	 * 
	 * @return a boolean value that determines weather the Player can move in
	 *         any direction inside the Board.
	 */
	public boolean playerCanMove() {
		for (Player each : playerBlobs) {
			if (each.canMove()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the Cell at the i row, j column.
	 * 
	 * @param i
	 *            the row number, beginning with 0.
	 * @param j
	 *            the column number, beginning with 0.
	 * @return the Cell at the i row, j column.
	 */
	public Cell at(int i, int j) {
		return board[i][j];
	}

	/**
	 * Returns the Blob in the Cell at the i row, j column.
	 * 
	 * @param i
	 *            the row number, beginning with 0.
	 * @param j
	 *            the column number, beginning with 0.
	 * @return the Blob in the Cell at the i row, j column.
	 */
	public Blob blobAt(int i, int j) {
		return at(i, j).blob();
	}

	/**
	 * Returns an interpretation of the Board into a String: the spaces
	 * represent the Emptyness, the ones represent the Player Blobs, and the
	 * twos represent the Computer Blobs.
	 * 
	 * @return an interpretation of the Board into a String.
	 */
	@Override
	public String toString() {
		String ans = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				ans += board[i][j].blob();
			}
			ans += "\n";
		}
		return ans;
	}

	/**
	 * Returns an 8x8 size Board, with two Computer Blobs, one on the upper left
	 * corner, the other on the upper right corner, and two Player Blobs, one on
	 * the lower left corner, and the other on the lower right corner.
	 * 
	 * @return a default Board fully initialized.
	 */
	public static Board defaultBoard() {
		int defaultSize = 8;
		Board board = new Board(defaultSize, defaultSize);
		board.initialize();
		board.put(new Cell(new Computer(board, 0, 0)));
		board.put(new Cell(new Computer(board, 0, 7)));
		board.put(new Cell(new Player(board, 7, 0)));
		board.put(new Cell(new Player(board, 7, 7)));
		return board;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		result = prime * result + columns;
		result = prime * result
				+ ((computerBlobs == null) ? 0 : computerBlobs.hashCode());
		result = prime * result
				+ ((playerBlobs == null) ? 0 : playerBlobs.hashCode());
		result = prime * result + rows;
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
		Board other = (Board) obj;
		if (columns != other.columns)
			return false;
		if (rows != other.rows)
			return false;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (!board[i][j].equals(other.board[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
}