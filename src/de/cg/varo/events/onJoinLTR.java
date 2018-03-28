package de.cg.varo.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import de.cg.varo.game.Methods;
import de.cg.varo.game.UUIDManager;
import de.cg.varo.game.Var;
import de.cg.varo.game.timer;

public class onJoinLTR implements Listener{

	@EventHandler() 
	public void onJoin (PlayerJoinEvent e) {
		
		e.setJoinMessage(Var.prefix + "Der Spieler §b" + e.getPlayer().getName() + "§a hat den Server betreten!");
		
		if (!UUIDManager.isInitialized(e.getPlayer().getUniqueId().toString(), e.getPlayer())) {
			
			UUIDManager.initID(e.getPlayer().getUniqueId().toString(), e.getPlayer());
			
		}
		
		
		if (Var.cfg.getBoolean("started") == true)  {
			
			timer.start(e.getPlayer());
			
			Methods.putCoords(e.getPlayer());
			
			
			//Clear players, if they have strikes
			if (Methods.toClear(e.getPlayer())) {
				
				e.getPlayer().getInventory().clear(); 
				
				e.getPlayer().getInventory().setBoots(new ItemStack(Material.AIR));
				e.getPlayer().getInventory().setLeggings(new ItemStack(Material.AIR));
				e.getPlayer().getInventory().setChestplate(new ItemStack(Material.AIR));
				e.getPlayer().getInventory().setHelmet(new ItemStack(Material.AIR));
				
				Var.cfg.set("toclear." + e.getPlayer().getUniqueId().toString(), false);
				
				Methods.saveFile("cfg");
				
			}
			
		} else {
			
			if (Var.defSpawn.getBoolean("players."+ e.getPlayer().getName() +".set")) {
				
				e.getPlayer().teleport(Methods.getSpawnLocation(e.getPlayer())); 
				
				
			}
			
			
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
}
