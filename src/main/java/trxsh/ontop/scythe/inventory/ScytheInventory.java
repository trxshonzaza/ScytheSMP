package trxsh.ontop.scythe.inventory;

import org.bukkit.inventory.Inventory;
import trxsh.ontop.scythe.data.ScytheData;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.utility.ItemUtility;

public class ScytheInventory extends CustomInventory {

    public ScytheInventory(String name) {
        super(name);
    }

    @Override
    public Inventory getInventory() {

        setInventory(createInventory(18));

        for(Scythe scythe : ScytheData.getScythes()) {

            addItem(scythe.getItem());

        }

        addItem(ItemUtility.getBanMenuStack());

        return loadInventory();

    }
}
