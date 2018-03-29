package de.cg.varo.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import de.cg.varo.game.Methods;
import de.cg.varo.game.Var;

public class onBlockBreakLTR implements Listener{
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)  {
		
		
		if (!Var.cfg.getBoolean("started") & e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			
			
			e.setCancelled(true);
			
		
			
			
			
		} else {
			
			
			if (e.getBlock().getType() == Material.CHEST) {
				
				if (Methods.chestIsLocked(e.getBlock().getLocation(), e.getPlayer())) {
				
					e.setCancelled(true);
				
					e.getPlayer().sendMessage(Var.prefix + "§cDiese Kiste ist gesichert!");
				
				} 
				
			}
			
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
			
		} else {
			
			if (e.getBlock().getType() == Material.CHEST) {
				
				int size = 0; 
				
				if (Var.chests.getConfigurationSection("chests").getKeys(false) != null) {
					
					size = Var.chests.getConfigurationSection("chests").getKeys(false).size(); 
					
				}
					
					
				size = Var.chests.getConfigurationSection("chests").getKeys(false).size(); 
				
				if (Var.teams.getBoolean("teams." + Methods.getTeam(e.getPlayer()) + ".lockChest")) {
					
					size++;
					
					Var.chests.set("chests.chest"+size+".X", e.getBlock().getLocation().getBlockX());
					Var.chests.set("chests.chest"+size+".Y", e.getBlock().getLocation().getBlockY());
					Var.chests.set("chests.chest"+size+".Z", e.getBlock().getLocation().getBlockZ());
					Var.chests.set("chests.chest"+size+".team", Methods.getTeam(e.getPlayer()));
					
					
					e.getPlayer().sendMessage(Var.prefix + "Teamkiste erstellt!");
					
					Methods.saveFile("chests");
					
				}
				
			}
			
			
		}
		
		
		
	}
	

}
