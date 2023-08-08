package trxsh.ontop.scythe.command;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import trxsh.ontop.scythe.utility.TextureUtility;

import java.lang.reflect.InvocationTargetException;

public class ResourceRequest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.RED + "you are not a valid player!");
            return true;

        }

        try {

            sender.sendMessage(ChatColor.GRAY + "Sending resource request...");

            TextureUtility.loadTexture((Player) sender);

            ((Player) sender).playSound(((Player) sender).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
            sender.sendMessage(ChatColor.GREEN + "Please press 'Accept' to load the resource pack.");

        }catch(InvocationTargetException e) {

            sender.sendMessage(ChatColor.RED + "Failed to send packet. Try again");

        }

        return true;

    }

}
