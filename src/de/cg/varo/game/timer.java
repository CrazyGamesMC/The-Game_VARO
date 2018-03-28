package de.cg.varo.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import de.cg.varo.main.Main;

public class timer {

	public static void start(Player p) {
		
		if (!Var.taskID.containsKey(p.getName())) {
			
			Var.time.put(p.getName(), Var.cfg.getInt("time")); 
			
			
			final int taskID = Main.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {

				@Override
				public void run() {
					
					
					
					
					if (Var.time.get(p.getName()) != 0) {
						
						Var.time.put(p.getName(), timer.getTime(p)-1); 
							
						if (timer.getTime(p) == 15) {
							
							if (!Var.already15.contains(p.getName())) {
								
								Bukkit.broadcastMessage("§aDer Spieler §b" + p.getName() + "§awird in 15 Sekunden gekickt!"); 
								Var.already15.add(p.getName()); 
								
							}
							
						}
						
					//Kick Player
					} else {
						
							boolean cankick = true;
							
							
							for(Entity e : p.getNearbyEntities(20, 15, 20)){
							    if(e instanceof Player){
							        if (!Methods.getTeam(p).equals((Methods.getTeam((Player) e)))) {
							        	
							        	cankick = false;
							        	break; 
							        	
							        }
							    }
							}
						
							
							if (cankick == true) {
								p.kickPlayer("§aDeine Zeit ist um!");
								timer.cancel(p);
							} else {
								
								p.sendMessage(Var.prefix + "§cEin Spieler ist in deiner Nähe! Du wirst noch nicht gekickt!");
								Var.time.put(p.getName(), 14); 
							}
					}
					
					
					
				}
				
				
				
				
			}, 0, 20); 
			
			Var.taskID.put(p.getName(), taskID); 
			
			
		}
		
		
	}

	public static int getTime(Player p) {
		
		if (Var.time.containsKey(p.getName())) {
			
			
			int ret = Var.time.get(p.getName()); 
			return ret; 
			
			
		} else {
			
			return -1; 
			
		}
		
		
		
	}
	
	public static void cancel(Player p) {
		
		if (Var.time.containsKey(p.getName())) {
			Var.time.remove(p.getName()); 
			
		}
		
		if (Var.already15.contains(p.getName())) {
			
			Var.already15.remove(p.getName()); 
			
		}
		
		if (Var.taskID.containsKey(p.getName())) {
			Main.plugin.getServer().getScheduler().cancelTask(Var.taskID.get(p.getName()));
			Var.taskID.remove(p.getName());
			
			
		}
		
		
		
	}
	
}
