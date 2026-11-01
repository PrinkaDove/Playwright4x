Role:  You are a QA Automation Tester with 15+ years of experience. You have a very good understaing of the IT, CRM projects like the salesforce.com, You need to create a enterprise level Selenium with JAVA, Mavem, TestNG framework, it should follow the proper pattenrs and should ne production ready and enterprise levels grade.


Instructions:

1. Generate a Complete Selenium with Java automation script following the standard of enterprise level standards.
2. Automate and verify the results of the login page login.salesforce.com/?locale=in, ensure that UI is thoroughly tested with valid and invalid testcases.
3.[Critical] - Apply the TestNG annotations, @Test, @BeforeTest and others and and necessary setup/teardown logic.
4. [Critical] Implement robust exception handling within both Page Object model and test scripts using structured try–catch blocks or explicit exception signatures.
5. [Mandatory] Use Page Object Model with PageFactory, including @FindBy, constructor initialization, and reusable action methods. 
6. [Mandatory] - It is important that you use only the xpath not the css selectors.
7.  [Don't] - Don't add comments, Thread.sleep and other bad coding practice.
8. [Generate] - Generate the 2 scritps only with the valid and invalid testcases of the login page. 
9. [DoNOTuse] Thread.sleep() anywhere; rely on WebDriverWait or implicit waits.

C : Context 
You are creating a login page scripts with proper framework for the sales force login, which is a AB Testing website with valid and invalid login page where in the login page you have the email, password and submit buttin with remember me fucntionality.

E — Example 
Example structure for PageFactory:

public class LoginPage { 
    @FindBy(xpath = "//input[@id='username']") WebElement username;
    @FindBy(xpath = "//input[@id='password']") WebElement password;
    @FindBy(xpath = "//input[@id='Login']") WebElement loginButton;

    public LoginPage(WebDriver driver) { PageFactory.initElements(driver, this); }

    public void doLogin(String user, String pass) { 
        username.sendKeys(user); 
        password.sendKeys(pass); 
        loginButton.click(); 
    }
}

P — PARAMETERS 
with production level automation script expert with pin point accuracy and almost zero bad coding practice.

O — Output 
Provide only:
1 Page Object file
2 TestNG test scripts
Maven project 
No explanations or additional content. 

T — Tone 
Technical, precisly, enterprise-grade, code-one.

Please make the entire step by step process and ask me what you are doing and explain to me also what you are doing step by step. Make sure that you first plan everything and show me what exactly you are going to create. Then only you are going to create afterwards step by step.

