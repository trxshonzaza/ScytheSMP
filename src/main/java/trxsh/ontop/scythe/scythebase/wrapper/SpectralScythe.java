package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import trxsh.ontop.scythe.Main;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;

import java.util.HashMap;

public class SpectralScythe extends Scythe {

    public SpectralScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
    }

    @Override
    public void doAbility(Player player) {

        player.hidePlayer(Main.Instance, player);

        Bukkit.getScheduler().runTaskLater(Main.Instance, new Runnable() {

            @Override
            public void run() {

                player.showPlayer(Main.Instance, player);

            }

        }, 20 * 5L);

    }

    @Override
    public void doPassive(Player player) {

    }
}