package fr.iutvalence.theris.battleship.view.gui;

import javax.swing.JFrame;

import fr.iutvalence.theris.battleship.controller.Controller;

/**
 * BattleShip's IHM converter. 
 * @author robinvi
 *
 */
public class BattleshipPanel implements Runnable {

	/**
	 * The window
	 */
	private JFrame window;
	
	/**
	 * TODO Javadoc
	 */
	private Launcher launcher;
	
	/**
	 * TODO Javadoc
	 */
	
	private Controller controller;
	
	public BattleshipPanel(Controller controller) {
		this.controller = controller;
	}
	
	public void run() {  
		this.guiInitializer();
	}
	
	/**
	 * TODO Javadoc
	 */
	private void guiInitializer(){
		this.window = new JFrame();
		this.window.setVisible(true);

		this.window.setSize(510, 175);
		this.window.setTitle("Battleship");
		this.window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		launcher = new Launcher(this);
		window.setContentPane(launcher);

	}
	
	public JFrame getWindow() {
		return window;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public Launcher getLauncher() {
		return launcher;
	}
}
