package nl.mlgeditz.advancedapi.api;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

/**
 * Created by MLGEditz and/or other contributors
 * No part of this publication may be reproduced,
 * distrubuted, of transmitted in any form or by any means.
 * Copyright © 22 mrt. 2018 2018 by MLGEditz
*/

public class Bossbar {
	
	public void createBossBar(Player p, String message, BarColor color, BarStyle style, BarFlag flag) {
		BossBar bar = Bukkit.createBossBar(message.replaceAll("&", "§"), color, style, flag);
		bar.addPlayer(p);
	}
	
	public void createBossBarForAll(String message, BarColor color, BarStyle style, BarFlag flag) {
		BossBar bar = Bukkit.createBossBar(message.replaceAll("&", "§"), color, style, flag);
		for (Player all : Bukkit.getOnlinePlayers()) {
		bar.addPlayer(all);
		}
	}

}
