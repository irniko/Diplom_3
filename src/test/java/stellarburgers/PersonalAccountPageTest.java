package stellarburgers;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;

@DisplayName("Страница личного кабинета")
public class PersonalAccountPageTest extends TestDataAndConstants {

    private WebDriver driver;
    UserApiMethods userApiMethods;
    PersonalAccountPage personalAccountPage;
    LoginPage loginPage;
    HomePage homePage;
    String accessToken;

    @Before
    public void setUp() {

        userApiMethods = new UserApiMethods();
        accessToken = userApiMethods.create(user).path("accessToken").toString();

        driver = Browsers.createDriver();
        personalAccountPage = new PersonalAccountPage(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        loginPage.implicitWait();
        homePage.implicitWait();
        personalAccountPage.implicitWait();

        homePage.openUrl();
        getLogin();
    }

    @Step("Авторизация пользователя")
    public void getLogin() {
        homePage.clickToPersonalAccountButton();
        loginPage.fillRegisterForm(user.getEmail(), user.getPassword());
        loginPage.clickButtonLogin();
        homePage.explicitWaitToVisibleHeaderHomePage();
    }

    @After
    @Step("Очистка данных")
    public void tearDown() {
        driver.quit();
        userApiMethods.delete(accessToken);
    }

    @Test
    @DisplayName("Переход на главную страницу по кнопке 'Конструктор'")
    public void checkMovingToHomePageByConstructorButton() {
        homePage.clickToPersonalAccountButton();
        personalAccountPage.clickConstructorButton();
        assertTrue(homePage.atPage());
    }

    @Test
    @DisplayName("Переход на главную страницу по клику на логотип Stellar Burgers")
    public void checkMovingToHomePageByLogo() {
        homePage.clickToPersonalAccountButton();
        personalAccountPage.clickLogoButton();
        assertTrue(homePage.atPage());
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке 'Выйти' в личном кабинете")
    public void checkExitFromPersonalAccount() {
        homePage.clickToPersonalAccountButton();
        personalAccountPage.clickExitButton();
        loginPage.explicitWaitToVisibleHeaderLoginPage();
        assertTrue(loginPage.atPage());
        Allure.step("Пользователь успешно вышел из аккаунта");
    }
}