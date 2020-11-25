import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
public class WebDriverAsosTest {
    WebDriver driver;

    public WebDriverAsosTest() {
    }

    @BeforeMethod
    public void preparationForTheTest() {
        System.setProperty("webdriver.chrome.driver", "E:/WebDriver/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.get("https://www.asos.com/ru/asos-design/myatno-zelenyj-oversized-dzhemper-v-shahmatnuyu-kletku-asos-design-tall/prd/21752016?clr=myatnyj&colourwayid=60219900&SearchQuery=&cid=7617");
    }

    @Test
    public void addToFavouritesTest() throws InterruptedException {
        Select sizeChoiceCombobox = new Select (driver.findElement(By.xpath("//*[@id=\"main-size-select-0\"]")));
        sizeChoiceCombobox.selectByVisibleText("L Длинный");
        List<WebElement> addToBasketButton = this.driver.findElements(By.xpath("//*[@id=\"product-add\"]/div/a/span[2]"));
        ((WebElement) addToBasketButton.get(0)).click();
        this.driver.get("https://www.asos.com/ru/bag?ctaref=mini%20bag");
        List<WebElement> basketList = this.driver.findElements(By.xpath("//*[@id=\"bagApp\"]/div/div/div[1]/div[1]/bag-item-list/ul"));
        Assert.assertTrue("Favourite list is empty", basketList.size() > 0);
    }

    @AfterMethod
    public void BrowserTearDown() {
        this.driver.quit();
    }
}

