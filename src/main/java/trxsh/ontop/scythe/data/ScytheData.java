package trxsh.ontop.scythe.data;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;
import trxsh.ontop.scythe.scythebase.wrapper.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScytheData {

    public static HashMap<Enchantment, Integer> infernoEnchantment = new HashMap<>();
    static {

        infernoEnchantment.put(Enchantment.FIRE_ASPECT, 3);

    }

    public static List<Scythe> scythes = new ArrayList<>();
    static {

        scythes.add(new InfernoScythe(
                ChatColor.GOLD + "" + ChatColor.BOLD + "Inferno Scythe",
                        "inferno",
                        "Throws a fireball at your enemies!",
                        ScytheType.INFERNO,
                infernoEnchantment
        ));

        scythes.add(new StrengthScythe(
                ChatColor.DARK_RED + "" + ChatColor.BOLD + "Strength Scythe",
                "strength",
                "Deal double damage!",
                ScytheType.STRENGTH,
                null
        ));

        scythes.add(new SpectralScythe(
                ChatColor.GRAY + "" + ChatColor.BOLD + "Spectral Scythe",
                "spectral",
                "Become invisible and make enemies glow!",
                ScytheType.SPECTRAL,
                null
        ));

    }

    public static List<Scythe> getScythes() {

        return scythes;

    }

}
