package trxsh.ontop.scythe.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;
import trxsh.ontop.scythe.utility.FakePlayerUtility;
import trxsh.ontop.scythe.utility.ScytheUtility;

public class DamageEvent implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {

        if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) {

            if(e.getDamager() instanceof LightningStrike) {

                LightningStrike strike = (LightningStrike) e.getDamager();

                assert strike.getCustomName() != null;
                if(strike.getCustomName().equals("TrxshLightningStrike")) {

                    if(e.getEntity() instanceof LivingEntity) {

                        e.setDamage(5 + e.getFinalDamage());

                    }

                }

            }

            return;

        }

        if(FakePlayerUtility.isFake((LivingEntity) e.getEntity()))
            return;

        if(FakePlayerUtility.isFake((LivingEntity) e.getDamager()))
            return;

        DataPlayer player = PlayerData.playerList.get(e.getDamager().getUniqueId());

        if(player.can2X)
            e.setDamage(e.getFinalDamage() * 2);

        if(player == null)
            return;

        Bukkit.broadcastMessage("added hit: " + player.getName());

        player.addHit();

        ItemStack attackedWith = player.getPlayer().getInventory().getItemInMainHand();

        Scythe scythe = ScytheUtility.getScytheByStack(attackedWith);

        if(scythe != null)
            if(scythe.getType() == ScytheType.FROSTBITE && player.getHits() >= 5)
                e.getEntity().setFreezeTicks(80);
        else if(scythe.getType() == ScytheType.SPECTRAL && player.getHits() >= 5)
                ((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 5, 1));

        if(player.getHits() >= 5)
            player.resetHits();

    }

}
