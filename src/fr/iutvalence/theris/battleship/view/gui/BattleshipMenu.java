package fr.iutvalence.theris.battleship.view.gui;


//TODO Javadoc on this page


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import fr.iutvalence.theris.battleship.view.gui.menuItems.About;
import fr.iutvalence.theris.battleship.view.gui.menuItems.Close;

public class BattleshipMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame window;
	
	private About AboutMenuItem;
	
	private Close CloseMenuItem;
	
	/**
	 * 
	 */
	public BattleshipMenu(JFrame window, BattleshipPanel battleshipPanel) {
		this.window=window;
		JMenu menu = new JMenu("Menu");
		
		this.AboutMenuItem = new About("About");
		this.CloseMenuItem = new Close("Close",battleshipPanel);
		menu.add(this.AboutMenuItem);
		menu.add(this.CloseMenuItem);
		
		this.add(menu);
	}

	
	
}
