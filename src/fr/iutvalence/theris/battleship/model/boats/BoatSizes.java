package fr.iutvalence.theris.battleship.model.boats;

/**
 * @author Robin
 * enum of the different sizes of the different ships.
 */
public enum BoatSizes {

	DESTROYER(2),
	CRUISER(3),
	SUBMARINE(3),
	BATTLESHIP(4),
	AIRCRAFTCARRIER(5);
	
	/**
	 * boat size.
	 */
	private final int size;
	
	/**
	 * link a size with a ship.
	 * @param size of the ship
	 */
	private BoatSizes(int size){
		this.size = size;
	}

	/**
	 * give the size of a boat type.
	 * @return the wanted size.
	 */
	public int getSize() {
		return size;
	}
	
}
