package tetris.controller;

/**
 * Interface that creates contains the necessary methods between the model and the view.
 * @author Sedara
 *
 */
public interface Controller {
	
	/**
	 * Method that creates a new game.
	 */
	public void createNewGame();
	
	/**
	 * Method that allows to display for the first time the GUI.
	 */
	public void startDisplay();
	
	/**
	 * Method that allows to refresh the display previously started.
	 */
	public void refreshDisplay();
	
	/**
	 * Method allowing to rotate a tetrimino on the GUI.
	 */
	public void rotateTetrimino();
	
	/**
	 * Method that allows to make the tetrimino currently played to go one box down.
	 */
	public void moveTetriminoForward();
	
	public void moveTetriminoRight();
	
	public void moveTetriminoLeft();
	
	public void pause();
	
	public void startGame();
	
	public void notifyWin();
	
	public void playMusic();
	
	public void stopMusic();
	
	public void horizontalSoundEffect();
	
	public void verticalSoundEffect();
	
	public void tetrisSoundEffect();

}