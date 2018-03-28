package de.cg.varo.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.cg.varo.game.Methods;
import de.cg.varo.game.Var;
import de.cg.varo.game.timer;
import de.cg.varo.main.Main;

public class startCMD implements CommandExecutor{

	private Main plugin; 
	
	public startCMD(Main main) {
		
		this.plugin = plugin; 
		
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender; 
		
		if (Var.cfg.getBoolean("started") == false & p.isOp()) {
			
			Main.countdown = 30; 
			
			
			Main.start_sced = Main.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable () {
				
				
				public void run() {
					
					if (Main.countdown == 30) {
						
						Bukkit.broadcastMessage(Var.prefix + "Das Spiel startet in §b" + Main.countdown + " SEKUNDEN"); 
						
					} else if (Main.countdown == 25) {
						
						Bukkit.broadcastMessage(Var.prefix + "Das Spiel startet in §b" + Main.countdown + " SEKUNDEN");
						
					} else if (Main.countdown == 20) {
						
						Bukkit.broadcastMessage(Var.prefix + "Das Spiel startet in §b" + Main.countdown + " SEKUNDEN");
						
					} else if (Main.countdown == 15) {
						
						Bukkit.broadcastMessage(Var.prefix + "Das Spiel startet in §b" + Main.countdown + " SEKUNDEN");
						
					} else if (Main.countdown == 10) {
						
						Bukkit.broadcastMessage(Var.prefix + "Das Spiel startet in §b" + Main.countdown + " SEKUNDEN");
						
					} else if (Main.countdown <= 5 & Main.countdown != 0) {
						
						Bukkit.broadcastMessage(Var.prefix + "Das Spiel startet in §b" + Main.countdown + " SEKUNDEN");
						
					}
					
					
					
					
					if (Main.countdown != 0) {
						
						Main.countdown--; 
						
					} else {
						
						Main.plugin.getServer().getScheduler().cancelTask(Main.start_sced);
						Var.cfg.set("started", true);
						Methods.saveFile("cfg");
						
						Bukkit.broadcastMessage(Var.prefix + "DAS SPIEL HAT GESTARTET!"); 
						
						p.performCommand("time set day"); 
						p.performCommand("weather clear"); 
						
						List<String> playerlist = new ArrayList<String>(); 
						
						for (Player p : Bukkit.getOnlinePlayers()) {
							
							p.setFoodLevel(20);
							p.setHealth(20.0);
							p.getInventory().clear(); 
							p.getInventory().addItem(new ItemStack(Material.BREAD, 15)); 
							
							timer.start(p);
							
							playerlist.add(p.getUniqueId().toString()); 
							
						}
						
						Var.cfg.set("aliveplayers", playerlist);
						
						Methods.saveFile("cfg");
					}
					
					
					
				}
				
				
			}, 20, 20) ; 
			
			
			
		}
		
		return true;
	}

}
