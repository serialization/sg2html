package html;

import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.HashSet;

import empty.OGFile;
import ogss.common.java.api.Access;
import ogss.common.java.api.Attribute;
import ogss.common.java.api.Edge;
import ogss.common.java.api.Graph;
import ogss.common.java.api.Node;
import ogss.common.java.internal.Obj;
import ogss.common.java.internal.nodes.MapNode;
import ogss.common.java.internal.nodes.SeqNode;
import ogss.common.java.internal.nodes.SetNode;

/**
 * Create output in a single .html-file.
 * 
 * @author Timm Felden
 */
public class MakeFile {
    public static final void apply(OGFile sf, boolean printProgress, boolean printStatistics, PrintStream out) {

        Graph G = new Graph(sf);
        HashSet<Node> targets = new HashSet<>();
        ArrayDeque<Node> todo = new ArrayDeque<>();

        out.println("<!DOCTYPE html>");
        out.println("  <html>");
        out.println("  <head>");
        out.printf("    <title>sg2html: %s</title>\n", sf.currentPath());
        out.println("  </head>");
        out.println("  <body>");
        out.println("    <h1>Types</h1>");

        for (Access<?> t : sf.allTypes()) {

            if (null == t.superType())
                out.print("<p>");

            out.printf("    <a name=t%1$s>%1$s</a> ", t.name());

            if (null != t.superType())
                out.printf("&lt: <a href=\"#t%s\">%1$s</a>", t.superType().name());

            out.print(" : ");

            int size = t.size();
            if (0 == size) {
                out.print("0");
            } else {
                Obj head = t.iterator().next();
                Obj last = t.get(head.ID() + size - 1);
                out.printf("%d %s %s", size, ref(sf, head, "First"), ref(sf, last, "Last"));
            }
            out.print("<br>\n        ");
        }

        out.println("<h1>Objects</h1>");

        for (Access<?> t : sf.allTypes())
            if (null == t.superType()) {
                for (Obj i : t) {
                    out.printf("<h3><a name=o%s%d><a href=\"#t%1$s\">%1$s</a>#%2$d</a></h3>\n<p>", sf.typeName(i),
                            i.ID());

                    Node n = G.getNodeFor(i);

                    boolean first = true;

                    for (Attribute f : n.attributes()) {
                        if (first)
                            first = false;
                        else
                            out.print("<br>");

                        out.printf("\n %s = %s", f.name, f.value);
                    }

                    for (Edge f : n.edges()) {
                        if (first)
                            first = false;
                        else
                            out.print("<br>");

                        targets.add(f.to);

                        out.printf("\n %s = %s", f.name, ref(sf, f.to));
                    }

                    out.println("<hr>");
                }
            }

        // containers
        todo.addAll(targets);
        while (!todo.isEmpty()) {
            Node ref = todo.pop();
            if (ref instanceof SeqNode || ref instanceof SetNode || ref instanceof MapNode) {
                out.printf("<h3><a name=c%s>%1$s</a></h3>\n<p>", ref.toString());

                boolean first = true;

                for (Attribute f : ref.attributes()) {
                    if (first)
                        first = false;
                    else
                        out.print("<br>");

                    out.printf("\n %s = %s", f.name, f.value);
                }

                for (Edge f : ref.edges()) {
                    if (first)
                        first = false;
                    else
                        out.print("<br>");

                    if (!targets.contains(f.to)) {
                        targets.add(f.to);
                        todo.push(f.to);
                    }

                    out.printf("\n %s = %s", f.name, ref(sf, f.to));
                }

                out.println("<hr>");
            }
        }

        out.println("</body></html>");

    }

    private static String ref(OGFile sf, Node ref) {
        if (null == ref.repr())
            return "null";

        if (ref.repr() instanceof Obj)
            return ref(sf, (Obj) ref.repr(), ref.toString());

        if (ref instanceof SeqNode || ref instanceof SetNode || ref instanceof MapNode) {
            return String.format("<a href=\"#c%1$s\">%1$s</a>", ref.toString());
        }

        return ref.toString();
    }

    private static String ref(OGFile sf, Obj ref, String text) {
        return String.format("<a href=\"#o%s%d\">%s</a>", sf.typeName(ref), ref.ID(), text);
    }
}
