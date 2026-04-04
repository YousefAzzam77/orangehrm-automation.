package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class LoginPage {

    WebDriver driver;
    WaitUtils wait;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    private By username = By.name("username");
    private By password = By.name("password");
    private By loginBtn = By.xpath("//button[@type='submit']");

    public void login(String user, String pass){
        wait.waitForVisibility(username).sendKeys(user);
        wait.waitForVisibility(password).sendKeys(pass);
        wait.waitForClickable(loginBtn).click();
    }
}