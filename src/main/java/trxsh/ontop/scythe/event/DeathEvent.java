package trxsh.ontop.scythe.event;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import trxsh.ontop.scythe.data.BanData;
import trxsh.ontop.scythe.utility.FakePlayerUtility;
import trxsh.ontop.scythe.utility.ItemUtility;
import trxsh.ontop.scythe.utility.OrbUtility;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        if(FakePlayerUtility.isFake(event.getEntity())) {

            return;

        }

        if(event.getEntity().getKiller() == null)
            return;

        Player killed = event.getEntity();
        Player killer = event.getEntity().getKiller();

        assert killer != null;
        OrbUtility.increaseLevel(killer.getUniqueId());
        OrbUtility.decreaseLevel(killed.getUniqueId());

        int killerLevel = OrbUtility.getOrbLevel(killer.getUniqueId());
        int killedLevel = OrbUtility.getOrbLevel(killed.getUniqueId());

        killed.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have been killed! your new orb level is " + killedLevel);
        killer.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You have killed a player! your new orb level is " + killerLevel);

        /* TODO: Make PlayerRespawnEvent for this line of code
        for(ItemStack item : killed.getInventory())
            for(Scythe scythe : ScytheData.getScythes())
                if(item.isSimilar(scythe.getItem())) {

                    killed.getInventory().remove(item);

                    killed.sendMessage(ChatColor.RED + "You have lost your scythe(s) as a result of dying.");

                }*/

        if(killedLevel < -5) {

            Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + killed.getName() + " was death-banned.");

            BanData.add(killed.getUniqueId());

            OrbUtility.setOrbLevel(killed.getUniqueId(), 0);

            killed.kickPlayer(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You are death-banned.\n" +
                    ChatColor.AQUA + "Get a friend to revive you!");

            Bukkit.getBanList(BanList.Type.NAME).addBan(killed.getName(), ChatColor.DARK_RED + "" + ChatColor.BOLD + "You are death-banned.\n" +
                    ChatColor.AQUA + "Get a friend to revive you!", null, "SCYTHE-PLUGIN");

        }

        if(killerLevel > 5) {

            killer.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Your orb level exceeds the maximum amount! you have earned a Scythe Token.");
            killer.getWorld().dropItem(killer.getLocation(), ItemUtility.getScytheTokenStack());

            OrbUtility.setOrbLevel(killer.getUniqueId(), 5);

        }

    }

    @EventHandler
    public void onNPCDeath(NPCDeathEvent e) {

        FakePlayerUtility.fakePlayers.remove(e.getNPC());
        e.getNPC().destroy();

    }

}
