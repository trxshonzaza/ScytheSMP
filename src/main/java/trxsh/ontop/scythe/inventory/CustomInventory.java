package trxsh.ontop.scythe.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public abstract class CustomInventory {

    private Inventory inventory = null;

    private String name;

    public CustomInventory(String name) {

        this.name = name;

    }

    public abstract Inventory getInventory();

    public Inventory loadInventory() {

        return inventory;

    }

    public void setInventory(Inventory inventory) {

        this.inventory = inventory;

    }

    public Inventory createInventory(int size) {

        return Bukkit.createInventory(null, size, name);

    }

    public void addItem(int index, ItemStack item) {

        if(inventory != null)
            inventory.setItem(index, item);
        else
            throw new NullPointerException("inventory is null");

    }

    public void addItem(ItemStack item) {

        if(inventory != null)
            inventory.addItem(item);
        else
            throw new NullPointerException("inventory is null");

    }

}
