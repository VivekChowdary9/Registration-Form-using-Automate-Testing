@echo off
echo Cleaning up and running tests...
echo.

echo Step 1: Deleting problematic files...
del src\test\java\com\frugaltesting\utils\ScreenshotManager.java 2>nul
del src\test\java\com\frugaltesting\utils\ReportGenerator.java 2>nul
del src\test\java\com\frugaltesting\TestRunner.java 2>nul
echo Done.

echo Step 2: Deleting target folder...
rmdir /s /q target 2>nul
echo Done.

echo Step 3: Running Maven clean test...
call mvn clean test

echo.
pause