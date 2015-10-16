package tetris.view.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tetris.view.Display;
import tetris.view.containers.LangPanel;

public class ButtonLang extends TetrisButton implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ButtonLang(Display display) {
		super(display, "lang");
		addActionListener(this);
		setPreferredSize(new Dimension(300, 100));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		display.getFrame().setContentPane(new LangPanel(display));
		display.getFrame().revalidate();
		
	}
	

}
