# OrangeHRM Automation

A comprehensive test automation framework for OrangeHRM combining UI automation and REST API testing using Selenium WebDriver, TestNG, and REST Assured.

## 📋 Project Overview

This project demonstrates a hybrid approach to test automation, featuring:
- **UI Automation** with Selenium WebDriver
- **REST API Testing** with REST Assured
- **Page Object Model** design pattern
- **Data-Driven Testing** approach
- **Explicit Waits** for reliable test execution
- **Hybrid Testing** combining UI and API workflows

## 🏗️ Project Structure

```
orangehrm-automation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   ├── BaseTest.java              # Base test setup and teardown
│   │   │   │   └── BaseAPITest.java           # Base API test configuration
│   │   │   ├── pages/
│   │   │   │   ├── AdminPage.java             # Admin page object
│   │   │   │   ├── DashboardPage.java         # Dashboard page object
│   │   │   │   └── LoginPage.java             # Login page object
│   │   │   └── utils/
│   │   │       ├── ConfigReader.java          # Configuration properties reader
│   │   │       ├── DataGenerator.java         # Test data generation utilities
│   │   │       └── WaitUtils.java             # Selenium wait utilities
│   │   └── resources/
│   │       └── config.properties              # Configuration file
│   └── test/
│       └── java/
│           └── tests/
│               ├── AdminTest.java             # Admin module UI tests
│               └── APITest.java               # Hybrid UI + API tests
└── pom.xml                                    # Maven configuration

```

## 🛠️ Technologies Used

- **Java 25**
- **Selenium 4.41.0** - Web browser automation
- **TestNG 7.12.0** - Test framework
- **REST Assured 6.0.0** - API testing
- **Maven** - Build tool

## 📦 Dependencies

- Selenium WebDriver
- TestNG
- REST Assured
- Apache Commons Lang

## ⚙️ Setup & Installation

### Prerequisites

- Java 25 or higher
- Maven 3.6+
- Chrome Browser (for ChromeDriver)
- Internet connection for external dependencies

### Installation Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/YousefAzzam77/orangehrm-automation.git
   cd orangehrm-automation
   ```

2. **Install dependencies**:
   ```bash
   mvn clean install
   ```

3. **Configure the test environment**:
   - Edit `src/main/resources/config.properties`
   - Set the OrangeHRM URL, username, password, and candidate information

4. **Run tests**:
   ```bash
   # Run all tests
   mvn test
   
   # Run specific test class
   mvn test -Dtest=AdminTest
   mvn test -Dtest=APITest
   
   # Run specific test method
   mvn test -Dtest=AdminTest#testUserCRUD
   mvn test -Dtest=APITest#testAddAndDeleteCandidateViaAPI
   ```

## 📝 Configuration

Update `src/main/resources/config.properties`:

```ini
url=https://opensource-demo.orangehrmlive.com/
username=Admin
password=admin123
browser=chrome
candidateFirstName=John
candidateMiddleName=Edward
candidateLastName=David
candidateEmailDomain=test.com
candidateContactNumber=1234567890
candidateKeywords=Test Candidate
candidateComment=Added via REST API
candidateDateOfApplication=2026-04-05
```

## 🧪 Test Scenarios

### AdminTest.java
- **Test:** `testUserCRUD()`
- **Description:** Creates, searches, and deletes a user in the Admin module
- **Steps:**
  1. Login to OrangeHRM
  2. Navigate to Admin module
  3. Add a new user with auto-generated credentials
  4. Search for the newly created user
  5. Delete the user and verify deletion

### APITest.java (Hybrid UI + API Test)
- **Test:** `testAddAndDeleteCandidateViaAPI()`
- **Description:** Complete workflow combining UI login and REST API operations
- **Part 1 - UI Login:** Navigate to OrangeHRM and login through web UI
- **Part 2 - Extract Cookie:** Retrieve authentication cookie from Selenium
- **Part 3 - Add Candidate API:** Create a new candidate using REST API endpoint with authentication
- **Part 4 - Delete Candidate API:** Delete the created candidate using REST API endpoint
- **Steps:**
  1. Login through UI automation (Selenium)
  2. Verify dashboard is loaded
  3. Extract authentication cookie from browser session
  4. Send POST request to add candidate via API with cookie
  5. Validate candidate created with valid ID
  6. Send DELETE request to remove candidate via API with cookie
  7. Validate candidate deleted successfully

## 🎯 Key Features

- ✅ **Page Object Model** - Maintainable and scalable page objects
- ✅ **Base Test Classes** - Common setup/teardown logic for UI and API
- ✅ **Explicit Waits** - WaitUtils for reliable element interactions
- ✅ **Data-Driven Testing** - All test data externalized in config.properties
- ✅ **Configuration Management** - Centralized configuration properties
- ✅ **TestNG Framework** - Comprehensive test execution and reporting
- ✅ **Hybrid Testing** - Combine UI automation with REST API calls
- ✅ **Cookie Management** - Extract and reuse authentication cookies across tests

## 🔐 Authentication

### UI Authentication
- Standard login form with username/password

### API Authentication
- REST API calls authenticated using cookies extracted from UI session
- Enables seamless transition from UI login to API operations

## 📊 Test Reports

After running tests, find reports in:
- `target/surefire-reports/` - TestNG reports
- Console output for real-time test status

## 🐛 Troubleshooting

### WebDriver Issues
- Ensure Chrome browser is installed
- ChromeDriver is automatically managed by Selenium Manager (v4.6+)

### API Testing Issues
- Verify cookie extraction is successful (check console for "✅ Authentication cookie extracted")
- Ensure API endpoints are accessible from your network

### Timeout Issues
- Adjust timeout values in `WaitUtils.java` (default: 10 seconds)
- Modify timeout values for slow applications

### Test Failures
- Verify the OrangeHRM instance is accessible
- Check login credentials in `config.properties`
- Ensure the application hasn't changed its UI structure or API contracts
- Verify all candidate properties are set in `config.properties`

## 📚 Learning Resources

- [Selenium Documentation](https://selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [REST Assured Documentation](https://rest-assured.io/)

## 👤 Author

Yousef Othman

---

**Note:** This project uses the OrangeHRM demo instance for testing. For production testing, configure your own OrangeHRM instance in the `config.properties` file.
