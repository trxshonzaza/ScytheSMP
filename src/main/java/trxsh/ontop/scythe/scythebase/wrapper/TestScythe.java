package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;
import trxsh.ontop.scythe.utility.ScytheUtility;

import java.util.HashMap;

/**
 * @deprecated Test scythe. Not Needed.
 */
@Deprecated
public class TestScythe extends Scythe {

    public TestScythe(String name, String key, String description, ScytheType type, HashMap<Enchantment, Integer> enchantments) {
        super(name, key, description, type, enchantments);
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
