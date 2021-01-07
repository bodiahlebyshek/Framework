package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://my.asos.com/";

    @FindBy(xpath = "//*[@id=\"EmailAddress\"]")
    private WebElement inputLogin;

    @FindBy(xpath = "//*[@id=\"Password\"]")
    private WebElement inputPassword;

    @FindBy(xpath = "//*[@id=\"signin\"]")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//*[@id=\"loginErrorMessage\"]")
    private WebElement emailFieldError;

    @FindBy(xpath = "//*[@id=\"Password-error\"]")
    private WebElement passwordFieldError;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div[1]/div[1]/div[1]/div/div/div[1]/div/div/div[2]/div[2]/div/div[2]/span")
    WebElement LoginText;

    @FindBy(xpath = "//*[@id=\"loginErrorMessage\"]")
    WebElement notifyText;

    @FindBy(xpath = "//*[@id=\"myaccount-dropdown\"]/div/div/div/div/span/a[1]")
    WebElement title;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div[1]/div[1]/div[1]/div/div/div[1]/div/div/div[2]/div[2]/div/div[2]/span")
    private WebElement LoginButton;

    @FindBy(xpath = "//*[@id=\"myAccountDropdown\"]/button")
    private WebElement AccountButton;

    @FindBy(xpath = "//*[@id=\"myaccount-dropdown\"]/div/div/div/div/span/a[1]")
    WebElement titleLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public LoginPage openPage() {
        driver.get(PAGE_URL);
        logger.info("Login page opened");
        return this;
    }

    public AccountPage login(User user) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        inputLogin.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        buttonSubmit.click();
        logger.info("Login performed");
        return new AccountPage(driver);
    }

    public String getEmailFieldError() {
        String resultEmailFieldError = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(emailFieldError))
                .getText();

        return resultEmailFieldError;
    }

    public String getPasswordFieldError() {
        String resultPasswordFieldError = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(passwordFieldError))
                .getText();

        return resultPasswordFieldError;
    }

    public String getLoginText() {
        String resultNotifyText = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(LoginText))
                .getText();
        return resultNotifyText;
    }

    public String getTileLogout() {
        String resultTitle = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(title))
                .getText();
        return resultTitle;
    }
    public String getTileLogin() {
        String resultTitle = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(titleLogin))
                .getText();
        return resultTitle;
    }



    public LoginPage openLogin() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(AccountButton))
                .click();

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(LoginButton))
                .click();
        logger.info("Account opened");
        return this;

    }
}