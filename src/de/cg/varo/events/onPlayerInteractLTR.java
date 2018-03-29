package de.cg.varo.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.cg.varo.game.Methods;
import de.cg.varo.game.Var;

public class onPlayerInteractLTR implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if (e.getClickedBlock().getType() == Material.CHEST) {
				
				if (Methods.chestIsLocked(e.getClickedBlock().getLocation(), e.getPlayer())) {
					
					e.getPlayer().sendMessage(Var.prefix + "§cDiese Kiste ist gesichert");
					e.setCancelled(true);
					
				}
				
			}
			
		}
		
	}

}
