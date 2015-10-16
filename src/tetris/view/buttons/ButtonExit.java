package tetris.view.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import tetris.view.Display;

/**
 * @author Sedara
 *
 */
public class ButtonExit extends TetrisButton implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public ButtonExit(Display display) {
		super(display, "exit");
		addActionListener(this);
		setPreferredSize(new Dimension(300, 100));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		display.getFrame().dispose();
		display.getFrame().setState(JFrame.EXIT_ON_CLOSE);
		
	}

}
