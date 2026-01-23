@echo off
echo ============================================
echo   FRUGAL TESTING - COMPLETE TEST EXECUTION
echo ============================================
echo.

echo 📅 Date: %date% %time%
echo.

echo Step 1: Cleaning previous builds...
call mvn clean

echo Step 2: Creating screenshot directories...
mkdir test-screenshots 2>nul
mkdir manual-screenshots 2>nul

echo Step 3: Running all automation tests...
echo.
call mvn test

echo.
echo Step 4: Checking results...
echo.

if exist "target\surefire-reports\*.txt" (
    echo ✅ Test reports generated in: target\surefire-reports\
    type target\surefire-reports\com.frugaltesting.RegistrationTest.txt | find "Tests run:"
) else (
    echo ⚠️ No test reports found
)

echo.
if exist "test-screenshots\*.png" (
    echo ✅ Automation screenshots found: 
    dir test-screenshots\*.png /b
) else (
    echo ⚠️ No automation screenshots. Please take manual screenshots.
)

echo.
echo ============================================
echo   REQUIRED SCREENSHOTS FOR SUBMISSION:
echo ============================================
echo.
echo 1. Frontend landing page (home.png)
echo 2. Form filled with data (form-filled.png) 
echo 3. Validation error (validation-error.png)
echo 4. Success message (success-page.png)
echo 5. Test execution results (test-results.png)
echo 6. Project structure (project-structure.png)
echo 7. Code files (code-files.png)
echo.

echo Please take these screenshots manually if needed.
echo.

pause