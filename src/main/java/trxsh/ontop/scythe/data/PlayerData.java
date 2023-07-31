package trxsh.ontop.scythe.data;

import trxsh.ontop.scythe.data.player.DataPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerData {

    public static HashMap<UUID, DataPlayer> playerList = new HashMap<>();

    public static void add(UUID id) {

        playerList.put(id, new DataPlayer(id));

    }

    public static void add(UUID id, DataPlayer player) {

        playerList.put(id, player);

    }

    public static void remove(UUID id) {

        playerList.remove(id);

    }

    public static boolean contains(UUID id) {

        return playerList.containsKey(id);

    }

}
