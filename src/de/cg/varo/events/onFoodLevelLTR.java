package de.cg.varo.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import de.cg.varo.game.Var;

public class onFoodLevelLTR implements Listener{
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		
		if (!Var.cfg.getBoolean("started")) {
			
			
			e.setCancelled(true);
			
			
		}
		
		
		
		
		
		
	}
	
	

}
