package com.frugaltesting.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    
    public static WebDriver initializeDriver(String browser, boolean headless) {
        WebDriver driver = null;
        
        if (browser.equalsIgnoreCase("chrome")) {
            System.out.println("   Initializing Chrome Driver...");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            
            if (headless) {
                options.addArguments("--headless");
            }
            
            // Add these arguments to avoid issues
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            
            driver = new ChromeDriver(options);
            System.out.println("   Chrome Driver initialized successfully");
        } else {
            System.out.println("   Only Chrome is supported in this version");
        }
        
        return driver;
    }
    
    public static Map<String, String> generateTestData() {
        Map<String, String> testData = new HashMap<>();
        String timestamp = String.valueOf(System.currentTimeMillis());
        
        testData.put("firstName", "TestUser");
        testData.put("lastName", "Automation");
        testData.put("email", "testuser" + timestamp + "@example.com");
        testData.put("phone", "98765" + (timestamp.length() > 7 ? timestamp.substring(7, 11) : "4321"));
        testData.put("age", "25");
        testData.put("gender", "male");
        testData.put("address", "123 Test Street, Automation City");
        testData.put("country", "india");
        testData.put("state", "telangana");
        testData.put("city", "hyderabad");
        testData.put("password", "TestPass123!");
        testData.put("confirmPassword", "TestPass123!");
        
        return testData;
    }
    
    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void closeDriver(WebDriver driver) {
        if (driver != null) {
            System.out.println("   Closing browser...");
            driver.quit();
            System.out.println("   Browser closed");
        }
    }
    
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Create screenshots directory
            File dir = new File("test-screenshots");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            // Create timestamp
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = sdf.format(new Date());
            
            // Take screenshot
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            
            // Save with meaningful name
            String fileName = screenshotName + "_" + timestamp + ".png";
            File destFile = new File(dir, fileName);
            
            // Save file
            FileOutputStream fos = new FileOutputStream(destFile);
            fos.write(screenshotBytes);
            fos.close();
            
            System.out.println("   📸 Screenshot saved: " + fileName);
            return fileName;
            
        } catch (Exception e) {
            System.out.println("   ⚠️ Could not capture screenshot: " + e.getMessage());
            return null;
        }
    }
}