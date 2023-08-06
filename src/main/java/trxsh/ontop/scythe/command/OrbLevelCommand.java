package trxsh.ontop.scythe.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import trxsh.ontop.scythe.data.OrbData;
import trxsh.ontop.scythe.utility.OrbUtility;

public class OrbLevelCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if(!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.RED + "you are not a valid player!");
            return true;

        }

        if(OrbData.contains(((Player) sender).getUniqueId())) {

            sender.sendMessage(ChatColor.GRAY + "Your orb level is: " + ChatColor.LIGHT_PURPLE + OrbUtility.getOrbLevel(((Player) sender).getUniqueId()));

        } else {

            sender.sendMessage(ChatColor.RED + "Player data does not exist! please re-login to fix.");

        }

        return true;

    }
    
}
