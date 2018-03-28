package de.cg.varo.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.cg.varo.game.Methods;
import de.cg.varo.game.Var;
import de.cg.varo.main.Main;

public class strikeCMD implements CommandExecutor{

	private Main plugin; 
	
	
	public strikeCMD(Main main) {
		
		this.plugin = plugin; 
		
	}
	
	/*
			This file is currently "work in progress"! Im gonna finish it soon!
	*/
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		if (args.length == 0 & sender instanceof Player) {
			
			Player p = (Player) sender; 
			
			p.sendMessage(Var.prefix + "Du hast �b" + Methods.getStrikes(p) + "�a Strikes!");
			
			
			
		}
		
		
		if (args.length == 2) {
			
			if (args[0].equalsIgnoreCase("add")) {
				
				String name = args[1]; 
				OfflinePlayer p2 = Bukkit.getServer().getOfflinePlayer(name);
				
				
				
				
				Methods.addStrikes(p2.getPlayer(), 1);
				
				System.out.println("Strike <-> Der Spieler erhielt nun einen weiteren Strike!");
				
				
				if (Methods.getStrikes(p2.getPlayer()) == 1) {
					
					int x = p2.getPlayer().getLocation().getBlockX(); 
					int y = p2.getPlayer().getLocation().getBlockY(); 
					int z = p2.getPlayer().getLocation().getBlockZ(); 
					
					
					System.out.println("Strike <-> Seine Coords: " + x + " / " + y + " / " + z);
					
				} else if (Methods.getStrikes(p2.getPlayer()) == 2) {
					
					p2.getPlayer().getInventory().clear(); 
					p2.getPlayer().getInventory().setBoots(new ItemStack(Material.AIR));
					p2.getPlayer().getInventory().setLeggings(new ItemStack(Material.AIR));
					p2.getPlayer().getInventory().setChestplate(new ItemStack(Material.AIR));
					p2.getPlayer().getInventory().setHelmet(new ItemStack(Material.AIR));
					
					System.out.println("Strike <-> Sein Inventar wurde geleert!");
					
				} else if (Methods.getStrikes(p2.getPlayer()) == 3) {
					
					p2.setBanned(true);
					
					System.out.println("Strike <-> Der Spieler wurde gebannt!");
					
					
					List<String> aliveplayers = Var.cfg.getStringList("aliveplayers"); 
					aliveplayers.remove(p2.getUniqueId());
					Var.cfg.set("aliveplayers", aliveplayers);
					Methods.saveFile("cfg");
					
					
					
					
				}
				
				
			}
			
			
		}
		
		
		
		return true;
	}

}
