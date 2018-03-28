package de.cg.varo.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.cg.varo.game.Methods;
import de.cg.varo.game.Var;
import de.cg.varo.game.timer;

public class onJoinLTR implements Listener{

	@EventHandler() 
	public void onJoin (PlayerJoinEvent e) {
		
		e.setJoinMessage(Var.prefix + "Der Spieler §b" + e.getPlayer().getName() + "§a hat den Server betreten!");
		
		
		if (Var.cfg.getBoolean("started") == true)  {
			
			timer.start(e.getPlayer());
			
		} else {
			
			if (Var.defSpawn.getBoolean("players."+ e.getPlayer().getName() +".set")) {
				
				e.getPlayer().teleport(Methods.getSpawnLocation(e.getPlayer())); 
				
				
			}
			
			
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
}
