# Shape Visitor defense guide

## Demonstration

1. Show Shape.accept and all three ShapeVisitor methods.
2. Trace Drawing.acceptAll(area) into Circle.accept: the runtime element selects accept; Circle's implementation calls visitCircle(this), where this has compile-time type Circle. The runtime visitor selects AreaCalculatorVisitor.visitCircle. Repeat with CsvExportVisitor to explain the second operation on the same element.
3. Run `./run.ps1`. Explain 4π + 9 + 6 = 27.566371 square units. Explain CSV dimension columns. Perimeters total 4π + 12 + 12 = 36.566371 units.
4. Show PerimeterCalculatorVisitor and its commit with `git log --oneline` and `git show`. Existing Circle, Square and Triangle files were unchanged.
5. Run `./run.ps1 -Test` and explain one independent expected formula, defensive-copy check, invalid-dimension check and concurrent test.

## Practice questions

**Why Visitor?** It separates unrelated operations from stable element types. Adding an operation adds a visitor; adding a shape type requires updating the visitor interface and every visitor.

**Where is double dispatch?** Drawing calls shape.accept(visitor), selecting accept using runtime shape type. That accept calls visitCircle/visitSquare/visitTriangle, selecting the implementation using runtime visitor type. No type-checking branches are needed.

**Why right triangles?** Base and height alone define a general triangle's area, but do not define its perimeter. The model explicitly restricts Triangle to perpendicular legs so its perimeter is base + height + hypot(base,height).

**Why not area() on Shape?** Visitor keeps operations outside the element classes, allowing the third operation without editing shapes.

**What does export mean?** CSV serialization of type and dimensions, not geometric rendering. Six decimal places are a presentation policy, so round-trip precision is limited.

**Why the helper Dimensions?** It centralizes repeated dimension validation and non-finite result checks while remaining package-private.

**What is the encapsulation tradeoff?** Visitors can read dimensions through immutable record accessors, but cannot mutate elements. Drawing copies its list. More detailed operations might require exposing more metadata.

**Are visitors thread-safe?** They are task-confined, not shared. Immutable elements can be shared. The concurrency test uses 100 independent visitors with a fixed ExecutorService, joins Futures, and shuts down in finally. The production demo remains sequential.

**Which Chapter 12 rules apply?** Run all tests, remove duplication, express intent and keep the design minimal. Chapter 13 defenses include limiting mutable data, copying data, separating concurrency and keeping tasks independent.

**What are the numeric limits?** Positive finite double dimensions only; overflow is reported; normal floating-point rounding and tiny-value underflow remain possible. Visitors accumulate if reused, so Demo creates fresh ones.

The student must perform the live defense and explain the implementation in their own words.
