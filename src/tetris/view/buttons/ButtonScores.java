package tetris.view.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import tetris.view.Display;
import tetris.view.containers.HighScores;

/**
 * @author Sedara
 *
 */
public class ButtonScores extends TetrisButton implements ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ButtonScores(Display display) {
		super(display, "scores");
		addActionListener(this);
		setPreferredSize(new Dimension(300, 100));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			display.setScores(new HighScores(display));
		} catch (IOException e1) {}
		display.getFrame().setContentPane(display.getScores());
		display.getFrame().revalidate();
		
	}

}
