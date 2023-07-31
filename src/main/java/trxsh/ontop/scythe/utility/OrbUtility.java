package trxsh.ontop.scythe.utility;

import trxsh.ontop.scythe.data.OrbData;

import java.util.UUID;

public class OrbUtility {

    public static void decreaseLevel(UUID id) {

        if(OrbData.contains(id)) {

            int level = OrbData.orbLevels.get(id);

            OrbData.replace(id, level - 1);

        }

    }

    public static void increaseLevel(UUID id) {

        if(OrbData.contains(id)) {

            int level = OrbData.orbLevels.get(id);

            OrbData.replace(id, level + 1);

        }

    }

    public static int getOrbLevel(UUID id) {

        if(OrbData.contains(id))
            return OrbData.orbLevels.get(id);

        return 0;

    }

    public static void setOrbLevel(UUID id, int level) {

        if(OrbData.contains(id))
             OrbData.replace(id, level);

    }

}
