package dev.dylanwilson.power_claim.commands;

import dev.dylanwilson.power_claim.Main;
import dev.dylanwilson.power_claim.utils.Lang;
import dev.dylanwilson.power_claim.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Claim implements CommandExecutor {
    private final Lang lang;

    public Claim(Main main) {
        this.lang = main.getLang();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(lang.getMessage(Message.CHUNK_CLAIMED));
        return true;
    }
}
