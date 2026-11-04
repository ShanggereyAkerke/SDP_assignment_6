package shapes;
import java.util.List;
import java.util.Locale;
public final class Main {
    private Main() { }
    public static Drawing sampleDrawing() {
        return new Drawing(List.of(new Circle(2), new Square(3), new Triangle(3, 4)));
    }
    public static void main(String[] args) {
        var drawing = sampleDrawing();
        var area = new AreaCalculatorVisitor();
        var export = new CsvExportVisitor();
        var perimeter = new PerimeterCalculatorVisitor();
        drawing.acceptAll(area); drawing.acceptAll(export); drawing.acceptAll(perimeter);
        System.out.println("Drawing: Circle(radius=2), Square(side=3), Triangle(base=3, height=4)");
        System.out.printf(Locale.ROOT, "Total area: %.6f square units%n", area.totalArea());
        System.out.println("CSV export:");
        System.out.print(export.csv());
        System.out.printf(Locale.ROOT, "Total perimeter: %.6f units%n", perimeter.totalPerimeter());
    }
}
