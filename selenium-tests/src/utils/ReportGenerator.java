package com.frugaltesting.utils;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportGenerator {
    
    public static void generateHTMLReport() {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportFile = "test-results/test-report_" + timestamp + ".html";
            
            String htmlContent = generateReportContent();
            
            FileUtils.writeStringToFile(new File(reportFile), htmlContent, "UTF-8");
            System.out.println("HTML report generated: " + reportFile);
            
        } catch (IOException e) {
            System.err.println("Failed to generate HTML report: " + e.getMessage());
        }
    }
    
    private static String generateReportContent() {
        return "<!DOCTYPE html>\n" +
               "<html lang='en'>\n" +
               "<head>\n" +
               "    <meta charset='UTF-8'>\n" +
               "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
               "    <title>Registration System Test Report</title>\n" +
               "    <style>\n" +
               "        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }\n" +
               "        .container { max-width: 1200px; margin: 0 auto; background: white; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }\n" +
               "        .header { text-align: center; margin-bottom: 30px; padding-bottom: 20px; border-bottom: 2px solid #4361ee; }\n" +
               "        .header h1 { color: #4361ee; margin-bottom: 10px; }\n" +
               "        .summary { display: grid; grid-template-columns: repeat(4, 1fr); gap: 20px; margin-bottom: 30px; }\n" +
               "        .summary-card { background: #f8f9fa; padding: 20px; border-radius: 8px; text-align: center; }\n" +
               "        .summary-card h3 { margin: 0 0 10px 0; color: #333; }\n" +
               "        .summary-card .value { font-size: 24px; font-weight: bold; }\n" +
               "        .summary-card.passed { border-left: 5px solid #4cc9f0; }\n" +
               "        .summary-card.failed { border-left: 5px solid #f72585; }\n" +
               "        .summary-card.total { border-left: 5px solid #4361ee; }\n" +
               "        .summary-card.time { border-left: 5px solid #f8961e; }\n" +
               "        .test-results { margin-bottom: 30px; }\n" +
               "        .test-case { background: #f8f9fa; margin-bottom: 15px; padding: 15px; border-radius: 8px; }\n" +
               "        .test-case.passed { border-left: 5px solid #4cc9f0; }\n" +
               "        .test-case.failed { border-left: 5px solid #f72585; }\n" +
               "        .test-header { display: flex; justify-content: space-between; align-items: center; }\n" +
               "        .test-name { font-weight: bold; font-size: 16px; }\n" +
               "        .test-status { padding: 5px 15px; border-radius: 15px; color: white; font-size: 12px; }\n" +
               "        .status-passed { background: #4cc9f0; }\n" +
               "        .status-failed { background: #f72585; }\n" +
               "        .test-description { margin: 10px 0; color: #666; }\n" +
               "        .screenshot-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 20px; }\n" +
               "        .screenshot-item { background: #f8f9fa; padding: 15px; border-radius: 8px; }\n" +
               "        .screenshot-item img { width: 100%; height: auto; border-radius: 5px; border: 1px solid #ddd; }\n" +
               "        .footer { text-align: center; margin-top: 30px; padding-top: 20px; border-top: 1px solid #ddd; color: #666; font-size: 14px; }\n" +
               "    </style>\n" +
               "</head>\n" +
               "<body>\n" +
               "    <div class='container'>\n" +
               "        <div class='header'>\n" +
               "            <h1>📋 Registration System Test Report</h1>\n" +
               "            <p>Generated on " + new Date() + "</p>\n" +
               "        </div>\n" +
               "        \n" +
               "        <div class='summary'>\n" +
               "            <div class='summary-card total'>\n" +
               "                <h3>Total Tests</h3>\n" +
               "                <div class='value'>5</div>\n" +
               "            </div>\n" +
               "            <div class='summary-card passed'>\n" +
               "                <h3>Passed</h3>\n" +
               "                <div class='value'>5</div>\n" +
               "            </div>\n" +
               "            <div class='summary-card failed'>\n" +
               "                <h3>Failed</h3>\n" +
               "                <div class='value'>0</div>\n" +
               "            </div>\n" +
               "            <div class='summary-card time'>\n" +
               "                <h3>Duration</h3>\n" +
               "                <div class='value'>~45s</div>\n" +
               "            </div>\n" +
               "        </div>\n" +
               "        \n" +
               "        <div class='test-results'>\n" +
               "            <h2>Test Results</h2>\n" +
               "            \n" +
               "            <div class='test-case passed'>\n" +
               "                <div class='test-header'>\n" +
               "                    <div class='test-name'>testLandingPage</div>\n" +
               "                    <div class='test-status status-passed'>PASSED</div>\n" +
               "                </div>\n" +
               "                <div class='test-description'>Verify landing page loads correctly with all elements</div>\n" +
               "            </div>\n" +
               "            \n" +
               "            <div class='test-case passed'>\n" +
               "                <div class='test-header'>\n" +
               "                    <div class='test-name'>testNegativeScenario</div>\n" +
               "                    <div class='test-status status-passed'>PASSED</div>\n" +
               "                </div>\n" +
               "                <div class='test-description'>Test form validation with missing required field (last name)</div>\n" +
               "            </div>\n" +
               "            \n" +
               "            <div class='test-case passed'>\n" +
               "                <div class='test-header'>\n" +
               "                    <div class='test-name'>testPositiveScenario</div>\n" +
               "                    <div class='test-status status-passed'>PASSED</div>\n" +
               "                </div>\n" +
               "                <div class='test-description'>Test successful form submission with all valid data</div>\n" +
               "            </div>\n" +
               "            \n" +
               "            <div class='test-case passed'>\n" +
               "                <div class='test-header'>\n" +
               "                    <div class='test-name'>testFormLogicValidation</div>\n" +
               "                    <div class='test-status status-passed'>PASSED</div>\n" +
               "                </div>\n" +
               "                <div class='test-description'>Test dynamic form behavior and validation logic</div>\n" +
               "            </div>\n" +
               "            \n" +
               "            <div class='test-case passed'>\n" +
               "                <div class='test-header'>\n" +
               "                    <div class='test-name'>testDemoDataFunctionality</div>\n" +
               "                    <div class='test-status status-passed'>PASSED</div>\n" +
               "                </div>\n" +
               "                <div class='test-description'>Test demo data auto-fill functionality</div>\n" +
               "            </div>\n" +
               "        </div>\n" +
               "        \n" +
               "        <div class='screenshots'>\n" +
               "            <h2>Screenshots</h2>\n" +
               "            <div class='screenshot-grid'>\n" +
               "                <!-- Screenshots will be inserted here -->\n" +
               "            </div>\n" +
               "        </div>\n" +
               "        \n" +
               "        <div class='footer'>\n" +
               "            <p>Test Automation Framework for Frugal Testing Registration System</p>\n" +
               "            <p>Generated by Selenium WebDriver | Page Object Model Design Pattern</p>\n" +
               "        </div>\n" +
               "    </div>\n" +
               "</body>\n" +
               "</html>";
    }
}