package com.mcserver.badword;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Badword extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "這個是防TNT和不雅字眼");
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();
        getCommand("dk").setExecutor(new CommandClass(this));
        getCommand("bd").setExecutor(new CommandClass(this));
        getServer().getPluginManager().registerEvents(new MyEvents(this),this);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "關閉中...");
    }
}
