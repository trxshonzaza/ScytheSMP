package trxsh.ontop.scythe.loop;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import trxsh.ontop.scythe.Main;
import trxsh.ontop.scythe.data.CooldownData;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;
import trxsh.ontop.scythe.scythebase.ScytheType;
import trxsh.ontop.scythe.utility.BossBars;
import trxsh.ontop.scythe.utility.PlayerUtility;
import trxsh.ontop.scythe.utility.ScytheUtility;

import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

public class CooldownLoop {

    public static boolean running = false;

    public static void start() {

        running = true;
        loop();

    }

    public static void loop() {

        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.Instance, new Runnable() {

            @Override
            public void run() {


                if(running)
                    for(DataPlayer p : PlayerData.playerList.values()) {

                        if(p.isOnline() && p.getPlayer() != null && p.getPlayerId() != null) {

                            Player player = p.getPlayer();

                            if(PlayerUtility.hasScytheInHand(p)) {

                                ScytheType type = Objects.requireNonNull(PlayerUtility.getScytheInHand(p)).getType();

                                if(CooldownData.hasCooldown(player.getUniqueId(), type))
                                    BossBars.showBossBar(player, "⚡" + " | " + ChatColor.RED + "On Cooldown (" + CooldownData.getRemainingDuration(player.getUniqueId(), type) / 1000 + "s)", false);
                                else
                                    BossBars.showBossBar(player, "⚡" + " | " + ChatColor.GREEN + "Ready", true);

                            }else {

                                for (Iterator<KeyedBossBar> it = Bukkit.getBossBars(); it.hasNext(); ) {

                                    BossBar bar = it.next();

                                    if(bar.getPlayers().contains(player))
                                        bar.removePlayer(player);

                                }

                            }

                        }

                    }

            }

        }, 30L, 30L);

    }

}
