package trxsh.ontop.scythe.data;

import java.util.HashMap;
import java.util.UUID;

public class OrbData {

    public static HashMap<UUID, Integer> orbLevels = new HashMap<>();

    public static void add(UUID id) {

        orbLevels.put(id, 0);

    }

    public static void remove(UUID id) {

        orbLevels.remove(id);

    }

    public static boolean contains(UUID id) {

        return orbLevels.containsKey(id);

    }

    public static void replace(UUID id, int level) {

        orbLevels.put(id, level);

    }

}
