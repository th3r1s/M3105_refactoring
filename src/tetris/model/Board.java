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
	
	
	/**
	 * Method allowing to set a tetrimino on the board by making the relatives Box around the first box having a tetrimino on it, depending on the tetrimino wanted.
	 * @param tetrimino
	 */
	public boolean createTetrimino(Tetriminos tetrimino, Location location)
	{
		List<Box> list = new ArrayList<Box>();
		
		Location[] tab = new Location[4];
		
		switch (tetrimino.getType()){
		case I:
			tab[1]= location;
			tab[0]= new Location(location.getRow()+1,location.getColumn());
			tab[2]= new Location(location.getRow()+2,location.getColumn());
			tab[3]= new Location(location.getRow()+3,location.getColumn());
			break;
		case O:
			tab[0]= location;
			tab[1]= new Location(location.getRow()+1,location.getColumn());
			tab[2]= new Location(location.getRow()+1,location.getColumn()-1);
			tab[3]= new Location(location.getRow(),location.getColumn()-1);
			break;
		case L:
			tab[2]= location;
			tab[0]= new Location(location.getRow()+1,location.getColumn());
			tab[1]= new Location(location.getRow()+2,location.getColumn());
			tab[3]= new Location(location.getRow()+2,location.getColumn()+1);
			break;
		case Z:
			tab[0]= location;
			tab[3]= new Location(location.getRow(),location.getColumn()-1);
			tab[1]= new Location(location.getRow()+1,location.getColumn());
			tab[2]= new Location(location.getRow()+1,location.getColumn()+1);
			break;
		case S:
			tab[0]= location;
			tab[3]= new Location(location.getRow(),location.getColumn()+1);
			tab[1]= new Location(location.getRow()+1,location.getColumn());
			tab[2]= new Location(location.getRow()+1,location.getColumn()-1);
			break;
		case T:
			tab[0]= location;
			tab[1]= new Location(location.getRow()+1,location.getColumn());
			tab[2]= new Location(location.getRow(),location.getColumn()-1);
			tab[3]= new Location(location.getRow(),location.getColumn()+1);
			break;
		case J:
			tab[2]= location;
			tab[3]= new Location(location.getRow()+2,location.getColumn()-1);
			tab[0]= new Location(location.getRow()+1,location.getColumn());
			tab[1]= new Location(location.getRow()+2,location.getColumn());
			break;
		default :	break;
		}
		
		for(int i=0;i<4;i++){
			list.add(box[tab[i].getRow()][tab[i].getColumn()]);
		}
		
		for(Box b : list){
			if(b.getState() != States.EMPTY)
				return false;
		}
		
		for(Box b : list){
			b.setTetrimino(tetrimino);
		}
		
		currentLocationPlayed = tab;
		
		return true;
	}
	
	public void setTetrimino(Tetriminos tetrimino, Location location){
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
