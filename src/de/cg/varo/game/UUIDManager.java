package de.cg.varo.game;

import org.bukkit.entity.Player;

public class UUIDManager {

	public static void initID(String id, Player p) {
		
		Var.uuids.set("IDs." + id + ".name", p.getName());
		Var.uuids.set("IDs." + id + ".exists", true);
		
		Methods.saveFile("uuid");
		
	}
	
	public static boolean isInitialized(String id, Player p) {
		
		if (Var.uuids.get("IDs." + id + ".exists") == null)
				return false; 
		
		boolean ret = Var.uuids.getBoolean("IDs." + id + ".exists"); 
		
		if (!getName(id).equals(p.getName())) {
			
			ret = false; 
			
		}
			
		
		return ret; 
		
	}
	
	public static String getName(String id) {
		
		return Var.uuids.getString("IDs." + id + ".name"); 
		
	}
	
	public static String getID(String name) {
		
		String id = "NO KEY FOUND!"; 
		
		
		for (String key : Var.uuids.getConfigurationSection("IDs").getKeys(false)) {
			
			if (getName(key).equals(name)) {
				
				return key; 
				
			}
			
			
		}
		
		
		return id; 
		
	}
	
	
}
