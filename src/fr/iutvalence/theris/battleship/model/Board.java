package fr.iutvalence.theris.battleship.model;

import fr.iutvalence.theris.battleship.model.boats.Boat;

/**
 * @author Robin
 * board.
 */
public class Board {

	/**
	 * default number of board's rows.
	 */
	private static final int NB_ROWS = 10;
	/**
	 * default number of board's columns.
	 */
	private static final int NB_COLUMNS = 10;
	
	/**
	 * number of board's rows.
	 */
	private final int rows;
	/**
	 * number of board's columns.
	 */
	private final int columns;
	
	/**
	 * box table of the board.
	 */
	private final Box box[][];
	
	/**
	 * board builder.
	 * @param rows
	 * @param columns
	 */
	public Board(int rows, int columns) {
		this.rows=columns;
		this.columns=rows;
		this.box= new Box[NB_ROWS][NB_COLUMNS];
		
		for(int nbRow = 0; nbRow<rows;nbRow++){
			for(int nbCol = 0;nbCol<columns;nbCol++){
				this.box[nbRow][nbCol]=new Box(new Location(nbRow,nbCol));
			}
		}
		
	}
	
	/**
	 * allow to create a board.
	 */
	public Board() {
		this(NB_ROWS, NB_COLUMNS);
	}
	
	/**
	 * allow to create a boat and to put it on the board. Check if there is no boats overlap.
	 * @param direction of the added boat
	 * @param location of the added boat
	 * @param boat : type of the added boat
	 * @return 
	 */
	public boolean createBoat(Direction direction, Location location, Boat boat) {
		Box[] relativesBoxes = boat.getRelativesBoxes();
		relativesBoxes[0] = getBox(location);
		boolean verif = false;
		try {
		for (int i = 1; i < boat.getSize(); i++) {
			relativesBoxes[i] = getNextBox(direction, relativesBoxes[i - 1]);
		}
		} catch (ArrayIndexOutOfBoundsException out) {
			System.err.println("Error : the boat doesn't fit on the board.");
			return false;
		}
		int i = 0;
		while (i < boat.getSize()) {

			if (relativesBoxes[i].getBoat() == null) {
				i++;

			} else {
				verif = true;
				System.err.println("Error : there is a overlap of boats.");
				break;
			}
		}
		if (!verif) {

				for (i = 0; i < boat.getSize(); i++) {
					relativesBoxes[i].setBoat(boat);
				}

			return true;
		} else {
			return false;
		}
	}

	/**
	 * method allowing to get the next box according to the direction.
	 * @param direction allowing to know which box we want to get
	 * @param firstBox : the box from the method is used
	 * @return
	 */
	private Box getNextBox(Direction direction, Box firstBox){
		switch(direction){
		case NS : return getBottomBox(firstBox.getLocation());
		case SN : return getUpperBox(firstBox.getLocation());
		case EW : return getLeftBox(firstBox.getLocation());
		case WE : return getRightBox(firstBox.getLocation());
		default:
			return null;
		}
	}
	
	/**
	 * allow to get a box according to a row and a column.
	 * @param row of the box
	 * @param column of the box
	 * @return the box we need to get
	 */
	public Box getBox(int row, int column){
		return this.box[row][column];
	}

	/**
	 * allow to get a box according to a location.
	 * @param location of the box
	 * @return the box we need to get
	 */
	public Box getBox(Location location){
		return getBox(location.getRow(), location.getColumn());
	}
	
	/**
	 * allow to get the bottom box of the location.
	 * @param location from the method is used
	 * @return the box we need to get
	 */
	public Box getBottomBox(Location location){
		return this.box[location.getRow()+1][location.getColumn()];
	}
	
	/**
	 * allow to get the upper box of the location.
	 * @param location from the method is used
	 * @return the box we need to get
	 */
	public Box getUpperBox(Location location){
		return this.box[location.getRow()-1][location.getColumn()];
	}
	
	/**
	 * allow to get the left box of the location.
	 * @param location from the method is used
	 * @return the box we need to get
	 */
	public Box getLeftBox(Location location){
		return this.box[location.getRow()][location.getColumn()-1];
	}
	
	/**
	 * allow to get the right box of the location.
	 * @param location from the method is used
	 * @return the box we need to get
	 */
	public Box getRightBox(Location location){
		return this.box[location.getRow()][location.getColumn()+1];
	}
	
	/**
	 * allow to get rows.
	 * @return row
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * allow to get columns.
	 * @return column
	 */
	public int getColumns() {
		return columns;
	}
	
	
	/**
	 * hit a box except if the hit is not on the board.
	 * @param position where we want to hit
	 * @return boolean : hit / not hit
	 */
	public boolean hit(Location position) {
		try{
		getBox(position).hit();
		return true;}
		catch(ArrayIndexOutOfBoundsException out){
			System.out.println("Error : the hit doesn't fit on the board.");
			return false;
		}
		
	}
	
	/**
	 * Method: To display on the console a grid with its cases states
	 * board toString. Foe allow to differentiate the player's board and the foe's board.
	 * @param foe : for the foe's board
	 * @return str : local variable with the string
	 */
    public String toString(boolean foe)
    {
            String str = "---------------------------------------------\n|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |\n---------------------------------------------\n";
            for (int rows = 0; rows<NB_ROWS;rows++)						
            {
            	str += "| "+rows+" ";
                    for (int columns = 0; columns<NB_COLUMNS;columns++)
                    	str += this.box[rows][columns].toString(foe);
                    str += "|\n---------------------------------------------\n";
            }
            return str;
    }
    
	
}