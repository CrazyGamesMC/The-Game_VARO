package de.cg.varo.game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class Var {
	
	
	
	
	static File config = new File("plugins//VARO//config.yml");
	
	static File teamfile = new File("plugins//VARO//teams.yml"); 
	
	static File killfile = new File ("plugins//VARO//kills.yml");
	
	static File defaultSpawn = new File ("plugins//VARO//spawns.yml");
	
	static File strikeFile = new File ("plugins//VARO//strikes.yml");
	
	
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(config); 
	
	public static YamlConfiguration teams = YamlConfiguration.loadConfiguration(teamfile); 
	
	public static YamlConfiguration kills = YamlConfiguration.loadConfiguration(killfile); 
	
	public static YamlConfiguration defSpawn = YamlConfiguration.loadConfiguration(defaultSpawn); 
	
	public static YamlConfiguration strikes = YamlConfiguration.loadConfiguration(strikeFile); 
	
	

	
	public static HashMap<String, String> requesting = new HashMap<String, String>(); 
	public static HashMap<String, Integer> taskID = new HashMap<String, Integer>(); 
	public static HashMap <String, Integer> time = new HashMap<String, Integer>(); 
	
	public static ArrayList<String> already15 = new ArrayList<String>(); 
	
	
	
	
	
	public static void createFiles() {
		
		File ordner = new  File("plugins//VARO");
		
		
		if (!ordner.exists()) {
			
			ordner.mkdir(); 
			
		}
		
		
		if (!config.exists()) {
			
			try {
				config.createNewFile();
				
				
				
				cfg.set("started", false);
				
				cfg.set("GameName", "Varo");
				
				cfg.set("enablePhases", true);
				
				cfg.set("Phasestate", 0);
				
				cfg.set("killItems", new ItemStack(Material.DIAMOND, 5));
				
				cfg.set("time", 900);
				
				
				
				
				cfg.save(config);
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
			} 
			
			
		}
		
		
		if (!teamfile.exists()) {
			
			try {
				teamfile.createNewFile();
			
				teams.set("ExampleTeam.Member1", "GENIUS");
				
				teams.save(teamfile);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			} 
			
			
		}
		
		
		if (!killfile.exists()) {
			
			try {
				killfile.createNewFile();
			
				
			} catch (IOException e) {
				
				e.printStackTrace();
			} 
			
			
		}
		
		if (!strikeFile.exists()) {
			
			try {
				strikeFile.createNewFile();
			
				
			} catch (IOException e) {
				
				e.printStackTrace();
			} 
			
			
		}
		
		
		if (!defaultSpawn.exists()) {
			
			try {
				defaultSpawn.createNewFile();
			
				
			} catch (IOException e) {
				
				e.printStackTrace();
			} 
			
			
		}
		
		
	}
	
	public static String prefix = "§7"+ cfg.getString("GameName") +"  »§a ";
	
	
	
	

}
