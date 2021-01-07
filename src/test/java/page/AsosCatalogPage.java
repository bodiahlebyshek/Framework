package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AsosCatalogPage extends AbstractPage  {

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath="//*[@id=\"product-22072051\"]/a/div[1]/img")
    WebElement closeModalLocator;
    @FindBy(xpath="//*[@id=\"//*[@id=\"plp\"]/div/div/div[1]/div/div/div/div[1]/ul/li[8]/div/button")
    WebElement colorFilterLocator;
    @FindBy(xpath="//*[@id=\"plp\"]/div/div/div[1]/div/div/div/div[1]/ul/li[8]/div/div/div/ul/li[1]/div/label")
    WebElement colorLocator;
    @FindBy(xpath="//*[@id=\"product-23119884\"]/a/div[1]/img")
    WebElement itemByFilterLocator;
    @FindBy(xpath="//*[@id=\"plp\"]/div/div/div[1]/div/div/div/div[1]/ul/li[1]/div/button")
    WebElement sortByNewLocator;
    @FindBy(xpath="//*[@id=\"plp_web_sort_whats_new\"]")
    WebElement newItem;
    @FindBy(xpath="//*[@id=\"product-21477142\"]/a/div[1]/img")
    WebElement sortByNewSelected;

    private String url;
    public AsosCatalogPage(WebDriver driver, String hoodieUrl){
        super(driver);
        this.url = hoodieUrl;
    }


    public AsosCatalogPage openPage() {
        driver.get(url);
        logger.info("AsosCatalog page opened");
        return this;
    }

    public AsosCatalogPage applyColorFilter(){
        WebElement colorFilterBtn = new WebDriverWait(driver,20)
                .until(ExpectedConditions.presenceOfElementLocated((By) colorFilterLocator));
        colorFilterBtn.click();
        WebElement colorBtn = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated((By) colorLocator));
        colorBtn.click();
        WebElement closeModalBtn = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated((By) closeModalLocator));
        closeModalBtn.click();
        logger.info("Color filter applied");
        return this;
    }

    public AsosProductPage openItemWithFilter(){
        WebElement itemByFilterBtn = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated((By) itemByFilterLocator));
        itemByFilterBtn.click();
        logger.info("AsosProduct page opened with filter");
        return new AsosProductPage(driver);
    }

    public AsosCatalogPage sortByNew() {
        WebElement sortByDropdown = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated((By) sortByNewLocator));
        sortByDropdown.click();
        WebElement priceHighToLowBtn = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated((By) newItem));
        priceHighToLowBtn.click();
        logger.info("AsosCatalog page sorted by new");
        return this;
    }

    public AsosProductPage sortByNewSelected(){
        WebElement itemByFilterBtn = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated((By) sortByNewSelected));
        itemByFilterBtn.click();
        logger.info("AsosCatalog page selected sort");
        return new AsosProductPage(driver);
    }
}