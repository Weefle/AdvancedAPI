package nl.mlgeditz.advancedapi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import nl.mlgeditz.advancedapi.AdvancedAPI;

/**
 * Created by MLGEditz and/or other contributors
 * No part of this publication may be reproduced,
 * distrubuted, of transmitted in any form or by any means.
 * Copyright © 26 mrt. 2018 2018 by MLGEditz
*/

public class MySQL {
	
	public static Connection connection;
	public static String host, database, username, password, table;
	public static int port;
	public static Statement statement;
	public static int money;
	
	public static void AsynchronousDatabase() {
		BukkitRunnable r = new BukkitRunnable() {
			   @Override
			   public void run() {
			      try {
			         openConnection();
			         statement = connection.createStatement();
			      } catch(ClassNotFoundException e) {
			    	  Bukkit.getConsoleSender().sendMessage("§f[AdvancedAPI] §cAcces Denied! Something went wrong while loading drivers!");
			      } catch(SQLException e) {
			         Bukkit.getConsoleSender().sendMessage("§f[AdvancedAPI] §cAcces Denied! Something went wrong while connecting to database!");
			      }
			   }
			};
			 
			r.runTaskAsynchronously(AdvancedAPI.plug);
	}
	
	
	public static void createTable() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS `" + table + "` (`UUID` varchar(200), `MONEY` INT(10))");
			Bukkit.getConsoleSender().sendMessage("§f[AdvancedAPI] §aTable " + table + " created succesfully");
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("§f[AdvancedAPI] §cAcces Denied! Something went wrong while creating table!");
        }
	}
	
	public static void openConnection() throws SQLException, ClassNotFoundException {
		port = AdvancedAPI.plug.getConfig().getInt("sql.port");
		host = AdvancedAPI.plug.getConfig().getString("sql.host");
		database = AdvancedAPI.plug.getConfig().getString("sql.database");
		username = AdvancedAPI.plug.getConfig().getString("sql.username");
		password = AdvancedAPI.plug.getConfig().getString("sql.password");
		table = AdvancedAPI.plug.getConfig().getString("sql.table");
		
		
		if (connection != null && !connection.isClosed()) {
			return;
		}
		
		synchronized(AdvancedAPI.plug) {
			if (connection != null && !connection.isClosed()) {
				return;
			}
			Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://" + MySQL.host+ ":" + MySQL.port + "/" + MySQL.database, MySQL.username, MySQL.password);
	        Bukkit.getConsoleSender().sendMessage("§f[AdvancedAPI] §aAcces granted! Connected with database");
	        Bukkit.getConsoleSender().sendMessage("§aHost§f: " + MySQL.host + ":" + MySQL.port);
	        Bukkit.getConsoleSender().sendMessage("§aDatabase§f: " + MySQL.database);
	        Bukkit.getConsoleSender().sendMessage("§aUsername§f: " + MySQL.username);
	        Bukkit.getConsoleSender().sendMessage("§aPassword§f: " + MySQL.password);
	        createTable();
		}
		
	}
	
	
	public static int getMoney(OfflinePlayer target) {
		try {
			PreparedStatement pst = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID = '" + target.getUniqueId().toString() + "';");
			ResultSet res = pst.executeQuery();
			if (res.next()) {
				money = res.getInt("MONEY");
				return MySQL.money;
			}
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("§f[AdvancedAPI] §cAcces Denied! Something went wrong getting MONEY value");
		}
		return money;
	}
	
	public static void setMoney(Player p, int amount) {
		try {
			 PreparedStatement pst = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID = '" + p.getUniqueId().toString() +  "';");
			 ResultSet res = pst.executeQuery();
			 if (res.next()) {
				 statement.executeUpdate("UPDATE " + table +  " SET MONEY = " + amount + " WHERE UUID = '" + p.getUniqueId().toString() + "';");
			 } else {
				 statement.executeUpdate("INSERT INTO " + table + " (UUID, MONEY) VALUES ('" + p.getUniqueId().toString() + "', " + amount + ");");
			 }
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("§f[AdvancedAPI] §cAcces Denied! Something went wrong setting MONEY value");
		}
	}
	
	
	public static void removeData(Player p) {
		try {
			statement.executeUpdate("DELETE FROM " + table + " WHERE UUID = '" + p.getUniqueId().toString() + "';");
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("§f[AdvancedAPI] §cAcces Denied! Something went wrong removing data");
		}
	}

}
