package trxsh.ontop.scythe.event;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class ResourceStatusEvent implements Listener {

    @EventHandler
    public void onDecline(PlayerResourcePackStatusEvent e) {

        if(e.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {

            e.getPlayer().sendMessage(ChatColor.RED + "You declined the resource pack.");

        }

        if(e.getStatus() == PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD) {

            e.getPlayer().sendMessage(ChatColor.RED + "The download failed.");

        }

        if(e.getStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {

            e.getPlayer().sendMessage(ChatColor.GREEN + "The resource pack loaded successfully. have fun!");

        }

    }

}
