package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import trxsh.ontop.scythe.Main;
import trxsh.ontop.scythe.data.CooldownData;
import trxsh.ontop.scythe.loop.BlockLoop;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;

import java.util.HashMap;

public class NatureScythe extends Scythe {

    public NatureScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
    }

    @Override
    public void doAbility(Player player) {

        CooldownData.add(player.getUniqueId(), 30000);

        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);

        HashMap<Location, Material> oldBlocks = new HashMap<>();

        Material twistingVines = Material.TWISTING_VINES;

        // old values: int x = -1; x <= 2;, int z = -1; z <= 2;
        // Iterate over a 4x4 area centered around the specified location
        for (int x = -2; x <= 4; x++) {

            for (int z = -2; z <= 4; z++) {

                Location currentLocation = player.getLocation().clone().add(x, 0, z);

                if(player.getWorld().getBlockAt(currentLocation.clone().subtract(0, 1, 0)).getType() != Material.AIR)
                    currentLocation = player.getLocation().clone().subtract(x, 1, z);

                if(player.getWorld().getBlockAt(currentLocation.clone().add(0, 1, 0)).getType() != Material.AIR)
                    currentLocation = player.getLocation().clone().add(x, 1, z);

                Block block = player.getWorld().getBlockAt(currentLocation);

                oldBlocks.put(block.getLocation(), block.getType());

                block.setType(twistingVines);

                BlockLoop.addBlockToLoop(player.getUniqueId(), block.getLocation());

                Location finalCurrentLocation = currentLocation;
                Bukkit.getScheduler().runTaskLater(Main.Instance, new Runnable() {

                    @Override
                    public void run() {

                        Block block1 = player.getWorld().getBlockAt(finalCurrentLocation.clone().add(0, 1, 0));

                        oldBlocks.put(block1.getLocation(), block1.getType());

                        block1.setType(twistingVines);

                        BlockLoop.addBlockToLoop(player.getUniqueId(), block1.getLocation());

                    }

                }, 5L);

            }

        }

        Bukkit.getScheduler().runTaskLater(Main.Instance, new Runnable() {

            @Override
            public void run() {

                for(Location l : oldBlocks.keySet()) {

                    BlockLoop.removeBlockFromLoop(l);

                    Material m = oldBlocks.get(l);

                    player.getWorld().getBlockAt(l).setType(m);

                }

                oldBlocks.clear();

            }

        }, 20 * 5L);

    }

    @Override
    public void doPassive(Player player) {

    }

}
