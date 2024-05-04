package stellarburgers;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

@DisplayName("Авторизация пользователя")
@RunWith(Parameterized.class)
public class LoginTest extends TestDataAndConstants {

    private WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    UserApiMethods userApiMethods;
    private final By buttonToLogin;
    private final String urlOfPageWithRegisterButton;
    private final String description;
    String accessToken;

    public LoginTest(By buttonToLogin, String urlOfPageWithRegisterButton, String description) {
        this.buttonToLogin = buttonToLogin;
        this.urlOfPageWithRegisterButton = urlOfPageWithRegisterButton;
        this.description = description;
    }

    @Parameterized.Parameters(name = "По кнопке: {2}")
    public static Object [][] getData() {
        return getTestData();
    }

    @Before
    public void setUp() {
        userApiMethods = new UserApiMethods();
        accessToken = userApiMethods.create(user).path("accessToken").toString();
        driver = Browsers.createDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        loginPage.implicitWait();
    }

    @After
    @Step("Очистка данных")
    public void tearDown() {
        driver.quit();
        userApiMethods.delete(accessToken);
    }

    @Test
    @DisplayName("Авторизация пользователя по кнопке")
    public void checkLoginByButton() {
        Allure.parameter("По кнопке", description);
        Allure.step("Открываем страницу " + urlOfPageWithRegisterButton);
        loginPage.openUrl(urlOfPageWithRegisterButton);
        assertTrue(loginPage.isLoginButtonVisible(buttonToLogin));
        loginPage.clickButtonToLogin(buttonToLogin);
        assertTrue(loginPage.atPage());

        loginPage.fillRegisterForm(user.getEmail(), user.getPassword());
        loginPage.clickButtonLogin();
        assertTrue(homePage.isVisibleHeaderHomePage());
        Allure.step("Пользователь успешно авторизован");
    }

}