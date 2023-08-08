package trxsh.ontop.scythe.utility;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class TextureUtility {

    public static void loadTexture(Player p) throws InvocationTargetException {

        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.RESOURCE_PACK_SEND);

        packet.getStrings().write(0, "https://download.mc-packs.net/pack/69162e46174c4517d5a9e4c1ea0b80aa8743a16d.zip").write(1, "sha-1 sum");

        ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);

    }

}
