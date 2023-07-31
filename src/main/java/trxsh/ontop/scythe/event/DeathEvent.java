package trxsh.ontop.scythe.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import trxsh.ontop.scythe.utility.OrbUtility;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        if(event.getEntity().getKiller() == null)
            return;

        Player killed = event.getEntity();
        Player killer = event.getEntity().getKiller();

        assert killer != null;
        OrbUtility.increaseLevel(killer.getUniqueId());
        OrbUtility.decreaseLevel(killed.getUniqueId());

        int killerLevel = OrbUtility.getOrbLevel(killer.getUniqueId());
        int killedLevel = OrbUtility.getOrbLevel(killed.getUniqueId());

        Bukkit.broadcastMessage("player killed, kllr: " + killerLevel + ", klld: " + killedLevel);

        killed.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have been killed! your new orb level is " + killedLevel);
        killer.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You have killed a player! your new orb level is " + killerLevel);

        if(killedLevel < -5) {

            Bukkit.broadcastMessage("player should be banned");
            //ban code here, still working on that

        }

    }

}
