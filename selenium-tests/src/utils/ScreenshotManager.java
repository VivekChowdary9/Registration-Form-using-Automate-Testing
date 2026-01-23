package com.frugaltesting.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotManager {
    
    private static final Logger logger = LogManager.getLogger(ScreenshotManager.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private static final String SCREENSHOT_DIR = "test-results/screenshots/";
    
    static {
        // Create screenshots directory if it doesn't exist
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    
    /**
     * Capture screenshot with timestamp
     * @param driver WebDriver instance
     * @param screenshotName Name of the screenshot
     * @return Path to the saved screenshot
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Create timestamp
            String timestamp = dateFormat.format(new Date());
            
            // Create screenshot file
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            
            // Generate file name
            String fileName = String.format("%s_%s.png", screenshotName, timestamp);
            String filePath = SCREENSHOT_DIR + fileName;
            
            // Save screenshot
            FileUtils.copyFile(screenshotFile, new File(filePath));
            
            logger.info("Screenshot captured: {}", fileName);
            return filePath;
            
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
            return null;
        }
    }
    
    /**
     * Capture screenshot for specific test step
     * @param driver WebDriver instance
     * @param testName Name of the test
     * @param stepName Name of the step
     * @return Path to the saved screenshot
     */
    public static String captureStepScreenshot(WebDriver driver, String testName, String stepName) {
        String screenshotName = String.format("%s_%s", testName, stepName);
        return captureScreenshot(driver, screenshotName);
    }
    
    /**
     * Capture full page screenshot (for documentation)
     * @param driver WebDriver instance
     * @param pageName Name of the page
     * @return Path to the saved screenshot
     */
    public static String capturePageScreenshot(WebDriver driver, String pageName) {
        String[] pageNames = {
            "landing-page", "form-filled", "validation-error", 
            "success-state", "country-selected", "state-selected"
        };
        
        for (String name : pageNames) {
            if (pageName.contains(name)) {
                return captureScreenshot(driver, name);
            }
        }
        
        return captureScreenshot(driver, pageName);
    }
    
    /**
     * Create screenshot report
     * @return HTML report with screenshots
     */
    public static String generateScreenshotReport() {
        File screenshotDir = new File(SCREENSHOT_DIR);
        File[] screenshots = screenshotDir.listFiles((dir, name) -> name.endsWith(".png"));
        
        if (screenshots == null || screenshots.length == 0) {
            return "<p>No screenshots available</p>";
        }
        
        StringBuilder html = new StringBuilder();
        html.append("<div class='screenshot-report'>");
        html.append("<h3>Screenshot Gallery</h3>");
        html.append("<div class='screenshot-grid'>");
        
        for (File screenshot : screenshots) {
            String fileName = screenshot.getName();
            html.append("<div class='screenshot-item'>");
            html.append("<h4>").append(fileName.replace(".png", "").replace("_", " ")).append("</h4>");
            html.append("<img src='").append(screenshot.getPath()).append("' alt='").append(fileName).append("'>");
            html.append("</div>");
        }
        
        html.append("</div></div>");
        return html.toString();
    }
    
    /**
     * Clean up old screenshots (keep only last 50)
     */
    public static void cleanupOldScreenshots() {
        File screenshotDir = new File(SCREENSHOT_DIR);
        File[] screenshots = screenshotDir.listFiles((dir, name) -> name.endsWith(".png"));
        
        if (screenshots != null && screenshots.length > 50) {
            // Sort by last modified
            java.util.Arrays.sort(screenshots, (f1, f2) -> 
                Long.compare(f2.lastModified(), f1.lastModified()));
            
            // Delete oldest screenshots
            for (int i = 50; i < screenshots.length; i++) {
                if (screenshots[i].delete()) {
                    logger.info("Deleted old screenshot: {}", screenshots[i].getName());
                }
            }
        }
    }
}