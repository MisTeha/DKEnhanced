package io.github.ukp123.dkenhanced.commands.help;

import io.github.ukp123.dkenhanced.utils.messageutils.MessageUtils;
import io.github.ukp123.dkenhanced.utils.messageutils.Messages;
import org.bukkit.entity.Player;

class HelpCommandPages {
    static void sCommandHelp(Player player, String[] args) {
        if (args.length == 2) {
            String lowerArg1 = args[1].toLowerCase();
            switch (lowerArg1) {
                case "help":
                    MessageUtils.sendMessage(Messages.SPECHELPPAGE_HELP, player);
                    return;
                case "prott":
                    MessageUtils.sendMessage(Messages.SPECHELPPAGE_PROTT, player);
                    return;
                default:
                    MessageUtils.sendMessage(Messages.ERROR_UNKNOWN_ARG, player, args[1]);
                    return;
            }
        }
    }
    static boolean commandList(Player player) {
        player.sendMessage(
        MessageUtils.replaceMessageVariables("HelpPage.upper_line") + "\n" +
        MessageUtils.replaceMessageVariables("HelpPage.help") + "\n" +
        MessageUtils.replaceMessageVariables("HelpPage.prott") + "\n" +
        MessageUtils.replaceMessageVariables("HelpPage.add") + "\n" +
        MessageUtils.replaceMessageVariables("HelpPage.remove") + "\n" +
        MessageUtils.replaceMessageVariables("HelpPage.lower_line"));
        return true;
    }
    static boolean credits(Player player) {
        player.sendMessage(
        MessageUtils.replaceMessageVariables("HelpPage.upper_line") + "\n" +
        MessageUtils.replaceMessageVariables("Credits.developer") + "\n" +
        MessageUtils.replaceMessageVariables("Credits.version") + "\n" +
        MessageUtils.replaceMessageVariables("Credits.help_command") + "\n" +
        MessageUtils.replaceMessageVariables("Credits.prefix") + "\n" +
        MessageUtils.replaceMessageVariables("HelpPage.lower_line"));
        return true;
    }
}
