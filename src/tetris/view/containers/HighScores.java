package tetris.view.containers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import tetris.controller.ConfigManager;
import tetris.view.Display;
import tetris.view.buttons.ButtonDefault;
import tetris.view.buttons.ButtonHome;

/**
 * Class that monitores the system of highscores.
 * @author Numa
 *
 */
public class HighScores extends TetrisPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel[][] tableau = new JLabel[10][4];
	private Display display;
	
	/**
	 * Constructor that sets up the highscores windows on the Swing interface.
	 * @param display
	 * @throws IOException
	 */
	public HighScores(Display display) throws IOException{
		super();
		this.display = display;
		String str;
		String[] data;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 4;
        gbc.weighty = 12;
        

		
		JLabel rank = new JLabel(display.getController().getString("rank"));
		JLabel playerName = new JLabel(display.getController().getString("player"));
		JLabel score = new JLabel(display.getController().getString("score"));
		JLabel tetris = new JLabel(display.getController().getString("tetris"));
		
		Dimension rankDimension = new Dimension(50,50);
		Dimension playerNameDimension = new Dimension(150,50);
		Dimension scoreDimension = new Dimension(100,50);
		Dimension tetrisDimension = new Dimension(50,50);
		
		
		rank.setPreferredSize(rankDimension);
		rank.setVerticalAlignment(SwingConstants.CENTER);
		rank.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
		add(rank, gbc);
		
		playerName.setPreferredSize(playerNameDimension);
		playerName.setVerticalAlignment(SwingConstants.CENTER);
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 0;
		add(playerName, gbc);
		
		score.setPreferredSize(scoreDimension);
		score.setVerticalAlignment(SwingConstants.CENTER);
		score.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 2;
        gbc.gridy = 0;
		add(score,gbc);
		
		tetris.setPreferredSize(tetrisDimension);
		tetris.setVerticalAlignment(SwingConstants.CENTER);
		tetris.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 3;
        gbc.gridy = 0;
		add(tetris, gbc);
		
		for(int i=0;i<10;i++){
			tableau[i][0] = new JLabel(String.valueOf(i+1));
			tableau[i][0].setPreferredSize(rankDimension);
			str = display.getController().getConfig().getDataInSection(ConfigManager.SECTION_HIGHSCORES, String.valueOf(i+1));
			data = str.split("-");
			tableau[i][1] = new JLabel(data[0]);
			tableau[i][1].setPreferredSize(playerNameDimension);
			tableau[i][2] = new JLabel(data[1]);
			tableau[i][2].setPreferredSize(scoreDimension);
			tableau[i][3] = new JLabel(data[2]);
			tableau[i][3].setPreferredSize(tetrisDimension);
		}
		for(int i=0;i<10;i++){
			for(int j=0;j<4;j++){
		        gbc.gridx = j;
		        gbc.gridy = i+1;
				tableau[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				tableau[i][j].setVerticalAlignment(SwingConstants.CENTER);
				tableau[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				add(tableau[i][j], gbc);
			}
		}
		
        gbc.gridx = 1;
        gbc.gridy = 12;
		add(new ButtonDefault(display, ConfigManager.SECTION_HIGHSCORES), gbc);
        gbc.gridx = 2;
		add(new ButtonHome(display), gbc);
	}
	
	/**
	 * Method allowing to refresh the highscores panel.
	 */
	public void refresh(){
		
		String str = null;
		String[] data;
		
		for(int i=0;i<10;i++){
			try {
				str = display.getController().getConfig().getDataInSection(ConfigManager.SECTION_HIGHSCORES, String.valueOf(i+1));
			} catch (IOException e) {}
			data = str.split("-");
			tableau[i][1].setText(data[0]);
			tableau[i][2].setText(data[1]);
			tableau[i][3].setText(data[2]);
		}
	}

}
