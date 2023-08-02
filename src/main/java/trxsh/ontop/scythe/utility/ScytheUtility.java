package trxsh.ontop.scythe.utility;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import trxsh.ontop.scythe.data.ScytheData;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;

public class ScytheUtility {

    public static Scythe getScythe(ScytheType type) {

        for(Scythe scythe : ScytheData.getScythes())
            if(scythe.getType() == type)
                return scythe;

        return null;

    }

    public static Scythe getScytheByName(String name) {

        for(Scythe scythe : ScytheData.getScythes())
            if(scythe.getName().equalsIgnoreCase(name))
                return scythe;

        return null;

    }

    public static Scythe getScytheByStack(ItemStack item) {

        for(Scythe scythe : ScytheData.getScythes())
            if(scythe.getItem().isSimilar(item))
                return scythe;

        return null;

    }

    public static void spawnCustomParticles(Player p) {

        Location playerLocation = p.getLocation();

        Particle particleType = Particle.SPELL_MOB;

        int circlePoints = 12;

        for (int i = 0; i < circlePoints; i++) {

            double angle = (2 * Math.PI * i) / circlePoints;

            double xOffset = Math.cos(angle) * 1.5;
            double zOffset = Math.sin(angle) * 1.5;

            p.getWorld().spawnParticle(particleType, playerLocation.getX() + xOffset,
                    playerLocation.getY(), playerLocation.getZ() + zOffset, 0, Color.RED);

        }

    }

}
