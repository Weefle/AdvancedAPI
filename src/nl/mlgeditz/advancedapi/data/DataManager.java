package nl.mlgeditz.advancedapi.data;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nl.mlgeditz.advancedapi.AdvancedAPI;

/**
 * Created by MLGEditz and/or other contributors
 * No part of this publication may be reproduced,
 * distrubuted, of transmitted in any form or by any means.
 * Copyright © 22 mrt. 2018 2018 by MLGEditz
*/

public class DataManager {

	static File dataConfig;
	public static FileConfiguration config;
	
	public static void setupConfig() {
		
			dataConfig = new File(AdvancedAPI.plug.getDataFolder(), "data.yml");
			config = YamlConfiguration.loadConfiguration(dataConfig);
			saveCustomConfig();
	}
	
	public static void saveCustomConfig() {
		try {
			config.save(dataConfig);
			YamlConfiguration.loadConfiguration(dataConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FileConfiguration getConfig() {
		FileConfiguration f = config;
		return f;
	}
	
	

}
