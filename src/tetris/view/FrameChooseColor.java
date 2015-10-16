package tetris.view;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tetris.model.Types;
import tetris.view.buttons.ButtonApplyColor;
import tetris.view.buttons.ButtonCancel;
import tetris.view.containers.Board;


/**
 * @author Sedara
 *
 */
public class FrameChooseColor extends JFrame implements WindowListener{

	private static final long serialVersionUID = 1L;
	
	private Display display;

	public FrameChooseColor(final Display display, final Board copiedBoard, Board currentBoard, Types type) {
		this.display = display;
		copiedBoard.setTetrimino(type, 3, 2);
		final JColorChooser chooser = new JColorChooser();
		copiedBoard.setEditingColor(false);
		JPanel jp = new JPanel();
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		jSplitPane.setDividerSize(0);
		jp.add(copiedBoard);
		final ButtonApplyColor applyColor = new ButtonApplyColor(display, type, null, this, currentBoard);
		jSplitPane.add(applyColor);
		jSplitPane.add(new ButtonCancel(display, this));
		jp.add(jSplitPane);
		chooser.setPreviewPanel(jp);
		setLocationRelativeTo(null);
		setSize(700, 500);

	    ColorSelectionModel model = chooser.getSelectionModel();
	    ChangeListener changeListener = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {
	        Color color = chooser.getColor();
	        applyColor.setColor(color);
	        display.setBoxesColor();
	        copiedBoard.refreshPanel(color);
	      }
	    };
	    model.addChangeListener(changeListener);
	    
		add(chooser);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(this);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		setState(EXIT_ON_CLOSE);
		display.getFrame().setEnabled(true);
		display.getFrame().toFront();
		
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
