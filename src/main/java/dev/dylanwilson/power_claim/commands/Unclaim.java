package dev.dylanwilson.power_claim.commands;

import dev.dylanwilson.power_claim.Main;
import dev.dylanwilson.power_claim.utils.Message;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Unclaim implements CommandExecutor {
    private final Main main;

    public Unclaim(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (! (sender instanceof Player)) {
            sender.sendMessage(main.getLang().getMessage(Message.NOT_A_PLAYER));
            return true;
        }

        Player player = (Player) sender;
        Chunk chunk = player.getLocation().getChunk();

        if (main.getClaimsTable().getOwner(chunk.getX(), chunk.getZ()) == null) {
            player.sendMessage(main.getLang().getMessage(Message.CHUNK_NOT_CLAIMED));
            return true;
        }

        if (main.getClaimsTable().unclaimChunk(chunk.getX(), chunk.getZ())) {
            player.sendMessage(main.getLang().getMessage(Message.CHUNK_UNCLAIMED));
        } else {
            player.sendMessage(main.getLang().getMessage(Message.UNEXPECTED_ERROR));
        }

        return true;
    }
}
