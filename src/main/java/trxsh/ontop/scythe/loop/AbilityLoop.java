package trxsh.ontop.scythe.loop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import trxsh.ontop.scythe.Main;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.utility.ScytheUtility;

public class AbilityLoop {

    public static boolean running = false;

    public static void start() {

        running = true;
        loop();

    }

    public static void loop() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.Instance, new Runnable() {

            @Override
            public void run() {

                if (running)
                    for (DataPlayer dp : PlayerData.playerList.values()) {

                        if (dp.isOnline() && dp.getPlayer() != null && dp.getPlayerId() != null) {

                            Player player = dp.getPlayer();

                            if (player == null)
                                return;

                            ItemStack mainHand = player.getInventory().getItem(EquipmentSlot.HAND);
                            ItemStack offHand = player.getInventory().getItem(EquipmentSlot.OFF_HAND);

                            assert mainHand != null;
                            if (mainHand.getItemMeta() != null) {

                                if (mainHand.getItemMeta().hasDisplayName()) {

                                    Scythe scythe = ScytheUtility.getScytheByStack(mainHand);

                                    if (scythe != null) {

                                        scythe.doPassive(player);
                                        ScytheUtility.spawnCustomParticles(player);

                                    }

                                }

                            }

                            assert offHand != null;
                            if (offHand.getItemMeta() != null) {

                                if (offHand.getItemMeta().hasDisplayName()) {

                                    Scythe scythe = ScytheUtility.getScytheByStack(offHand);

                                    if (scythe != null) {

                                        scythe.doPassive(player);
                                        ScytheUtility.spawnCustomParticles(player);

                                    }

                                }

                            }

                        }

                    }

            }

        }, 30L, 30L);

    }

}
