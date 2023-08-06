package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import trxsh.ontop.scythe.Main;
import trxsh.ontop.scythe.data.CooldownData;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;
import trxsh.ontop.scythe.utility.MathUtility;

import java.util.HashMap;

public class StormScythe extends Scythe {


    public StormScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
    }

    @Override
    public void doAbility(Player player) {

        if(!player.getWorld().isThundering()) {

            player.sendMessage(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "It Starts to Rain...");
            player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, .5f, .5f);

            player.getWorld().setThundering(true);

        } else {

            CooldownData.add(player.getUniqueId(), 15000);

            Bukkit.getScheduler().runTask(Main.Instance, new Runnable() {

                @Override
                public void run() {

                    Block b = MathUtility.getSolidBlockLookingAt(player, 50);

                    if(b == null)
                        return;

                    LightningStrike strike = player.getWorld().strikeLightning(b.getLocation());
                    strike.setCustomName("TrxshLightningStrike");

                    Bukkit.getScheduler().runTaskLater(Main.Instance, new Runnable() {

                        @Override
                        public void run() {

                            Block b = MathUtility.getSolidBlockLookingAt(player, 50);

                            if(b == null)
                                return;

                            LightningStrike strike = player.getWorld().strikeLightning(b.getLocation());
                            strike.setCustomName("TrxshLightningStrike");

                            Bukkit.getScheduler().runTaskLater(Main.Instance, new Runnable() {

                                @Override
                                public void run() {

                                    Block b = MathUtility.getSolidBlockLookingAt(player, 50);

                                    if(b == null)
                                        return;

                                    LightningStrike strike = player.getWorld().strikeLightning(b.getLocation());
                                    strike.setCustomName("TrxshLightningStrike");

                                }

                            }, 20 * 2L);

                        }

                    }, 20 * 2L);

                }

            });

        }

    }

    @Override
    public void doPassive(Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 0));

    }
}
