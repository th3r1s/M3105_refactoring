package tetris;

import java.io.IOException;

import tetris.controller.ConfigManager;
import tetris.controller.Controller;
import tetris.controller.SwingController;

/**
 * Main class where the program is launched.
 * @author Numa
 *
 */
public class Main 
{
	public static void main(String[] args) 
	{
		Controller controller;
		ConfigManager config = null;
		
		try {
			config = new ConfigManager("config");
		} catch (IOException e) {}
		
		controller = new SwingController(config);

		controller.startDisplay();
		
		
	}
}
