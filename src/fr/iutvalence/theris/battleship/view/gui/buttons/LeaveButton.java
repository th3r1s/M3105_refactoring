package fr.iutvalence.theris.battleship.view.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.iutvalence.theris.battleship.view.gui.BattleshipPanel;


public class LeaveButton extends JButton implements ActionListener {

private static final long serialVersionUID = 1L;
	
	private BattleshipPanel battleshipPanel;
	

	
	public LeaveButton(String name, BattleshipPanel battleshipPanel) {
		super(name);
		this.battleshipPanel = battleshipPanel;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		battleshipPanel.getWindow().setContentPane(battleshipPanel.getLauncher());
		battleshipPanel.getWindow().revalidate();
		battleshipPanel.getWindow().setSize(510, 175);
		battleshipPanel.getWindow().setLocationRelativeTo(null);
		
	}
	
}
