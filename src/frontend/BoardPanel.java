package frontend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;

import backend.Board;
import backend.Computer;
import backend.Emptyness;
import backend.Player;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final int cellSize;
	private final int rows, columns;
	private Image[][] cells;
	private Board board;
	private HashMap<String, Image> map;
	private BoardPanelListener listener;
	private boolean listenerEnabled;

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * BoardPanel constructor
	 * 
	 * @param rows
	 * 			board panel rows amount.
	 * @param cols 
	 * 			board panel columns amount.
	 * @param cellSize
	 * 			defines the size of each cell.
	 * 
	 */
	public BoardPanel(final int rows, int cols, final int cellSize) throws IOException {
		this.rows = rows;
		this.columns = cols;
		this.cellSize = cellSize;
		this.cells = new Image[rows][columns];
		this.listenerEnabled = true;
		setBounds(0, 25, cols*cellSize, rows*cellSize);
		setSize(columns * cellSize + 1, rows * cellSize + 1);
		map = loadImages();
		setLayout(null);
		addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int row = e.getY() / cellSize;
				int col = e.getX() / cellSize;
				if (row >= rows || col >= columns || row < 0 || col < 0) {
					return;
				}
				if (listenerEnabled) {
					listener.cellClicked(row, col);
				}
			}
		});
	}

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * update images in the image board
	 * 
	 * @param b
	 * 			board to set images from.
	 * 
	 */
	public void setBoard(Board b) {
		board = b;
		for (int i = 0; i < b.rows(); i++) {
			for (int j = 0; j < b.cols(); j++) {
				setImage(i, j, map.get("background"+i+j));
				if(board.blobAt(i,j) != null){
					appendImage(i, j, map.get(board.blobAt(i, j).getClass().getName()));
				}
			}
		}
		repaint();
	}

	/**
	 * Elimina el contenido de una celda determinada. Para que el cambio se vea
	 * reflejado es necesario invocar al metodo repaint.
	 */
	public void clearImage(int row, int column) {
		cells[row][column] = null;
	}

	/**
	 * Coloca una imagen en una celda determinada. Si la celda ya contenía otra
	 * imagen, la reemplaza. Para que el cambio se vea reflejado es necesario
	 * invocar al metodo repaint.
	 */
	public void setImage(int row, int column, Image image) {
		cells[row][column] = new BufferedImage(cellSize, cellSize,
				BufferedImage.TYPE_INT_ARGB);
		cells[row][column].getGraphics().drawImage(image, 0, 0, null);
	}

	/**
	 * Superpone una imagen sobre una celda. Si la celda está vacía, es
	 * equivalente a usar {@code setImage}. Si la celda no está vacía y la
	 * imagen a superponer contiene transparencias, entonces se superpone la
	 * imagen encima de la existente. Para que el cambio se vea reflejado es
	 * necesario invocar al metodo repaint.
	 */
	public void appendImage(int row, int column, Image image) {
		if (cells[row][column] == null) {
			cells[row][column] = new BufferedImage(cellSize, cellSize,
					BufferedImage.TYPE_INT_ARGB);
		}
		cells[row][column].getGraphics().drawImage(image, 0, 0, null);
	}

	/**
	 * @see JPanel#paint(Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (cells[i][j] != null) {
					g.drawImage(cells[i][j], j * cellSize, i * cellSize, null);
				}
			}
		}
	}
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * Sets the mouse listener to be able to play the game.
	 * 
	 * @param lis
	 * 			listener used to play the game.
	 * 
	 */
	public void setListener(BoardPanelListener lis){
		this.listener = lis;
	}
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * sets the listener to enabled.
	 * 
	 * 
	 */
	public void enableListener(){
		listenerEnabled = true;
	}
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * stes te listener to disabled.
	 * 
	 */
	public void disableListener(){
		listenerEnabled = false;
	}

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * loads the images to a map.
	 * 
	 */
	public HashMap<String, Image> loadImages() throws IOException {
		HashMap<String, Image> imageMap = new HashMap<String, Image>();
		Image[][] background = new Image[rows][columns];
		for(int i=0 ; i<rows ; i++ ){
			for(int j=0 ; j<columns ; j++){
				background[i][j] = ImageUtils.loadImage("images" + File.separator + "background"+i+j+".png");
			}
		}
		Image greenBlob = ImageUtils.loadImage("images" + File.separator + "greenBlob.png");
		Image blueBlob = ImageUtils.loadImage("images" + File.separator + "blueBlob.png");
		Image emptyness = ImageUtils.loadImage("images" + File.separator + "empty.png");
		
		for(int i=0 ; i<rows ; i++ ){
			for(int j=0 ; j<columns ; j++){
				imageMap.put("background"+i+j, background[i][j]);
			}
		}
		imageMap.put(Player.class.getName(), blueBlob);
		imageMap.put(Computer.class.getName(), greenBlob);
		imageMap.put(Emptyness.class.getName(), emptyness);
		return imageMap;
	}
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * hilights the cells with a distance less or equal to 2 from the 
	 * row and col in params if it's empty. 
	 * 
	 * @param row
	 * 			row where the object to be enlightened is positioned.
	 * @param col 
	 * 			column where the object to be enlightened is positioned.
	 * 
	 */
	public void lightOn(int row, int col){
		for(int i = Math.max(row-2, 0) ; i < Math.min(row+3, rows) ; i++){
			for(int j = Math.max(col-2, 0) ; j < Math.min(col+3, columns) ; j++){
				if(board.at(i,j).isEmpty()){
					setImage(i,j,ImageUtils.colorize(cells[i][j], Color.LIGHT_GRAY));
					repaint();
				}
			}
		}
		repaint();
	}
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * turns off the hilightings of the cells with a distance less or equal to 2 from the 
	 * row and col in params if it's empty. 
	 * 
	 * @param row
	 * 			row where the object to be darkened is positioned.
	 * @param col 
	 * 			column where the object to be darkened is positioned.
	 * 
	 */
	public void lightOff(int row, int col){
		for(int i = Math.max(row-2, 0) ; i < Math.min(row+3, rows) ; i++){
			for(int j = Math.max(col-2, 0) ; j < Math.min(col+3, columns) ; j++){
				if(board.at(i,j).isEmpty()){
					setImage(i, j, map.get("background"+i+j));
				}
			}
		}
	}
}
