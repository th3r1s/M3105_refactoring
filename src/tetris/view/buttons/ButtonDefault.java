package tetris.view.buttons;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import tetris.view.Display;
import tetris.view.containers.TetrisPanel;

public class ButtonDefault extends TetrisButton implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sectionName;
		
	public ButtonDefault(Display display, String sectionName) {
		super(display, "default");
		this.sectionName = sectionName;
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			display.getController().getConfig().setDefaultSection(sectionName);
		} catch (IOException e1) {}	
		display.setBoxesColor();
		Container c = display.getFrame().getContentPane();
		if(c instanceof TetrisPanel)
			((TetrisPanel) c).refresh();
	}

}
