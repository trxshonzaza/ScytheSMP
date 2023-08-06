package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import trxsh.ontop.scythe.data.CooldownData;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;
import trxsh.ontop.scythe.utility.FakePlayerUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MirageScythe extends Scythe {

    public MirageScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
    }

    @Override
    public void doAbility(Player player) {

        CooldownData.add(player.getUniqueId(), 30000);

        List<Location> locations = new ArrayList<>();

        int radius = 4;

        int centerX = player.getLocation().getBlockX();
        int centerZ = player.getLocation().getBlockZ();

        for (int x = centerX - radius; x <= centerX + radius; x++) {

            for (int z = centerZ - radius; z <= centerZ + radius; z++) {

                Location location = new Location(player.getWorld(), x, player.getLocation().getY(), z);

                if(location.getBlock().getType() == Material.AIR && location.add(0, 1, 0).getBlock().getType() == Material.AIR) {

                    locations.add(location);

                }

            }

        }

        if(locations.isEmpty())
            return;

        Location l1 = FakePlayerUtility.spawnFakePlayer(player.getUniqueId(), locations.get(new Random().nextInt(locations.size())));
        Location l2 = FakePlayerUtility.spawnFakePlayer(player.getUniqueId(), locations.get(new Random().nextInt(locations.size())));
        Location l3 = FakePlayerUtility.spawnFakePlayer(player.getUniqueId(), locations.get(new Random().nextInt(locations.size())));

        player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, l1, 20);
        player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, l2, 20);
        player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, l3, 20);

        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f);

    }

    @Override
    public void doPassive(Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10 * 20, 0));

    }

}
