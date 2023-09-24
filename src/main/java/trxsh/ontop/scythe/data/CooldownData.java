package trxsh.ontop.scythe.data;

import trxsh.ontop.scythe.scythebase.ScytheType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CooldownData {

    /*
    THE COOLDOWN DURATION IS IN MILLISECONDS!!!!
    CALCULATION: (seconds) * 500
     */

    private static HashMap<UUID, Long> infernoCooldowns = new HashMap<>();
    private static HashMap<UUID, Long> infernoCooldownDurations = new HashMap<>();

    private static HashMap<UUID, Long> spectralCooldowns = new HashMap<>();
    private static HashMap<UUID, Long> spectralCooldownDurations = new HashMap<>();

    private static HashMap<UUID, Long> strengthCooldowns = new HashMap<>();
    private static HashMap<UUID, Long> strengthCooldownDurations = new HashMap<>();

    public static void add(UUID id, long duration, ScytheType type) {

        switch(type) {

            case INFERNO:
                infernoCooldowns.put(id, System.currentTimeMillis() + duration);
                infernoCooldownDurations.put(id, duration);
                break;
            case SPECTRAL:
                spectralCooldowns.put(id, System.currentTimeMillis() + duration);
                spectralCooldownDurations.put(id, duration);
                break;
            case STRENGTH:
                strengthCooldowns.put(id, System.currentTimeMillis() + duration);
                strengthCooldownDurations.put(id, duration);
                break;

        }

    }

    public static boolean hasCooldown(UUID id, ScytheType type) {

        switch(type) {

            case INFERNO:
                return infernoCooldowns.containsKey(id) && System.currentTimeMillis() - infernoCooldownDurations.get(id) < getCooldownDuration(id, type);
            case SPECTRAL:
                return spectralCooldowns.containsKey(id) && System.currentTimeMillis() - spectralCooldownDurations.get(id) < getCooldownDuration(id, type);
            case STRENGTH:
                return strengthCooldowns.containsKey(id) && System.currentTimeMillis() - strengthCooldownDurations.get(id) < getCooldownDuration(id, type);


        }

        return false;

    }

    public static long getRemainingDuration(UUID id, ScytheType type) {

        switch(type) {

            case INFERNO:
                if (hasCooldown(id, type)) {

                    long elapsedTime = System.currentTimeMillis() - infernoCooldowns.get(id);

                    return Math.max(infernoCooldownDurations.get(id) - elapsedTime, 0);

                }
            case SPECTRAL:
                if (hasCooldown(id, type)) {

                    long elapsedTime = System.currentTimeMillis() - spectralCooldowns.get(id);

                    return Math.max(spectralCooldownDurations.get(id) - elapsedTime, 0);

                }
            case STRENGTH:
                if (hasCooldown(id, type)) {

                    long elapsedTime = System.currentTimeMillis() - strengthCooldowns.get(id);

                    return Math.max(strengthCooldownDurations.get(id) - elapsedTime, 0);

                }

        }

        return 0;

    }

    public static long getCooldownDuration(UUID id, ScytheType type) {

        switch(type) {

            case INFERNO:
                return infernoCooldownDurations.getOrDefault(id, 0L);
            case SPECTRAL:
                return spectralCooldownDurations.getOrDefault(id, 0L);
            case STRENGTH:
                return strengthCooldownDurations.getOrDefault(id, 0L);

        }

        return 0;

    }

}
