package shapes;
final class Dimensions {
    private Dimensions() { }
    static void requirePositive(double value) {
        if (!Double.isFinite(value) || value <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive and finite");
        }
    }
    static double finiteResult(double value) {
        if (!Double.isFinite(value)) throw new ArithmeticException("Result overflow");
        return value;
    }
}
