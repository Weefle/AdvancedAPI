package nl.mlgeditz.advancedapi.api;

import org.bukkit.entity.Player;

import nl.mlgeditz.advancedapi.AdvancedAPI;
import nl.mlgeditz.advancedapi.data.DataManager;
import nl.mlgeditz.advancedapi.db.MySQL;

/**
 * Created by MLGEditz and/or other contributors
 * No part of this publication may be reproduced,
 * distrubuted, of transmitted in any form or by any means.
 * Copyright © 22 mrt. 2018 2018 by MLGEditz
*/

public class Money {
	
	
	public void addMoney(Player sender, Player reciever, int price) {
		
		if (AdvancedAPI.databasebol == true) {
			int money = MySQL.getMoney(reciever);
			int toadd = money + price;
		    MySQL.setMoney(reciever, toadd);
		} else {
		int money = DataManager.getConfig().getInt(reciever.getUniqueId().toString() + ".money");
		DataManager.getConfig().set(reciever.getUniqueId().toString() + ".money", money + price);
		DataManager.saveCustomConfig();
		}
	}
	
	public void setMoney(Player sender, Player reciever, int price) {
		if (AdvancedAPI.databasebol == true) {
			MySQL.setMoney(reciever, price);
		} else {
		DataManager.getConfig().set(reciever.getUniqueId().toString() + ".money", price);
		DataManager.saveCustomConfig();
		}
	}
	
	public void payMoney(Player sender, Player reciever, int price) {
		int recievermoney = DataManager.getConfig().getInt(reciever.getUniqueId().toString() + ".money");
		int sendermoney = DataManager.getConfig().getInt(sender.getUniqueId().toString() + ".money");
		if (sendermoney < price) {
			return;
		} else {
			if (sender.getName() == reciever.getName()) {
				return;
			} else {
				if (AdvancedAPI.databasebol == true) {
					MySQL.setMoney(reciever, recievermoney - price);
					MySQL.setMoney(sender, sendermoney + price);
				} else {
				DataManager.getConfig().set(reciever.getUniqueId().toString() + ".money", recievermoney + price);
				DataManager.getConfig().set(reciever.getUniqueId().toString() + ".money", sendermoney - price);
				DataManager.saveCustomConfig();
				}
			}
		}
	}
	
	public int getMoney(Player p) {
		if (AdvancedAPI.databasebol == true) {
			int money = MySQL.getMoney(p);
			return money;
		} else {
		int money = DataManager.getConfig().getInt(p.getUniqueId().toString() + ".money");
		return money;
		}
    }

}
