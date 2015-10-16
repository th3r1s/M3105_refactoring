package fr.iutvalence.theris.battleship.view;

/**
 * @author Robin
 * @param <A> 
 * @param <B>
 */
public class Tuple<A, B> {

	private A firstElement;
	private B secondElement;
	
	/**
	 * @param firstElement
	 * @param secondElement
	 */
	public Tuple(A firstElement, B secondElement) {
		this.firstElement = firstElement;
		this.secondElement = secondElement;
	}

	/**
	 * @return
	 */
	public A getFirstElement() {
		return firstElement;
	}

	/**
	 * @param firstElement
	 */
	public void setFirstElement(A firstElement) {
		this.firstElement = firstElement;
	}

	/**
	 * @return
	 */
	public B getSecondElement() {
		return secondElement;
	}

	/**
	 * @param secondElement
	 */
	public void setSecondElement(B secondElement) {
		this.secondElement = secondElement;
	}
	
	
	
}
