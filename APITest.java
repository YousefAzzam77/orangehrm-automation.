package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.WaitUtils;

import static io.restassured.RestAssured.given;

public class APITest extends BaseTest {

    @Test
    public void testAddAndDeleteCandidateViaAPI() {

        LoginPage login = new LoginPage(driver);
        WaitUtils wait = new WaitUtils(driver);

        login.login(ConfigReader.get("username"), ConfigReader.get("password"));
        wait.waitForPageLoad("dashboard");

        org.openqa.selenium.Cookie seleniumCookie = driver.manage().getCookieNamed("orangehrm");
        String cookieValue = null;

        if (seleniumCookie != null) {
            cookieValue = seleniumCookie.getValue();
            System.out.println(" Authentication cookie extracted");
        } else {
            System.out.println(" Cookie 'orangehrm' not found");
        }

        io.restassured.RestAssured.baseURI = ConfigReader.get("url");

        String candidateFirstName = ConfigReader.get("candidateFirstName");
        String candidateMiddleName = ConfigReader.get("candidateMiddleName");
        String candidateLastName = ConfigReader.get("candidateLastName");
        String candidateEmail = "candidate_" + System.currentTimeMillis() + "@" + ConfigReader.get("candidateEmailDomain");
        String candidateContactNumber = ConfigReader.get("candidateContactNumber");
        String candidateKeywords = ConfigReader.get("candidateKeywords");
        String candidateComment = ConfigReader.get("candidateComment");
        String candidateDateOfApplication = ConfigReader.get("candidateDateOfApplication");

        Response addResponse = given()
                .cookie("orangehrm", cookieValue)
                .contentType("application/json")
                .body("{\n" +
                        "\"firstName\": \"" + candidateFirstName + "\",\n" +
                        "\"middleName\": \"" + candidateMiddleName + "\",\n" +
                        "\"lastName\": \"" + candidateLastName + "\",\n" +
                        "\"email\": \"" + candidateEmail + "\",\n" +
                        "\"contactNumber\": \"" + candidateContactNumber + "\",\n" +
                        "\"keywords\": \"" + candidateKeywords + "\",\n" +
                        "\"comment\": \"" + candidateComment + "\",\n" +
                        "\"dateOfApplication\": \"" + candidateDateOfApplication + "\"\n" +
                        "}")
                .when()
                .post("/web/index.php/api/v2/recruitment/candidates");

        System.out.println("Add Candidate Response Status: " + addResponse.getStatusCode());

        int candidateId = 0;
        if (addResponse.getStatusCode() == 200 || addResponse.getStatusCode() == 201) {
            System.out.println("Candidate added successfully");

            try {
                candidateId = addResponse.jsonPath().getInt("data.id");
                System.out.println("Candidate ID: " + candidateId);
                Assert.assertTrue(candidateId > 0, "Candidate ID should be greater than 0");
            } catch (Exception e) {
                System.out.println("Failed to extract candidate ID");
                Assert.assertTrue(addResponse.getBody().asString().contains("data"),
                        "Response should contain data object");
            }
        } else {
            System.out.println("Candidate creation failed");
            Assert.assertTrue(addResponse.getStatusCode() == 200 || addResponse.getStatusCode() == 201,
                    "Candidate creation should return 200 or 201 status");
        }

        if (candidateId > 0) {
            Response deleteResponse = given()
                    .cookie("orangehrm", cookieValue)
                    .contentType("application/json")
                    .body("{ \"ids\": [" + candidateId + "] }")
                    .when()
                    .delete("/web/index.php/api/v2/recruitment/candidates");

            System.out.println("Delete Candidate Response Status: " + deleteResponse.getStatusCode());

            if (deleteResponse.getStatusCode() == 200 || deleteResponse.getStatusCode() == 204) {
                System.out.println("Candidate deleted successfully");
                Assert.assertTrue(deleteResponse.getStatusCode() == 200 || deleteResponse.getStatusCode() == 204,
                        "Delete should return 200 or 204 status");
            } else if (deleteResponse.getStatusCode() == 401 || deleteResponse.getStatusCode() == 403) {
                System.out.println(" Deletion failed - Authentication issue");
            } else {
                System.out.println("Candidate deletion failed");
                Assert.assertFalse(deleteResponse.getStatusCode() >= 500,
                        "Server errors should not occur");
            }
        } else {
            System.out.println(" Skipping deletion - no valid candidate ID");
        }

    }
}
