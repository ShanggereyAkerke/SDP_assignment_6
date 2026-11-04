package shapes;
import java.util.Locale;
public final class CsvExportVisitor implements ShapeVisitor {
    private final StringBuilder rows = new StringBuilder("type,dimension1,dimension2\n");
    private void row(String type, double first, String second) {
        rows.append(String.format(Locale.ROOT, "%s,%.6f,%s%n", type, first, second));
    }
    @Override public void visitCircle(Circle c) { row("Circle", c.radius(), ""); }
    @Override public void visitSquare(Square s) { row("Square", s.side(), ""); }
    @Override public void visitTriangle(Triangle t) {
        row("Triangle", t.base(), String.format(Locale.ROOT, "%.6f", t.height()));
    }
    public String csv() { return rows.toString(); }
}
