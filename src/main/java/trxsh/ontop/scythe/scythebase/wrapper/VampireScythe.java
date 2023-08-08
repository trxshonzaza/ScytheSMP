package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import trxsh.ontop.scythe.data.CooldownData;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;
import trxsh.ontop.scythe.utility.MathUtility;

import java.util.HashMap;

public class VampireScythe extends Scythe {

    public VampireScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
    }

    @Override
    public void doAbility(Player player) {

        if(!MathUtility.isLookingAtPlayer(player, 50)) {

            player.sendMessage(ChatColor.RED + "You are not looking at a player!");
            return;

        }

        Player entity = MathUtility.getPlayerLookingAt(player, 50);
        BlockIterator iterator = new BlockIterator(player, 50);

        CooldownData.add(player.getUniqueId(), 15000);

        while(iterator.hasNext()) {

            Block b = iterator.next();
            Location l = b.getLocation();

            player.getWorld().spawnParticle(Particle.REDSTONE, l, 5, 0, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));
            player.getWorld().spawnParticle(Particle.REDSTONE, l, 5, .2, .2D, .1D, 0, new Particle.DustOptions(Color.BLACK, 1));

        }

        if(entity == null)
            return;

        entity.damage(6);

        double targetHealth = player.getHealth() + 6;

        if(targetHealth >= 20)
            player.setHealth(20);
        else
            player.setHealth(targetHealth);

        entity.sendMessage(ChatColor.RED + player.getName() + " took 3 hearts from you.");

        player.sendMessage(ChatColor.RED + "You took 3 hearts from " + entity.getName() + ".");

        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH, 1f, 1f);
        player.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH, 1f, 1f);

    }

    @Override
    public void doPassive(Player player) {

    }
}
