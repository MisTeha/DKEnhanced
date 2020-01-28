package io.github.ukp123.dkenhanced.commands.utils.messageutils;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public enum Messages {
    CREDITS_DEVELOPER("Credits.developer"),
    CREDITS_VERSION("Credits.version"),
    CREDITS_HELPCOMMAND("Credits.help_command"),
    CREDITS_PREFIX("Credits.prefix"),

    HELPPAGE_UPPERLINE("HelpPage.upper_line"),
    HELPPAGE_LOWERLINE("HelpPage.lower_line"),
    HELPPAGE_HELP("HelpPage.help"),
    HELPPAGE_PROTT("HelpPage.prott"),
    HELPPAGE_ADD("HelpPage.add"),
    HELPPAGE_REMOVE("HelpPage.remove"),

    SPECHELPPAGE_HELP("SpecificHelpPage.help"),
    SPECHELPPAGE_PROTT("SpecificHelpPage.prott"),

    ERROR_PLUGIN_UNCONFIGURED("ErrorMessages.plugin_unconfigured"),
    ERROR_NOPERM_MESSAGE("ErrorMessages.no_permission_message"),
    ERROR_UNKNOWN_ARG("ErrorMessages.unknown_arg"),

    PROTT_WE_WG_UNDEFINED("ProttCommand.we_wg_undefined"),
    PROTT_WE_UNDEFINED("ProttCommand.we_undefined"),
    PROTT_WG_UNDEFINED("ProttCommand.wg_undefined"),
    PROTT_SELECTION_UNDEFINED("ProttCommand.selection_undefined"),
    PROTT_PLAYER_UNDEFINED("ProttCommand.player_undefined"),
    PROTT_PLAYER_DISCONNECTED("ProttCommand.player_disconnected"),
    PROTT_PLAYER_OVER_PROTLIMIT("ProttCommand.player_over_prot_limit"),
    PROTT_USED_ILLEGAL_CHAR("ProttCommand.used_illegal_characters"),
    PROTT_IGNORING_PROTLIMIT("ProttCommand.ignoring_prot_limit"),
    PROTT_PROT_MADE("ProttCommand.prot_made"),

    MEMBER_PLAYER_UNDEFINED("MemberCommands.player_undefined"),
    MEMBER_NO_REGION("MemberCommands.no_region"),
    MEMBER_PLAYER_NOTOWNER("MemberCommands.player_not_owner"),
    MEMBER_MEMBER_ADDED("MemberCommands.member_added"),
    MEMBER_MEMBER_REMOVED("MemberCommands.member_removed");

    public final String path;

    Messages(String path_) {
        path = path_;
    }

}
