package trxsh.ontop.scythe.utility;

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

}
