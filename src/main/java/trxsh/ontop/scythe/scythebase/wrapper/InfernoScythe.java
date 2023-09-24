package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import trxsh.ontop.scythe.data.CooldownData;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;

import java.util.HashMap;

public class InfernoScythe extends Scythe {

    public InfernoScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
    }

    @Override
    public void doAbility(Player player) {

        CooldownData.add(player.getUniqueId(), 15000, getType());

        Fireball fireball = player.launchProjectile(Fireball.class, player.getEyeLocation().getDirection());

        fireball.setBounce(false);
        fireball.setVisualFire(true);
        fireball.setYield(3F);

        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1f, 1f);

    }

    @Override
    public void doPassive(Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10 * 20, 1));

    }
}
