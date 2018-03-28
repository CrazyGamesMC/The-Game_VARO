package de.cg.varo.game;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Methods {

	public static void saveFile(String id) {
		
		if (id.equals("cfg")) {
			
			try {
				Var.cfg.save(Var.config);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else if (id.equals("teams")) {
			
			try {
				Var.teams.save(Var.teamfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		 else if (id.equals("kills")) {
				
				try {
					Var.kills.save(Var.killfile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		 
		 
		 else if (id.equals("spawns")) {
				
				try {
					Var.defSpawn.save(Var.defaultSpawn);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}else if (id.equals("strikes")) {
				
				try {
					Var.strikes.save(Var.strikeFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}else if (id.equals("coords")) {
				
				try {
					Var.coords.save(Var.coordsFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}else {
				
				System.out.println("Wrong parameters in Methods.saveFile()");
				
				
			}
		
		
		
		
		
	}

	
	
	public static void showTeamInfo (Player p) {
		
		p.sendMessage("�7Teamname � �6" + Var.teams.getString("players." + p.getName() + ".team")); 
		p.sendMessage("�7Mitglied 1 � �6" + Var.teams.getString("teams." + Var.teams.getString("players." + p.getName() + ".team") + ".Member1")); 
		p.sendMessage("�7Mitglied 2 � �6" + Var.teams.getString("teams." + Var.teams.getString("players." + p.getName() + ".team") + ".Member2")); 
		p.sendMessage("�7Kills � �6" + Var.kills.getString("teams." + Var.teams.getString("players." + p.getName() + ".team") + ".kills")); 
	}
	
	
	public static void createTeam(String name, Player p) {
		
		Var.teams.set("teams." + name + ".Member1", p.getName());
		Var.teams.set("teams." + name + ".Member2", "Nobody");
		Var.teams.set("teams." + name + ".exists", true);
		Var.teams.set("players." + p.getName() + ".team" , name);
		
		
		Var.teams.set("players." + p.getName() + ".member", 1);
		
		
		Methods.saveFile("teams");
		
		
		
		
		
		
		
		
	}



	public static void joinTeam(String teamname, Player p) {
		
		
		Player p2 = Bukkit.getServer().getPlayer(Var.teams.getString("teams." + teamname + ".Member1")); 
		
		try {
				if (p2.isOnline() == false) {
					
					p.sendMessage(Var.prefix + "�cDer Teamleiter ist nicht online.");
					return; 
					
				}
			
			} catch (Exception e) {
				
				p.sendMessage(Var.prefix + "�cDer Teamleiter ist nicht online.");
				return; 
				
			}
		
		
		if (Var.teams.getBoolean("teams." + teamname + ".exists") == true) {
			
			Var.requesting.put(p.getName(), Var.teams.getString("teams." + teamname + ".Member1")); 
			p2.sendMessage(Var.prefix + "Der Spieler �b" + p.getName() + "�a hat dir eine Teamanfrage gesendet. Nimm mit �b'/team accept " + p.getName() + "'�a an!");
			
			
			
			
			
			
		} else {
			
			p.sendMessage(Var.prefix + "�cDas Team exestiert nicht.");
			
		}
		
		
		
		
	}
	
	public static void setToTeam(String teamname, Player p) {
		
		Var.teams.set("teams." + teamname + ".Member2", p.getName());
		Var.teams.set("players." + p.getName() + ".team", teamname);
		
		if (Methods.getTeam(p) != null) {
			
			if (Var.teams.getInt("players." + p.getName() + ".member") == 1) {
				
				Var.teams.set("teams." + Methods.getTeam(p) + ".Member1", "Nobody");
				
				
				
			}
			
			else if (Var.teams.getInt("players." + p.getName() + ".member") == 2) {
				
				Var.teams.set("teams." + Methods.getTeam(p) + ".Member2", "Nobody");
				
				
				
			}
			
			
		}
		
		
		
		Methods.saveFile("teams");
	}
	
	
	public static String getTeam(Player p) {
		
	
		
		String team = Var.teams.getString("players." + p.getName() + ".team"); 
		
		
		return team; 
		
	}
	
	
	public static Location getSpawnLocation (Player p ) {
		
		
		Location loc = p.getLocation(); 
		
		
		loc.setX(Var.defSpawn.getDouble("players." + p.getName() + ".spawn.X"));
		loc.setY(Var.defSpawn.getDouble("players." + p.getName() + ".spawn.Y"));
		loc.setZ(Var.defSpawn.getDouble("players." + p.getName() + ".spawn.Z"));
		loc.setPitch((float) Var.defSpawn.getDouble("players." + p.getName() + ".spawn.Pitch"));
		loc.setYaw((float) Var.defSpawn.getDouble("players." + p.getName() + ".spawn.Yaw"));
		
		return loc;
		
		
		
	}
	
	public static void setSpawnLocation (Player p, String playername) {
		
		
		Location loc = p.getLocation(); 
		
		Var.defSpawn.set("players." + playername + ".spawn.X", loc.getX());
		Var.defSpawn.set("players." + playername +".spawn.Y", loc.getY());
		Var.defSpawn.set("players." + playername + ".spawn.Z", loc.getZ());
		Var.defSpawn.set("players."+ playername +".spawn.Yaw", loc.getYaw());
		Var.defSpawn.set("players."+ playername +".spawn.Pitch", loc.getPitch());
		Var.defSpawn.set("players." +  playername + ".set", true);
		
		saveFile("spawns");
		
		
		
	}
	
	public static boolean isBattlePhase() {
		
		if (Var.cfg.getBoolean("enablePhases") & Var.cfg.getInt("Phasestate") == 1) {
			return true; 
		} else  {
			return false; 
		}
		
	}
	
	public static int getStrikes(OfflinePlayer p) {
		
		int ret = Var.strikes.getInt("players." + p.getUniqueId().toString() + ".strikes");
		
		
		return ret; 
		
		
		
	}
	
	public static void addStrikes(OfflinePlayer p, int ammount) {
		
		Var.strikes.set("players." + p.getUniqueId().toString() + ".strikes" , Methods.getStrikes(p)+ammount); 
		
		
		
		Methods.saveFile("strikes"); 
		
		
		
		
	}
	
	public static boolean toClear(Player p) {
		
		return Var.cfg.getBoolean("toclear." + p.getUniqueId()); 
		
	}
	
	public static void putCoords(Player p) {
		
		Location loc = p.getLocation(); 
		
		double x = loc.getX(); 
		double y = loc.getY(); 
		double z = loc.getZ(); 
		
		Var.coords.set(p.getUniqueId().toString() + ".X", x);
		Var.coords.set(p.getUniqueId().toString() + ".Y", y);
		Var.coords.set(p.getUniqueId().toString() + ".Z", z);
		
		Methods.saveFile("coords");
	}
	
	

	
	
}
