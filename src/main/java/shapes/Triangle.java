package shapes;
/** Right triangle: base and height are perpendicular legs. */
public record Triangle(double base, double height) implements Shape {
    public Triangle { Dimensions.requirePositive(base); Dimensions.requirePositive(height); }
    @Override public void accept(ShapeVisitor visitor) { visitor.visitTriangle(this); }
}
