package framework.pages;

import java.time.Duration;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement loginError;

    public LoginPage(WebDriver driver) {
        this.driver = Objects.requireNonNull(driver, "WebDriver must not be null");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            PageFactory.initElements(driver, this);
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to initialize the login page elements.", exception);
        }
    }

    public void open(String url) {
        try {
            driver.get(url);
            wait.until(ExpectedConditions.visibilityOf(usernameField));
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to open the Salesforce login page.", exception);
        }
    }

    public void enterUsername(String username) {
        enterText(usernameField, username, "username");
    }

    public void enterPassword(String password) {
        enterText(passwordField, password, "password");
    }

    public void setRememberMe(boolean selected) {
        try {
            wait.until(ExpectedConditions.visibilityOf(rememberMeCheckbox));
            if (rememberMeCheckbox.isSelected() != selected) {
                rememberMeCheckbox.click();
            }
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to update the remember-me selection.", exception);
        }
    }

    public void submitLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to submit the Salesforce login form.", exception);
        }
    }

    public void login(String username, String password, boolean rememberMe) {
        enterUsername(username);
        enterPassword(password);
        setRememberMe(rememberMe);
        submitLogin();
    }

    public String getLoginErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginError)).getText().trim();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("The Salesforce login error message was not displayed.", exception);
        }
    }

    public boolean isAuthenticated() {
        try {
            return wait.until(currentDriver -> !currentDriver.getCurrentUrl().contains("login.salesforce.com"));
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Salesforce did not complete the login redirect.", exception);
        }
    }

    private void enterText(WebElement field, String value, String fieldName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(field));
            field.clear();
            field.sendKeys(Objects.requireNonNull(value, fieldName + " must not be null"));
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to enter the " + fieldName + ".", exception);
        }
    }
}