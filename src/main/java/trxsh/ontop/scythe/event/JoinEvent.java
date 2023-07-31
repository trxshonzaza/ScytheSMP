package trxsh.ontop.scythe.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        DataPlayer dp = null;
        Player p = e.getPlayer();

        if(PlayerData.contains(p.getUniqueId()))
            dp = PlayerData.playerList.get(p.getUniqueId());
        else {

            dp = new DataPlayer(p.getUniqueId());
            PlayerData.add(p.getUniqueId(), dp);

        }

        

    }

}
