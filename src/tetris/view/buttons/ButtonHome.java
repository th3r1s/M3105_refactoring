package tetris.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import tetris.view.Display;

/**
 * @author Sedara
 *
 */
public class ButtonHome extends TetrisButton implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	
	public ButtonHome(Display display) {
		this(display, null);		
	}
	
	public ButtonHome(Display display, JFrame frameToClose) {
		super(display, "home");
		frame = frameToClose;
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(frame != null){
			frame.dispose();
			frame.setState(JFrame.EXIT_ON_CLOSE);
		}
		try{
			display.getController().stopMusic();
		}catch(NullPointerException e1){}
		display.getController().stopGame();
		display.getFrame().setSize(440, 900);
		display.getFrame().setLocationRelativeTo(null);
		display.getFrame().setContentPane(display.getHomeMenu());
		display.getFrame().setEnabled(true);
		display.setKeyListener(null);
		display.getFrame().revalidate();
		display.getFrame().toFront();
	}

}
