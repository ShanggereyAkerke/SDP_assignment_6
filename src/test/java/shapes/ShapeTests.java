package shapes;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
public final class ShapeTests {
    private static int checks;
    private static void check(boolean ok, String message) {
        checks++; if (!ok) throw new AssertionError(message);
    }
    private static void close(double actual, double expected) {
        check(Math.abs(actual - expected) < 1e-10, "Expected " + expected + ", got " + actual);
    }
    private static void rejects(Class<? extends RuntimeException> type, Runnable task) {
        try { task.run(); } catch (RuntimeException error) {
            check(type.isAssignableFrom(error.getClass()), "Wrong exception"); return;
        }
        throw new AssertionError("Expected " + type.getSimpleName());
    }
    private static double area(Drawing drawing) {
        var visitor = new AreaCalculatorVisitor(); drawing.acceptAll(visitor); return visitor.totalArea();
    }
    public static void main(String[] args) throws Exception {
        var drawing = Main.sampleDrawing();
        close(area(new Drawing(List.of(new Circle(2)))), 4 * Math.PI);
        close(area(new Drawing(List.of(new Square(3)))), 9);
        close(area(new Drawing(List.of(new Triangle(3, 4)))), 6);
        close(area(drawing), 4 * Math.PI + 15);
        close(area(new Drawing(List.of())), 0);
        var perimeter = new PerimeterCalculatorVisitor(); drawing.acceptAll(perimeter);
        close(perimeter.totalPerimeter(), 4 * Math.PI + 24);
        for (Shape shape : drawing.shapes()) {
            var visitor = new PerimeterCalculatorVisitor(); shape.accept(visitor);
            check(visitor.totalPerimeter() > 0, "Perimeter dispatch");
        }
        var export = new CsvExportVisitor(); drawing.acceptAll(export);
        String nl = System.lineSeparator();
        String expected = "type,dimension1,dimension2\nCircle,2.000000," + nl
            + "Square,3.000000," + nl + "Triangle,3.000000,4.000000" + nl;
        check(export.csv().equals(expected), "CSV values order and dispatch");
        String snapshot = export.csv(); export.visitSquare(new Square(1));
        check(snapshot.equals(expected), "Immutable export snapshot");
        Locale original = Locale.getDefault();
        try {
            Locale.setDefault(Locale.GERMANY);
            var localized = new CsvExportVisitor(); drawing.acceptAll(localized);
            check(localized.csv().equals(expected), "Locale independent decimals");
        } finally { Locale.setDefault(original); }
        var emptyExport = new CsvExportVisitor(); new Drawing(List.of()).acceptAll(emptyExport);
        check(emptyExport.csv().equals("type,dimension1,dimension2\n"), "Empty CSV");
        var source = new ArrayList<Shape>(); source.add(new Circle(1));
        var copy = new Drawing(source); source.clear();
        check(copy.shapes().size() == 1, "Defensive copy");
        rejects(UnsupportedOperationException.class, () -> copy.shapes().clear());
        for (double bad : new double[] {0, -1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            rejects(IllegalArgumentException.class, () -> new Circle(bad));
            rejects(IllegalArgumentException.class, () -> new Square(bad));
            rejects(IllegalArgumentException.class, () -> new Triangle(bad, 1));
            rejects(IllegalArgumentException.class, () -> new Triangle(1, bad));
        }
        rejects(NullPointerException.class, () -> new Drawing(null));
        rejects(NullPointerException.class, () -> new Drawing(java.util.Arrays.asList((Shape)null)));
        rejects(NullPointerException.class, () -> drawing.acceptAll(null));
        rejects(ArithmeticException.class, () -> area(new Drawing(List.of(new Square(Double.MAX_VALUE)))));
        rejects(ArithmeticException.class, () -> new Circle(Double.MAX_VALUE).accept(new PerimeterCalculatorVisitor()));
        var repeated = new AreaCalculatorVisitor(); drawing.acceptAll(repeated); drawing.acceptAll(repeated);
        close(repeated.totalArea(), 2 * (4 * Math.PI + 15));
        var pool = Executors.newFixedThreadPool(4);
        try {
            var tasks = new ArrayList<Callable<Double>>();
            for (int i=0; i<100; i++) tasks.add(() -> area(drawing));
            for (var result : pool.invokeAll(tasks)) close(result.get(), 4 * Math.PI + 15);
        } finally { pool.shutdown(); }
        System.out.println("PASS: " + checks + " checks");
    }
}
