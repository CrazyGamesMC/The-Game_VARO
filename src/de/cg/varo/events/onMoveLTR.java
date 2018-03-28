package de.cg.varo.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.cg.varo.game.Var;

public class onMoveLTR implements Listener{
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		Player p = (Player) e.getPlayer(); 
		
		if (Var.cfg.getBoolean("started") == false & p.getGameMode() != GameMode.CREATIVE) {
			
			
			
			Location loc = p.getLocation(); 
			
			
			if (e.getTo().getBlockX() != e.getFrom().getBlockX() |  e.getTo().getBlockZ() != e.getFrom().getBlockZ()) {
			
				loc.setX(e.getFrom().getX());
				loc.setY(e.getFrom().getY());
				loc.setZ(e.getFrom().getZ());
				loc.setPitch(e.getTo().getPitch());
				loc.setYaw(e.getTo().getYaw());
				
			
				p.teleport(loc); 
			}
			
			
		}
		
		
		
		
		
		
	}
	
	

}
