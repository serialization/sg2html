/*   ____  _____________                                                                                              *\
 *  / __ \/ ___/ __/ __/  Your OGSS/Java Binding                                                                      * 
 * / /_/ / (_\_\ \_\ \    <<debug>>                                                                                   * 
 * \____/\___/___/___/    by: <<some developer>>                                                                      * 
\*                                                                                                                    */
package empty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;

import ogss.common.java.api.OGSSException;
import ogss.common.java.internal.AnyRefType;
import ogss.common.java.internal.EnumPool;
import ogss.common.java.internal.EnumProxy;
import ogss.common.java.internal.FieldDeclaration;
import ogss.common.java.internal.FieldType;
import ogss.common.java.internal.HullType;
import ogss.common.java.internal.InterfacePool;
import ogss.common.java.internal.Obj;
import ogss.common.java.internal.Pool;
import ogss.common.java.internal.StringPool;
import ogss.common.java.internal.SubPool;
import ogss.common.java.internal.UnrootedInterfacePool;
import ogss.common.java.internal.fieldDeclarations.AutoField;
import ogss.common.java.internal.fieldDeclarations.KnownField;
import ogss.common.java.internal.fieldTypes.*;
import ogss.common.java.restrictions.TypeRestriction;
import ogss.common.streams.BufferedOutStream;
import ogss.common.streams.FileInputStream;
import ogss.common.streams.MappedInStream;


@SuppressWarnings("all")
public final class internal {
    private internal() {}

    public static final class PB extends ogss.common.java.internal.PoolBuilder {
        PB() {
            super(10);
        }

        @Override
        protected String[] literals() {
            return new String[]{""};
        }

        @Override
        protected int kcc(int id) {
            switch (id) {
            default: return -1;
            }
        }

        @Override
        protected String name(int id) {
            return null;
        }

        @Override
        protected Pool<?> make(int id, int idx) {
            return null;
        }

        @Override
        protected String enumName(int id) {
            return null;
        }

        @Override
        protected Enum<?>[] enumMake(int id) {
            return null;
        }
    }

}
