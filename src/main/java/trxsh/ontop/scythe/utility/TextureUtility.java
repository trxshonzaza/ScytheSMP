package trxsh.ontop.scythe.utility;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class TextureUtility {

    public static void loadTexture(Player p) throws InvocationTargetException {

        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.RESOURCE_PACK_SEND);

        packet.getStrings().write(0, "https://download.mc-packs.net/pack/349ae6091d8df86d7f478e316657b8ca1b8ce39f.zip").write(1, "sha-1 sum");

        ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);

    }

}
