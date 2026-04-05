package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

public class BaseAPITest {

    protected RequestSpecification request;

    public BaseAPITest() {
        // Set base URI for all API tests
        RestAssured.baseURI = ConfigReader.get("url");

        // Create a base request specification
        request = RestAssured.given()
                .contentType("application/json");
    }

    protected RequestSpecification getRequestSpec() {
        return request;
    }

    protected String getBaseUrl() {
        return ConfigReader.get("url");
    }

    protected String getUsername() {
        return ConfigReader.get("username");
    }

    protected String getPassword() {
        return ConfigReader.get("password");
    }
}
