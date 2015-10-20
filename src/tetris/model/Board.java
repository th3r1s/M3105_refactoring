package tetris.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the game board.
 * @author th3r1s
 *
 */
public class Board {

	private final static int NB_ROWS = 22;

	private final static int NB_COLUMNS = 10;

	private final int rows;

	private final int columns;

	private Box[][] box;
	
	private Location[] currentLocationPlayed;


	/**
	 * Constructor with parameters for Board class.
	 * @param rows
	 * @param columns
	 */
	public Board(int rows, int columns) 
	{
		
		this.rows=rows;
		this.columns=columns;
		this.box = new Box[rows][columns];
		currentLocationPlayed = new Location[4];
		InitBox();
	}
	
	/**
	 * Constructor for Board class.
	 */
	public Board()
	{
		this(NB_ROWS,NB_COLUMNS);
	}
	
	/**
	 * Collection that returns the played boxes into a list.
	 * @return List<Box>
	 */
	public List<Box> getPlayedBoxes(){
		List<Box> list = new ArrayList<Box>();
		for(int i=0;i<4;i++){
			list.add(box[currentLocationPlayed[i].getRow()][currentLocationPlayed[i].getColumn()]);
		}
		return list;
	}
	
	public void move(List<Box> newList){
		for(int i=0;i<4;i++){
			currentLocationPlayed[i] = newList.get(i).getBoxLocation();
		}
	}
	
	

	
	public void setTetrimino(Tetrimino tetrimino, Location location){
		box[location.getRow()][location.getColumn()].setTetrimino(tetrimino);
	}
	
	public void removeTetrimino(Location location, States state){
		box[location.getRow()][location.getColumn()].removeTetrimino(state);
	}
	
	/**
	 * Method that initializes the Box grid.
	 */
	public void InitBox()
	{
		for(int counterRows = 0 ; counterRows < NB_ROWS; counterRows++)
		{
			for(int counterColumns = 0 ; counterColumns < NB_COLUMNS; counterColumns++)
			{
				this.box[counterRows][counterColumns] = new Box(new Location(counterRows, counterColumns));
			}
		}
	}
	
	/**
	 * Method returning the box which is the next on the board (depending on the direction you want to get the box from and the box chosed first).
	 * @param direction
	 * @param initialBox
	 * @return Box
	 */
	public Box getNextBox(String direction, Location location){
		switch(direction)
		{
		case "bottom" : return getBottomBox(location);
		case "upper" : return getUpperBox(location);
		case "left" : return getLeftBox(location);
		case "right" : return getRightBox(location);
		default:
			return null;
		}
	}
	
	/**
	 * allow to get the bottom box of the location.
	 * @param location from the method is used
	 * @return the box we need to get
	 */
	private Box getBottomBox(Location position){
		return this.box[position.getRow()+1][position.getColumn()];
	}
	
	/**
	 * allow to get the upper box of the location.
	 * @param location from the method is used
	 * @return the box we need to get
	 */
	private Box getUpperBox(Location position){
		return this.box[position.getRow()-1][position.getColumn()];
	}
	
	/**
	 * allow to get the left box of the location.
	 * @param location from the method is used
	 * @return the box we need to get
	 */
	private Box getLeftBox(Location position){
		return this.box[position.getRow()][position.getColumn()-1];
	}
	
	/**
	 * allow to get the right box of the location.
	 * @param location from the method is used
	 * @return the box we need to get
	 */
	private Box getRightBox(Location position){
		return this.box[position.getRow()][position.getColumn()+1];
	}

	/**
	 * Method that displays the game board.
	 */
	public Box getBox(int row, int column)
	{
		return this.box[row][column];
	}
	
	public Location[] getCurrentLocationPlayed() {
		return currentLocationPlayed;
	}
	
	public void setCurrentLocationPlayed(Location[] currentLocationPlayed) {
		this.currentLocationPlayed = currentLocationPlayed;
	}
	
	public String toString()
	{
		String str="---------------------------------------------\n"
				+ "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |\n---------------------------------------------\n";
		for(int counterRows = 0; counterRows < this.rows ; counterRows++)
		{
        	str += "| "+counterRows+ (counterRows < 10 ? " ": "" );
			for(int counterColumns = 0; counterColumns < this.columns; counterColumns++)
			{
				str += this.box[counterRows][counterColumns].toString();
			}
            str += "|\n---------------------------------------------\n";
		}	
		return str;
	}
	
	/**
	 * Getter that returns the number of row.
	 * @return int
	 */
	public int getRows() 
	{
		return rows;
	}
	
	/**
	 * Method that returns the number of columns.
	 * @return int
	 */
	public int getColumns() 
	{
		return columns;
	}

}
