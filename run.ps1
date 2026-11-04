param([switch]$Test)
$ErrorActionPreference = 'Stop'
Push-Location $PSScriptRoot
try {
    New-Item -ItemType Directory -Force build | Out-Null
    $sources = @(Get-ChildItem src/main/java,src/test/java -Recurse -Filter *.java | ForEach-Object FullName)
    & javac --release 17 -Xlint:all -Werror -d build @sources
    if ($LASTEXITCODE -ne 0) { throw 'Compilation failed' }
    if ($Test) { & java -cp build shapes.ShapeTests } else { & java -cp build shapes.Main }
    if ($LASTEXITCODE -ne 0) { throw 'Execution failed' }
} finally { Pop-Location }
