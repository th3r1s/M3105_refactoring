package tetris.view;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;

import tetris.controller.ConfigManager;
import tetris.controller.SwingController;
import tetris.model.Tetrimino;
import tetris.view.containers.Game;
import tetris.view.containers.HighScores;
import tetris.view.containers.HomeMenu;

/**
 * Class that's used to monitor the display on the Swing interface.
 * @author Sedara
 *
 */
public class Display implements Runnable{

	private JFrame frame;
	private final HomeMenu homeMenu;
	private Game game;
	private HighScores scores;
	private SwingController controller;
	private KeyListener keyListener;
	
	public Display(SwingController controller) {
		this.controller = controller;
		this.homeMenu = new HomeMenu(this);
		this.keyListener = null;
	}
	
	@Override
	public void run() {
		this.frame = new JFrame();		
		
		frame.setContentPane(homeMenu);
		frame.setTitle("Tetris");
		frame.setSize(440, 900);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void setBoxesColor(){
		for(BoxesColors b : BoxesColors.values()){
			try {
				b.setColor(new Color(Integer.parseInt(controller.getConfig().getDataInSection(ConfigManager.SECTION_BOXES_COLORS, b.toString()))));
			} catch (NumberFormatException  | IOException e) {}
		}
	}
	
	public void setNextTetrimino(Tetrimino type){
		game.getNextTetrimino().resetPanel();;
		game.getNextTetrimino().setTetrimino(type, 3, 2);
	}
	
	public void endGame(){
		frame.setEnabled(false);
		FrameEndGame frameEndGame = new FrameEndGame(this);
		String[] data;
		int rank, score;
		try {
			for(String str : getController().getConfig().getDataFromEntiereSection(ConfigManager.SECTION_HIGHSCORES)){
				data = str.split(":");
				rank = Integer.valueOf(data[0]);
				data = data[1].split("-");
				score = (!data[1].equals(" ") ? Integer.valueOf(data[1]) : 0);
				if(score < getController().getScore()){
					new FrameHighScoreSaver(this, frameEndGame, rank);
					frameEndGame.setEnabled(false);
					break;
				}
			}
		} catch (NumberFormatException | IOException e) {}
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public HomeMenu getHomeMenu() {
		return homeMenu;
	}
	
	public HighScores getScores() {
		return scores;
	}
	
	public void setScores(HighScores scores) {
		this.scores = scores;
	}
	
	public void refreshGame(int score, int tetris){
		game.refresh(score, tetris);
	}
	
	public SwingController getController() {
		return controller;
	}
	
	public void setKeyListener(KeyListener keyListener) {
		if(this.keyListener != null)
			frame.removeKeyListener(this.keyListener);
		this.keyListener = keyListener;
		frame.addKeyListener(keyListener);
	}
	

}
