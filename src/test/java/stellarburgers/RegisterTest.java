package stellarburgers;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

@DisplayName("Регистрация пользователя")
public class RegisterTest extends TestDataAndConstants {

    private WebDriver driver;
    RegisterPage registerPage;
    LoginPage loginPage;
    UserApiMethods userApiMethods;
    String accessToken;

    @Before
    public void setUp() {
        driver = Browsers.createDriver();
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        userApiMethods = new UserApiMethods();
        registerPage.openUrl();
        registerPage.implicitWait();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Step("Очистка данных")
    public void cleanUp() {
        accessToken = userApiMethods.login(UserLoginDetails.fromUser(user)).path("accessToken").toString();
        userApiMethods.delete(accessToken);
    }

    @Test
    @DisplayName("Регистрация пользователя: пароль 6 символов")
    public void checkRegistrationFormWith6SymbolsPassword() {

        user.setPassword(PASSWORD_6_SYMBOLS);
        assertTrue(registerPage.isRegistrationFormVisible());
        registerPage.fillRegisterForm(user.getName(), user.getEmail(), user.getPassword());
        registerPage.clickRegisterButton();
        loginPage.explicitWaitToVisibleHeaderLoginPage();
        assertTrue(loginPage.atPage());
        Allure.step("Пользователь успешно зарегистрирован");

        cleanUp();
    }

    @Test
    @DisplayName("Регистрация пользователя: пароль больше, чем 6 символов")
    public void checkRegistrationFormWithMore6SymbolsPassword() {
        user.setPassword(PASSWORD_MORE_THEN_6_SYMBOLS);
        assertTrue(registerPage.isRegistrationFormVisible());
        registerPage.fillRegisterForm(user.getName(), user.getEmail(), user.getPassword());
        registerPage.clickRegisterButton();
        loginPage.explicitWaitToVisibleHeaderLoginPage();
        assertTrue(loginPage.atPage());
        Allure.step("Пользователь успешно зарегистрирован");

        cleanUp();
    }

    @Test
    @DisplayName("Регистрация пользователя: пароль меньше, чем 6 символов")
    public void checkRegistrationFormWithLess6SymbolsPassword() {
        user.setPassword(PASSWORD_LESS_THEN_6_SYMBOLS);
        assertTrue(registerPage.isRegistrationFormVisible());
        registerPage.fillRegisterForm(user.getName(), user.getEmail(), user.getPassword());
        registerPage.clickRegisterButton();
        assertTrue(registerPage.isErrorMessageVisible("Некорректный пароль"));
        Allure.step("Пользователь не зарегистрирован");
    }

    @Test
    @DisplayName("Регистрация пользователя: пустое поле пароль")
    public void checkRegistrationFormWithEmptyPassword() {
        user.setPassword("");
        assertTrue(registerPage.isRegistrationFormVisible());
        registerPage.fillRegisterForm(user.getName(), user.getEmail(), user.getPassword());
        registerPage.clickRegisterButton();
        assertTrue(registerPage.atPage());
        Allure.step("Пользователь не зарегистрирован");
    }

}
