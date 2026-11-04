package shapes;
public final class AreaCalculatorVisitor implements ShapeVisitor {
    private double totalArea;
    private void add(double area) { totalArea = Dimensions.finiteResult(totalArea + area); }
    @Override public void visitCircle(Circle c) { add(Math.PI * c.radius() * c.radius()); }
    @Override public void visitSquare(Square s) { add(s.side() * s.side()); }
    @Override public void visitTriangle(Triangle t) { add((t.base() / 2) * t.height()); }
    public double totalArea() { return totalArea; }
}
