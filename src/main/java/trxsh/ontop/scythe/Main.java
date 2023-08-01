package trxsh.ontop.scythe;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import trxsh.ontop.scythe.command.TestCommand;
import trxsh.ontop.scythe.event.DeathEvent;
import trxsh.ontop.scythe.event.InteractEvent;
import trxsh.ontop.scythe.event.JoinEvent;
import trxsh.ontop.scythe.file.FileManager;
import trxsh.ontop.scythe.file.wrapper.BanFileManager;
import trxsh.ontop.scythe.file.wrapper.OrbFileManager;
import trxsh.ontop.scythe.file.wrapper.PlayerFileManager;
import trxsh.ontop.scythe.loop.AbilityLoop;

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

        Bukkit.getLogger().info("enabling plugin (prod trxsh 2.0#1988)");

        Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);

        Bukkit.getPluginCommand("tester").setExecutor(new TestCommand());

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

        Instance = this;

        Bukkit.getLogger().info("plugin is now enabled. starting repeating tasks (prod trxsh 2.0#1988)");

        AbilityLoop.start();

    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("disabling plugin (prod trxsh 2.0#1988)");

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

        Bukkit.getLogger().info("plugin is disabled (prod trxsh 2.0#1988)");

    }

}
