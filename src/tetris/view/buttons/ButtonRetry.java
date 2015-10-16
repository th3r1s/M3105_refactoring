package tetris.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import tetris.view.Display;
import tetris.view.TetrisKeyListener;
import tetris.view.containers.Game;

public class ButtonRetry extends TetrisButton implements ActionListener{
	 
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;

	public ButtonRetry(Display display, JFrame endGameFrame){
		super(display, "retry");
		frame = endGameFrame;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		frame.setState(JFrame.EXIT_ON_CLOSE);
		display.getFrame().setEnabled(true);
		display.getFrame().toFront();		
		display.setBoxesColor();
		display.getController().createNewGame();
		display.setGame(new Game(display));
		display.getFrame().setContentPane(display.getGame());
		int tab[] = display.getController().getBoardDimensions();
		display.getFrame().setSize(tab[1]*40+40 +40*5, tab[0]*40+40);
		display.getFrame().setLocationRelativeTo(null);
		display.getFrame().revalidate();
		display.setKeyListener(new TetrisKeyListener(display));
		display.getFrame().requestFocusInWindow();
		display.getController().startGame();
		//display.getController().playMusic();

	}
}
