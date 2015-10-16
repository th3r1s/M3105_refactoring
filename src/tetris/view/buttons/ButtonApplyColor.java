package tetris.view.buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

import tetris.controller.ConfigManager;
import tetris.model.Types;
import tetris.view.Display;
import tetris.view.containers.Board;

/**
 * @author Sedara
 *
 */
public class ButtonApplyColor extends TetrisButton implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Color color;
	private Types type;
	private JFrame frameToClose;
	private Board board;

	public ButtonApplyColor(Display display, Types type, Color color, JFrame frameToClose, Board board) {
		super(display, "apply");
		this.type = type;
		this.color = color;
		this.frameToClose = frameToClose;
		this.board = board;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			display.getController().getConfig().saveData(ConfigManager.SECTION_BOXES_COLORS, type, Integer.toString(color.getRGB()));
		} catch (IOException e1) {}
		frameToClose.dispose();
		board.refreshPanel(color);
        display.setBoxesColor();
		
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

}
