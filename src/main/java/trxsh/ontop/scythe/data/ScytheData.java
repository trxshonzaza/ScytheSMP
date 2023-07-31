package trxsh.ontop.scythe.data;

import trxsh.ontop.scythe.scythebase.Scythe;
import trxsh.ontop.scythe.scythebase.ScytheType;
import trxsh.ontop.scythe.scythebase.wrapper.TestScythe;

import java.util.ArrayList;
import java.util.List;

public class ScytheData {

    public static List<Scythe> scythes = new ArrayList<>();
    static {

        scythes.add(new TestScythe(
                "Test Scythe",
                        "test",
                        "test description",
                        ScytheType.TEST));

    }

    public static List<Scythe> getScythes() {

        return scythes;

    }

}
