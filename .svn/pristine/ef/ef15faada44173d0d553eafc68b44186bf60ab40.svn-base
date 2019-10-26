package frontend;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EndFrame extends CommonFrame {

	private static final long serialVersionUID = 1L;
	Image img;
	ImagePanel background; 
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * EndFrame's constructor. 
	 * 
	 * @param s 
	 * 			string that decides to show either the win or lose photo.
	 * 
	 */
	public EndFrame(String s) {
		super();
		File bg = new File("images" + File.separator + s);
		try {
			img = ImageIO.read(bg);
		} catch (IOException e) {
			showErrorMessage(e.getMessage(), "System error");
		}
		background = new ImagePanel(img);
		add(background);

		MenuButton restart = new MenuButton("RESTART", 35, 460);
		MouseOver moRestart = new MouseOver() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GameFrame game = new GameFrame();
				setVisible(false);
				game.setVisible(true);
			}
		};
		moRestart.setButton(restart);
		restart.addMouseListener(moRestart);
		background.add(restart);
	}

}
