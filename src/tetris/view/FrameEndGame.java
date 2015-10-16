package tetris.view;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tetris.view.buttons.ButtonHome;
import tetris.view.buttons.ButtonRetry;

/**
 * @author Sedara
 *
 */
public class FrameEndGame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public FrameEndGame(Display display) {
		display.getFrame().setEnabled(false);
		setSize(400, 100);
		setResizable(false);
		
		JPanel jPanel = new JPanel();
		
		JLabel jLabel = new JLabel(String.format(display.getController().getString("endgame"), display.getController().getScore(), display.getController().getTetris()));
		jLabel.setVerticalAlignment(SwingConstants.CENTER);
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setPreferredSize(new Dimension(400,20));	
		jPanel.add(jLabel);
		
		ButtonRetry buttonRetry = new ButtonRetry(display, this);
		ButtonHome buttonHome = new ButtonHome(display, this);
		jPanel.add(buttonRetry);
		jPanel.add(buttonHome);
		
		add(jPanel);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

}
