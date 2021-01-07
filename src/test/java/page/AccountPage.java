package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class AccountPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://my.asos.com/identity/login?signin=b0e12093cc1e0a0c409ae79d04b09911";

    private final By accountEmailInfo = By.xpath("//*[@id=\"EmailAddress\"]");

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div[1]/div[1]/div[1]/div/div/div[1]/nav/ul[6]/li/a")
    WebElement logout;

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public AccountPage openPage() {
        driver.get(PAGE_URL);
        logger.info("Login page opened");
        return this;
    }

    public String getUserEmail() {
        WebElement linkLoggedInUser = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(accountEmailInfo));
        return linkLoggedInUser.getText();
    }


    public MainPage logout() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(logout));
        logout.click();

        return new MainPage(driver);

    }


}