package trxsh.ontop.scythe.file.wrapper;

import trxsh.ontop.scythe.data.OrbData;
import trxsh.ontop.scythe.file.FileManager;
import trxsh.ontop.scythe.utility.StringUtility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

public class OrbFileManager extends FileManager {

    public OrbFileManager(File file) {
        super(file);
    }

    @Override
    public void save() throws IOException {

        FileWriter writer = new FileWriter(getFile());
        StringBuilder builder = new StringBuilder();

        for(UUID id : OrbData.orbLevels.keySet()) {

            int orbLevel = OrbData.orbLevels.get(id);

            builder.append(id.toString())
                    .append("::")
                    .append(orbLevel)
                    .append(StringUtility.getSeparator());

        }

        writer.write(builder.toString());
        writer.close();

    }

    @Override
    public void load() throws IOException {

        for(String line : Files.readAllLines(getFile().toPath())) {

            UUID id = UUID.fromString(line.split("::")[0]);
            int orbLevel = Integer.parseInt(line.split("::")[1]);

            OrbData.add(id, orbLevel);

        }

    }
}
