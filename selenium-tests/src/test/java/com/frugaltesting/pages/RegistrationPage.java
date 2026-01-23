package com.frugaltesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {
    
    private WebDriver driver;
    
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Page Elements - Remove @FindBy for resetBtn if it doesn't exist
    @FindBy(css = ".logo h1")
    public WebElement pageTitle;
    
    @FindBy(id = "firstName")
    public WebElement firstNameInput;
    
    @FindBy(id = "lastName")
    public WebElement lastNameInput;
    
    @FindBy(id = "email")
    public WebElement emailInput;
    
    @FindBy(id = "phone")
    public WebElement phoneInput;
    
    @FindBy(id = "age")
    public WebElement ageInput;
    
    @FindBy(css = "input[name='gender'][value='male']")
    public WebElement genderMale;
    
    @FindBy(css = "input[name='gender'][value='female']")
    public WebElement genderFemale;
    
    @FindBy(id = "address")
    public WebElement addressTextarea;
    
    @FindBy(id = "country")
    public WebElement countrySelect;
    
    @FindBy(id = "state")
    public WebElement stateSelect;
    
    @FindBy(id = "city")
    public WebElement citySelect;
    
    @FindBy(id = "password")
    public WebElement passwordInput;
    
    @FindBy(id = "confirmPassword")
    public WebElement confirmPasswordInput;
    
    @FindBy(id = "terms")
    public WebElement termsCheckbox;
    
    @FindBy(id = "submitBtn")
    public WebElement submitButton;
    
    // Remove or comment out resetBtn if it doesn't exist in HTML
    // @FindBy(id = "resetBtn")
    // public WebElement resetButton;
    
    @FindBy(id = "lastNameError")
    public WebElement lastNameError;
    
    @FindBy(id = "formAlert")
    public WebElement formAlert;
    
    // Page Methods
    public void openPage(String url) {
        driver.get(url);
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public void enterFirstName(String firstName) {
        if (firstNameInput.isDisplayed()) {
            firstNameInput.clear();
            firstNameInput.sendKeys(firstName);
        }
    }
    
    public void enterLastName(String lastName) {
        if (lastNameInput.isDisplayed()) {
            lastNameInput.clear();
            lastNameInput.sendKeys(lastName);
        }
    }
    
    public void enterEmail(String email) {
        if (emailInput.isDisplayed()) {
            emailInput.clear();
            emailInput.sendKeys(email);
        }
    }
    
    public void enterPhoneNumber(String phone) {
        if (phoneInput.isDisplayed()) {
            phoneInput.clear();
            phoneInput.sendKeys(phone);
        }
    }
    
    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male") && genderMale.isDisplayed()) {
            genderMale.click();
        } else if (gender.equalsIgnoreCase("female") && genderFemale.isDisplayed()) {
            genderFemale.click();
        }
    }
    
    public void enterAge(String age) {
        if (ageInput.isDisplayed()) {
            ageInput.clear();
            ageInput.sendKeys(age);
        }
    }
    
    public void enterAddress(String address) {
        if (addressTextarea.isDisplayed()) {
            addressTextarea.clear();
            addressTextarea.sendKeys(address);
        }
    }
    
    public void selectCountry(String country) {
        if (countrySelect.isDisplayed()) {
            Select select = new Select(countrySelect);
            select.selectByValue(country);
        }
    }
    
    public void selectState(String state) {
        if (stateSelect.isDisplayed()) {
            Select select = new Select(stateSelect);
            select.selectByValue(state);
        }
    }
    
    public void selectCity(String city) {
        if (citySelect.isDisplayed()) {
            Select select = new Select(citySelect);
            select.selectByValue(city);
        }
    }
    
    public void enterPassword(String password) {
        if (passwordInput.isDisplayed()) {
            passwordInput.clear();
            passwordInput.sendKeys(password);
        }
    }
    
    public void enterConfirmPassword(String password) {
        if (confirmPasswordInput.isDisplayed()) {
            confirmPasswordInput.clear();
            confirmPasswordInput.sendKeys(password);
        }
    }
    
    public void acceptTerms() {
        if (termsCheckbox.isDisplayed() && !termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
    }
    
    public void submitForm() {
        if (submitButton.isDisplayed() && submitButton.isEnabled()) {
            submitButton.click();
        }
    }
    
    // Remove resetForm method or make it safe
    public void resetForm() {
        // Safe implementation - just reload the page
        driver.navigate().refresh();
    }
    
    public boolean isSubmitEnabled() {
        return submitButton.isEnabled();
    }
    
    public boolean isErrorDisplayed(WebElement errorElement) {
        try {
            return errorElement.isDisplayed() && !errorElement.getText().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSuccessAlertDisplayed() {
        try {
            return formAlert.isDisplayed() && formAlert.getAttribute("class").contains("success");
        } catch (Exception e) {
            return false;
        }
    }
}