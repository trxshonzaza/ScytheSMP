package trxsh.ontop.scythe.file.wrapper;

import trxsh.ontop.scythe.data.PlayerData;
import trxsh.ontop.scythe.data.player.DataPlayer;
import trxsh.ontop.scythe.file.FileManager;
import trxsh.ontop.scythe.utility.StringUtility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

public class PlayerFileManager extends FileManager {

    public PlayerFileManager(File file) {
        super(file);
    }

    @Override
    public void save() throws IOException {

        FileWriter writer = new FileWriter(getFile());
        StringBuilder builder = new StringBuilder();

        for(UUID id : PlayerData.playerList.keySet())
            builder.append(id.toString())
                    .append(StringUtility.getSeparator());

        writer.write(builder.toString());
        writer.close();

    }

    @Override
    public void load() throws IOException {

        for(String line : Files.readAllLines(getFile().toPath())) {

            UUID id = UUID.fromString(line);

            DataPlayer p = new DataPlayer(id);

            if(!PlayerData.contains(id))
                PlayerData.add(id, p);

        }

    }

}
