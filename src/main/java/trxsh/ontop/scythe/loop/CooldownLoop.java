package trxsh.ontop.scythe.loop;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import trxsh.ontop.scythe.Main;
import trxsh.ontop.scythe.data.CooldownData;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;

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

                            if(CooldownData.contains(player.getUniqueId()))
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("⚡" + " | " + ChatColor.RED + "On Cooldown"));
                            else
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("⚡" + " | " + ChatColor.GREEN + "Ready"));

                        }

                    }

            }

        }, 30L, 30L);

    }

    /*
    NOTE: THE DELAY FOR THE COOLDOWN IS IN TICKS!!!!!
    DOING THIS METHOD WRONG WILL RESULT IN PLUGIN ISSUES!!
    CALCULATION: (seconds) * 20
     */

    public static void runCooldown(UUID playerId, long ticks) {

        CooldownData.add(playerId);

        Bukkit.getScheduler().runTaskLater(Main.Instance, new Runnable() {

            @Override
            public void run() {

                if(CooldownData.contains(playerId))
                    CooldownData.remove(playerId);

            }

        }, ticks);

    }

}
