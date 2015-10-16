package tetris.model;

/**
 * Class that represents the locations of the boxes.
 * @author Numa
 *
 */
public class Location {

	private final int row;
	
	private final int column;
	
	/**
	 * Constructor parametered that creates a location.
	 * @param row
	 * @param column
	 */
	public Location(int row, int column) {
		this.row=row;
		this.column=column;
	}

	/**
	 * Method allowing to get the column from a location.
	 * @return
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Method allowing to get the row from a location.
	 * @return
	 */
	public int getRow() {
		return row;
	}

}
