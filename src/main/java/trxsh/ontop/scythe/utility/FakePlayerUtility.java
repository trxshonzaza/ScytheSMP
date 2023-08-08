package trxsh.ontop.scythe.utility;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.FollowTrait;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.mcmonkey.sentinel.SentinelTrait;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakePlayerUtility {

    public static List<NPC> fakePlayers = new ArrayList<>();

    public static Location spawnFakePlayer(UUID id, Location location) {

        Player player = Bukkit.getPlayer(id);

        if(player == null)
            throw new NullPointerException("player is null");

        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, player.getName());

        npc.data().set(NPC.Metadata.REMOVE_FROM_PLAYERLIST, true);
        npc.data().set(NPC.Metadata.DROPS_ITEMS, false);

        npc.addTrait(SentinelTrait.class);

        SentinelTrait sentinel = npc.getOrAddTrait(SentinelTrait.class);

        sentinel.setHealth(10);
        sentinel.realistic = true;
        sentinel.reach = 3.5;
        sentinel.allowKnockback = true;
        sentinel.fightback = true;
        sentinel.damage = 4;
        sentinel.autoswitch = true;
        sentinel.invincible = false;

        SkinTrait skin = npc.getOrAddTrait(SkinTrait.class);

        skin.setSkinName(player.getName());

        Equipment npcInv = npc.getOrAddTrait(Equipment.class);

        npcInv.set(Equipment.EquipmentSlot.HELMET, player.getInventory().getHelmet());
        npcInv.set(Equipment.EquipmentSlot.CHESTPLATE, player.getInventory().getChestplate());
        npcInv.set(Equipment.EquipmentSlot.LEGGINGS, player.getInventory().getLeggings());
        npcInv.set(Equipment.EquipmentSlot.BOOTS, player.getInventory().getBoots());
        npcInv.set(Equipment.EquipmentSlot.HAND, player.getInventory().getItemInMainHand());
        npcInv.set(Equipment.EquipmentSlot.OFF_HAND, player.getInventory().getItemInOffHand());

        npc.spawn(location);

        FollowTrait follow = npc.getOrAddTrait(FollowTrait.class);

        follow.follow(player);

        sentinel.addIgnore("uuid:" + id);
        sentinel.addTarget("players");
        sentinel.addTarget("monsters");

        fakePlayers.add(npc);

        return location;

    }

    public static boolean isFake(LivingEntity entity) {

        return entity.hasMetadata("NPC");

    }

}
