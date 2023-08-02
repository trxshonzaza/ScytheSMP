package trxsh.ontop.scythe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import trxsh.ontop.scythe.command.OrbLevelCommand;
import trxsh.ontop.scythe.command.TestCommand;
import trxsh.ontop.scythe.data.OrbData;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;
import trxsh.ontop.scythe.event.*;
import trxsh.ontop.scythe.file.FileManager;
import trxsh.ontop.scythe.file.wrapper.BanFileManager;
import trxsh.ontop.scythe.file.wrapper.OrbFileManager;
import trxsh.ontop.scythe.file.wrapper.PlayerFileManager;
import trxsh.ontop.scythe.loop.AbilityLoop;
import trxsh.ontop.scythe.loop.CooldownLoop;
import trxsh.ontop.scythe.utility.OrbUtility;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    /*
    -------------------------------------------------------------------------------
    made for acetral
    coded by trxsh 2.0#1988
    no recoding or redistribution of this without developer or owners approval

    my github
    https://github.com/trxshonzaza

    ████████╗██████╗ ██╗  ██╗███████╗██╗  ██╗      ███╗   ███╗ █████╗ ██████╗ ███████╗ ████████╗██╗  ██╗██╗███████╗
    ╚══██╔══╝██╔══██╗╚██╗██╔╝██╔════╝██║  ██║      ████╗ ████║██╔══██╗██╔══██╗██╔════╝ ╚══██╔══╝██║  ██║██║██╔════╝
       ██║   ██████╔╝ ╚███╔╝ ███████╗███████║█████╗██╔████╔██║███████║██║  ██║█████╗█████╗██║   ███████║██║███████╗
       ██║   ██╔══██╗ ██╔██╗ ╚════██║██╔══██║╚════╝██║╚██╔╝██║██╔══██║██║  ██║██╔══╝╚════╝██║   ██╔══██║██║╚════██║
       ██║   ██║  ██║██╔╝ ██╗███████║██║  ██║      ██║ ╚═╝ ██║██║  ██║██████╔╝███████╗    ██║   ██║  ██║██║███████║
       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝      ╚═╝     ╚═╝╚═╝  ╚═╝╚═════╝ ╚══════╝    ╚═╝   ╚═╝  ╚═╝╚═╝╚══════╝
    -------------------------------------------------------------------------------
     */

    public static Main Instance = null;

    public FileManager ba;
    public FileManager or;
    public FileManager pl;

    @Override
    public void onEnable() {

        Bukkit.getLogger().info(
                "-------------------------------------------------------------------------------------------------------------------------\n\n" +
                "    coded by trxsh 2.0#1988\n" +
                "    no recoding or redistribution of this without developer or owners approval\n" +
                "\n" +
                "    my github\n" +
                "    https://github.com/trxshonzaza\n" +
                "\n" +
                "    ████████╗██████╗ ██╗  ██╗███████╗██╗  ██╗      ███╗   ███╗ █████╗ ██████╗ ███████╗ ████████╗██╗  ██╗██╗███████╗\n" +
                "    ╚══██╔══╝██╔══██╗╚██╗██╔╝██╔════╝██║  ██║      ████╗ ████║██╔══██╗██╔══██╗██╔════╝ ╚══██╔══╝██║  ██║██║██╔════╝\n" +
                "       ██║   ██████╔╝ ╚███╔╝ ███████╗███████║█████╗██╔████╔██║███████║██║  ██║█████╗█████╗██║   ███████║██║███████╗\n" +
                "       ██║   ██╔══██╗ ██╔██╗ ╚════██║██╔══██║╚════╝██║╚██╔╝██║██╔══██║██║  ██║██╔══╝╚════╝██║   ██╔══██║██║╚════██║\n" +
                "       ██║   ██║  ██║██╔╝ ██╗███████║██║  ██║      ██║ ╚═╝ ██║██║  ██║██████╔╝███████╗    ██║   ██║  ██║██║███████║\n" +
                "       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝      ╚═╝     ╚═╝╚═╝  ╚═╝╚═════╝ ╚══════╝    ╚═╝   ╚═╝  ╚═╝╚═╝╚══════╝\n");

        Bukkit.getLogger().info("enabling plugin (prod trxsh 2.0#1988)");

        Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DamageEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LeaveEvent(), this);

        Bukkit.getPluginCommand("tester").setExecutor(new TestCommand());
        Bukkit.getPluginCommand("orblevel").setExecutor(new OrbLevelCommand());

        ba = new BanFileManager(new File("ban.sav"));
        or = new OrbFileManager(new File("orb.sav"));
        pl = new PlayerFileManager(new File("player.sav"));

        try {

            Bukkit.getLogger().info("loading data");

            if(ba.exists())
                ba.load();
            if(or.exists())
                or.load();
            if(pl.exists())
                pl.load();

            Bukkit.getLogger().info("loaded data");

        }catch(IOException e) {

            Bukkit.getLogger().warning("failed to load file, i/o error");
            e.printStackTrace();

        }catch(Exception e) {

            Bukkit.getLogger().warning("failed to load file, general error");
            e.printStackTrace();

        }

        for(Player p : Bukkit.getOnlinePlayers()) {

            DataPlayer dp = null;

            if(!PlayerData.contains(p.getUniqueId())) {

                dp = new DataPlayer(p.getUniqueId());
                PlayerData.add(p.getUniqueId(), dp);
                OrbData.add(p.getUniqueId());

            } else {

                dp = PlayerData.playerList.get(p.getUniqueId());

            }

            dp.setData(p);

            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            p.sendMessage(ChatColor.AQUA + "Plugin successfully reloaded. welcome back :)");
            p.sendMessage(ChatColor.GRAY + "Your orb level is: " + ChatColor.LIGHT_PURPLE + OrbUtility.getOrbLevel(p.getUniqueId()));

        }

        Instance = this;

        Bukkit.getLogger().info("plugin is now enabled. starting loops (prod trxsh 2.0#1988)");

        AbilityLoop.start();
        CooldownLoop.start();

        Bukkit.getLogger().info("loops started. (prod trxsh 2.0#1988)\n" +
                "\n\n-------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("-------------------------------------------------------------------------------------------------------------------------\n\n" +
                "disabling plugin (prod trxsh 2.0#1988)");

        ba = new BanFileManager(new File("ban.sav"));
        or = new OrbFileManager(new File("orb.sav"));
        pl = new PlayerFileManager(new File("player.sav"));

        try {

            Bukkit.getLogger().info("saving data");

            ba.save();
            or.save();
            pl.save();

            Bukkit.getLogger().info("saved data");

        }catch(IOException e) {

            Bukkit.getLogger().warning("failed to save file, i/o error");
            e.printStackTrace();

        }catch(Exception e) {

            Bukkit.getLogger().warning("failed to save file, general error");
            e.printStackTrace();

        }

        Bukkit.getLogger().info("plugin is disabled (prod trxsh 2.0#1988)" +
                "\n\n-------------------------------------------------------------------------------------------------------------------------");

    }

}
