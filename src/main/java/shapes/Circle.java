package shapes;
/** Immutable shape dimensions, measured in units. */
public record Circle(double radius) implements Shape {
    public Circle { Dimensions.requirePositive(radius); }
    @Override public void accept(ShapeVisitor visitor) { visitor.visitCircle(this); }
}
