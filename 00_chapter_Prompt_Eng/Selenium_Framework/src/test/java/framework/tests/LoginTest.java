package framework.tests;

import framework.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private String loginUrl;

    @BeforeTest
    public void configureTest() {
        String configuredUrl = System.getenv("SF_LOGIN_URL");
        loginUrl = configuredUrl == null || configuredUrl.isBlank()
                ? "https://login.salesforce.com/?locale=in"
                : configuredUrl;
    }

    @BeforeMethod
    public void setUp() {
        try {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ZERO);
            driver.manage().window().maximize();
            loginPage = new LoginPage(driver);
            loginPage.open(loginUrl);
        } catch (RuntimeException exception) {
            closeDriverAfterSetupFailure(exception);
            throw new IllegalStateException("Unable to prepare the Salesforce login test.", exception);
        }
    }

    @Test(description = "Valid credentials complete the Salesforce login flow")
    public void validCredentialsAllowLogin() {
        String username = System.getenv("SF_USERNAME");
        String password = System.getenv("SF_PASSWORD");
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new SkipException("Set SF_USERNAME and SF_PASSWORD to run the valid-login test.");
        }

        try {
            loginPage.login(username, password, true);
            Assert.assertTrue(loginPage.isAuthenticated(), "Expected Salesforce to leave the login page.");
        } catch (RuntimeException exception) {
            throw new AssertionError("The valid Salesforce login flow failed.", exception);
        }
    }

    @Test(description = "Invalid credentials display a login error")
    public void invalidCredentialsDisplayError() {
        String username = System.getenv("SF_INVALID_USERNAME");
        String password = System.getenv("SF_INVALID_PASSWORD");
        String invalidUsername = username == null || username.isBlank() ? "invalid.user@example.invalid" : username;
        String invalidPassword = password == null || password.isBlank() ? "InvalidPassword-NotARealCredential" : password;

        try {
            loginPage.login(invalidUsername, invalidPassword, false);
            Assert.assertFalse(loginPage.getLoginErrorMessage().isBlank(), "Expected a non-empty login error message.");
        } catch (RuntimeException exception) {
            throw new AssertionError("The invalid Salesforce login flow did not produce the expected error.", exception);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver == null) {
            return;
        }
        try {
            driver.quit();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to close the browser after the test.", exception);
        } finally {
            driver = null;
            loginPage = null;
        }
    }

    private void closeDriverAfterSetupFailure(RuntimeException setupException) {
        if (driver == null) {
            return;
        }
        try {
            driver.quit();
        } catch (WebDriverException cleanupException) {
            setupException.addSuppressed(cleanupException);
        } finally {
            driver = null;
            loginPage = null;
        }
    }
}