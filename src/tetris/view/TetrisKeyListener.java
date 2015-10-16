package tetris.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import tetris.controller.ConfigManager;

/**
 * Class that will be used to monitor the action from the keyboard to the swing interface.
 * @author Sedara
 * 
 */
public class TetrisKeyListener extends KeyAdapter{

	private Display display;
	
	public TetrisKeyListener(Display display) {
		this.display = display;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		try {
			if(display.getController().isListenerAllowed()){
				if(e.getKeyCode() == Integer.valueOf(display.getController().getConfig().getDataInSection(ConfigManager.SECTION_CONTROLS, ConfigManager.CONTROL_FORWARD))){
					display.getController().restartTimer();
				}
				if(e.getKeyCode() == Integer.valueOf(display.getController().getConfig().getDataInSection(ConfigManager.SECTION_CONTROLS, ConfigManager.CONTROL_ROTATE))){
					display.getController().rotateTetrimino();
				}

				if(e.getKeyCode() == Integer.valueOf(display.getController().getConfig().getDataInSection(ConfigManager.SECTION_CONTROLS, ConfigManager.CONTROL_MOVE_RIGHT))){
					display.getController().moveTetriminoRight();
				}

				if(e.getKeyCode() == Integer.valueOf(display.getController().getConfig().getDataInSection(ConfigManager.SECTION_CONTROLS, ConfigManager.CONTROL_MOVE_LEFT))){
					display.getController().moveTetriminoLeft();
				}

				if(e.getKeyCode() == Integer.valueOf(display.getController().getConfig().getDataInSection(ConfigManager.SECTION_CONTROLS, ConfigManager.CONTROL_PAUSE))){
					display.getController().pause();
				}
			}

			
		} catch (NumberFormatException | IOException e1) {}
	}

}
