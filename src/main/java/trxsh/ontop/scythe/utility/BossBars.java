package trxsh.ontop.scythe.utility;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBars {

    public static void showBossBar(Player player, String string, boolean ready) {

        BarColor color = null;

        if(ready)
            color = BarColor.GREEN;
        else
            color = BarColor.RED;

        BossBar bossBar = Bukkit.createBossBar(string, color, BarStyle.SOLID);

        bossBar.addPlayer(player);

    }

}
