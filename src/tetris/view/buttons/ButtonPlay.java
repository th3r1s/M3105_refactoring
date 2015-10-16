package tetris.view.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tetris.view.Display;
import tetris.view.TetrisKeyListener;
import tetris.view.containers.Game;

/**
 * @author Sedara
 *
 */
public class ButtonPlay extends TetrisButton implements ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ButtonPlay(Display display) {
		super(display, "play");
		addActionListener(this);
		setPreferredSize(new Dimension(300, 100));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
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
		display.getController().playMusic();
	}

}
