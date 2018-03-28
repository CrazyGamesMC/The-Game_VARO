package de.cg.varo.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.cg.varo.game.Methods;
import de.cg.varo.game.Var;
import de.cg.varo.main.Main;

public class teamCMD implements CommandExecutor{

	private Main plugin; 

	public teamCMD(Main main) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		
		
		Player p = (Player) sender; 
		
		
		
		if (args.length == 0) {
			
			
			Methods.showTeamInfo(p); 
			
		} else 
		
		if (args.length == 2 & Var.cfg.getBoolean("started") == false) {
			
			if (args[0].equalsIgnoreCase("create")) {
				
				if (Methods.getTeam(p) != null) {
					
					if (Var.teams.getInt("players." + p.getName() + ".member") == 1) {
						
						Var.teams.set("teams." + Methods.getTeam(p) + ".Member1", "Nobody");
						Var.teams.set("teams." + Methods.getTeam(p), "");
						
						Var.teams.set("players." + p.getName() + ".team", "");
						
						
						
					}
					
					else if (Var.teams.getInt("players." + p.getName() + ".member") == 2) {
						
						Var.teams.set("teams." + Methods.getTeam(p) + ".Member2", "Nobody");
						Var.teams.set("players." + p.getName() + ".team", "");
						
						
					}
					
					
				}
				
				Methods.saveFile("teams");
				
				String teamname = args[1]; 
				
				p.sendMessage(Var.prefix + "Du hast das Team §b#" + teamname + "§a erstellt");
				
				Methods.createTeam(teamname, p);
				
				
				
				
				
			} else if (args[0].equalsIgnoreCase("join")) {
				
				String teamname = args[1]; 
				
				
				if (Var.teams.getBoolean("teams." + teamname + ".exists") == false ) {
					
					p.sendMessage(Var.prefix + "§cDas Team exestiert nicht.");
					return true; 
					
				}
				
				
				
				Methods.joinTeam(teamname, p); 
				
			} else if (args[0].equalsIgnoreCase("accept")) {
				
				String playername = args[1]; 
				
				
				
					Player p2 = Bukkit.getServer().getPlayer(playername);
				
				
					
					
				try {
					if (p2.isOnline() == false) {
					
						p.sendMessage(Var.prefix + "§cDer Spieler ist nicht online");
						
						return true; 
					
					}
				
				} catch (Exception e) {
					
					p.sendMessage(Var.prefix + "§cDer Spieler ist nicht online");
					
					return true; 
				}
				
				
				if (!Var.requesting.containsKey(p2.getName())) {
					
					p.sendMessage(Var.prefix + "§cDer Spieler hat dir keine Teamanfrage gesendet!");
					return true; 
					
				} else {
					
					if (!Var.requesting.get(p2.getName()).equals(p.getName())) {
						
						p.sendMessage(Var.prefix + "§cDer Spieler hat dir keine Teamanfrage gesendet!");
						return true; 
						
					}
					
				}
				
				Var.teams.set("players." + p2.getName() + ".member", 2);
				Methods.setToTeam(Methods.getTeam(p), p2); 
				
				p.sendMessage(Var.prefix + "§b" + p2.getName() + "§a ist nun in deinem Team!");
				p2.sendMessage(Var.prefix + "Du bist nun im Team von §b" + p.getName());
				
				
			}
			
			
		}
		
		
		
		
		
		
		return true;
	}

}
