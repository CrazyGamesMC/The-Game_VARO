package de.cg.varo.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import de.cg.varo.game.Methods;
import de.cg.varo.game.Var;

public class onEntityDamageByEntityLTR implements Listener{

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		
		if (e.getEntity() instanceof Player == false)
			return; 
		
		if (e.getDamager() instanceof Player == false)
			return; 
		
		
		Player p = (Player) e.getEntity(); 
		Player p2 = (Player) e.getDamager(); 
		
		if (Var.cfg.getBoolean("started") == false & p2.getGameMode() != GameMode.CREATIVE) {
			
			
			e.setCancelled(true);
			
		}
		
		
		if (Methods.getTeam(p).equals(Methods.getTeam(p2))) {
			
			
			e.setDamage(0);
			
		}
		
		
	}
	
	
}
