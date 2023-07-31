package trxsh.ontop.scythe.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import trxsh.ontop.scythe.data.ScytheData;
import trxsh.ontop.scythe.scythebase.Scythe;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.isOp()) {

            if(sender instanceof Player) {

                Inventory inventory = Bukkit.createInventory(null, ScytheData.getScythes().size());

                for(Scythe scythe : ScytheData.getScythes())
                    inventory.addItem(scythe.getItem());

                ((Player) sender).openInventory(inventory);

                return true;

            }

        }

        return false;

    }

}
