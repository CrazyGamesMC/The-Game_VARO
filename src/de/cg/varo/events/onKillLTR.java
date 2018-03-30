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
				
				
					Methods.checkForWin(p);				
					
				
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
			
			Methods.checkForWin(p);		
			
			
			if (Methods.isBattlePhase()) {
				
			
				
				e.getDrops().add(Var.cfg.getItemStack("killItems")); 
				
			}
			
			
		} else {
			
			e.setDeathMessage(Var.prefix + "Der Spieler §b" + p.getName() + "§a ist gestorben!");
			
		}
		
		
		
		
		
	}
	
	
	
}
