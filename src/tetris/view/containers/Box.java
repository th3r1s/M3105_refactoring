package tetris.view.containers;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import tetris.model.Types;
import tetris.view.BoxesColors;

/**
 * Class that represents the Box model class to the Swing interface.
 * @author Sedara
 *
 */
public class Box extends JLabel{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Class setting how have to be seen a box.
	 */
	public Box() {
		setBackground(BoxesColors.EMPTY.getColor());
		setOpaque(true);
		setPreferredSize(new Dimension(40,40));
		setBorder(new LineBorder(Color.BLACK));
	}
	
	/**
	 * Class tat links the types enumeration (the tetriminos) to the color they will have during a game.
	 * @param type
	 */
	public void setColor(Types type){
		
		BoxesColors color;
		
		try{
		switch(type){
		case I : color = BoxesColors.I; break;
		case J : color = BoxesColors.J; break;
		case O : color = BoxesColors.O; break;
		case L : color = BoxesColors.L; break;
		case S : color = BoxesColors.S; break;
		case T : color = BoxesColors.T; break;
		case Z : color = BoxesColors.Z; break;
		default : color = BoxesColors.EMPTY; break;
		}
		}catch(NullPointerException e){color = BoxesColors.EMPTY;}
		
		setBackground(color.getColor());
	}

	public void setColor(Color color) {
		setBackground(color);
		
	}
	
}
