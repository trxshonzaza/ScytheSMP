package trxsh.ontop.scythe.utility;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.ScytheData;
import trxsh.ontop.scythe.data.player.DataPlayer;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;

public class ScytheUtility {

    public static Scythe getScytheByStack(ItemStack item) {

        for(Scythe scythe : ScytheData.getScythes())
            if(scythe.getItem().isSimilar(item))
                return scythe;

        return null;

    }

    public static void spawnCustomParticles(Player p) {

        Location playerLocation = p.getLocation();

        Particle particleType = Particle.REDSTONE;

        int circlePoints = 20;

        for (int i = 0; i < circlePoints; i++) {

            double angle = (2 * Math.PI * i) / circlePoints;

            double xOffset = Math.cos(angle) * 1.5;
            double zOffset = Math.sin(angle) * 1.5;

            p.getWorld().spawnParticle(particleType, playerLocation.getX() + xOffset,
                    playerLocation.getY(), playerLocation.getZ() + zOffset, 0, 0, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));

        }

        DataPlayer dp = PlayerData.playerList.get(p.getUniqueId());

        if(dp == null)
            return;

        particleType = Particle.FLAME;

        if(dp.can2X) {

            circlePoints = 20;

            for (int i = 0; i < circlePoints; i++) {

                double angle = (2 * Math.PI * i) / circlePoints;

                double xOffset = Math.cos(angle) * .5;
                double zOffset = Math.sin(angle) * .5;

                p.getWorld().spawnParticle(particleType, playerLocation.getX() + xOffset,
                        playerLocation.getY() + 2D, playerLocation.getZ() + zOffset, 0, 0, 0, 0, 0);

            }

        }

    }

}
