package fr.iutvalence.theris.battleship.view.gui;




import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import fr.iutvalence.theris.battleship.view.gui.buttons.LeaveButton;

public class PutBoatPanel extends JSplitPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Text text;
	
	private BattleshipPanel battleshipPanel;
	

	
	public PutBoatPanel(BattleshipPanel battleshipPanel) {
		super(JSplitPane.HORIZONTAL_SPLIT);
		
		this.battleshipPanel = battleshipPanel;
		
		JSplitPane buttons = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		LeaveButton quit = new LeaveButton("Quitter", this.battleshipPanel);
		JButton restart = new JButton("Restart");
		buttons.add(restart);
		buttons.add(quit);
		buttons.setDividerSize(0);
		
		JSplitPane buttons2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JButton validate = new JButton("Validate");
		buttons2.add(buttons);
		buttons2.add(validate);
		buttons2.setDividerSize(0);
		
		JSplitPane play_buttons = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JPanel game = new JPanel();
		play_buttons.add(game);
		play_buttons.add(buttons2);
		play_buttons.setDividerSize(0);
		play_buttons.setResizeWeight(0.8);
		
		JSplitPane pb_text = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		text = new Text(battleshipPanel.getController().getPlayer(1).getNickname());
		pb_text.add(text);
		pb_text.add(play_buttons);
		pb_text.setDividerSize(0);
		
		JSplitPane putBoat = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JPanel direction = new JPanel();
		JPanel boats = new JPanel();
		putBoat.add(boats);
		putBoat.add(direction);
		putBoat.setDividerSize(0);
		
		add(pb_text);
		add(putBoat);
		setDividerSize(0);
		setResizeWeight(0.8);
		
	}
	
	public void setNickName(int n_player) {
		this.text.setText(battleshipPanel.getController().getPlayer(n_player).getNickname());
	}


	
}
