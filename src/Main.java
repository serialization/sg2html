import java.io.IOException;

import empty.OGFile;
import html.MakeFile;
import ogss.common.java.api.Mode;

public class Main {
    public static void main(String[] args) throws Exception {
        MakeFile.apply(OGFile.open(args[0], Mode.Read, Mode.ReadOnly), false, false, System.out);
    }
}
