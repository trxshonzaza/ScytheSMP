package trxsh.ontop.scythe.scythebase;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.HashMap;

public abstract class Scythe {

    public String key;
    public String name;
    public String description;
    public ScytheType type;
    public HashMap<Enchantment, Integer> enchantments;

    public Scythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {

        this.key = key;
        this.name = name;
        this.description = description;
        this.type = type;
        this.enchantments = enchantments;

    }

    public String getName() {

        return name;

    }

    public String getKey() {

        return key;

    }

    public ItemStack getItem() {

        ItemStack stack = new ItemStack(Material.NETHERITE_SWORD);

        ItemMeta meta = stack.getItemMeta();

        assert meta != null;
        meta.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
        meta.setUnbreakable(true);
        meta.setLore(Collections.singletonList(getDescription()));
        meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + getName());

        if(enchantments != null)
            for(Enchantment enchantment : enchantments.keySet())
                meta.addEnchant(enchantment, enchantments.get(enchantment), true);

        stack.setItemMeta(meta);

        return stack;

    }

    public String getDescription() {

        return description;

    }

    public ScytheType getType() {

        return type;

    }

    public abstract void doAbility(Player player);
    public abstract void doPassive(Player player);

}
