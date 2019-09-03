package io.github.ukp123.dkenhanced;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;



public class TestCommand implements CommandExecutor {

    public DKEnhanced plugin;

    public TestCommand(DKEnhanced p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("This is a test command for DKEnhanced.");
            return true;
        } else if (sender instanceof ConsoleCommandSender) {
            plugin.getLogger().info("ThisIsATestCommand.");
            return true;
        }
        return false;
    }
}