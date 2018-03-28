package de.cg.varo.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.cg.varo.game.Var;
import de.cg.varo.game.timer;

public class onLeaveLTR implements Listener{
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		if (Var.cfg.getBoolean("started") == true)
			timer.cancel(e.getPlayer());
		
		
		e.setQuitMessage("§7Der Spieler " + e.getPlayer().getName() + " hat das Spiel verlassen!");
		
		
	}
	

}
