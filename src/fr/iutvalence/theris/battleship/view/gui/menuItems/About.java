package fr.iutvalence.theris.battleship.view.gui.menuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.iutvalence.theris.battleship.view.gui.BattleshipPanel;

public class About extends JMenuItem implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BattleshipPanel battleshipPanel;

	public About(String name) {
		super(name);
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		JFrame about = new JFrame("About");
		JPanel aboutPanel = new JPanel();
		JLabel aboutText = new JLabel("Created by Th3r1s");
		aboutPanel.add(aboutText);
		about.add(aboutPanel);
		about.setVisible(true);
		about.setSize(175, 80);
		about.setLocationRelativeTo(null);
	}
}

