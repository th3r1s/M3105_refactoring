package fr.iutvalence.theris.battleship.view.gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.Border;

import fr.iutvalence.theris.battleship.view.gui.buttons.Launch;

public class Launcher extends JSplitPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Launch playButton;
	
	public Launcher(BattleshipPanel battleshipPanel) {
		super(JSplitPane.VERTICAL_SPLIT);
		battleshipPanel.getWindow().setJMenuBar(new BattleshipMenu(battleshipPanel.getWindow(),battleshipPanel));
		
		PlayerPanel players = new PlayerPanel();
	    Border border = BorderFactory.createTitledBorder("Joueurs" );
	    players.setBorder(border);
	    
		JPanel init = new JPanel();
		playButton = new Launch("Lancer la partie", battleshipPanel, players.getPlayer1(), players.getPlayer2());
		init.add(playButton);
		
		add(players);
		add(init);
		
		setDividerSize(0);
	}

}
