package trxsh.ontop.scythe.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;

public class DamageEvent implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {

        if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player))
            return;

        DataPlayer player = PlayerData.playerList.get(e.getDamager().getUniqueId());

        if(player == null)
            return;

        Bukkit.broadcastMessage("added hit: " + player.getName());

        player.addHit();
        //ScytheUtility.handleHits() <-- Will add later

    }

}
