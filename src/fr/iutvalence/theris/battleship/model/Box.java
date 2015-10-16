package fr.iutvalence.theris.battleship.model;

import fr.iutvalence.theris.battleship.model.boats.Boat;

/**
 * @author Robin
 *
 */
/**
 * @author Robin
 *
 */
public class Box {

	/**
	 * box location.
	 */
	private final Location location;
	
	/**
	 * hitted box ?
	 */
	private boolean hitted;
	
	/**
	 * a local boat if there is.
	 */
	private Boat boat;


	/**
	 * create a box according to a location.
	 * @param location where we want to create a box
	 */
	public Box(Location location) {
		this.location = location;
		this.boat = null;
	}


	/**
	 * give the boat.
	 * @return the  boat
	 */
	public Boat getBoat() {
		return boat;
	}

	/**
	 * set a local boat.
	 * @param boat
	 */
	public void setBoat(Boat boat) {
		this.boat = boat;
	}


	/**
	 * give the location.
	 * @return the location.
	 */
	public Location getLocation() {
		return location;
	}
	
	
	
	/**
	 * box hitted ?
	 * @return hitted
	 */
	public boolean isHitted() {
		return hitted;
	}


	/**
	 * hit the box and the boat if it exists.
	 */
	public void hit() {
		this.hitted = true;
		if(this.boat != null)
			this.boat.isHitted();
	}


	/**
	 * box toString. Foe allow to differentiate the player's box and the foe's box.
	 * @param foe : for the foe's box
	 * @return toString
	 */
	public String toString(boolean foe){
		if(this.boat != null){
			if(hitted && !boat.isSinked())
				return "| o ";
			if(hitted && boat.isSinked())
				return "| X ";
			if(!hitted && !boat.isSinked()){
				if(!foe)
					return "| B ";
				else
					return "|   ";
			}
			
					
		}
		return (hitted ? "| x " : "|   ");
	}
}
