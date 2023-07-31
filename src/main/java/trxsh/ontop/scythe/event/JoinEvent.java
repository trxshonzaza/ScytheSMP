package trxsh.ontop.scythe.event;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import trxsh.ontop.scythe.data.OrbData;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;
import trxsh.ontop.scythe.utility.OrbUtility;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        DataPlayer dp = null;
        Player p = e.getPlayer();

        if(!PlayerData.contains(p.getUniqueId())) {

            dp = new DataPlayer(p.getUniqueId());
            PlayerData.add(p.getUniqueId(), dp);
            OrbData.add(p.getUniqueId());

        } else {

            dp = PlayerData.playerList.get(p.getUniqueId());

        }

        dp.setData(e.getPlayer());

        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
        p.sendMessage(ChatColor.AQUA + "Welcome back :)");
        p.sendMessage(ChatColor.GRAY + "Your orb level is: " + ChatColor.LIGHT_PURPLE + OrbUtility.getOrbLevel(p.getUniqueId()));

    }

}
