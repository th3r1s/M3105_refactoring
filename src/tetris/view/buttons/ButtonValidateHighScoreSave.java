package tetris.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import tetris.controller.ConfigManager;
import tetris.view.Display;

/**
 * @author Sedara
 *
 */
public class ButtonValidateHighScoreSave extends TetrisButton implements ActionListener{

	private static final long serialVersionUID = 1L;
	JTextField field;
	JFrame current;
	JFrame frame;
	int rank;
	
	public ButtonValidateHighScoreSave(Display display, JTextField textField, JFrame currentFrame, JFrame endgame, int rank) {
		super(display, "apply");
		field = textField;
		this.rank = rank;
		current = currentFrame;
		frame = endgame;
		addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(!field.getText().equalsIgnoreCase("")){
			try {
				display.getController().getConfig().saveWithShift(ConfigManager.SECTION_HIGHSCORES,String.valueOf(rank) , field.getText()+"-"+
			display.getController().getScore()+"-"+display.getController().getTetris());
			} catch (IOException e1) {}
			current.dispose();
			current.setState(JFrame.EXIT_ON_CLOSE);
			display.getFrame().toFront();
			frame.toFront();
			frame.setEnabled(true);
			
		}

	}

}
