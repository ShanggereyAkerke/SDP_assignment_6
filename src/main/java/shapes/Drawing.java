package shapes;
import java.util.List;
import java.util.Objects;
public record Drawing(List<Shape> shapes) {
    public Drawing { shapes = List.copyOf(shapes); }
    public void acceptAll(ShapeVisitor visitor) {
        Objects.requireNonNull(visitor, "visitor");
        for (Shape shape : shapes) shape.accept(visitor);
    }
}
