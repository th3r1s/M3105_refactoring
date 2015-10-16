package tetris.view.containers;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import tetris.controller.ConfigManager;
import tetris.view.Display;
import tetris.view.FrameControl;

/**
 * @author Sedara
 *
 */
public class LabelControl extends JLabel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private String key;
	private Display display;
	
	
	public LabelControl(Display display, String key) throws NumberFormatException, IOException {
		super(KeyEvent.getKeyText(Integer.valueOf(display.getController().getConfig().getDataInSection(ConfigManager.SECTION_CONTROLS, key))));
		this.display = display;
		this.key = key;
		addMouseListener(this);
	}
	
	
	public String getKey() {
		return key;
	}
	
	public void mouseClicked(MouseEvent e) {
		
		List<String> list = null;
		try {
			list = display.getController().getConfig().getDataFromEntiereSection(ConfigManager.SECTION_CONTROLS);
		} catch (IOException e1) {}
		
		List<Integer> usedCodes = new ArrayList<Integer>();
		for(String str : list){
			usedCodes.add(Integer.valueOf(str.split(":")[1]));
		}
		
		
		
		new FrameControl(display, key, usedCodes, this);
		
		display.getFrame().setEnabled(false);
		
	}

	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
