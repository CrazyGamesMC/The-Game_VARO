package de.cg.varo.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.cg.varo.game.Var;

public class onLoginLTR implements Listener{
	
	@EventHandler
	public void onLoginLTR(PlayerLoginEvent e) {
		
		if (Var.cfg.getBoolean("started") && !Var.cfg.getList("aliveplayers").contains(e.getPlayer().getUniqueId().toString()))
			e.disallow(Result.KICK_FULL, "§cDu bist kein Teilnehmer.\n §aBitte wende dich an die Admins, falls ein Fehler vorliegt!");
		
	}

}
