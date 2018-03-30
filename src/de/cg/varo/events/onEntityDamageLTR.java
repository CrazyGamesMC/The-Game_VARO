package de.cg.varo.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import de.cg.varo.game.Methods;

public class onEntityDamageLTR implements Listener{
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		
		if (Methods.hasEnded() == true) {
			
			e.setCancelled(true);
			
		}
		
	}
	

}
