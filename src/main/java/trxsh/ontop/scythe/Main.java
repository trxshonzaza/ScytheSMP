package trxsh.ontop.scythe;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import trxsh.ontop.scythe.command.ResourceRequest;
import trxsh.ontop.scythe.command.operator.OpenScythes;
import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;
import trxsh.ontop.scythe.event.*;
import trxsh.ontop.scythe.file.FileManager;
import trxsh.ontop.scythe.file.wrapper.PlayerFileManager;
import trxsh.ontop.scythe.loop.AbilityLoop;
import trxsh.ontop.scythe.loop.BlockLoop;
import trxsh.ontop.scythe.loop.CooldownLoop;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    /*
    -------------------------------------------------------------------------------
    made for acetral and SMPDuels
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
                "   ╚══ ██╔══╝██╔══██╗╚██╗██╔╝██╔════╝██║  ██║      ████╗ ████║██╔══██╗██╔══██╗██╔════╝ ╚══██╔══╝██║  ██║██║██╔════╝\n" +
                "       ██║   ██████╔╝ ╚███╔╝ ███████╗███████║█████╗██╔████╔██║███████║██║  ██║█████╗█████╗██║   ███████║██║███████╗\n" +
                "       ██║   ██╔══██╗ ██╔██╗ ╚════██║██╔══██║╚════╝██║╚██╔╝██║██╔══██║██║  ██║██╔══╝╚════╝██║   ██╔══██║██║╚════██║\n" +
                "       ██║   ██║  ██║██╔╝ ██╗███████║██║  ██║      ██║ ╚═╝ ██║██║  ██║██████╔╝███████╗    ██║   ██║  ██║██║███████║\n" +
                "       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝      ╚═╝     ╚═╝╚═╝  ╚═╝╚═════╝ ╚══════╝    ╚═╝   ╚═╝  ╚═╝╚═╝╚══════╝\n");

        Bukkit.getLogger().info("enabling plugin (prod trxsh 2.0#1988)");

        Bukkit.getPluginManager().registerEvents(new InteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DamageEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LeaveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ResourceStatusEvent(), this);

        Bukkit.getPluginCommand("loadresources").setExecutor(new ResourceRequest());

        // Operator commands

        Bukkit.getPluginCommand("scythes").setExecutor(new OpenScythes());

        if(!getDataFolder().exists())
            getDataFolder().mkdir();

        pl = new PlayerFileManager(new File(getDataFolder(), "player.sav"));

        try {

            Bukkit.getLogger().info("loading data");

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

            } else {

                dp = PlayerData.playerList.get(p.getUniqueId());

            }

            dp.setData(p);

        }

        Instance = this;

        Bukkit.getLogger().info("plugin is now enabled. starting loops (prod trxsh 2.0#1988)");

        AbilityLoop.start();
        CooldownLoop.start();
        BlockLoop.start();

        Bukkit.getLogger().info("loops started. (prod trxsh 2.0#1988)\n" +
                "\n\n-------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("-------------------------------------------------------------------------------------------------------------------------\n\n" +
                "disabling plugin (prod trxsh 2.0#1988)");

        AbilityLoop.running = false;
        CooldownLoop.running = false;
        BlockLoop.running = false;

        pl = new PlayerFileManager(new File(getDataFolder(), "player.sav"));

        try {

            Bukkit.getLogger().info("saving data");

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
