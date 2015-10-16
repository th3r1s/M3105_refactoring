package tetris.view.buttons;

import javax.swing.JButton;

import tetris.view.Display;

/**
 * @author Sedara
 *
 */
public abstract class TetrisButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String key;
	
	protected Display display;
	
	public TetrisButton(Display display, String key) {
		super(display.getController().getString(key));
		this.key = key;
		this.display = display;
	}
	
	public String getKey() {
		return key;
	}

}
