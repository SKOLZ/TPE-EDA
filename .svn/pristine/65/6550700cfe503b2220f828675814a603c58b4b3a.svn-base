package frontend;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MenuFrame extends CommonFrame {

	private static final long serialVersionUID = 1L;
	private JPanel actualPanel;
	private Image background;
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * MenuFame's constructor. 
	 * 
	 */
	public MenuFrame() {
		super();
		File bg = new File("images/blobwars.png");
		try {
			background = ImageIO.read(bg);
		} catch (IOException e) {
			showErrorMessage(e.getMessage(), "System error");
		}
		actualPanel = new ImagePanel(background);
		add(actualPanel);

		final MenuButton newGame = new MenuButton("New Game", 20, 200);
		MouseOver moNewGame = new MouseOver() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				newGame.removeMouseListener(this);
				GameFrame game = new GameFrame();
				setVisible(false);
				Sounds.stopAll();
				game.setVisible(true);
			}
		};
		moNewGame.setButton(newGame);
		newGame.addMouseListener(moNewGame);

		MenuButton aboutUs = new MenuButton("About Us", 20, 250);
		MouseOver moAboutUs = new MouseOver() {
			public AboutPanel about;
			@Override
			public void mouseClicked(MouseEvent arg0) {
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				repaint();
				File aboutUs = new File("images/about.png");
				Image aboutUsImg = null;
				try {
					aboutUsImg = ImageIO.read(aboutUs);
					about = new AboutPanel(aboutUsImg);
					add(about);
					getContentPane().setComponentZOrder(about, 0);
					getContentPane().setComponentZOrder(actualPanel, 1);
					repaint();
				} catch (IOException e1) {
					showErrorMessage(e1.getMessage(), "System error");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				about.setVisible(false);
				remove(about);
			}
			
			
		};
		moAboutUs.setButton(aboutUs);
		aboutUs.addMouseListener(moAboutUs);

		MenuButton quit = new MenuButton("Quit", 20, 300);
		MouseOver moQuit = new MouseOver() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		};
		moQuit.setButton(quit);
		quit.addMouseListener(moQuit);

		actualPanel.add(newGame);
		actualPanel.add(aboutUs);
		actualPanel.add(quit);
	}

}
