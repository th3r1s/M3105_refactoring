package fr.iutvalence.theris.battleship.model;

/**
 * @author Robin
 *
 */
public class Location {

	/**
	 * position's row.
	 */
	private final int row;
	/**
	 * position's column.
	 */
	private final int column;
	
	/**
	 * create a location.
	 * @param row
	 * @param column
	 */
	public Location(int row, int column) {
		this.row=row;
		this.column=column;
	}

	/**
	 * give the position's row.
	 * @return wanted row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * give the postion's column.
	 * @return wanted column
	 */
	public int getColumn() {
		return column;
	}
	
	
}
