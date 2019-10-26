package backend;

public class Cell {

	private Blob blob;

	/**
	 * Constructor for the Cell class.
	 * 
	 * @param blob
	 *            the Blob that will be contained in the current Cell object.
	 */
	public Cell(Blob blob) {
		this.blob = blob;
		blob.add(blob.board());
	}

	/**
	 * A setter for the Cell's Blob.
	 * 
	 * @param blob
	 *            the Blob that will be contained in the current Cell object.
	 * @return this.
	 */
	public Cell setBlob(Blob blob) {
		if (isEmpty()) {
			this.blob = blob;
		}
		return this;
	}

	/**
	 * Returns a char representation of the Blob contained in the current Cell.
	 * The boolean parameter specifies weather the player moves first or not:
	 * this is because of the internal implementation of the IA.
	 * 
	 * @param boolean A value that represents weather the player moves first or
	 *        not.
	 * 
	 * @return a char representation of the current Blob.
	 */
	public char toChar(boolean willSwap) {
		return blob.toChar(willSwap);
	}

	/**
	 * Returns a boolean value that determines weather this Cell contains an
	 * Emptyness Blob instance.
	 * 
	 * @return a boolean value that determines weather a this Cell contains an
	 *         Emptyness blob or not.
	 */
	public boolean isEmpty() {
		return blob.isEmpty();
	}

	/**
	 * Returns a boolean value that determines weather the current Cell contains
	 * a Player Blob instance.
	 * 
	 * @return a boolean value that determines weather the current Cell contains
	 *         a Player Blob instance.
	 */
	public boolean containsPlayer() {
		return blob.isPlayer();
	}

	/**
	 * A getter for the Cell's Blob.
	 * 
	 * @return the Blob contained in the current Cell.
	 */
	public Blob blob() {
		return blob;
	}

	/**
	 * A getter for the row position of the current Cell.
	 * 
	 * @return the row position of the current Cell.
	 */
	public int row() {
		return blob.row();
	}

	/**
	 * A getter for the column position of the current Cell.
	 * 
	 * @return the column position of the current Cell.
	 */
	public int column() {
		return blob.column();
	}

	/**
	 * Every Cell object should know the Board in which it is contained: this is
	 * because sometimes some Cell objects need to alter the state of their
	 * neighbors, and this can only be achieved by having a reference to the
	 * container Board.
	 * 
	 * @return a reference to the Board that contains the current Cell.
	 */
	public Board board() {
		return blob.board();
	}

	/**
	 * Returns the opposite of the Blob contained in the current Cell.
	 * 
	 * @param blob
	 *            The current blob
	 * @return a blob that is the exact opposite of the Blob contained in the
	 *         current Cell.
	 */
	public Blob convert(Blob blob) {
		return this.blob = this.blob.convert(blob);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blob == null) ? 0 : blob.hashCode());
		result = prime * result
				+ ((blob.board() == null) ? 0 : blob.board().hashCode());
		result = prime * result + blob.column();
		result = prime * result + blob.row();
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
		Cell other = (Cell) obj;
		if (blob == null) {
			if (other.blob != null)
				return false;
		} else if (!blob.equals(other.blob))
			return false;
		if (blob.board() == null) {
			if (other.board() != null)
				return false;
		} else if (!blob.board().equals(other.board()))
			return false;
		if (blob.column() != other.column())
			return false;
		if (blob.row() != other.row())
			return false;
		return true;
	}
}