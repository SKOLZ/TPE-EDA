package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CommonFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int cellSize = 64;
	private static final int rows = 8;
	private static final int cols = 8;
	private static final int fixWidth = 6;
	private static final int fixHeight = 28;

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * creates a frame with the proprties shared by it's subclasses. 
	 * 
	 */
	public CommonFrame() {
		super("Umbrella Blob Wars");
		File icon = new File("images/icon.png");
		try {
			Image iconImg = ImageIO.read(icon);
			setIconImage(iconImg);
		} catch (IOException e) {
			showErrorMessage(e.getMessage(), "System error");
		}
		setSize(cellSize * rows + fixWidth, cellSize * cols + fixHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2
				- getHeight() / 2);
		setLayout(null);
	}

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * shows errors in a new frame. 
	 * 
	 * @param error
	 * 			type of error to be sent.
	 * @param title 
	 * 			title of the new error frame.
	 * 
	 */
	public void showErrorMessage(String error, String title) {
		JLabel errorMessage = new JLabel(error);
		errorMessage.setSize(errorMessage.getText().length() * 7, 80);
		errorMessage.setVisible(true);
		JFrame popup = new JFrame();
		Dimension size = popup.getToolkit().getScreenSize();
		popup.setTitle(title);
		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popup.setSize(errorMessage.getSize());
		JPanel panel = new JPanel();
		panel.setSize(popup.getSize());
		popup.setLocation(size.width / 2 - popup.getWidth() / 2, size.height
				/ 2 - popup.getHeight() / 2);
		panel.setBackground(new Color(255, 255, 255));
		panel.add(errorMessage);
		panel.setVisible(true);
		popup.add(panel);
		popup.setVisible(true);
		popup.setResizable(false);
	}
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * returns the columns. 
	 * 
	 * 
	 */
	public int cols() {
		return cols;
	}

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * returns the rows. 
	 * 
	 * 
	 */
	public int rows() {
		return rows;
	}
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * returns the fixWidth. 
	 * 
	 * 
	 */
	public int fixWidth() {
		return fixWidth;
	}
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * returns the fixHeight. 
	 * 
	 * 
	 */
	public int fixHeight() {
		return fixHeight;
	}
		
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * returns the cellSize. 
	 * 
	 * 
	 */
	public int cellSize() {
		return cellSize;
	}
}
