package tetris.view.containers;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import tetris.controller.ConfigManager;
import tetris.view.Display;
import tetris.view.buttons.ButtonHome;
import tetris.view.buttons.ButtonMusic;

/**
 * Class that represents the game class from model to Swing interface.
 * @author Sedara
 *
 */
public class Game extends JSplitPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Board board;
	
	private final Board nextTetrimino;
	
	private final JLabel infoGame;
	
	private Display display;
	
	/**
	 * Constructor that sets up a game before its launching.
	 * @param display
	 */
	public Game(Display display) {
		super(JSplitPane.HORIZONTAL_SPLIT);
		this.display = display;
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		jSplitPane.setDividerSize(0);
		nextTetrimino = new Board(display, 5, 5, false);
		JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JPanel jp = new JPanel();
		infoGame = new JLabel();
		jsp.add(infoGame);
		jp.add(new ButtonHome(display));
		ButtonMusic buttonMusic = new ButtonMusic(display);
		buttonMusic.setFocusable(false);
		jp.add(buttonMusic);
		
		jsp.add(jp);
		jsp.setResizeWeight(0.9);
		jsp.setDividerSize(0);
		jSplitPane.add(nextTetrimino);
		jSplitPane.add(jsp);
		int tab[] = display.getController().getBoardDimensions();
		JPanel jPanel = new JPanel();
		board = new Board(display, tab[0], tab[1], false);
		jSplitPane.setPreferredSize(new Dimension(40*5,40*5));
		add(jSplitPane);
		jPanel.add(board);
		add(jPanel);
		setDividerSize(0);
		setInfoText(0,0);
		
		
	}
	
	/**
	 * Call to the method used to refresh the board on the Swing interface.
	 */
	public void refresh(int score, int tetris){
		board.refreshGamingPanel();
		setInfoText(score, tetris);
	}
	
	public Board getNextTetrimino() {
		return nextTetrimino;
	}
	
	
	public void setInfoText(int score, int tetris){
		String str = "";
		try {
			str = "<html>"+display.getController().getString("score")+" : " + score +
					"<br> Tetris : " + tetris +
					"<br><br><br><br>" + display.getController().getString("setpause1") + " [" + 
					KeyEvent.getKeyText(Integer.parseInt(display.getController().getConfig().getDataInSection(ConfigManager.SECTION_CONTROLS, "pause"))) + "] " +
					display.getController().getString("setpause2") + "<br>" + " [" +
					KeyEvent.getKeyText(Integer.parseInt(display.getController().getConfig().getDataInSection(ConfigManager.SECTION_CONTROLS, ConfigManager.CONTROL_FORWARD))) + "] " +
					display.getController().getString("setpause3") +
					"</html>";
		} catch (IOException e) {}		
		
		
		infoGame.setText(str);
		
	}
	

}
