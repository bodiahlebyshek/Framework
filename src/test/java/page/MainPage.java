package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage
{

    private final By language = By.xpath("//*[@id=\"chrome-header\"]/header/div[2]/div/ul/li[3]/div/button");
    private final By chengeLanguageButton = By.xpath("//button[@data-testid=\"save-country-button\"]");
    private final By price = By.xpath("//*[@id=\"chrome-header\"]/header/div[2]/div/ul/li[3]/div/button");
    private final By chengePriceButton = By.id("//button[@data-testid=\"save-country-button\"]");
    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://www.asos.com/ru/";
    private final String ITEM_PAGE_URL = "https://www.asos.com/ru/adidas-originals/seraya-futbolka-s-krupnym-logotipom-adidas-originals-adicolor/prd/21133012?clr=seryj&colourwayid=60142916&SearchQuery=&cid=7113";

    @FindBy(xpath = "//*[@id=\"chrome-sticky-header\"]/div[1]/div/div/div/form/div/button")
    WebElement SearchButton;

    @FindBy(xpath = "//*[@id=\"chrome-search\"]")
    WebElement SearchField;

    @FindBy(xpath = "//*[@id=\"aside-content\"]/div[1]/h1")
    WebElement itemTitle;

    @FindBy(xpath = "//*[@id=\"EmailAddress\"]")
    WebElement mailingField;

    @FindBy(xpath = "//*[@id=\"signInForm\"]/fieldset/div[2]/div[1]/div[1]/span")
    WebElement mailingFieldError;

    @FindBy(xpath = "//*[@id=\"myAccountDropdown\"]/button/span")
    WebElement loginButton;



    public MainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public MainPage openPage()
    {
        driver.get(PAGE_URL);
        logger.info("Main page opened");
        return this;
    }


    public MainPage typeSearchRequest(String searchRequest){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(SearchField))
                .click();

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(SearchField))
                .sendKeys(searchRequest);

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(SearchButton))
                .click();


        return this;
    }

    public String getItemTitle(){
        String ResultItemTitle = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(itemTitle))
                .getText();
        logger.info("Item getting");
        return ResultItemTitle;
    }

    public CatalogPage searchItem(String searchRequest){
        this.typeSearchRequest(searchRequest);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(SearchField))
                .sendKeys(Keys.ENTER);
        logger.info("An item search has been made");
        return new CatalogPage(driver);
    }

    public MainPage sendEmailToMailingField(User user)
    {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(mailingField))
                .sendKeys(user.getUsername(),Keys.ENTER);
        logger.info("Email has been sent to the mailing box");
        return this;
    }

    public LoginPage pressLoginButton(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
        logger.info("Login button pressed");
        return new LoginPage(driver);
    }
    public MainPage changeLang(){
        driver.findElement(language).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
        Select sizeChoiceCombobox = new Select(driver.findElement(By.id("country")));
        sizeChoiceCombobox.selectByVisibleText("Россия");
        /*driver.findElement(By.id("RU")).click();*/
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
        chengeLanguageButton();
        logger.info("Language changed");
        return this;
    }
    public void chengeLanguageButton(){
        driver.findElement(chengeLanguageButton).click();

    }

    public String getMenuLang(){
        System.out.println(driver.findElement(By.id("men-floor")).getText());
        logger.info("Language getting");
        return driver.findElement(By.id("men-floor")).getText();

    }
    public MainPage changePrice(){
        driver.findElement(price).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
        Select sizeChoiceCombobox = new Select(driver.findElement(By.id("country")));
        sizeChoiceCombobox.selectByVisibleText("Россия");
        /*driver.findElement(By.id("RU")).click();*/
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
        chengePriseButton();
        logger.info("Price changed");
        return this;
    }
    public void chengePriseButton(){
        driver.findElement(chengePriceButton).click();

    }
    public MainPage openItemPage()
    {
        driver.get(ITEM_PAGE_URL);
        logger.info("Item page opened");
        return this;
    }

    public String getPriseItem(){
        System.out.println(driver.findElement(By.xpath("//*[@id=\"product-price\"]/div/span/span[4]/span[1]")).getText());
        logger.info("Prise item getting");
        return driver.findElement(By.xpath("//*[@id=\"product-price\"]/div/span/span[4]/span[1]")).getText();

    }


}