package trxsh.ontop.scythe.utility;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import trxsh.ontop.scythe.data.player.DataPlayer;
import trxsh.ontop.scythe.scythebase.Scythe;

public class PlayerUtility {

    public static boolean hasScytheInHand(DataPlayer dp) {

        if (dp.isOnline() && dp.getPlayer() != null && dp.getPlayerId() != null) {

            Player player = dp.getPlayer();

            if (player == null)
                return false;

            ItemStack mainHand = player.getInventory().getItem(EquipmentSlot.HAND);
            ItemStack offHand = player.getInventory().getItem(EquipmentSlot.OFF_HAND);

            assert mainHand != null;
            if (mainHand.getItemMeta() != null) {

                if (mainHand.getItemMeta().hasDisplayName()) {

                    Scythe scythe = ScytheUtility.getScytheByStack(mainHand);

                    if (scythe != null)
                        return true;

                }

            }

            assert offHand != null;
            if (offHand.getItemMeta() != null) {

                if (offHand.getItemMeta().hasDisplayName()) {

                    Scythe scythe = ScytheUtility.getScytheByStack(offHand);

                    if (scythe != null)
                        return true;

                }

            }

        }

        return false;

    }

}
