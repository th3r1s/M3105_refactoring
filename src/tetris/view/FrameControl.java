package tetris.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import tetris.controller.ConfigManager;
import tetris.view.containers.LabelControl;

/**
 * @author Sedara
 *
 */
public class FrameControl extends JFrame implements KeyListener, WindowListener{

	private static final long serialVersionUID = 1L;
	
	private List<Integer> usedCodes;

	private LabelControl label;
	
	private JLabel text;

	private Display display;
	
	private String key;
	
	public FrameControl(Display display, String key, List<Integer> usedCodes, LabelControl label) {
		this.usedCodes = usedCodes;
		this.label = label;
		this.display = display;
		this.key = key;
		text = new JLabel(display.getController().getString("selectcontrol"));
		setSize(200,100);
		text.setVerticalAlignment(SwingConstants.CENTER);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		add(text);
		toFront();
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addKeyListener(this);
		addWindowListener(this);
	}

	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		boolean b = false;
		for(int i : usedCodes){
			if(e.getKeyCode() == i)
				b = true;
		}
		if(!b){
			dispose();
			setState(EXIT_ON_CLOSE);
			label.setText(KeyEvent.getKeyText(e.getKeyCode()));
			try {
				display.getController().getConfig().saveData(ConfigManager.SECTION_CONTROLS, key, String.valueOf(e.getKeyCode()));
			} catch (IOException e1) {System.out.println("err");}
			text.setText(display.getController().getString("selectcontrol"));
		}
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowClosed(WindowEvent e) {
		setState(EXIT_ON_CLOSE);
		display.getFrame().toFront();
		display.getFrame().setEnabled(true);
		
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
