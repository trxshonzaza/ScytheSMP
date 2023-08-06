package trxsh.ontop.scythe.utility;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ItemUtility {

    public static ItemStack getBanMenuStack() {

        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);

        ItemMeta meta = stack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN + "Unban Player Menu");
        meta.setLore(Collections.singletonList(ChatColor.GREEN + "Unban a Player!"));
        meta.setUnbreakable(true);

        stack.setItemMeta(meta);

        return stack;

    }

    public static ItemStack getScytheTokenStack() {

        ItemStack stack = new ItemStack(Material.GHAST_TEAR);

        ItemMeta meta = stack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN + "Scythe Token");
        meta.setLore(Collections.singletonList("Right Click To Open Scythe Menu!"));
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        meta.setUnbreakable(true);

        stack.setItemMeta(meta);

        return stack;

    }

    public static BanEntry<?> getBanEntryFromStack(ItemStack stack) {

        if(stack.getItemMeta() == null)
            return null;

        if(!stack.getItemMeta().hasDisplayName())
            return null;

        String name = stack.getItemMeta().getDisplayName();

        return Bukkit.getBanList(BanList.Type.NAME).getBanEntry(name);

    }

}
