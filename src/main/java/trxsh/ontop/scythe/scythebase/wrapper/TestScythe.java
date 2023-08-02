package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;
import trxsh.ontop.scythe.utility.ScytheUtility;

public class TestScythe extends Scythe {

    public TestScythe(String name, String key, String description, ScytheType type) {
        super(name, key, description, type);
    }

    @Override
    public void doAbility(Player player) {

        Bukkit.broadcastMessage("test ability");

    }

    @Override
    public void doPassive(Player player) {

        ScytheUtility.spawnCustomParticles(player);

        Bukkit.broadcastMessage("test passive");

    }
}
