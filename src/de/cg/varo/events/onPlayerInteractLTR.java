package de.cg.varo.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;

import de.cg.varo.game.Methods;
import de.cg.varo.game.Var;

public class onPlayerInteractLTR implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		//CHESTS
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if (e.getClickedBlock().getType() == Material.CHEST) {
				
				if (Methods.chestIsLocked(e.getClickedBlock().getLocation(), e.getPlayer())) {
					
					e.getPlayer().sendMessage(Var.prefix + "§cDiese Kiste ist gesichert");
					e.setCancelled(true);
					
				}
				
			}
			
		}
		
		//RULES
		if (e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK )	{
			
			if (Methods.useRules()) {
				
				
				Player p = e.getPlayer(); 
				
				if (e.getItem() == null) {
					return; 
					
				}
				
				if (e.getItem().getType() == Material.EYE_OF_ENDER) {
					
					e.setCancelled(true);
					
					p.sendMessage(Var.prefix + "§cDieses Item ist gegen die Regeln!");
					
					p.getInventory().remove(e.getItem());
					
					p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 10);
					
					return; 
					
				} else if (e.getItem().getType() == Material.GOLDEN_APPLE && e.getItem().getData().getData() == (byte) 1) {
					
					e.setCancelled(true);
					
					p.sendMessage(Var.prefix + "§cDieses Item ist gegen die Regeln!");
					
					p.getInventory().remove(e.getItem());
					
					Methods.addStrikes(p.getUniqueId().toString(), 1);
					
					Methods.checkStrikes(p.getUniqueId().toString(), p);
					
					p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 10);
					
					return; 
					
				} else if (e.getItem().getType() == Material.SADDLE) {
					
					e.setCancelled(true);
					
					p.sendMessage(Var.prefix + "§cDieses Item ist gegen die Regeln!");
					
					p.getInventory().remove(e.getItem());
					
					Methods.addStrikes(p.getUniqueId().toString(), 1);
					
					Methods.checkStrikes(p.getUniqueId().toString(), p);
					
					p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 10);
					
					return; 
					
				}else if (e.getItem().getType() == Material.FISHING_ROD) {
					
					e.setCancelled(true);
					
					p.sendMessage(Var.prefix + "§cDieses Item ist gegen die Regeln!");
					
					p.getInventory().remove(e.getItem());
					
					p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 10);
					
					return; 
					
				}else if (e.getItem().getType() == Material.POTION && e.getItem().getData().getData() != (byte) 8261&& e.getItem().getData().getData() != (byte) 8229) {
					
					e.setCancelled(true);
					
					p.sendMessage(Var.prefix + "§cDieses Item ist gegen die Regeln!");
					
					p.getInventory().remove(e.getItem());
					
					Methods.addStrikes(p.getUniqueId().toString(), 1);
					
					Methods.checkStrikes(p.getUniqueId().toString(), p);
					
					p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 10);
					
					
				}
				
				
				
			}
		}
		
	}

}
