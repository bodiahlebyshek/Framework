package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AsosCatalogPage;
import page.LoginPage;
import page.MainPage;
import service.UserCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class AsosTests extends CommonConditions {

    @Test(priority = 1)
    public void logoutTest() {
        UserCreator userCreator = new UserCreator();
        User testUser = userCreator.withCredentialsFromProperty();

        String resultTitle = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .logout()
                .pressLoginButton()
                .getTileLogout();

        assertThat(resultTitle, is(equalTo("Sign In\n")));
    }
    @Test(priority = 2)
    public void SeacrhItemTest(){
        String resultItemTitle = new MainPage(driver)
                .openPage()
                .typeSearchRequest("BOSS Magn large logo cross body bag in black")
                .getItemTitle();

        assertThat(resultItemTitle, is(equalTo("BOSS Magn large logo cross body bag in black")));
    }



    @Test(priority = 3)

    public void incorrectEmailLoginTest(){
        UserCreator userCreator = new UserCreator();
        User testUser = userCreator.withIncorrectEmail();
        new LoginPage(driver)
                .openPage()
                .login(testUser);

        String ResultEmailFieldError =  new LoginPage(driver).getEmailFieldError();
        assertThat(ResultEmailFieldError, is(equalTo("Looks like either your email address or password were incorrect. Wanna try again?")));
    }


    @Test(priority = 4)
    public void emptyPasswordLoginTest() {
        UserCreator userCreator = new UserCreator();
        User testUser = userCreator.withEmptyPassword();
        new LoginPage(driver)
                .openPage()
                .login(testUser);

        String ResultPasswordFieldError =  new LoginPage(driver).getPasswordFieldError();

        assertThat(ResultPasswordFieldError, is(equalTo("Hey, we need a password here")));
    }
    @Test(priority = 5)
    public void incorrentDataLoginTest(){
        UserCreator userCreator = new UserCreator();
        User testUser = userCreator.withCredentialsFromProperty();

        new LoginPage(driver)
                .openPage()
                .login(testUser);

        String ResultLoginText = new LoginPage(driver).getLoginText();
        assertThat(ResultLoginText, is(equalTo("Uladzislau Ilyinou")));
    }
    @Test(priority = 6)
    public void incorrectSeacrhTest() {
        String ResultEmptyMessageText = new MainPage(driver)
                .openPage()
                .searchItem("aaaaasdadadadsada")
                .getEmptyMessageText();

        assertThat(ResultEmptyMessageText, is(equalTo("But don't give up – check the spelling or try less specific search terms.")));

    }
    @Test(priority = 7)
    public void colorFilterTest(){
        String expectedColor = "Beige";
        String selectedItemColor = new AsosCatalogPage(driver,"https://www.asos.com/men/new-in/new-in-clothing/cat/?cid=6993&nlid=mw|clothing|shop+by+product")
                .openPage()
                .applyColorFilter()
                .openItemWithFilter()
                .getItemColor();
        assertThat(selectedItemColor, containsString(expectedColor));
    }

    @Test(priority = 8)
    public void sprtByNewTest(){
        String getSortNemSymbol = "New In: Clothing";
        String selectedSortNemSymbol = new AsosCatalogPage(driver,"https://www.asos.com/men/new-in/new-in-clothing/cat/?cid=6993&nlid=mw|clothing|shop+by+product")
                .openPage()
                .sortByNew()
                .sortByNewSelected()
                .getSortNemSymbol();
        assertThat(selectedSortNemSymbol, containsString(getSortNemSymbol));}


@Test(priority = 9)
public void ChangeLangTest(){
    MainPage mainPage = new MainPage(driver);
    Assert.assertEquals(
            mainPage.openPage()
                    .changeLang()
                    .getMenuLang(),
            "МУЖСКОЕ",
            "language has not been changes"
            );
}
    @Test(priority = 10)
    public void ChangePriceTest(){
        MainPage mainPage = new MainPage(driver);
        Assert.assertEquals(
                mainPage.openItemPage()
                        .changePrice()
                        .getPriseItem(),
                "2 190,00 руб.",
                "Prise has not been changes"
        );
    }
}