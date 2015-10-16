package tetris.controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * Class that contains everything to manage the settings file.
 * @author Sedara
 *
 */
public class ConfigManager{
	
	
	private File config;
	private static final String[] defaultControls = {"moveleft:"+KeyEvent.VK_LEFT,"moveright:"+KeyEvent.VK_RIGHT,"rotate:"+KeyEvent.VK_UP,
													 "forward:"+KeyEvent.VK_DOWN,"pause:"+KeyEvent.VK_ESCAPE};
	private static final String[] defaultHighScores = {"1: - - ","2: - - ","3: - - ","4: - - ","5: - - ","6: - - ","7: - - ","8: - - ","9: - - ","10: - - "};
	private static final String[] defaultBoxesColors = {"I:"+Integer.toString(Color.RED.getRGB()),"O:"+Integer.toString(Color.BLUE.getRGB()),"T:"+Integer.toString(Color.GRAY.getRGB()),
														"L:"+Integer.toString(Color.MAGENTA.getRGB()),"J:"+Integer.toString(Color.WHITE.getRGB()),"Z:"+Integer.toString(Color.CYAN.getRGB()),
														"S:"+Integer.toString(Color.GREEN.getRGB()),"EMPTY:"+Integer.toString(Color.BLACK.getRGB())};
	private static final String[] defaultLang = {"English:en","Français:fr","current:en"};
	public static final String SECTION_BOXES_COLORS = "boxescolors";
	public static final String SECTION_CONTROLS = "controls";
	public static final String SECTION_HIGHSCORES = "highscores";
	public static final String SECTION_LANG = "lang";
	public static final String CONTROL_MOVE_LEFT = "moveleft";
	public static final String CONTROL_MOVE_RIGHT = "moveright";
	public static final String CONTROL_FORWARD = "forward";
	public static final String CONTROL_ROTATE = "rotate";
	public static final String CONTROL_PAUSE = "pause";

	
	

	/**
	 * Creates or load the configuration if it does exist or not.
	 * @param fileName : configuration file's name. It will be named <i>fileName</i>.ini
	 * @throws IOException
	 */
	public ConfigManager(String fileName) throws IOException {
		
		config = new File(fileName+".ini");
		if(!config.exists())
			createDefaultConfig();
		
		
	}
	
	/**
	 * @param sectionName : researched section.
	 * @param searched : researched line.
	 * @return the data at the line <i>searched</i>.
	 * @throws IOException
	 */
	public String getDataInSection(String sectionName, String searched) throws IOException{
		return ConfigSection.getDataFromSection(new BufferedReader(new FileReader(config)), sectionName, searched);
	}
	
	/**
	 * @param sectionName : researched section.
	 * @param searched : researched line.
	 * @return the key to the line <i>searched</i>.
	 * @throws IOException
	 */
	public String getKeyFromDataInSection(String sectionName, String searched) throws IOException{
		return ConfigSection.getKeyFromDataFromSection(new BufferedReader(new FileReader(config)), sectionName, searched);
	}
	
	/**
	 * @param sectionName : the name of the researched section.
	 * @return A list of the lines of this section.
	 * @throws IOException
	 */
	public ArrayList<String> getDataFromEntiereSection(String sectionName) throws IOException{
		return  ConfigSection.getDatasFromEntiereSection(new BufferedReader(new FileReader(config)), sectionName);
	}
	
	/** Saves datas by shifting the following lines.
	 * @param sectionName : researched section.
	 * @param searched : replaced line.
	 * @param newString :the data saved at the line <i>searched</i>.
	 * @throws IOException
	 */
	public void saveWithShift(String sectionName, String searched, String newString) throws IOException{
		ConfigSection.writeDataAndShift(config, sectionName, searched, newString);
	}

	
	/**
	 * Saves datas into the configuration file.
	 * @param sectionName : The name of the section in which are located the datas.
	 * @param searched : The researched line of the saveguard will be done (translated into a String). It won't be overwrite, the saving will be done after.
	 * @param newString : The object to save (translated into a String) to the line <i>searched</i>.
	 * @throws IOException
	 */
	public void saveData(String sectionName, Object searched, Object newString) throws IOException{
		ConfigSection.writeDataInSection( config , sectionName , searched.toString() , newString.toString());	
	}
	
	/** Put (again or not) a section with its default values.
	 * @param sectionName : The name of the section to write.
	 * @throws IOException
	 */
	public void setDefaultSection(String sectionName) throws IOException{
		ConfigSection.setDefaultSection(config, sectionName);
	}
	
	
	/**
	 * Creates a configuration file by default.
	 * @throws IOException
	 */
	private void createDefaultConfig() throws IOException{
		config.createNewFile();
		PrintWriter fw = new PrintWriter(new FileWriter(config));
		ConfigSection.createConfigSection(fw, SECTION_CONTROLS , defaultControls);
		ConfigSection.createConfigSection(fw, SECTION_HIGHSCORES , defaultHighScores);
		ConfigSection.createConfigSection(fw, SECTION_BOXES_COLORS, defaultBoxesColors);
		ConfigSection.createConfigSection(fw, SECTION_LANG, defaultLang);
		fw.close();
		
	}
	
	
	
	
	
