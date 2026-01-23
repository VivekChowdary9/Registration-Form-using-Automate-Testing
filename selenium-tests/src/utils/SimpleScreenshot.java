package com.frugaltesting.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleScreenshot {
    
    public static void capture(WebDriver driver, String testName) {
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
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            
            // Save with meaningful name
            String fileName = testName + "_" + timestamp + ".png";
            File destFile = new File(dir, fileName);
            
            // Copy file using FileUtils
            FileUtils.copyFile(screenshot, destFile);
            
            System.out.println("   📸 Screenshot saved: " + fileName);
            
        } catch (Exception e) {
            System.out.println("   ❌ Could not capture screenshot: " + e.getMessage());
        }
    }
}