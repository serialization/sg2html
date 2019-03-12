import java.io.IOException;

import empty.OGFile;
import html.MakeFile;
import ogss.common.java.api.Mode;
import ogss.common.java.api.SkillException;

public class Main {
    public static void main(String[] args) throws SkillException, IOException {
        MakeFile.apply(OGFile.open(args[0], Mode.Read, Mode.ReadOnly), false, false, System.out);
    }
}
