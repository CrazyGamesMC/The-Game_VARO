package de.cg.varo.cmd;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.translate.UnicodeUnpairedSurrogateRemover;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.cg.varo.game.Methods;
import de.cg.varo.game.UUIDManager;
import de.cg.varo.game.Var;
import de.cg.varo.main.Main;

public class strikeCMD implements CommandExecutor{

	private Main plugin; 
	
	
	public strikeCMD(Main main) {
		
		this.plugin = plugin; 
		
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		if (args.length == 0 & sender instanceof Player) {
			
			Player p = (Player) sender; 
			
			p.sendMessage(Var.prefix + "Du hast §b" + Methods.getStrikes(UUIDManager.getID(p.getName())) + "§a Strikes!");
			
			
			
		}
		
		
		if (args.length == 2) {
			
			if (args[0].equalsIgnoreCase("add") && sender instanceof ConsoleCommandSender) {
				
				String name = args[1]; 
				OfflinePlayer p2 = Bukkit.getServer().getOfflinePlayer(name);
				
				
				String id = UUIDManager.getID(name); 
				
				//Check if the player is registered!
				if (!Var.uuids.getBoolean("IDs." + id + ".exists")) {
					
					System.out.println("Die ID dieses Spielers ist nicht registriert.");
					
					return true; 
					
				}
				
				Methods.addStrikes(id, 1);
				
				System.out.println("Strike <-> Der Spieler erhielt nun einen weiteren Strike!");
				
				
				if (Methods.getStrikes(id) == 1) {
					
					int x = (int) Var.coords.getDouble(id + ".X");
					int y = (int) Var.coords.getDouble(id + ".Y");
					int z = (int) Var.coords.getDouble(id + ".Z");
					
					
					System.out.println("Strike <-> Seine Coords: " + x + " / " + y + " / " + z);
					
				} else if (Methods.getStrikes(id) == 2) {
					
					Var.cfg.set("toclear." + p2.getUniqueId().toString(), true);
					
					Methods.saveFile("cfg");
					
					System.out.println("Strike <-> Sein Inventar wurde geleert!");
					
				} else if (Methods.getStrikes(id) == 3) {
					
					p2.setBanned(true);
					
					System.out.println("Strike <-> Der Spieler wurde gebannt!");
					
					
					List<String> aliveplayers = Var.cfg.getStringList("aliveplayers"); 
					aliveplayers.remove(p2.getUniqueId().toString());
					Var.cfg.set("aliveplayers", aliveplayers);
					Methods.saveFile("cfg");
					
					
					
					
				}
				
				
			}
			
			
		}
		
		
		
		return true;
	}

}
