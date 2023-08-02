package trxsh.ontop.scythe.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CooldownData {

    /*
    THE COOLDOWN DURATION IS IN MILLISECONDS!!!!
    CALCULATION: (seconds) * 1000
     */

    public static HashMap<UUID, Long> cooldowns = new HashMap<>();
    private static HashMap<UUID, Long> cooldownDurations = new HashMap<>();

    public static void add(UUID id, long duration) {

        cooldowns.put(id, System.currentTimeMillis() + duration);
        cooldownDurations.put(id, duration);

    }

    public static boolean hasCooldown(UUID id) {

        return cooldowns.containsKey(id) && System.currentTimeMillis() - cooldowns.get(id) < getCooldownDuration(id);

    }

    public static long getRemainingDuration(UUID id) {

        if (hasCooldown(id)) {

            long elapsedTime = System.currentTimeMillis() - cooldowns.get(id);

            return Math.max(cooldownDurations.get(id) - elapsedTime, 0);

        }

        return 0;

    }

    public static long getCooldownDuration(UUID id) {

        return cooldownDurations.getOrDefault(id, 0L);

    }

}
