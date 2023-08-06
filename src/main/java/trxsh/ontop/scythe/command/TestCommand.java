package trxsh.ontop.scythe.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import trxsh.ontop.scythe.data.ScytheData;
import trxsh.ontop.scythe.inventory.BanInventory;
import trxsh.ontop.scythe.inventory.ScytheInventory;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.utility.ItemUtility;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.isOp()) {

            if(sender instanceof Player) {

                /*Inventory inventory = Bukkit.createInventory(null, 18, "prod trxsh 2.0#1988 | <3");

                for(Scythe scythe : ScytheData.getScythes())
                    inventory.addItem(scythe.getItem());*/

                ((Player) sender).getInventory().addItem(ItemUtility.getScytheTokenStack());

                return true;

            }

        }

        return false;

    }

}
