# Assignment requirement mapping

| Requirement | Evidence |
|---|---|
| Official alternative topic | Option B, Shape Visitor |
| Java 17 compatible code | run.ps1, --release 17; tested on JDK 25.0.1 |
| Element accept interface | Shape.java |
| At least two elements | Circle.java, Square.java, Triangle.java |
| Matching double dispatch | Each accept calls matching visit method with this |
| Visitor interface per element | ShapeVisitor.java |
| At least two visitors | AreaCalculatorVisitor, CsvExportVisitor, plus PerimeterCalculatorVisitor |
| Collection and traversal | Drawing copies List<Shape> and acceptAll traverses it |
| Five justified clean-code principles with excerpts | Eight annotated principles, report pages 3–4 |
| Chapter 12 and 13 guidance | Tests, duplication removal, expressiveness, minimal design, data copies and task isolation |
| No type-checking dispatch chains | Direct dispatch throughout production code |
| Extensibility | Third visitor commit changes no concrete elements |
| Report introduction, UML, output, conclusion | Six-page PDF and editable REPORT.md |
| Student details | Asankhan Shyngys, SE-2504 |
| README and clear structure | README, src/main/java, src/test/java, docs |
| Incremental history | Local .git included in ZIP |
| GitHub repository and real report link | Pending publication and URL insertion |
| Live demonstration and questions | Prepared DEFENSE.md; actual defense remains student's task |
| Moodle upload and deadline | Student must submit and verify LMS deadline |

Verification: 141 checks passed; compilation is warning-free. Report pages are rendered and visually reviewed. No claim is made that GitHub publication, Moodle upload, or the live defense has occurred.
