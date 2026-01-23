package com.frugaltesting;

import org.openqa.selenium.WebDriver;

import com.frugaltesting.pages.RegistrationPage;
import com.frugaltesting.utils.TestHelper;

public class StandaloneTest {
    public static void main(String[] args) {
        System.out.println("🚀 Running standalone registration test...");
        
        WebDriver driver = TestHelper.initializeDriver("chrome", false);
        RegistrationPage page = new RegistrationPage(driver);
        
        try {
            // Test 1: Open page
            String url = "file:///C:/Program%20Files/registration-system/registration-system/frontend/index.html";
            System.out.println("Opening: " + url);
            page.openPage(url);
            TestHelper.pause(3000);
            
            // Test 2: Fill form
            System.out.println("Filling form...");
            page.enterFirstName("John");
            page.enterLastName("Doe");
            page.enterEmail("john.doe@example.com");
            page.selectGender("male");
            
            System.out.println("✅ Form filled successfully!");
            
        } catch (Exception e) {
            System.out.println("❌ Test failed: " + e.getMessage());
        } finally {
            TestHelper.closeDriver(driver);
        }
    }
}