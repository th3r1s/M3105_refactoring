package tetris.controller;


import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;

import tetris.model.Game;
import tetris.model.Tetrimino;
import tetris.view.Display;

/**
 * Class that redefines the methods required by the Controller interface. It is implemented from the Controller interface.
 * @author Sedara
 *
 */
public class SwingController implements Controller{


	private Game game;

	private Display display;

	private ConfigManager config;

	private Locale locale;

	private ResourceBundle bundle;
	
	private Clip music;

	/**
	 * Method that allows to monitor the swing window.
	 */
	public SwingController(ConfigManager config) {
		this.config = config;
		String lang = "";
		try {
			lang = config.getDataInSection(ConfigManager.SECTION_LANG, "current");
		} catch (IOException e) {}
		locale = new Locale(lang.toLowerCase(), lang.toUpperCase());
		bundle = ResourceBundle.getBundle("TetrisLang", locale);
		game = null;
	}

	/**
	 * Method that allows to launch the music
	 */
	public void playMusic() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("musics/tetris_themeA_loop.wav").getAbsoluteFile());
	        this.music = AudioSystem.getClip();
	        music.open(audioInputStream);
	        music.start();
	        music.loop(Clip.LOOP_CONTINUOUSLY);
	    } catch(Exception ex) {
	    }
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void horizontalSoundEffect() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("soundEffects/horizontalSoundEffect.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	    }
	}

	public void verticalSoundEffect() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("soundEffects/verticalSoundEffect2.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	    }
	}
	
	public void tetrisSoundEffect() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("soundEffects/tetrisSoundEffect.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	    }
	}
	
	public void createNewGame(){
		game = new Game(this);
	}
	
	public void stopGame(){
		if(game != null){
			game.stopTimer();
			game = null;
		}
	}
	
	public void restartTimer(){
		game.stopTimer();
		game.startTimer();
	}

	public String getString(String key){
		return bundle.getString(key);
	}

	public void setLanguage(String lang) {
		locale = new Locale(lang.toLowerCase(), lang.toUpperCase());
		bundle = ResourceBundle.getBundle("TetrisLang", locale);
	}


	public void startDisplay() {
		display = new Display(this);
		SwingUtilities.invokeLater(display);

	}

	/**
	 * Method returning the board dimensions (speaking in Boxes), into an int grid.
	 * @return int[]
	 */
	public int[] getBoardDimensions(){
		int i = game.getBoard().getRows();
		int j = game.getBoard().getColumns();
		int dim[] = {i,j};
		return dim;
	}

	/**
	 * Method that returns the type of a grid by giving it the row and the column.
	 * @param row
	 * @param col
	 * @return Types
	 */
	public Tetrimino getTypeFromBox(int row, int col){
		try{
			return game.getBoard().getBox(row, col).getTetrimino();
		}catch(NullPointerException e){return null;}
	}
	
	public int getScore(){
		return game.getScore();
	}
	
	public int getTetris(){
		return game.getTetris();
	}


	@Override
	public void refreshDisplay(){
		display.refreshGame(getScore(), getTetris());
		display.setNextTetrimino(game.getNextType());
	}
	
	@Override
	public void rotateTetrimino(){
		if(game.rotateTetrimino())
			verticalSoundEffect();
		refreshDisplay();
	}


	@Override
	public void moveTetriminoForward(){		

	}


	@Override
	public void moveTetriminoRight(){
		if(game.moveTetrimino("right"))
			horizontalSoundEffect();
		refreshDisplay();
	}



	@Override
	public void moveTetriminoLeft(){
		if(game.moveTetrimino("left"))
			horizontalSoundEffect();
		refreshDisplay();
	}


	@Override
	public void pause(){
			this.game.stopTimer();
	}

	// TODO Remonte trop haut
	
	public ConfigManager getConfig(){
		return config;
	}

	@Override
	public void startGame(){
		game.play();
		
	}
	
	public boolean isListenerAllowed(){
		return game.isListenerAllowed();
	}

	@Override
	public void notifyWin() {
		display.endGame();
		
	}

}
