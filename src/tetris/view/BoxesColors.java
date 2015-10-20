package tetris.view;

import java.awt.Color;

import tetris.model.Tetrimino;

/**
 * Enumeration that contains the colors the tetriminos will get on the Swing interface.
 * @author Sedara
 *
 */
public enum BoxesColors {
	
	I,
	O,
	T,
	L,
	J,
	Z,
	S,
	EMPTY;
	
	private Color color;
	
	private BoxesColors() {
		this.color = null;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public static BoxesColors getEquivalent(Tetrimino type){
		
		switch(type){
		case I : return I;
		case O : return O;
		case T : return T;
		case L : return L;
		case J : return J;
		case S : return S;
		case Z : return Z;
		default : return EMPTY;
		}
	}

}
