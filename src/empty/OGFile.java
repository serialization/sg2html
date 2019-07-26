/*   ____  _____________                                                                                              *\
 *  / __ \/ ___/ __/ __/  Your OGSS/Java Binding                                                                      * 
 * / /_/ / (_\_\ \_\ \    <<debug>>                                                                                   * 
 * \____/\___/___/___/    by: <<some developer>>                                                                      * 
\*                                                                                                                    */
package empty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import ogss.common.java.api.Mode;
import ogss.common.java.api.OGSSException;
import ogss.common.java.internal.KCC;
import ogss.common.java.internal.Pool;
import ogss.common.java.internal.StateInitializer;

/**
 * An abstract OGSS file that is hiding all the dirty implementation details
 * from you.
 *
 * @note Type access fields start with a capital letter to avoid collisions and to match type names.
 *
 * @author Timm Felden
 */
@SuppressWarnings("all")
public final class OGFile extends ogss.common.java.internal.State {

    /**
     * Create a new OGSS file based on argument path and mode.
     *
     * @throws IOException
     *             on IO and mode related errors
     * @throws OGSSException
     *             on file or specification consistency errors
     */
    public static OGFile open(String path, Mode... mode) throws IOException, OGSSException {
        return new OGFile(StateInitializer.make(Paths.get(path), new internal.PB(), mode));
    }

    /**
     * Create a new OGSS file based on argument path and mode.
     *
     * @throws IOException
     *             on IO and mode related errors
     * @throws OGSSException
     *             on file or specification consistency errors
     */
    public static OGFile open(File path, Mode... mode) throws IOException, OGSSException {
        return new OGFile(StateInitializer.make(path.toPath(), new internal.PB(), mode));
    }

    /**
     * Create a new OGSS file based on argument path and mode.
     *
     * @throws IOException
     *             on IO and mode related errors
     * @throws OGSSException
     *             on file or specification consistency errors
     */
    public static OGFile open(Path path, Mode... mode) throws IOException, OGSSException {
        return new OGFile(StateInitializer.make(path, new internal.PB(), mode));
    }

    private OGFile(StateInitializer init) {
        super(init);


        init.awaitResults();
    }
}
