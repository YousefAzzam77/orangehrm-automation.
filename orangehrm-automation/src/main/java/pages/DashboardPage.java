package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class DashboardPage {

    WebDriver driver;
    WaitUtils wait;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    private By adminTab = By.xpath("//span[text()='Admin']");
    private By adminHeader = By.xpath("//h6[text()='Admin']");


    public void goToAdmin(){
        wait.waitForClickable(adminTab).click();

        // Wait until Admin page loads
        wait.waitForVisibility(adminHeader);
    }
}