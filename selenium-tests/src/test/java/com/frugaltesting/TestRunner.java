package com.frugaltesting;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRunner {
    
    public static void main(String[] args) {
        // Create TestNG instance
        TestNG testNG = new TestNG();
        
        // Create XML Suite
        XmlSuite suite = new XmlSuite();
        suite.setName("Registration System Test Suite");
        suite.setParallel(XmlSuite.ParallelMode.NONE);
        suite.setThreadCount(1);
        
        // Create Test
        XmlTest test = new XmlTest(suite);
        test.setName("Registration Form Tests");
        
        // Add parameters
        Map<String, String> parameters = new HashMap<>();
        parameters.put("browser", "chrome");
        parameters.put("headless", "false");
        parameters.put("baseUrl", "http://localhost:5500/frontend");
        test.setParameters(parameters);
        
        // Add test classes
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass("com.frugaltesting.RegistrationTest"));
        test.setXmlClasses(classes);
        
        // Add suite to TestNG
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNG.setXmlSuites(suites);
        
        // Run tests
        System.out.println("Starting test execution...");
        testNG.run();
        System.out.println("Test execution completed!");
        
        // Exit with appropriate code
        System.exit(testNG.hasFailure() ? 1 : 0);
    }
}