	/**
	 * Class that manages sections.
	 */
	private static class ConfigSection {
		
		
		/**
		 * Method that creates a section
		 * @param file : Target file.
		 * @param sectionName : Name of the section to create.
		 * @param content : Content to put inside.
		 */
		public static void createConfigSection(PrintWriter file, String sectionName, String[] content){
			
			file.println("SECTION:"+sectionName+"");
			for(int i=0;i<content.length;i++){
				file.println(content[i]);
			}
			file.println("*");
		}
		
		
		/**
		 * Method that returns the researched line from the configuration file.
		 * @param file : Source file.
		 * @param sectionName : Researched section.
		 * @param searched : Researched line.
		 * @return The complete line.
		 * @throws IOException
		 */
		public static String getDataFromSection(BufferedReader file, String sectionName, String searched) throws IOException{
			String line;	
			String str = "";
			while(!(line = file.readLine()).equalsIgnoreCase("SECTION:"+sectionName));			
			while(!(line = file.readLine()).equalsIgnoreCase("*")){
				str = line.split(":")[0];
				if(str.equalsIgnoreCase(searched)){
					str = line.split(":")[1];
					break;
				}
							
			}
			file.close();
			return str;
		}
		
		/**
		 * @param file
		 * @param sectionName
		 * @param searched
		 * @return String
		 * @throws IOException
		 */
		public static String getKeyFromDataFromSection(BufferedReader file, String sectionName, String searched) throws IOException{
			String line;	
			String str = "";
			while(!(line = file.readLine()).equalsIgnoreCase("SECTION:"+sectionName));			
			while(!(line = file.readLine()).equalsIgnoreCase("*")){
				str = line.split(":")[1];
				if(str.equalsIgnoreCase(searched)){
					str = line.split(":")[0];
					break;
				}
							
			}
			file.close();
			return str;
		}
		
		/**
		 * @param file : source file.
		 * @param sectionName : The name of the researched section.
		 * @return A list of this section's lines.
		 * @throws IOException
		 */
		public static ArrayList<String> getDatasFromEntiereSection(BufferedReader file, String sectionName) throws IOException{
			String line;	
			ArrayList<String> str = new ArrayList<String>();
			while(!(line = file.readLine()).equalsIgnoreCase("SECTION:"+sectionName));			
			while(!(line = file.readLine()).equalsIgnoreCase("*")){
				str.add(line);
							
			}
			file.close();
			return str;
			
		}
		
		/** Writes into a section and shifts the following ones (used there for the scores).
		 * @param file : the target file
		 * @param sectionName : the section's name.
		 * @param searched : the researched line.
		 * @param newString : the data saved at the line <i>searched</i>.
		 * @throws IOException
		 */
		public static void writeDataAndShift(File file, String sectionName, String searched, String newString) throws IOException{
			String line;
			File f = new File("file.temp");
			PrintWriter pw = new PrintWriter(new FileWriter(f));
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(!(line = br.readLine()).equalsIgnoreCase("SECTION:"+sectionName)){
				pw.println(line);
			}
			
			pw.println(line);
			
			String[] words;
			String lastLine = "";
						
			while(!(line = br.readLine()).equalsIgnoreCase("*")){
				words = line.split(":");
				if(searched.equalsIgnoreCase(words[0])){
					pw.println(words[0]+":"+newString);
					lastLine = line;
					break;
				}else
					pw.println(line);
					
			}
			
			if(!lastLine.equalsIgnoreCase("")){

				while(!(line = br.readLine()).equalsIgnoreCase("*")){
					words = lastLine.split(":");
					if(Integer.valueOf(words[0])+1 < 11){
						pw.print(Integer.valueOf(words[0])+1);
						pw.println(":"+words[1]);
					}
					lastLine = line;

				}
			}
			
			pw.println(line);
			
			while((line = br.readLine()) != null){
				pw.println(line);
			}
			
			pw.close();
			br.close();
			file.delete();
			f.renameTo(file);
		}
		
		
		/**
		 * Method allowing to write into a file.
		 * @param file : source and target file.
		 * @param sectionName : Name of the section in which will the datas be written.
		 * @param searched : the researched line.
		 * @param newString : The data saved at the line <i>searched</i>.
		 * @throws IOException
		 */
		public static void writeDataInSection(File file, String sectionName, String searched, String newString) throws IOException{
			String line;
			File f = new File("file.temp");
			PrintWriter pw = new PrintWriter(new FileWriter(f));
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(!(line = br.readLine()).equalsIgnoreCase("SECTION:"+sectionName)){
				pw.println(line);
			}
			
			pw.println(line);
			
			String[] words;
						
			while(!(line = br.readLine()).equalsIgnoreCase("*")){
				words = line.split(":");
				if(searched.equalsIgnoreCase(words[0]))
					pw.println(words[0]+":"+newString);
				else
					pw.println(line);
					
			}
			
			pw.println(line);
			
			while((line = br.readLine()) != null){
				pw.println(line);
			}
			
			pw.close();
			br.close();
			file.delete();
			f.renameTo(file);

			
		}
		

		/** Method that reset a section to its default values.
		 * @param file : the target file.
		 * @param sectionName : The name of the section to write.
		 * @throws IOException
		 */
		public static void setDefaultSection(File file, String sectionName) throws IOException{
			BufferedReader br = new BufferedReader(new FileReader(file));
			File f = new File("file.temp");
			PrintWriter pw = new PrintWriter(new FileWriter(f));
			String line;
			while(!(line = br.readLine()).equalsIgnoreCase("SECTION:"+sectionName)){
				pw.println(line);
			}
			String[] content = null;
			switch(sectionName){
			case SECTION_CONTROLS : content = defaultControls; break;
			case SECTION_HIGHSCORES : content = defaultHighScores; break;
			case SECTION_BOXES_COLORS : content = defaultBoxesColors; break;
			}
			createConfigSection(pw, sectionName, content);
			while(!(line = br.readLine()).equalsIgnoreCase("*"));
			while((line = br.readLine()) != null){
				pw.println(line);
			}
			pw.close();
			br.close();
			file.delete();
			f.renameTo(file);
			
		}
		
		

	}
	
	
	

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
