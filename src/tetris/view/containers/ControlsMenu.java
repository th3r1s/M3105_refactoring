package tetris.view.containers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import tetris.controller.ConfigManager;
import tetris.view.Display;
import tetris.view.buttons.ButtonDefault;
import tetris.view.buttons.ButtonHome;

public class ControlsMenu extends TetrisPanel{
	
	private static final long serialVersionUID = 1L;
	private static LabelControl[] modifiers = new LabelControl[5];
	private static JLabel[] controls = new JLabel[5];
	
	private Display display;
	
	public ControlsMenu(Display display) throws IOException{
		super();
		this.display = display;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 2;
		gbc.weighty = 7;
		
		controls[0] = new JLabel(display.getController().getString("left"));
		controls[1] = new JLabel(display.getController().getString("right"));
		controls[2] = new JLabel(display.getController().getString("rotate"));
		controls[3] = new JLabel(display.getController().getString("forward"));
		controls[4] = new JLabel(display.getController().getString("pause"));
		
		modifiers[0] = new LabelControl(display, ConfigManager.CONTROL_MOVE_LEFT);
		modifiers[1] = new LabelControl(display,  ConfigManager.CONTROL_MOVE_RIGHT);
		modifiers[2] = new LabelControl(display,  ConfigManager.CONTROL_ROTATE);
		modifiers[3] = new LabelControl(display,  ConfigManager.CONTROL_FORWARD);
		modifiers[4] = new LabelControl(display,  ConfigManager.CONTROL_PAUSE);
		
		JLabel com = new JLabel(display.getController().getString("command"));
		JLabel key = new JLabel(display.getController().getString("key"));
		com.setPreferredSize(new Dimension(200, 50));
		key.setPreferredSize(new Dimension(200, 50));
		
		com.setHorizontalAlignment(SwingConstants.CENTER);
		com.setVerticalAlignment(SwingConstants.CENTER);
		
		key.setHorizontalAlignment(SwingConstants.CENTER);
		key.setVerticalAlignment(SwingConstants.CENTER);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(com, gbc);
		
		gbc.gridx = 1;
		add(key, gbc);		
		
		for(int i=0;i<5;i++){
			
			gbc.gridx = 0;
			gbc.gridy = i+1;
			controls[i].setPreferredSize(new Dimension(200, 100));
			controls[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			controls[i].setHorizontalAlignment(SwingConstants.CENTER);
			controls[i].setVerticalAlignment(SwingConstants.CENTER);
			add(controls[i], gbc);
			
			gbc.gridx = 1;
			gbc.gridy = i+1;
			modifiers[i].setPreferredSize(new Dimension(200, 100));
			modifiers[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			modifiers[i].setHorizontalAlignment(SwingConstants.CENTER);
			modifiers[i].setVerticalAlignment(SwingConstants.CENTER);
			add(modifiers[i], gbc);
		}
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(new ButtonDefault(display, ConfigManager.SECTION_CONTROLS), gbc);
		
		gbc.gridx = 1;
		add(new ButtonHome(display), gbc);
		
	}

	@Override
	public void refresh() {
		controls[0].setText(display.getController().getString("left"));
		controls[1].setText(display.getController().getString("right"));
		controls[2].setText(display.getController().getString("rotate"));
		controls[3].setText(display.getController().getString("forward"));
		controls[4].setText(display.getController().getString("pause"));
		
		for(LabelControl l : modifiers){
			try {
				l.setText(KeyEvent.getKeyText(Integer.valueOf(display.getController().getConfig().getDataInSection(ConfigManager.SECTION_CONTROLS, l.getKey()))));
			} catch (NumberFormatException | IOException e) {}
		}
		
	}
}
