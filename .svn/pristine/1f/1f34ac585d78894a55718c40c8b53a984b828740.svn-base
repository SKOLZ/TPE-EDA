package frontend;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MouseOver extends MouseAdapter{
        
    private JButton b;
    
	/**
	 * 
	 * sets the button to work with.
	 * 
	 * @param b
	 * 		button to apply things to.
	 */
    public void setButton(JButton b){
            this.b = b;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
            b.setForeground(Color.RED);
    }
    @Override
    public void mouseExited(MouseEvent e) {
            b.setForeground(Color.BLACK);
    }

}
