package tetris.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import tetris.view.Display;

public class ButtonCancel extends TetrisButton implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JFrame frameToClose;
	
	private JFrame frameToFront;

	public ButtonCancel(Display display, JFrame frameToClose) {
		this(display, frameToClose, null);
	}
	
	public ButtonCancel(Display display, JFrame frameToClose, JFrame frameToFront) {
		super(display, "cancel");
		this.frameToClose = frameToClose;
		this.frameToFront = frameToFront;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frameToClose.dispose();
		display.getFrame().toFront();
		if(frameToFront != null){
			frameToFront.toFront();
			frameToFront.setEnabled(true);
		}
	}

}
