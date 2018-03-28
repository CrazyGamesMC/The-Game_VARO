package de.cg.varo.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import de.cg.varo.game.Var;

public class onBlockBreakLTR implements Listener{
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)  {
		
		
		if (!Var.cfg.getBoolean("started") & e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			
			
			e.setCancelled(true);
			
			
			
		}
		
		
		if (e.getBlock().getType() == Material.DIAMOND_ORE & Var.cfg.getBoolean("enablePhases") & Var.cfg.getInt("Phasestate") == 1) {
			
			e.getBlock().setType(Material.AIR);
			e.setExpToDrop(0);
			
			
			
		}
		
		
		
		
	}
	
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		
		if (!Var.cfg.getBoolean("started") & e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			
			
			e.setCancelled(true);
			
			
			
		}
		
		
		
	}
	

}
