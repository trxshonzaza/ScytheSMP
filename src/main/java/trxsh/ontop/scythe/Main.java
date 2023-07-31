package trxsh.ontop.scythe;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import trxsh.ontop.scythe.command.TestCommand;
import trxsh.ontop.scythe.event.DeathEvent;
import trxsh.ontop.scythe.event.InteractEvent;
import trxsh.ontop.scythe.event.JoinEvent;
import trxsh.ontop.scythe.loop.AbilityLoop;

public final class Main extends JavaPlugin {

    public static Main Instance = null;

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);

        Bukkit.getPluginCommand("tester").setExecutor(new TestCommand());

        Instance = this;

        AbilityLoop.start();

    }

    @Override
    public void onDisable() {

    }

}
