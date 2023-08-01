package trxsh.ontop.scythe.file;

import java.io.File;
import java.io.IOException;

public abstract class FileManager {

    private File file;

    public FileManager(File file) {

        this.file = file;

    }

    public abstract void save() throws IOException;
    public abstract void load() throws IOException;

    public File getFile() {

        return file;

    }

    public boolean exists() {

        return file.exists();

    }

}
