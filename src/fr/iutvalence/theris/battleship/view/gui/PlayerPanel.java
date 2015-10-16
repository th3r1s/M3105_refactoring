package fr.iutvalence.theris.battleship.view.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.Border;

public class PlayerPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlayerEntry player1;
	
	private PlayerEntry player2;
	
	public PlayerPanel() {

		player1 = new PlayerEntry(0);
		add(player1);
	    Border border1 = BorderFactory.createLineBorder(Color.BLUE);
	    player1.setBorder(border1);
	    
	    
		player2 = new PlayerEntry(1);
		add(player2);
	    Border border2 = BorderFactory.createLineBorder(Color.RED);
	    player2.setBorder(border2);
	}
	
	public PlayerEntry getPlayer1() {
		return player1;
	}

	public PlayerEntry getPlayer2() {
		return player2;
	}
	
}
