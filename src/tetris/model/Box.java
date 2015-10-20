package tetris.model;

/**
 * Class that represents the boxes into the game grid.
 * @author Numa
 *
 */
public class Box {

	private final Location boxLocation;

	private States state;

	private Tetrimino tetrimino;

	/**
	 * Constructor parameterized for Box class.
	 * @param position
	 */
	public Box(Location position) {
		this.boxLocation=position;
		this.state=States.EMPTY;
		this.tetrimino = null;
	}


	/**
	 * Method allowing to get a Box position.
	 * @return
	 */
	public Location getBoxLocation() 
	{
		return boxLocation;
	}

	/**
	 * Method allowing to get the tetrimino assigned to a box.
	 * @return
	 */
	public Tetrimino getTetrimino()
	{
		return tetrimino;
	}

	/**
	 * Method allowing to modify the tetrimino assigned to a box.
	 * @param tetrimino
	 */
	public void setTetrimino(Tetrimino tetrimino) 
	{
		this.tetrimino = tetrimino;
		this.state = States.PLAYED;  //TODO : Remettre PLACED � la place de PLAYED
	}
	
	public void setTetrimino(Tetrimino tetrimino, States state) 
	{
		this.tetrimino = tetrimino;
		this.state = state;  //TODO : Remettre PLACED � la place de PLAYED
	}

	/**
	 * Method allowing to modify the state of a Box.
	 * @param state
	 */
	public void setState(States state)
	{
		this.state = state;
	}

	/**
	 * Getter returning the state of the selected box.
	 * @return States
	 */
	public States getState()
	{
		return this.state;
	}
	
	public void removeTetrimino(States state){
		if(state == States.EMPTY)
			tetrimino = null;
		this.state = state;  //TODO : Remettre PLACED � la place de PLAYED
	}
	
	@Override
	public String toString() 
	{
		return "| "+this.state.toChar()+" ";
		
	}
}
