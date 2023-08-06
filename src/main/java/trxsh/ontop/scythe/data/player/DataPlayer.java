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

    public int hits = 0;

    public boolean can2X = false;

    public DataPlayer(UUID id) {

        OfflinePlayer player = Bukkit.getOfflinePlayer(id);

        if(player.isOnline()) {

            isOnline = true;
            this.player = player.getPlayer();

        }

        this.playerId = id;
        this.name = player.getName();

    }

    public void setData(Player player) {

        if(player.isOnline()) {

            isOnline = true;
            this.player = player;

        }

        this.playerId = player.getUniqueId();
        this.name = player.getName();

    }

    public String getName() {

        return name;

    }

    public boolean isOnline() {

        return isOnline;

    }

    public UUID getPlayerId() {

        return playerId;

    }

    public Player getPlayer() {

        return player;

    }

    public void addHit() {

        hits++;

    }

    public void subtractHit() {

        hits--;

    }

    public void resetHits() {

        hits = 0;

    }

    public int getHits() {

        return hits;

    }

}
