package trxsh.ontop.scythe.scythebase;

import org.bukkit.entity.Player;

public abstract class Scythe {

    public String key;
    public String name;
    public ScytheType type;

    public Scythe(String name, String key, ScytheType type) {

        this.key = key;
        this.name = name;
        this.type = type;

    }

    public String getName() {

        return name;

    }

    public String getKey() {

        return key;

    }

    public ScytheType getType() {

        return type;

    }

    public abstract void doAbility(Player player);
    public abstract void doPassive(Player player);

}
