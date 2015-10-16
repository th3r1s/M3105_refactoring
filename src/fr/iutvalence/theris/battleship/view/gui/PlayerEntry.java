package fr.iutvalence.theris.battleship.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class PlayerEntry extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * TODO Javadoc
	 */
	private JTextField nickname = new JTextField("nickname");
	
	private JLabel label;
	
	public PlayerEntry(int player) {
		super(JSplitPane.HORIZONTAL_SPLIT);
		label = new JLabel(String.format("Player %d :",player+1));
		Font police = new Font("Arial", Font.BOLD, 14);
		nickname.setFont(police);
		nickname.setPreferredSize(new Dimension(150, 30));
		if (player==0){
			nickname.setForeground(Color.BLUE);
		}
		else{
			nickname.setForeground(Color.RED);
		}
	    add(label);
	    add(nickname);
	    setPreferredSize(new Dimension(230,30));
	    setVisible(true);  
	    setDividerSize(0);
	    // Entre 0 et 1
	    setResizeWeight(0.5);
	}
	
	public String getNickName(){
		return nickname.getText();
	}
}
