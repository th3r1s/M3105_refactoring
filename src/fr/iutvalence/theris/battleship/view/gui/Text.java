package fr.iutvalence.theris.battleship.view.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Text extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea text;
	
	public Text(String nickname){
		
	JTextArea text = new JTextArea("Au tour de : " + nickname);
	add(text);
		
	}
	
	public void setText(String str){
		text.setText(str);
	}
}
