package fr.iutvalence.theris.battleship.view.gui.menuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import fr.iutvalence.theris.battleship.view.gui.BattleshipPanel;

public class Close extends JMenuItem implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BattleshipPanel battleshipPanel;
	
	public Close(String name, BattleshipPanel battleshipPanel) {
		super(name);
		this.battleshipPanel = battleshipPanel;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		this.battleshipPanel.getWindow().dispose();
		this.battleshipPanel.getWindow().setState(JFrame.EXIT_ON_CLOSE);
	}
}
