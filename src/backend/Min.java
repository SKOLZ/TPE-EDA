package backend;

public class Min extends Minimax {

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
	public Min(IABoard iaBoard, int depth, int currentDepth, boolean prune,
			long timeMillis) {
		super(iaBoard, depth, currentDepth, prune, timeMillis);
	}

	/**
	 * It's a recursive algorithm for choosing the next move for the player. A
	 * value is associated with each position or state of the game. This value
	 * is computed by means of a position evaluation function and it indicates
	 * how good it would be for the player to reach that position. The player
	 * then makes the move that minimizes the maximum value of the position
	 * resulting from the computer's possible following moves. If it is the
	 * player's turn to move, the player gives a value to each of his legal
	 * moves.
	 * 
	 * @param value
	 *            an int value used when pruning: if the pruning is activated,
	 *            and value is smaller than the stored value in Max, then Min
	 *            calls minimax for Max, passing value as an argument.
	 *            Otherwise, the method returns the stored value in Min.
	 * 
	 * @param cycles
	 *            an object only used when the time mode is activated: this
	 *            object is used to increment an internal value, and, when such
	 *            value is equal to 500 (defined in the minimax algorithm by the
	 *            user), then the algorithm asks weather it ran out of time or
	 *            not.
	 * 
	 * @return an int value only used when the time mode is activated: if the
	 *         return value of a minimax call made from Min is equal to -1, then
	 *         it means the algorithm run out of time; otherwise, the algorithm
	 *         still has some time to run.
	 */
	@Override
	public int minimax(int value, Cycle cycles) {
		if (time()) {
			if (cycles.cycle() >= 500) {
				if (timeMillis() <= System.currentTimeMillis()) {
					return -1;
				}
				cycles.reset();
			}
		}
		setValue(Integer.MAX_VALUE);
		for (int i = 0; i < board().rows(); i++) {
			for (int j = 0; j < board().columns(); j++) {
				if (board().at(i, j) == 'p') {
					int iLim = Math.min(i + 3, board().rows());
					int jLim = Math.min(j + 3, board().columns());
					for (int i1 = Math.max(i - 2, 0); i1 < iLim; i1++) {
						for (int j1 = Math.max(j - 2, 0); j1 < jLim; j1++) {
							cycles.increment();
							IABoard aux = analizeMove(i, j, i1, j1);
							if (aux != null) {
								if (aux.gameOver()) {
									if (aux.playerHasWon()) {
										assignBestMove(Integer.MIN_VALUE, i, j,
												i1, j1);
									} else {
										assignBestMove(Integer.MAX_VALUE - 1,
												i, j, i1, j1);
									}
									return value();
								} else {
									if (currentDepth() < depth()) {
										if (prune()) {
											if (value < value()) {
												Max max = new Max(aux, depth(),
														currentDepth() + 1,
														prune(), timeMillis());
												max.minimax(value(), cycles);
												if (time() && max.value() == -1) {
													return -1;
												}
												assignBestMove(max.value(), i,
														j, i1, j1);
											} else {
												return value;
											}
										} else {
											Max max = new Max(aux, depth(),
													currentDepth() + 1,
													prune(), timeMillis());
											max.minimax(value, cycles);
											if (time() && max.value() == -1) {
												return -1;
											}
											assignBestMove(max.value(), i, j,
													i1, j1);
										}
									} else {
										assignBestMove(evaluate(aux), i, j, i1,
												j1);
									}
								}
							}
						}
					}
				}
			}
		}
		return value();
	}

	/**
	 * If the value parameter is smaller than the value stored in Min, then the
	 * stored value will be replaced with the one received, and the best
	 * movement stored in Min will also be updated.
	 * 
	 * @param value
	 *            the value of a certain movement made by Min.
	 * @param iFrom
	 *            the initial row number in the Board of the movement made by
	 *            Min.
	 * @param jFrom
	 *            the initial column number in the Board of the movement made by
	 *            Min.
	 * @param iTo
	 *            the final row number in the Board of the movement made by Min.
	 * @param jTo
	 *            the final column number in the Board of the movement made by
	 *            Min.
	 */
	public void assignBestMove(int value, int iFrom, int jFrom, int iTo, int jTo) {
		if (value() > value) {
			setMovement(iFrom, jFrom, iTo, jTo);
			setValue(value);
		}
	}
}