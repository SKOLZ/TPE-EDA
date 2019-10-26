package frontend;
import java.awt.Color;
import java.awt.Image;

public class AboutPanel extends ImagePanel {
		
	private static final long serialVersionUID = 1L;
	
	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * Constructor for the AboutUs class.
	 * 
	 * @param image
	 *            the äbout us"image shown in the menu.
	 * 
	 */
	public AboutPanel(Image image){
		super(image);
		setSize(350, 350);
		setLocation(145, 125);
		setBackground(new Color(255,255,255,180));
	}
}