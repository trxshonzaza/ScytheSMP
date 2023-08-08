package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import trxsh.ontop.scythe.data.CooldownData;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;
import trxsh.ontop.scythe.utility.MathUtility;

import java.util.HashMap;
import java.util.UUID;

public class EnderScythe extends Scythe {

    public EnderScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
    }

    @Override
    public void doAbility(Player player) {

        Block b = MathUtility.getSolidBlockLookingAt(player, 50);

        if(b == null)
            return;

        CooldownData.add(player.getUniqueId(), 15000);

        Location targetLocation = b.getLocation().clone().add(0, 1, 0);

        targetLocation.setPitch(player.getLocation().getPitch());
        targetLocation.setYaw(player.getLocation().getYaw());

        player.teleport(targetLocation);

        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

    }

    @Override
    public void doPassive(Player player) {

    }

    public void doOtherAbility(Player player) {

        DataPlayer dp = PlayerData.playerList.get(player.getUniqueId());

        if(dp == null)
            return;

        UUID hitId = dp.getLastHit();

        if(hitId == null) {

            player.sendMessage(ChatColor.RED + "Last hit player identity does not exist.");
            return;

        }

        Player lastHit = Bukkit.getPlayer(hitId);

        if(lastHit != null) {

            if(lastHit.isOnline()) {

                CooldownData.add(player.getUniqueId(), 30000);

                Location lastHitLocation = lastHit.getLocation();

                lastHitLocation.setPitch(player.getLocation().getPitch());
                lastHitLocation.setYaw(player.getLocation().getYaw());

                player.teleport(lastHitLocation);

                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

            }else {

                player.sendMessage(ChatColor.RED + "Last hit player is not online.");

            }

        }else {

            player.sendMessage(ChatColor.RED + "Last hit player does not exist.");

        }

    }
}
