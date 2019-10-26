package backend;

public class Game {

	private Board board;
	private boolean alreadyAskedIfGameOver = false;
	private static int n;
	private static boolean time;
	private static boolean prune;
	private static boolean willSwap;

	/**
	 * Constructor for the Game class.
	 * 
	 * @param board
	 *            a reference to the game Board.
	 */
	public Game(Board board) {
		this.board = board;
		this.board.setGame(this);
	}

	/**
	 * Setter for the game properties.
	 * 
	 * @param restriction
	 *            : the amount of depth/time.
	 * @param isTime
	 *            a boolean value that determines weather the IA will execute
	 *            the minimax algorithm in time or depth.
	 * @param hasPrune
	 *            a boolean value that represents weather the minimax algorithm
	 *            will be executed with or without pruning.
	 * @param swap
	 *            a boolean value that represents weather the game should swap
	 *            the player's blobs with the computer's blobs.
	 */
	public static void setProperties(int restriction, boolean isTime,
			boolean hasPrune, boolean swap) {
		n = restriction;
		time = isTime;
		prune = hasPrune;
		willSwap = swap;
	}

	/**
	 * Moves the Blob in the (iFrom, jFrom) position in the board to the (iTo,
	 * jTo) position. If it could be moved successfully, the method returns
	 * true, otherwise it returns false.
	 * 
	 * @param iFrom
	 *            the initial row value.
	 * @param jFrom
	 *            the initial column value.
	 * @param iTo
	 *            the final row value.
	 * @param jTo
	 *            the final column value.
	 * @return a boolean value that represents weather the Blob could be
	 *         successfully moved.
	 */
	public boolean move(int iFrom, int jFrom, int iTo, int jTo) {
		alreadyAskedIfGameOver = false;
		return board.blobAt(iFrom, jFrom).move(iTo, jTo);
	}

	/**
	 * A getter for the depht/time value.
	 * 
	 * @return the depht/time value.
	 */
	public int restriction() {
		return n;
	}

	/**
	 * Returns a boolean value that determines weather the algorithm will be
	 * executed with a time parameter or not.
	 * 
	 * @return a boolean value that determines weather the algorithm will be
	 *         executed with a time parameter or not.
	 */
	public boolean isTime() {
		return time;
	}

	/**
	 * Returns a boolean value that determines weather the algorithm will be
	 * executed with or without pruning.
	 * 
	 * @return a boolean value that determines weather the algorithm will be
	 *         executed with or without pruning.
	 */
	public boolean hasPrune() {
		return prune;
	}

	/**
	 * Returns a boolean value that represents weather the game is over or not.
	 * 
	 * @return a boolean value that represents weather the game is over or not.
	 */
	public boolean gameOver() {
		if (alreadyAskedIfGameOver) {
			return true;
		}
		alreadyAskedIfGameOver = true;
		return !board.playerCanMove() || board.computerBlobs().size() == 0;
	}

	/**
	 * Returns a boolean value that represents weather the player has won or
	 * not.
	 * 
	 * @return a boolean value that represents weather the player has won or
	 *         not.
	 */
	public boolean playerHasWon() {
		return gameOver()
				&& board.playerBlobs().size() - board.computerBlobs().size() > 0;
	}

	/**
	 * Returns a reference of the game Board.
	 * 
	 * @return a reference of the game Board.
	 */
	public Board board() {
		return board;
	}

	/**
	 * Exexutes the minimax algorithm, makes the best movement for the computer,
	 * and returns it as a String with the format [iFrom, jFrom][iTo, jTo] Time
	 * spent = t DEPTH = d Expored states = s.
	 * 
	 * @return a String with the format [iFrom, jFrom][iTo, jTo] Time spent = t
	 *         DEPTH = d Expored states = s.
	 */
	public String calculateNextMovement() {
		Minimax max = null;
		Cycle cycle = new Cycle();
		long begining = System.currentTimeMillis();
		if (!time) {
			max = new Max(board.iaBoard(willSwap), n, 1, prune, -1);
			max.minimax(Integer.MAX_VALUE, cycle);
		} else {
			max = Minimax.timeMinimax(n, prune, board.iaBoard(willSwap), cycle);
		}
		String ans = "";
		if (!move(max.iFrom(), max.jFrom(), max.iTo(), max.jTo())) {
			ans += "PASS.";
		} else {
			ans += "[" + max.iFrom + "," + max.jFrom + "][" + max.iTo + ","
					+ max.jTo + "]";
		}
		ans += "\nTime spent = " + (System.currentTimeMillis() - begining)
				+ " milliseconds." + "\nDEPTH = " + max.depth() + ".\n"
				+ "Explored states: " + cycle.total() + ".";
		return ans;
	}
}