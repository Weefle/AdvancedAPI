package nl.mlgeditz.advancedapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import nl.mlgeditz.advancedapi.api.ActionBar;
import nl.mlgeditz.advancedapi.api.Bossbar;
import nl.mlgeditz.advancedapi.api.Money;
import nl.mlgeditz.advancedapi.api.Particles;
import nl.mlgeditz.advancedapi.api.Players;
import nl.mlgeditz.advancedapi.api.Title;
import nl.mlgeditz.advancedapi.data.DataManager;
import nl.mlgeditz.advancedapi.db.MySQL;

/**
 * Created by MLGEditz and/or other contributors
 * No part of this publication may be reproduced,
 * distrubuted, of transmitted in any form or by any means.
 * Copyright Â© 22 mrt. 2018 2018 by MLGEditz
*/

public class AdvancedAPI extends JavaPlugin {
	
	public static Plugin plug;
	private static Money money = new Money();
	private static ActionBar actionbar = new ActionBar();
	private static Particles particles = new Particles();
	private static Title title = new Title();
	private static Players player = new Players();
	private static Bossbar bar = new Bossbar();
	
	public static boolean databasebol = true;
	
	@Override
	public void onEnable() {
		plug = this;
		
		    DataManager.setupConfig();
			getConfig().options().copyDefaults(true);
			getConfig().addDefault("sql.host", "localhost");
			getConfig().addDefault("sql.port", 3306);
			getConfig().addDefault("sql.database", "database");
			getConfig().addDefault("sql.username", "root");
			getConfig().addDefault("sql.password", "password");
			getConfig().addDefault("sql.table", "tablename");
			getConfig().addDefault("storagetype", "file");
			saveConfig();
			
			if (getConfig().getString("storagetype").equalsIgnoreCase("file")) {
				databasebol = false;
				DataManager.setupConfig();
			} else if (getConfig().getString("storagetype").equalsIgnoreCase("mysql")) {
				databasebol = true;
				MySQL.AsynchronousDatabase();
			}
			
			Bukkit.getConsoleSender().sendMessage("§f[AdvancedAPI§f] §aLoaded AdvancedAPI v" + getDescription().getVersion() + " succesfully!");
		
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§f[AdvancedAPI§f] §aDisabled AdvancedAPI v" + getDescription().getVersion() + " succesfully!");
	}

	public static nl.mlgeditz.advancedapi.api.Money Money() {
		return money;
	}
	
	public static nl.mlgeditz.advancedapi.api.ActionBar ActionBar() {
		return actionbar;
	}
	
	public static nl.mlgeditz.advancedapi.api.Particles Particles() {
		return particles;
	}
	
	public static nl.mlgeditz.advancedapi.api.Title Title() {
		return title;
	}
	
	public static nl.mlgeditz.advancedapi.api.Players Player() {
		return player;
	}
	
	public static nl.mlgeditz.advancedapi.api.Bossbar BossBar() {
		return bar;
	}
	
}
