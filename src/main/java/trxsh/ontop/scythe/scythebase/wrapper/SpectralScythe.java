package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import trxsh.ontop.scythe.Main;
import trxsh.ontop.scythe.data.CooldownData;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;
import trxsh.ontop.scythe.utility.PlayerUtility;

import java.util.HashMap;

public class SpectralScythe extends Scythe {

    public SpectralScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
    }

    @Override
    public void doAbility(Player player) {

        CooldownData.add(player.getUniqueId(), 30000);

        PlayerUtility.hidePlayerForEveryone(player);

        player.sendMessage(ChatColor.GRAY + "You are now hidden for 5 seconds.");
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, .5f);

        Bukkit.getScheduler().runTaskLater(Main.Instance, new Runnable() {

            @Override
            public void run() {

                PlayerUtility.showPlayerForEveryone(player);

                player.sendMessage(ChatColor.GRAY + "You are now visible.");
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, .5f);

            }

        }, 20 * 5L);

    }

    @Override
    public void doPassive(Player player) {

    }
}
