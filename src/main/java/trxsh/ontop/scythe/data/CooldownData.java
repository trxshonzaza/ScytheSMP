package trxsh.ontop.scythe.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CooldownData {

    public static List<UUID> cooldowns = new ArrayList<>();

    public static void add(UUID id) {

        cooldowns.add(id);

    }

    public static void remove(UUID id) {

        cooldowns.remove(id);

    }

    public static boolean contains(UUID id) {

        return cooldowns.contains(id);

    }

}
