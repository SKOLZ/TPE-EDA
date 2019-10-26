package backend;

public abstract class Minimax {

	private IABoard iaBoard;
	private int value;
	private int depth;
	private int currentDepth;
	public int iFrom, jFrom, iTo, jTo;
	private long timeMillis = -1;
	public boolean prune;

	/**
	 * Constructor for the Minimax class.
	 * 
	 * @param iaBoard
	 *            a reference to the game IABoard.
	 * @param depth
	 *            the depth value for the minimax algorithm.
	 * @param curentDepth
	 *            the current depth for the minimax algorithm.
	 * @param prune
	 *            a boolean value that determines weather the minimax algorithm
	 *            will be executed with or without pruning.
	 * @param timeMillis
	 *            the maximum time to find the best movement for the minimax
	 *            algorithm.
	 */
	public Minimax(IABoard iaBoard, int depth, int currentDepth, boolean prune,
			long timeMillis) {
		this.iaBoard = iaBoard;
		this.depth = depth;
		this.currentDepth = currentDepth;
		this.prune = prune;
		this.timeMillis = timeMillis;
	}

	/**
	 * Constructor for the Minimax class.
	 * 
	 * @param iaBoard
	 *            a reference to the game IABoard.
	 * 
	 * @param timeMillis
	 *            the maximum time to find the best movement for the minimax
	 *            algorithm.
	 */
	public Minimax(IABoard iaBoard, int timeMillis) {
		this.iaBoard = iaBoard;
		this.timeMillis = timeMillis;
	}

	/**
	 * Returns a boolean value that determines weather the algorithm will be
	 * executed using tree pruning or not.
	 * 
	 * @return a boolean value that determines weather the algorithm will be
	 *         executed using tree pruning or not.
	 */
	public boolean prune() {
		return prune;
	}

	/**
	 * Returns the amount of time that the algorithm will run, in case the time
	 * mode is activated, in milliseconds. If the time mode is deactivated, then
	 * the method will return -1.
	 * 
	 * @return the amount of time that the algorithm will run, in case the time
	 *         mode is activated, in milliseconds. If the time mode is
	 *         deactivated, then the method will return -1.
	 */
	public long timeMillis() {
		return timeMillis;
	}

	/**
	 * Returns a boolean value that determines weather the algorithm will be
	 * executed in time mode or not.
	 * 
	 * @return a boolean value that determines weather the algorithm will be
	 *         executed in time mode or not.
	 */
	public boolean time() {
		return timeMillis != -1;
	}

	/**
	 * Returns the value of the initial row number for the best movement.
	 * 
	 * @return the value of the initial row number for the best movement.
	 */
	public int iFrom() {
		return iFrom;
	}

	/**
	 * Returns the value of the initial column number for the best movement.
	 * 
	 * @return the value of the initial column number for the best movement.
	 */
	public int jFrom() {
		return jFrom;
	}

	/**
	 * Returns the value of the final row number for the best movement.
	 * 
	 * @return the value of the final row number for the best movement.
	 */
	public int iTo() {
		return iTo;
	}

	/**
	 * Returns the value of the final column number for the best movement.
	 * 
	 * @return the value of the final column number for the best movement.
	 */
	public int jTo() {
		return jTo;
	}

	/**
	 * Setter for the best movement.
	 * 
	 * @param iFrom
	 *            the value of the initial row number for the best movement.
	 * @param jFrom
	 *            the value of the initial column number for the best movement.
	 * @param iTo
	 *            the value of the final row number for the best movement.
	 * @param jTo
	 *            the value of the final column number for the best movement.
	 */
	public void setMovement(int iFrom, int jFrom, int iTo, int jTo) {
		this.iFrom = iFrom;
		this.jFrom = jFrom;
		this.iTo = iTo;
		this.jTo = jTo;
	}

	/**
	 * Getter for the game IABoard.
	 * 
	 * @return a reference to the game IABoard.
	 */
	public IABoard board() {
		return iaBoard;
	}

	/**
	 * Getter for the algorithm's current depth.
	 * 
	 * @return the algorithm's current depth.
	 */
	public int currentDepth() {
		return currentDepth;
	}

	/**
	 * Getter for the best movement value.
	 * 
	 * @return the best movement value.
	 */
	public int value() {
		return value;
	}

