package trxsh.ontop.scythe.command.operator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import trxsh.ontop.scythe.inventory.ScytheInventory;

public class OpenScythes implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(!(sender instanceof Player)) {

            sender.sendMessage("no permission.");
            return true;

        }

        if(!sender.isOp()) {

            sender.sendMessage("no permission.");
            return true;

        }

        ((Player) sender).openInventory(new ScytheInventory("Developed by trxsh 2.0#1988 <3").getInventory());

        return true;
    }


}
