# OrangeHRM Automation

A comprehensive UI test automation framework for OrangeHRM using Selenium WebDriver and TestNG.

## 📋 Project Overview

This project demonstrates a robust UI automation approach for testing OrangeHRM, featuring:
- **UI Automation** with Selenium WebDriver
- **Page Object Model** design pattern
- **Data-Driven Testing** approach
- **Explicit Waits** for reliable test execution

## 🏗️ Project Structure

```
orangehrm-automation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   └── BaseTest.java              # Base test setup and teardown
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
│               └── AdminTest.java             # Admin module tests
└── pom.xml                                    # Maven configuration

```

## 🛠️ Technologies Used

- **Java 25**
- **Selenium 4.41.0** - Web browser automation
- **TestNG 7.12.0** - Test framework
- **Maven** - Build tool

## 📦 Dependencies

- Selenium WebDriver
- TestNG
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
   - Set the OrangeHRM URL, username, and password

4. **Run tests**:
   ```bash
   # Run all tests
   mvn test
   
   # Run specific test class
   mvn test -Dtest=AdminTest
   
   # Run specific test method
   mvn test -Dtest=AdminTest#testUserCRUD
   ```

## 📝 Configuration

Update `src/main/resources/config.properties`:

```ini
url=https://opensource-demo.orangehrmlive.com/
username=Admin
password=admin123
browser=chrome
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

## 🎯 Key Features

- ✅ **Page Object Model** - Maintainable and scalable page objects
- ✅ **Base Test Class** - Common setup/teardown logic
- ✅ **Explicit Waits** - WaitUtils for reliable element interactions
- ✅ **Data Generation** - Random test data generation
- ✅ **Configuration Management** - Externalized configuration properties
- ✅ **TestNG Framework** - Comprehensive test execution and reporting

## 🔐 Authentication

### UI Authentication
- Standard login form with username/password

## 📊 Test Reports

After running tests, find reports in:
- `target/surefire-reports/` - TestNG reports
- Console output for real-time test status

## 🐛 Troubleshooting

### WebDriver Issues
- Ensure Chrome browser is installed
- ChromeDriver is automatically managed by Selenium Manager (v4.6+)

### Timeout Issues
- Adjust timeout values in `WaitUtils.java` (default: 10 seconds)
- Modify timeout values for slow applications

### Test Failures
- Verify the OrangeHRM instance is accessible
- Check login credentials in `config.properties`
- Ensure the application hasn't changed its UI structure

## 📚 Learning Resources

- [Selenium Documentation](https://selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)

## 👤 Author

Yousef Azzam

## 📄 License

This project is open-source and available for educational purposes.

## 🤝 Contributing

Contributions are welcome! Feel free to submit pull requests or open issues.

---

**Note:** This project uses the OrangeHRM demo instance for testing. For production testing, configure your own OrangeHRM instance in the `config.properties` file.