	/**
	 * Setter for the best movement value.
	 * 
	 * @param value
	 *            the value that will be set.
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Setter for the IABoard.
	 * 
	 * @param iaBoard
	 *            the IABoard to be set.
	 */
	public void setIABoard(IABoard iaBoard) {
		this.iaBoard = iaBoard;
	}

	/**
	 * Getter for the algorithm's max depth.
	 * 
	 * @return the algorithm's max depth.
	 */
	public int depth() {
		return depth;
	}

	/**
	 * Checks weather the Blob at (blobI, blobJ) can move to (positionI,
	 * positionJ). If true, then the method clones the current IABoard and moves
	 * the Blob in that IABoard. Afterwards, it returns the cloned IABoard.
	 * 
	 * @param blobI
	 *            the initial row position for the Blob.
	 * @param blobJ
	 *            the initial column position for the Blob.
	 * @param positionI
	 *            the final row position for the Blob.
	 * @param positionJ
	 *            the final column position for the Blob.
	 * @return
	 */
	public IABoard analizeMove(int blobI, int blobJ, int positionI,
			int positionJ) {
		if (iaBoard.at(positionI, positionJ) == ' ') {
			char c = iaBoard.at(blobI, blobJ);
			IABoard aux = iaBoard.clone();
			int destinyI = positionI - blobI;
			int destinyJ = positionJ - blobJ;
			if (destinyI >= -1 && destinyI <= 1 && destinyJ >= -1
					&& destinyJ <= 1) {
				aux.moveDistanceOne(positionI, positionJ, c);
			} else {
				aux.moveDistanceTwo(blobI, blobJ, positionI, positionJ, c);
			}
			aux.contaminate(positionI, positionJ);
			return aux;
		}
		return null;
	}

	/**
	 * It's a recursive algorithm for choosing the next move in an two-player
	 * game. A value is associated with each position or state of the game. This
	 * value is computed by means of a position evaluation function and it
	 * indicates how good it would be for a player to reach that position. The
	 * player then makes the move that maximizes the minimum value of the
	 * position resulting from the opponent's possible following moves. If it is
	 * A's turn to move, A gives a value to each of his legal moves.
	 * 
	 * @param value
	 *            an int value used when pruning: if the pruning is activated,
	 *            and value is better than the stored value in Minimax, then
	 *            Minimax calls minimax for Max or Min, depending on which one
	 *            the current Minimax object is, passing value as an argument.
	 *            Otherwise, the method returns the stored value in Minimax.
	 * 
	 * @param cycles
	 *            an object only used when the time mode is activated: this
	 *            object is used to increment an internal value, and, when such
	 *            value is equal to 'n' (defined in the minimax algorithm by the
	 *            user), then the algorithm asks weather it ran out of time or
	 *            not.
	 * 
	 * @return an int value only used when the time mode is activated: if the
	 *         return value of a minimax call made from Minimax is equal to -1,
	 *         then it means the algorithm run out of time; otherwise, the
	 *         algorithm still has some time to run.
	 */
	public abstract int minimax(int value, Cycle cycles);

	public static Minimax timeMinimax(int time, boolean prune, IABoard iaBoard,
			Cycle cycles) {
		int depth = 1;
		Minimax bestAnswer = null;
		boolean firstTime = true;
		long currentTime = System.currentTimeMillis();
		while (time + currentTime >= System.currentTimeMillis()) {
			if (firstTime) {
				bestAnswer = new Max(iaBoard, depth++, 1, prune, time
						+ currentTime);
				bestAnswer.minimax(Integer.MAX_VALUE, cycles);
				firstTime = false;
			} else {
				Minimax aux = new Max(iaBoard, depth++, 1, prune, time
						+ currentTime);
				int ans = aux.minimax(Integer.MAX_VALUE, cycles);
				if (ans > -1 && aux.value() > bestAnswer.value()) {
					bestAnswer = aux;
				}
			}
		}
		return bestAnswer;
	}

	/**
	 * An evaluation function that substracts the amount of blobs that the
	 * player has from the amount of blobs that the computer has.
	 * 
	 * @param iaBoard
	 *            a reference to the game IABoard.
	 * @return an int value resulting from substracting the amount of blobs that
	 *         the player has from the amount of blobs that the computer has.
	 */
	public int evaluate(IABoard iaBoard) {
		return iaBoard.computerBlobs() - iaBoard.playerBlobs();
	}
}