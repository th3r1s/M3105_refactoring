package tetris.view.containers;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tetris.controller.ConfigManager;
import tetris.view.Display;
import tetris.view.buttons.ButtonHome;


/**
 * Class that represents the languages panel on the Swing interface.
 * @author Numa
 *
 */
public class LangPanel extends JSplitPane implements ListSelectionListener{

	private static final long serialVersionUID = 1L;
	
	private Display display;
    private JScrollPane scroll;
    private JList<String> jl;
    private ButtonHome home;
	
	/**
	 * Constructor that sets up the languages panel before displaying it.
	 * @param display
	 */
	public LangPanel(Display display) {
		super(JSplitPane.VERTICAL_SPLIT);
        List<String> list = null;
        this.display = display;
		try {
			list = display.getController().getConfig().getDataFromEntiereSection(ConfigManager.SECTION_LANG);
		} catch (IOException e) {}
		
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        for(String str : list){
        	if(!str.split(":")[0].equalsIgnoreCase("current"))
        		dlm.addElement(str.split(":")[0]);
        }
        jl = new JList<String>(dlm);
        jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);       
        try {
			jl.setSelectedValue(display.getController().getConfig().getKeyFromDataInSection(
					ConfigManager.SECTION_LANG, display.getController().getConfig().getDataInSection(ConfigManager.SECTION_LANG, "current")), true);
		} catch (IOException e) {}
        jl.addListSelectionListener(this);
        scroll = new JScrollPane(jl);
        JPanel jp1 = new JPanel();
        jp1.add(scroll);
        add(jp1);
        scroll.setPreferredSize(new Dimension(100, 300));
        JPanel jp2 = new JPanel();
        home = new ButtonHome(display);
        jp2.add(home);
        add(jp2);
        setDividerSize(0);
	}

	public void valueChanged(ListSelectionEvent e) {
		try {
			display.getController().setLanguage(display.getController().getConfig().getDataInSection(ConfigManager.SECTION_LANG, jl.getSelectedValue()));
		} catch (IOException e1) {}
		try {
			display.getController().getConfig().saveData(ConfigManager.SECTION_LANG, "current", display.getController().getConfig().getDataInSection(ConfigManager.SECTION_LANG, jl.getSelectedValue()));
		} catch (IOException e1) {}
		home.setText(display.getController().getString(home.getKey()));
		display.getHomeMenu().setButtonsText();
		
	}
	
	
	
	

}
