package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;

import java.util.HashMap;

public class EclipseScythe extends Scythe {

    public EclipseScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
    }

    @Override
    public void doAbility(Player player) {

    }

    @Override
    public void doPassive(Player player) {

        long time = player.getWorld().getTime();

        if(time > 0 && time < 1230) {

            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 10, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 10, 1));

        } else {

            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 10, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 0));

        }

    }
}
