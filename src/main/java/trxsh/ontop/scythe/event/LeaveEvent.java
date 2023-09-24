package trxsh.ontop.scythe.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;

public class LeaveEvent implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {

        DataPlayer dp = null;
        Player p = e.getPlayer();

        if(!PlayerData.contains(p.getUniqueId())) {

            dp = new DataPlayer(p.getUniqueId());
            PlayerData.add(p.getUniqueId(), dp);

        } else {

            dp = PlayerData.playerList.get(p.getUniqueId());

        }

        dp.isOnline = false;
        dp.player = null;

    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {

        DataPlayer dp = null;
        Player p = e.getPlayer();

        if(!PlayerData.contains(p.getUniqueId())) {

            dp = new DataPlayer(p.getUniqueId());
            PlayerData.add(p.getUniqueId(), dp);

        } else {

            dp = PlayerData.playerList.get(p.getUniqueId());

        }

        dp.isOnline = false;
        dp.player = null;

    }

}
