package tetris.model;

/**
 * Class that represents a tetrimino.
 * @author th3r1s
 *
 */
public class Tetriminos {
	
	private final Location initLocation;
	
	private final Types type;
		
	/**
	 * Constructor for our tetriminos, including the box the tetrimino will take.
	 * @param type
	 * @param location
	 */
	public Tetriminos(Types type) 
	{
		this.type=type;
		this.initLocation = new Location(0,5);

	}
	
	/**
	 * Getter that returns the type (the tetrimino) of the current box.
	 * @return Types
	 */
	public Types getType() {
		return type;
	}
	
	public Location getInitLocation() {
		return initLocation;
	}
	
	@Override
	public String toString() {
		switch (this.type)
		{
		case I : return "I";
		case O : return "O";
		case L : return "L";
		case Z : return "Z";
		case S : return "S";
		case T : return "T";
		default : return "Unknown";
		}
	}
}
