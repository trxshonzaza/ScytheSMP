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
import trxsh.ontop.scythe.utility.PlayerUtility;

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

                            if(PlayerUtility.hasScytheInHand(p))
                                if(CooldownData.hasCooldown(player.getUniqueId()))
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("⚡" + " | " + ChatColor.RED + "On Cooldown (" + CooldownData.getRemainingDuration(player.getUniqueId()) / 1000 + "s)"));
                                else
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("⚡" + " | " + ChatColor.GREEN + "Ready"));

                        }

                    }

            }

        }, 30L, 30L);

    }

}
