package com.mcserver.badword;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClass implements CommandExecutor {
    private Badword plugin;
    public CommandClass(Badword bd){
        plugin = bd;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("dk")) {
            if (sender instanceof Player && player.isOp()){
                if (args.length < 1) {
                    player.sendMessage(ChatColor.AQUA + "{true | false}");
                    return true;
                }
                else if (args.length == 1) {
                    if (args[0].equals("true")) {
                        boolean TNT_enable = plugin.getConfig().getBoolean("TNT");
                        if (TNT_enable) {
                            player.sendMessage(ChatColor.YELLOW+"已經啟動了");
                            return  true;
                        } else {
                            plugin.getConfig().set("TNT", true);
                            player.sendMessage(ChatColor.YELLOW+"以修改為true");
                            plugin.saveConfig();
                            return true;
                        }
                    }
                    else if (args[0].equals("false")){
                        boolean enable = plugin.getConfig().getBoolean("TNT");
                        if (enable) {
                            plugin.getConfig().set("TNT", false);
                            player.sendMessage(ChatColor.YELLOW+"以修改為false");
                            plugin.saveConfig();
                            return true;
                        }
                        else {
                            player.sendMessage(ChatColor.YELLOW+"已經關閉了");
                            return true;
                        }
                    }
                    else{
                        player.sendMessage("{true | false}");
                    }
                }
                else {
                    player.sendMessage("/dk {true | false}");
                }
            }
            else{
                player.sendMessage(ChatColor.RED+"請管理員來使用");
            }
        }
        return true;
    }
}
