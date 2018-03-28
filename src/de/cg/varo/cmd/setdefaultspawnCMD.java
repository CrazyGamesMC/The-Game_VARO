package de.cg.varo.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.cg.varo.game.Methods;
import de.cg.varo.game.Var;
import de.cg.varo.main.Main;

public class setdefaultspawnCMD implements CommandExecutor{

	private Main plugin; 
	
	public setdefaultspawnCMD(Main main) {
		
		this.plugin = plugin; 
		
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender; 
		
		if (p.isOp() == true ) {
			if (args.length == 0) {
			
				p.sendMessage(Var.prefix + "§c/setdefualtspawn [NAME]");
			
			
				return true; 
			}
		
			if (args.length == 1) {
			
				Methods.setSpawnLocation(p, args[0]);
			
				p.sendMessage(Var.prefix + "Der Spawn von §b" + args[0] + "§a wurde gesetzt!");
			
			
			} 
		
		
		}
		
		
		return true;
	}

}
