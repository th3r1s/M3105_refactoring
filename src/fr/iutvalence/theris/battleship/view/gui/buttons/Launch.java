package fr.iutvalence.theris.battleship.view.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.iutvalence.theris.battleship.view.gui.BattleshipPanel;
import fr.iutvalence.theris.battleship.view.gui.PlayerEntry;
import fr.iutvalence.theris.battleship.view.gui.PutBoatPanel;

public class Launch extends JButton implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BattleshipPanel battleshipPanel;
	
	private PlayerEntry player1;
	
	private PlayerEntry player2;

	public Launch(String name, BattleshipPanel battleshipPanel, PlayerEntry player1, PlayerEntry player2) {
		super(name);
		this.battleshipPanel = battleshipPanel;
		this.player1 = player1;
		this.player2 = player2;
		addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		battleshipPanel.getController().createNewGame(player1.getNickName(), player2.getNickName());
		
		PutBoatPanel j = new PutBoatPanel(this.battleshipPanel);
		battleshipPanel.getWindow().setContentPane(j);
		battleshipPanel.getWindow().setSize(510, 510);
		battleshipPanel.getWindow().revalidate();
		battleshipPanel.getWindow().setLocationRelativeTo(null);
		
	}


}
