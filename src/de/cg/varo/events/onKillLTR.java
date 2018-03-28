package de.cg.varo.events;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import de.cg.varo.game.Methods;
import de.cg.varo.game.Var;
import de.cg.varo.game.timer;

public class onKillLTR implements Listener{

	@EventHandler
	public void onKill (PlayerDeathEvent e) {
		
		
		Player p = (Player) e.getEntity(); 
		
		if (p.getKiller() instanceof Player == false) {
			
			e.setDeathMessage(Var.prefix + "Der Spieler §b" + p.getName() + "§a ist gestorben!"); 
			
			if (Var.cfg.getBoolean("started") == true) {
				
				p.kickPlayer("§cDu bist ausgeschieden!"); 
				p.setBanned(true);
				
				
				
			}
			
			return; 
		}
		
		
		Player p2 = p.getKiller() ; 
		
		
		
		if (Var.cfg.getBoolean("started") == true) {
			
			
			Var.kills.set("teams." + Var.teams.getString("players." + p2.getName() + ".team") + ".kills"
					, Var.kills.getInt("teams." + Var.teams.getString("players." + p2.getName() + ".team") + ".kills")+1) ;
			
			e.setDeathMessage(Var.prefix + "Der Spieler §b" + p.getName() + "§a wurde von Team §b#" + Var.teams.getString("players." + p2.getName() + ".team") + "§a getötet!");
			Bukkit.broadcastMessage(Var.prefix + "Sie haben nun §b" + Var.kills.getInt("teams." + Var.teams.getString("players." + p2.getName() + ".team") + ".kills") + "§a Kills!");
			
			p.kickPlayer("§cDu bist ausgeschieden!"); 
			p.setBanned(true);
			
			if (e.getEntity() instanceof Player == false)
				return; 
			
			List<String> aliveplayers = Var.cfg.getStringList("aliveplayers"); 
			
			if (aliveplayers.contains(p.getUniqueId().toString())) {
				
				aliveplayers.remove(p.getUniqueId().toString());
				
			}
			
			Var.cfg.set("aliveplayers", aliveplayers);	
			
			Methods.saveFile("cfg");
			
			if (aliveplayers.size() == 2) {
				
				UUID UUID1 = UUID.fromString(aliveplayers.get(1)); 
				UUID UUID2 = UUID.fromString(aliveplayers.get(1)); 
				
				OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(UUID1); 
				OfflinePlayer player2 = Bukkit.getServer().getOfflinePlayer(UUID2); 
				
				String team1 = Var.teams.getString("players." + player.getName() + ".team"); 
				String team2 = Var.teams.getString("players." + player2.getName() + ".team"); 
				
				if (Var.teams.getString(team1).equals(team2)) {
					
					Bukkit.broadcastMessage(Var.prefix + "Das Team §b#" + team1 + "§a hat das Spiel gewonnen!");
					
					for (Player player3 : Bukkit.getOnlinePlayers()) {
						
						player3.playSound(player3.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 10);
						timer.cancel(player3); 
						
					}
					
				}
				
				
			}
			
			if (aliveplayers.size() == 1) {
				
				for (Player player3 : Bukkit.getOnlinePlayers()) {
					
					player3.playSound(player3.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 10);
					
					String team1 = Methods.getTeam(player3); 
					
					player3.sendMessage((Var.prefix + "Das Team §b#" + team1 + "§a hat das Spiel gewonnen!"));
					
					timer.cancel(player3); 
					
				}
				
				
				
			}
			
			
			
			if (Methods.isBattlePhase()) {
				
			
				
				e.getDrops().add(Var.cfg.getItemStack("killItems")); 
				
			}
			
			
		} else {
			
			e.setDeathMessage(Var.prefix + "Der Spieler §b" + p.getName() + "§a ist gestorben!");
			
		}
		
		
		
		
		
	}
	
	
	
}
