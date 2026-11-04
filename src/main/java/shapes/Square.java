package shapes;
/** Immutable shape dimensions, measured in units. */
public record Square(double side) implements Shape {
    public Square { Dimensions.requirePositive(side); }
    @Override public void accept(ShapeVisitor visitor) { visitor.visitSquare(this); }
}
