package trxsh.ontop.scythe.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BanData {

    public static List<UUID> banList = new ArrayList<>();

    public static void add(UUID id) {

        banList.add(id);

    }

    public static void remove(UUID id) {

        banList.remove(id);

    }

    public static boolean contains(UUID id) {

        return banList.contains(id);

    }

}
