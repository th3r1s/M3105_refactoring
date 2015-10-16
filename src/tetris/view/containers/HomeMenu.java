package tetris.view.containers;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import tetris.view.Display;
import tetris.view.buttons.ButtonColors;
import tetris.view.buttons.ButtonControls;
import tetris.view.buttons.ButtonExit;
import tetris.view.buttons.ButtonLang;
import tetris.view.buttons.ButtonPlay;
import tetris.view.buttons.ButtonScores;
import tetris.view.buttons.TetrisButton;

/**
 * Class that represents the main menu, the root of the program where you arrive after launching it.
 * @author Numa
 *
 */
public class HomeMenu extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Display display;
	
	/**
	 * Constructor that sets up the main menu before displaying it.
	 * @param display
	 */
	public HomeMenu(Display display) {
		super();
		this.display = display;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 6;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
		add(new ButtonPlay(display), gbc);
		
		gbc.gridy = 1;
		add(new ButtonScores(display), gbc);
		
		gbc.gridy = 2;
		add(new ButtonColors(display), gbc);
		
		gbc.gridy = 3;
		add(new ButtonControls(display), gbc);
		
		gbc.gridy = 4;
		add(new ButtonLang(display), gbc);
		
		gbc.gridy = 5;
		add(new ButtonExit(display), gbc);
	}
	
	public void setButtonsText(){
		for(Component c : getComponents()){
			if(c instanceof TetrisButton){
				((TetrisButton) c).setText(display.getController().getString(((TetrisButton) c).getKey()));
			}
				
		}
	}

}
