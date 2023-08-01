package trxsh.ontop.scythe.file.wrapper;

import trxsh.ontop.scythe.data.BanData;
import trxsh.ontop.scythe.file.FileManager;
import trxsh.ontop.scythe.utility.StringUtility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

public class BanFileManager extends FileManager {

    public BanFileManager(File file) {
        super(file);
    }

    @Override
    public void save() throws IOException {

        FileWriter writer = new FileWriter(getFile());
        StringBuilder builder = new StringBuilder();

        for(UUID id : BanData.banList)
            builder.append(id.toString())
                    .append(StringUtility.getSeparator());

        writer.write(builder.toString());
        writer.close();

    }

    @Override
    public void load() throws IOException {

        for(String line : Files.readAllLines(getFile().toPath())) {

            UUID id = UUID.fromString(line);

            if(!BanData.contains(id))
                BanData.add(id);

        }

    }
}
