package trxsh.ontop.scythe.utility;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MathUtility {

    public static boolean isLookingAtPlayer(Player pl, double radius) {

        List<Player> players = new ArrayList();

        for(Entity e : pl.getNearbyEntities(radius, radius, radius))
            if(e instanceof Player)
                players.add((Player) e);

        if(players.isEmpty()) {

            //Bukkit.broadcastMessage("no players are nearby or you are not looking at a player!");
            return false;

        }

        for(Player p : players) {

            Location playerEye = pl.getEyeLocation();
            Vector facing = p.getEyeLocation().toVector().subtract(playerEye.toVector());
            double dot = facing.normalize().dot(playerEye.getDirection());

            if (dot > .50D) {

                //Bukkit.broadcastMessage("player was looking at " + p.getName());
                return true;

            }

        }

        return false;

    }

    public static Player getPlayerLookingAt(Player pl, double radius) {

        List<Player> players = new ArrayList();

        for(Entity e : pl.getNearbyEntities(radius, radius, radius))
            if(e instanceof Player)
                players.add((Player) e);

        if(players.isEmpty()) {

            //Bukkit.broadcastMessage("no players are nearby or you are not looking at a player!");
            return null;

        }

        for(Player p : players) {

            Location playerEye = pl.getEyeLocation();
            Vector facing = p.getEyeLocation().toVector().subtract(playerEye.toVector());
            double dot = facing.normalize().dot(playerEye.getDirection());

            if (dot > .50D) {

                //Bukkit.broadcastMessage("player was looking at " + p.getName());
                return p;

            }

        }

        return null;

    }

    public static boolean isPlayersNearby(Player player, double radius) {

        List<Player> players = new ArrayList();

        for(Entity e : player.getNearbyEntities(radius, radius, radius))
            if(e instanceof Player)
                players.add((Player) e);

        return !players.isEmpty();

    }

    public static List<Player> getPlayersNearby(Player player, double radius) {

        List<Player> players = new ArrayList();

        for(Entity e : player.getNearbyEntities(radius, radius, radius))
            if(e instanceof Player)
                players.add((Player) e);

        if(players.isEmpty())
            return null;
        else
            return players;

    }

    public static boolean isLookingAtEntity(Player pl, double radius) {

        List<LivingEntity> entities = new ArrayList();

        for(Entity e : pl.getNearbyEntities(radius, radius, radius))
            if(e instanceof LivingEntity)
                entities.add((LivingEntity) e);

        if(entities.isEmpty()) {

            //Bukkit.broadcastMessage("no entities are nearby or you are not looking at a entity!");
            return false;

        }

        for(LivingEntity e : entities) {

            Location playerEye = pl.getEyeLocation();
            Vector facing = e.getEyeLocation().toVector().subtract(playerEye.toVector());
            double dot = facing.normalize().dot(playerEye.getDirection());

            if (dot > .50D) {

                //Bukkit.broadcastMessage("player was looking at " + e.getName());
                return true;

            }

        }

        return false;

    }

    public static boolean isEntitiesNearby(Player player, double radius) {

        List<Entity> entities = new ArrayList();

        for(Entity e : player.getNearbyEntities(radius, radius, radius))
            entities.add(e);

        return !entities.isEmpty();

    }

    public static List<Entity> getEntitiesNearby(Player player, double radius) {

        List<Entity> entities = new ArrayList();

        for(Entity e : player.getNearbyEntities(radius, radius, radius))
            entities.add(e);

        if(entities.isEmpty())
            return null;
        else
            return entities;

    }

    public static LivingEntity getEntityLookingAt(Player pl, double radius) {

        List<LivingEntity> entities = new ArrayList();

        for(Entity e : pl.getNearbyEntities(radius, radius, radius))
            if(e instanceof LivingEntity)
                entities.add((LivingEntity) e);

        if(entities.isEmpty()) {

            //Bukkit.broadcastMessage("no entities are nearby or you are not looking at a entity!");
            return null;

        }

        for(LivingEntity e : entities) {

            Location playerEye = pl.getEyeLocation();
            Vector facing = e.getEyeLocation().toVector().subtract(playerEye.toVector());
            double dot = facing.normalize().dot(playerEye.getDirection());

            if (dot > .50D) {

                //Bukkit.broadcastMessage("player was looking at " + e.getName());
                return e;

            }

        }

        return null;

    }

    public static Block getSolidBlockLookingAt(Player pl, int radius) {

        Block b = pl.getTargetBlock(null, radius);

        if(b == null || b.getType() == Material.AIR)
            return null;
        else
            return b;

    }

    public static Block getBlockLookingAt(Player pl, int radius) {

        return pl.getTargetBlock(null, radius);

    }

}

