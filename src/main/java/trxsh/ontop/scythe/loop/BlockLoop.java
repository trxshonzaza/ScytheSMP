package trxsh.ontop.scythe.loop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import trxsh.ontop.scythe.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BlockLoop {

    public static boolean running = false;

    public static HashMap<Location, UUID> damageLocations = new HashMap<>();

    public static void start() {

        running = true;
        loop();

    }

    public static void loop() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.Instance, new Runnable() {

            @Override
            public void run() {

                if(running)
                    for(Location l : damageLocations.keySet()) {

                        UUID ownerId = damageLocations.get(l);

                        Player owner = Bukkit.getPlayer(ownerId);

                        if(owner == null) {

                            damageLocations.remove(l);
                            continue;

                        }

                        List<LivingEntity> entities = new ArrayList<>();

                        for(Entity e : owner.getWorld().getNearbyEntities(l, 1, 1, 1))
                            if(e instanceof LivingEntity)
                                entities.add((LivingEntity) e);

                        for(LivingEntity e : entities)
                            if(!e.getUniqueId().equals(ownerId))
                                e.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 5, 0));

                    }

            }

        }, 30L, 30L);

    }

    public static void addBlockToLoop(UUID owner, Location l) {

        damageLocations.put(l, owner);

    }

    public static void removeBlockFromLoop(Location l) {

        damageLocations.remove(l);

    }

}
