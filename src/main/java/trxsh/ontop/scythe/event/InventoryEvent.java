package trxsh.ontop.scythe.event;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.inventory.ItemStack;
import trxsh.ontop.scythe.data.BanData;
import trxsh.ontop.scythe.data.ScytheData;
import trxsh.ontop.scythe.inventory.BanInventory;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.utility.ItemUtility;
import trxsh.ontop.scythe.utility.OrbUtility;
import trxsh.ontop.scythe.utility.ScytheUtility;

import java.util.Objects;
import java.util.UUID;

public class InventoryEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if(e.getView().getTitle().contains("Scythe Token Menu")) {

            Player player = (Player) e.getWhoClicked();
            ItemStack item = e.getCurrentItem();

            e.setCancelled(true);
            player.updateInventory();

            if(OrbUtility.getOrbLevel(player.getUniqueId()) != 5) {

                if(!player.isOp()) {

                    player.closeInventory();

                    player.sendMessage(ChatColor.RED + "You do not have a sufficent orb level! Your orb level must be 5.");

                    return;

                }

            }

            assert item != null;
            if(item.isSimilar(ItemUtility.getBanMenuStack())) {

                item.setAmount(0);

                player.openInventory(new BanInventory("Ban Menu").getInventory());
                return;

            }

            Scythe scythe = ScytheUtility.getScytheByStack(item);

            if(scythe != null) {

                if(player.getInventory().firstEmpty() == -1) {

                    player.sendMessage(ChatColor.RED + "Your inventory is full. Please remove an item to receive your scythe.");

                } else {

                    player.getInventory().addItem(scythe.getItem());
                    player.closeInventory();
                    player.sendMessage(ChatColor.GREEN + "You have received " + ChatColor.LIGHT_PURPLE + scythe.getName());

                    OrbUtility.setOrbLevel(player.getUniqueId(), 0);

                    for(ItemStack invItem : player.getInventory())
                         if(invItem != null)
                             if(invItem.isSimilar(ItemUtility.getScytheTokenStack())) {

                                 if(invItem.getAmount() > 1)
                                     invItem.setAmount(invItem.getAmount() - 1);
                                 else if(invItem.getAmount() == 1)
                                     invItem.setAmount(0);

                             }

                }

            }

        } else if(e.getView().getTitle().contains("Ban Menu")) {

            Player player = (Player) e.getWhoClicked();
            ItemStack item = e.getCurrentItem();

            e.setCancelled(true);
            player.updateInventory();

            if(OrbUtility.getOrbLevel(player.getUniqueId()) != 5) {

                if(!player.isOp()) {

                    player.closeInventory();

                    player.sendMessage(ChatColor.RED + "You do not have a sufficent orb level! Your orb level must be 5.");

                    return;

                }

            }

            for(UUID id : BanData.banList) {

                OfflinePlayer banned = Bukkit.getOfflinePlayer(id);

                if(item != null) {

                    if(item.getItemMeta() != null) {

                        if(item.getItemMeta().hasDisplayName()) {

                            if(Objects.requireNonNull(banned.getName()).equalsIgnoreCase(item.getItemMeta().getDisplayName())) {

                                BanEntry<?> entry = ItemUtility.getBanEntryFromStack(item);

                                if(entry == null)
                                    return;

                                Bukkit.getBanList(BanList.Type.NAME).pardon(entry.getTarget());
                                BanData.remove(banned.getUniqueId());

                                player.closeInventory();
                                player.sendMessage(ChatColor.GREEN + "Player unbanned!");

                                for(ItemStack invItem : player.getInventory())
                                    if(invItem.isSimilar(ItemUtility.getScytheTokenStack())) {

                                        if(invItem.getAmount() > 1)
                                            invItem.setAmount(invItem.getAmount() - 1);
                                        else if(invItem.getAmount() == 1)
                                            invItem.setAmount(0);

                                    }

                                OrbUtility.setOrbLevel(player.getUniqueId(), 0);

                            } else {

                                player.closeInventory();
                                player.sendMessage(ChatColor.RED + "An error occured. Please try again.");

                            }

                        }

                    }

                }

            }

        }

    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent e) {

        for(Scythe scythe : ScytheData.getScythes())
            if(e.getInventory().contains(scythe.getItem()))
                e.setResult(null);

    }

    @EventHandler
    public void onPrepareEnchantmentTable(PrepareItemEnchantEvent e) {

        for(Scythe scythe : ScytheData.getScythes())
            if(e.getItem().isSimilar(scythe.getItem())) {

                e.setCancelled(true);
                e.getEnchanter().closeInventory();

                e.getEnchanter().sendMessage(ChatColor.RED + "You cannot enchant a Scythe!");

            }

    }

    @EventHandler
    public void onPrepareGrindstone(PrepareGrindstoneEvent e) {

        for(Scythe scythe : ScytheData.getScythes())
            if(e.getInventory().contains(scythe.getItem()))
                e.setResult(null);

    }

}
