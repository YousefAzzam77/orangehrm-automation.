package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {
    WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    private By recordCount = By.xpath(

            "//span[contains(.,'Records Found')]"

    );
    private By addBtn = By.xpath(

            "//button[text()=' Add ']"

    );
    private By saveBtn = By.xpath(
            "//button[text()=' Save ']"
    );
    private By searchBtn = By.xpath(
            "//button[text()=' Search ']"
    );
private By deleteBtn = By.xpath("//button[i[contains(@class, 'bi-trash')]]");

    private By confirmDeleteButton = By.xpath(
            "//button[contains(., 'Yes, Delete')]"
    );
    private By userRoleDropdown = By.xpath(
            "//label[text()='User Role']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]"
    );
    private By employeeNameInput = By.xpath(

            "//label[text()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input"

    );

    private By statusDropdown = By.xpath(
            "//label[text()='Status']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]"
    );

    private By usernameInput = By.xpath(
            "//label[text()='Username']/ancestor::div[contains(@class,'oxd-input-group')]//input"
    );

    private By passwordInput = By.xpath(
            "//label[text()='Password']/ancestor::div[contains(@class,'oxd-input-group')]//input"
    );
    private By confirmPasswordInput = By.xpath(
            "//label[text()='Confirm Password']/ancestor::div[contains(@class,'oxd-input-group')]//input"
    );


 public int getRecordCount() {
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

     String text = wait.until(ExpectedConditions
             .visibilityOfElementLocated(recordCount))
             .getText();

    return Integer.parseInt(text.replaceAll("\\D+", ""));
 }




    public void clickAdd() {
        driver.findElement(addBtn).click();
    }

    public void clickSave() {
        driver.findElement(saveBtn).click();
    }

    public void deleteUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
    }
    public void fillUserForm(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // User Role
        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='listbox']//span[text()='Admin']")
    )).click();

        // Employee Name
        WebElement emp = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameInput));
    emp.sendKeys("a");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span")
        )).click();

        // Status
    wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@role='listbox']//span[text()='Enabled']")
        )).click();

        // Username
    wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).sendKeys(username);

        // Password
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);

    // Confirm Password
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordInput)).sendKeys(password);
    }





















    public void searchUser(String username) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(searchBtn).click();
    }
}








