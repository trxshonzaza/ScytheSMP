package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import trxsh.ontop.scythe.Main;
import trxsh.ontop.scythe.data.CooldownData;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FrostbiteScythe extends Scythe {

    public FrostbiteScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
    }

    @Override
    public void doAbility(Player player) {

        CooldownData.add(player.getUniqueId(), 30000);

        Location l = player.getLocation();

        HashMap<Location, Material> oldBlocks = new HashMap<>();

        int radius = 3;
        Material material = Material.BLUE_ICE;

        for (int x = -radius; x <= radius; x++) {

            for (int y = -radius; y <= radius; y++) {

                for (int z = -radius; z <= radius; z++) {

                    if (Math.abs(x) == radius || Math.abs(y) == radius || Math.abs(z) == radius) {

                        Location blockLocation = l.clone().add(x, y, z);

                        oldBlocks.put(blockLocation, player.getWorld().getBlockAt(blockLocation).getType());
                        player.getWorld().getBlockAt(blockLocation).setType(material);

                    }

                }

            }

        }

        Bukkit.getScheduler().runTaskLater(Main.Instance, new Runnable() {

            @Override
            public void run() {

                for(Location l : oldBlocks.keySet()) {

                    Material m = oldBlocks.get(l);

                    player.getWorld().getBlockAt(l).setType(m);

                }

                oldBlocks.clear();

            }

        }, 20 * 10L);

    }

    @Override
    public void doPassive(Player player) {

    }
}
