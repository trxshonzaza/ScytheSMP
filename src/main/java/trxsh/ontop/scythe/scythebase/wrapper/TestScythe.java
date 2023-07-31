package trxsh.ontop.scythe.scythebase.wrapper;

import org.bukkit.entity.Player;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;

public class TestScythe extends Scythe {

    public TestScythe(String name, String key, ScytheType type) {
        super(name, key, type);
    }

    @Override
    public void doAbility(Player player) {



    }

    @Override
    public void doPassive(Player player) {

    }
}
