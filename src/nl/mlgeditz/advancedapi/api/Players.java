package nl.mlgeditz.advancedapi.api;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by MLGEditz and/or other contributors
 * No part of this publication may be reproduced,
 * distrubuted, of transmitted in any form or by any means.
 * Copyright © 22 mrt. 2018 2018 by MLGEditz
*/

public class Players {
	
	public void setFoodLevel(Player p, int level) {
		p.setFoodLevel(level);
	}
	
	public void setHealthLevel(Player p, double level) {
		p.setHealth(level);
	}
	
	public void addPotionEffect(Player p, PotionEffectType type, int duration, int amount) {
		p.addPotionEffect(new PotionEffect(type, duration, amount));
	}
	
	public void removePotionEffect(Player p, PotionEffectType type) {
		p.removePotionEffect(type);
	}
	
	public void setCustomName(Player p, String name) {
		p.setCustomName(name.replaceAll("&", "§"));
		p.setDisplayName(name);
		p.setCustomNameVisible(true);
		p.setPlayerListName(name);
	}
	
	public void setGlow(Player p) {
		p.setGlowing(true);
	}
	
	public void removeGlow(Player p) {
		p.setGlowing(false);
	}
	
	public void setAllowFlight(Player p) {
		p.setAllowFlight(true);
		p.isFlying();
	}
	
	public void setFlight(Player p) {
		p.setAllowFlight(true);
		p.setFlying(true);
	}
	
	public void removeFlight(Player p) {
		p.setAllowFlight(false);
		p.setFlying(false);
	}
	
	public void changeGameMode(Player p, GameMode gm) {
		p.setGameMode(gm);
	}
	
	public void giveItem(Player p, ItemStack item) {
		p.getInventory().addItem(item);
	}

}
