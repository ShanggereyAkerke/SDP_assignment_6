# Assignment 6 — Shape Visitor

Student: Asankhan Shyngys, SE-2504. Official topic B from the assignment.

Java 17 compatible, no external dependencies. Circle, Square, and Triangle are immutable elements. Triangle specifically models a right triangle whose base and height are perpendicular legs. All dimensions use the same arbitrary unit and must be positive and finite.

## Run and test

Install JDK 17 or newer. From this folder in PowerShell:

```powershell
./run.ps1
./run.ps1 -Test
```

Compilation uses `--release 17 -Xlint:all -Werror`. Verified on JDK 25.0.1 targeting Java 17; execution on a JDK 17 runtime has not been performed here. Tests use explicit checks without needing `-ea`.

On macOS/Linux:

```sh
mkdir -p build
find src/main/java src/test/java -name '*.java' > sources.txt
javac --release 17 -Xlint:all -Werror -d build @sources.txt
java -cp build shapes.Main
java -cp build shapes.ShapeTests
```

## Project structure

- `src/main/java/shapes`: elements, visitor contract, three visitors, Drawing and Main.
- `src/test/java/shapes/ShapeTests.java`: 141 checks covering formulas, dispatch, invalid values, snapshots, CSV, locale and task isolation.
- `docs/Shape_Visitor_Report.pdf`: six-page report.
- `docs/REPORT.md`: editable report content; insert the real GitHub URL before submission.
- `docs/uml.puml` and `docs/uml.png`: editable and rendered UML.
- `docs/DEFENSE.md`: demonstration and questions.
- `docs/REQUIREMENTS.md`: assignment mapping and remaining submission steps.

## Design

Drawing copies its shape list and traverses it in insertion order. Each shape calls its matching visit method. Visitors own operation-specific mutable state and must not be shared across concurrent tasks. Use a fresh visitor per traversal; reusing one accumulates results. CSV exports dimensions with six decimal places and Locale.ROOT. Circle dimension1 is radius, Square dimension1 is side, and Triangle dimension1/dimension2 are base/height. All operations run on the same drawing.

Areas use double precision and may underflow for extremely tiny dimensions. Non-finite results throw ArithmeticException. Math.hypot avoids unnecessary intermediate overflow in triangle hypotenuse calculation. This is an educational in-memory geometry model, not a CAD tool.

## Local history and GitHub

The included `.git` directory has real incremental commits under an explicit builder identity. Run `git log --oneline`. The third visitor commit changes no existing shape element.

Create an empty GitHub repository and substitute its actual URL:

```sh
git remote add origin https://github.com/OWNER/REPOSITORY.git
git push -u origin main
```

GitHub publication has not been performed. Insert the real URL into the report and upload the report and link to Moodle. Confirm the actual Moodle deadline because the provided material has inconsistent weekday/date labels. Review and adapt the work in accordance with the assignment's individual-authorship policy and instructor AI-use rules; prepare to defend it yourself.
