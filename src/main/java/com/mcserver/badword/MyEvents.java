package com.mcserver.badword;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class MyEvents implements Listener {
    private Badword plugin;
    public MyEvents(Badword bd){
        plugin = bd;
    }
    @EventHandler
    public void blockBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.GRAY + "You break " + ChatColor.YELLOW + material);
    }
    @EventHandler
    public void blockplace(BlockPlaceEvent event){
        Block block =event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();
        boolean TNT_enable = plugin.getConfig().getBoolean("TNT");
        if (TNT_enable){
            if (material.equals(Material.TNT)){
                block.setType(Material.AIR);
                player.sendMessage(ChatColor.RED + "You cannot place TNT");
            }
        }
    }
    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent event){
        String message = event.getMessage();
        Player player = event.getPlayer();
        boolean BAD_enable = plugin.getConfig().getBoolean("BAD");
        List<String> wordlist = plugin.getConfig().getStringList("banned-words");
        if (BAD_enable){
            for (String bannedword : wordlist){
                if (message.contains(bannedword)){
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You can not said that.");
                }
            }
        }
    }


}
