package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;
import utils.DataGenerator;
import utils.WaitUtils;

import java.time.Duration;

public class AdminTest extends BaseTest {

    @Test
    public void testUserCRUD() {

        LoginPage login = new LoginPage(driver);
        DashboardPage dashboard = new DashboardPage(driver);
        AdminPage admin = new AdminPage(driver);
        WaitUtils waitUtils = new WaitUtils(driver);
        String newUsername = DataGenerator.generateUsername();

        // Login
        login.login(ConfigReader.get("username"), ConfigReader.get("password"));
        // Navigate
        waitUtils.waitForPageLoad("dashboard");
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed!");

        dashboard.goToAdmin();

        int beforeCount = admin.getRecordCount();

        // Add User
        admin.clickAdd();

        admin.fillUserForm(newUsername, ConfigReader.get("password"));

        admin.clickSave();

        int afterAdd = admin.getRecordCount();
        Assert.assertEquals(afterAdd, beforeCount + 1, "User was not added!");

        // Search user
        admin.searchUser(newUsername);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Delete user
        admin.deleteUser();
        driver.navigate().refresh();
        int afterDelete = admin.getRecordCount();
        Assert.assertEquals(afterDelete, beforeCount, "User was not deleted!");
    }
}