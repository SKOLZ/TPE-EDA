package frontend;


public interface BoardPanelListener {
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * method used to take action on the cell clicked in the board panel. 
	 * 
	 * @param row
	 * 			row clicked.
	 * @param col 
	 *			column clicked. 
	 */
	public void cellClicked(int row, int column);
}
