package page;
        import org.apache.logging.log4j.LogManager;
        import org.apache.logging.log4j.Logger;
        import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AsosProductPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    @FindBy(xpath="//*[@id=\"product-colour\"]/section/div/div/span")
    WebElement itemByFilterColorLocator;
    @FindBy(xpath="//*[@id=\"chrome-breadcrumb\"]/div/nav/ol/li[4]/a")
    WebElement getSortNemSymbol;
    private String url;


    public AsosProductPage(WebDriver driver, String productUrl) {
        super(driver);
        this.url = productUrl;

    }

    public AsosProductPage(WebDriver driver) {
        super(driver);
    }


    public AsosProductPage openPage() {
        driver.get(url);
        logger.info("AsosProduct page opened");
        return this;
    }

    public String getItemColor() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated((By) itemByFilterColorLocator)).getText();

    }

    public String getSortNemSymbol() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated((By) getSortNemSymbol)).getText();
    }

}