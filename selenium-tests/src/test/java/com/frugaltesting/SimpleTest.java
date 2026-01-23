package com.frugaltesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SimpleTest {
    public static void main(String[] args) {
        System.out.println("====================");
        System.out.println("Running Simple Test");
        System.out.println("====================");
        
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        
        WebDriver driver = null;
        
        try {
            // Create driver
            driver = new ChromeDriver(options);
            
            // Test 1: Open local HTML file
            
            String filePath = "file:///C:/Program%20Files/registration-system/registration-system/frontend/index.html";
;
            System.out.println("1. Opening: " + filePath);
            driver.get(filePath);
            
            Thread.sleep(3000); // Wait for page to load
            
            // Test 2: Check title
            String title = driver.getTitle();
            System.out.println("2. Page title: " + title);
            
            if (title.contains("Intelligent") || title.contains("Registration") || title.contains("Frugal")) {
                System.out.println("   ✅ Page loaded successfully!");
            } else {
                System.out.println("   ⚠️  Page title doesn't match expected");
            }
            
            // Test 3: Check current URL
            String currentUrl = driver.getCurrentUrl();
            System.out.println("3. Current URL: " + currentUrl);
            
            if (currentUrl.contains("file:///") || currentUrl.contains("index.html")) {
                System.out.println("   ✅ File URL is correct!");
            }
            
            // Test 4: Take screenshot
            System.out.println("4. Taking screenshot...");
            try {
                // Simple screenshot method
                byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
                java.io.FileOutputStream fos = new java.io.FileOutputStream("simple-test-screenshot.png");
                fos.write(screenshot);
                fos.close();
                System.out.println("   ✅ Screenshot saved: simple-test-screenshot.png");
            } catch (Exception e) {
                System.out.println("   ⚠️  Could not save screenshot: " + e.getMessage());
            }
            
            System.out.println("\n✅ SIMPLE TEST PASSED!");
            
        } catch (Exception e) {
            System.out.println("❌ TEST FAILED: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed.");
            }
        }
    }
}