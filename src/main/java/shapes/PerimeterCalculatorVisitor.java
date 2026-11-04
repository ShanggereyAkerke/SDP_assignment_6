package shapes;
public final class PerimeterCalculatorVisitor implements ShapeVisitor {
    private double totalPerimeter;
    private void add(double value) {
        totalPerimeter = Dimensions.finiteResult(totalPerimeter + value);
    }
    @Override public void visitCircle(Circle c) { add(2 * Math.PI * c.radius()); }
    @Override public void visitSquare(Square s) { add(4 * s.side()); }
    @Override public void visitTriangle(Triangle t) {
        add(t.base() + t.height() + Math.hypot(t.base(), t.height()));
    }
    public double totalPerimeter() { return totalPerimeter; }
}
