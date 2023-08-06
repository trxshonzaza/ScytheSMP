package trxsh.ontop.scythe.inventory;

import org.bukkit.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import trxsh.ontop.scythe.data.BanData;

import java.util.Collections;
import java.util.UUID;

public class BanInventory extends CustomInventory {

    public BanInventory(String name) {
        super(name);
    }

    @Override
    public Inventory getInventory() {

        setInventory(createInventory(54));

        for(UUID id : BanData.banList) {

            OfflinePlayer player = Bukkit.getOfflinePlayer(id);

            if(player.getName() == null)
                continue;

            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);

            SkullMeta meta = (SkullMeta) skull.getItemMeta();

            assert meta != null;
            meta.setOwningPlayer(player);

            meta.setDisplayName(player.getName());

            BanEntry<?> entry = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(player.getName());

            if(entry == null)
                continue;

            meta.setLore(Collections.singletonList("Was banned on " + entry.getCreated()));

            skull.setItemMeta(meta);

            addItem(skull);

        }

        return loadInventory();

    }

}
