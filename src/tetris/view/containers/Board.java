package tetris.view.containers;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import tetris.model.Types;
import tetris.view.BoxesColors;
import tetris.view.Display;
import tetris.view.FrameChooseColor;

/**
 * Class that represents the game board, created on the Swing interface.
 * @author Sedara
 *
 */
public class Board extends JPanel implements MouseListener{
	
	
	private static final long serialVersionUID = 1L;
	
	private Box[][] boxes;
	private int rows;
	private int columns;
	
	private Display display;
	
	private Box[] currentTetriomino;
	private Types currentTypePlayed;
	private boolean editingColor;
	
	public Board(Display display, int rows, int columns, boolean editingColor) {
		super();
		boxes = new Box[rows][columns];
		this.rows = rows;
		this.columns = columns;
		this.display = display;
		if(editingColor)
			addMouseListener(this);		
		
		currentTetriomino = new Box[4];
		
		setLayout(new GridLayout(rows, columns));
		
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				boxes[i][j] = new Box();
				add(boxes[i][j]);
			}
		}
		
	}
	
	/**
	 * Method used to refresh the Swing board panel (i.e the game grid).
	 */
	public void refreshGamingPanel(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				boxes[i][j].setColor(display.getController().getTypeFromBox(i, j));
			}
		}
	}
	
	public void refreshPanel(Color color){
		for(int i=0;i<currentTetriomino.length;i++){
			currentTetriomino[i].setColor(color);
		}
	}
	
	public void resetPanel(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				boxes[i][j].setColor(BoxesColors.EMPTY.getColor());
			}
		}
	}
		
	public void setTetrimino(Types type, int row, int col) 
	{	
		
		this.currentTypePlayed = type;
		switch (type){
		case I:
			currentTetriomino[0] = boxes[row][col];
			currentTetriomino[1] = boxes[row-1][col];
			currentTetriomino[2] = boxes[row-2][col];
			currentTetriomino[3] = boxes[row-3][col];
			break;
		case O:
			currentTetriomino[0] = boxes[row][col];
			currentTetriomino[1] = boxes[row-1][col];
			currentTetriomino[2] = boxes[row-1][col+1];
			currentTetriomino[3] = boxes[row][col+1];
			break;
		case L:
			currentTetriomino[0] = boxes[row][col];
			currentTetriomino[1] = boxes[row-1][col];
			currentTetriomino[2] = boxes[row-2][col];
			currentTetriomino[3] = boxes[row][col+1];
			break;
		case Z:
			currentTetriomino[0] = boxes[row][col];
			currentTetriomino[1] = boxes[row][col+1];
			currentTetriomino[2] = boxes[row-1][col];
			currentTetriomino[3] = boxes[row-1][col-1];
			break;
		case S:
			currentTetriomino[0] = boxes[row][col];
			currentTetriomino[1] = boxes[row][col-1];
			currentTetriomino[2] = boxes[row-1][col];
			currentTetriomino[3] = boxes[row-1][col+1];
			break;
		case T:
			currentTetriomino[0] = boxes[row][col];
			currentTetriomino[1] = boxes[row-1][col];
			currentTetriomino[2] = boxes[row-1][col-1];
			currentTetriomino[3] = boxes[row-1][col+1];
			break;
		case J:
			currentTetriomino[0] = boxes[row][col];
			currentTetriomino[1] = boxes[row][col-1];
			currentTetriomino[2] = boxes[row-1][col];
			currentTetriomino[3] = boxes[row-2][col];
			break;
		default : return;
		
		}
		
		for(int i=0;i<currentTetriomino.length;i++){
			currentTetriomino[i].setColor(type);
		}
		
	}
	
	public void removeTetrimino(){
		for(int i=0;i<currentTetriomino.length;i++){
			currentTetriomino[i].setColor(BoxesColors.EMPTY.getColor());
		}
	}
	
	private Board copy(){
		return new Board(display, rows, columns, editingColor);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		FrameChooseColor frame = new FrameChooseColor(display, copy(), this, currentTypePlayed);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		display.getFrame().setEnabled(false);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void setEditingColor(boolean editingColor) {
		this.editingColor = editingColor;
		removeMouseListener(this);
	}
	
	public Types getCurrentTypePlayed() {
		return currentTypePlayed;
	}

}
