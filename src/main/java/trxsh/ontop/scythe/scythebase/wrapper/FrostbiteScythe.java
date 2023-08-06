package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import trxsh.ontop.scythe.Main;
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

        Location l = player.getLocation();

        List<Block> oldBlocks = new ArrayList<>();

        int radius = 5;
        Material material = Material.BLUE_ICE;

        for (int x = -radius; x <= radius; x++) {

            for (int y = -radius; y <= radius; y++) {

                for (int z = -radius; z <= radius; z++) {

                    if (Math.abs(x) == radius || Math.abs(y) == radius || Math.abs(z) == radius) {

                        Location blockLocation = l.clone().add(x, y, z);

                        oldBlocks.add(player.getWorld().getBlockAt(blockLocation));
                        player.getWorld().getBlockAt(blockLocation).setType(material);

                    }

                }

            }

        }

        Bukkit.getScheduler().runTaskLater(Main.Instance, new Runnable() {

            @Override
            public void run() {

                for(Block b : oldBlocks) {

                    player.getWorld().getBlockAt(b.getLocation()).setType(b.getType());

                }

                oldBlocks.clear();

            }

        }, 20 * 10L);

    }

    @Override
    public void doPassive(Player player) {

    }
}
