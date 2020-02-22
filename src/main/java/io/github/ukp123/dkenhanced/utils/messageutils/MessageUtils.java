package io.github.ukp123.dkenhanced.utils.messageutils;

import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MessageUtils {

    public static DKEnhanced plugin;
    public static void sendMessage(Messages message, Player player, String... variables) {
        String path = message.path;

        String m = plugin.getMessagesConfig().getString(path);
        assert m != null;
        m = getString(m);
        if (variables.length > 0)
            m = String.format(m, variables);
        player.sendMessage(m);
    }

    private static String getString(String m) {
        m = m.replace("{PREFIX}", Objects.requireNonNull(plugin.getMessagesConfig().getString("prefix")));
        m = m.replace("{DEVELOPER}", "ukp123");
        m = m.replace("{VERSION}", plugin.getDescription().getVersion());
        m = m.replace("{HELP_COMMAND}", "/dk help");
        m = m.replace("{PROT_LIMIT}", Integer.toString(plugin.getConfig().getInt("commands.prott.prot_limit")));
        m = ChatColor.translateAlternateColorCodes('&', m);
        return m;
    }

    public static String replaceMessageVariables(String path) {
        String m = plugin.getMessagesConfig().getString(path);
        if (m == null) {
            return "null";
        }
        m = getString(m);
        return m;
    }
}
