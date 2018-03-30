package de.cg.varo.main;

import org.bukkit.plugin.java.JavaPlugin;

import de.cg.varo.cmd.setdefaultspawnCMD;
import de.cg.varo.cmd.startCMD;
import de.cg.varo.cmd.strikeCMD;
import de.cg.varo.cmd.teamCMD;
import de.cg.varo.cmd.timeCMD;
import de.cg.varo.events.onBlockBreakLTR;
import de.cg.varo.events.onEntityDamageByEntityLTR;
import de.cg.varo.events.onEntityDamageLTR;
import de.cg.varo.events.onFoodLevelLTR;
import de.cg.varo.events.onJoinLTR;
import de.cg.varo.events.onKillLTR;
import de.cg.varo.events.onLeaveLTR;
import de.cg.varo.events.onLoginLTR;
import de.cg.varo.events.onMoveLTR;
import de.cg.varo.events.onPlayerInteractLTR;
import de.cg.varo.game.Var;

public class Main extends JavaPlugin{

	public static Main plugin; 
	
	public static int countdown ; 
	
	public static int start_sced; 
	
	@Override
	public void onEnable() {
		
		registerEvents(); 
		registerCommands(); 
		Var.createFiles();
		
		plugin = this; 
		
	}
	
	@Override
	public void onDisable() {
		
		
		
	}
	
	
	
	public void registerEvents() {
		
		getServer().getPluginManager().registerEvents(new onKillLTR(), this);
		getServer().getPluginManager().registerEvents(new onJoinLTR(), this);
		getServer().getPluginManager().registerEvents(new onMoveLTR(), this);
		getServer().getPluginManager().registerEvents(new onBlockBreakLTR(), this);
		getServer().getPluginManager().registerEvents(new onFoodLevelLTR(), this);
		getServer().getPluginManager().registerEvents(new onEntityDamageByEntityLTR(), this);
		getServer().getPluginManager().registerEvents(new onLeaveLTR(), this);
		getServer().getPluginManager().registerEvents(new onPlayerInteractLTR(), this);
		getServer().getPluginManager().registerEvents(new onLoginLTR(), this);
		getServer().getPluginManager().registerEvents(new onEntityDamageLTR(), this);
		
	}
	
	public void registerCommands() {
		
		teamCMD cTeamCMD = new teamCMD(this);
		getCommand("team").setExecutor(cTeamCMD);
		
		setdefaultspawnCMD cDefSpawnCMD = new setdefaultspawnCMD(this); 
		getCommand("setdefaultspawn").setExecutor(cDefSpawnCMD);
		
		startCMD cStartCMD = new startCMD (this); 
		getCommand("start").setExecutor(cStartCMD);
		
		timeCMD cTimeCMD = new timeCMD (this); 
		getCommand("time").setExecutor(cTimeCMD);
		
		strikeCMD cStrikeCMD = new strikeCMD(this); 
		getCommand("strike").setExecutor(cStrikeCMD);
		
		
	}
	
	
}
