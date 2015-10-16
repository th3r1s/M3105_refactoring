package tetris.model;

/**
 * Enumeration that contains every box states.
 * @author th3r1s
 *
 */
public enum States {
	
	/**
	 * The box has nothing on it, no tetrimino.
	 */
	EMPTY(' '),
	
	/**
	 * The box is played, a tetrimino is passing through it.
	 */
	PLAYED('X'),
	
	/**
	 * The box has a tetrimino placed on it (it can't move unless you get a complete line).
	 */
	PLACED('O');
	
	private char id;
	
	private States(char id) {
		this.id = id;
	}
	
	
	public char toChar(){
		return this.id;
	}
	
}
