package fr.iutvalence.theris.battleship.model.boats;

import fr.iutvalence.theris.battleship.model.Box;

/**
 * boat class which is derived boats.
 * @author Robin
 */
public abstract class Boat {
	
	/**
	 * boat size.
	 */
	private final int size;
	
	/**
	 * boat life.
	 */
	private int life;
	
	/**
	 * relatives boxes list.
	 */
	private final Box[] relativesBoxes;

	/**
	 * boat constructor.
	 * @param size of the ship
	 * @param direction wanted
	 * @param relativesBoxes of the ship
	 */
	public Boat(int size){
		this.size = size;
		this.life = size;
		relativesBoxes = new Box[size];
	}

	/**
	 * give the boat's size.
	 * @return wanted size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Boat is hitted and loses life.
	 */
	public void isHitted() {
		this.life--;
	}
	
	/**
	 * the ship is sinked because it doesn't have any life.
	 * @return boolean : is sinked ?
	 */
	public boolean isSinked() {
		return(life==0 ? true : false);
	}

	/**
	 * give the relatives boxes list.
	 * @return wanted relatives boxes list
	 */
	public Box[] getRelativesBoxes() {
		return relativesBoxes;
	}
	
	public int getLife(){
		return life;
	}

}
