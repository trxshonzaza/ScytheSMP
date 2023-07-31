package trxsh.ontop.scythe.data.player;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DataPlayer {

    public String name;

    public boolean isOnline = false;

    public UUID playerId;

    public Player player;

    public DataPlayer(UUID id) {

        OfflinePlayer player = Bukkit.getOfflinePlayer(id);

        if(player.isOnline()) {

            isOnline = true;
            this.player = player.getPlayer();

        }

        this.playerId = id;
        this.name = player.getName();

    }

}
