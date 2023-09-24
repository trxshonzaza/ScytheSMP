package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import trxsh.ontop.scythe.data.CooldownData;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;

import java.util.HashMap;

public class StrengthScythe extends Scythe {

    public StrengthScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
    }

    @Override
    public void doAbility(Player player) {

        if(PlayerData.contains(player.getUniqueId())) {

            CooldownData.add(player.getUniqueId(), 20000, getType());

            DataPlayer p = PlayerData.playerList.get(player.getUniqueId());

            p.can2X = true;

            player.sendMessage(ChatColor.DARK_RED + "The next hit will deal double damage.");
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1f, .5f);

        }

    }

    @Override
    public void doPassive(Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 10, 0));

    }

}
