package de.cg.varo.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.cg.varo.game.Var;
import de.cg.varo.game.timer;
import de.cg.varo.main.Main;

public class timeCMD implements CommandExecutor{

	private Main plugin; 
	
	public timeCMD(Main main) {
		
		this.plugin = plugin; 
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender; 
		
		if (args.length == 0) {
			if (Var.cfg.getBoolean("started")) {
			
				p.sendMessage("§aDu hast noch §b" + timer.getTime(p)/60 + " Minuten §aund §b" + (timer.getTime(p) - (timer.getTime(p)/60)*60) + " Sekunden!"); 
			
			} else {
			
				p.sendMessage(Var.prefix + "Das Spiel hat noch nicht begonnen!");
			
			}
		} else {
			
			if (args[1].equalsIgnoreCase("day") && p.isOp()) {
				
				p.getWorld().setTime(0);
				
			}
			
		}
		
		
		
		return true;
	}

}
