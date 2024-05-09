package stellarburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage extends TestDataAndConstants {

    private WebDriver driver;
    private final By emailLocator = By.xpath(".//label[text() = 'Email']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    private final By passwordLocator = By.xpath(".//label[text() = 'Пароль']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    private final By buttonLogin = By.xpath(".//button[text() = 'Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    @Step("Проверяем видимость кнопки 'Войти'")
    public boolean isLoginButtonVisible(By buttonToLogin) {
        return driver.findElement(buttonToLogin).isDisplayed();
    }

    @Step("Кликаем на кнопку")
    public void clickButtonToLogin(By buttonTologin) {
        driver.findElement(buttonTologin).click();
    }

    @Step("Проверяем, что осуществился переход на страницу " + URL_LOGIN_PAGE)
    public boolean atPage() {
        return driver.getCurrentUrl().equals(URL_LOGIN_PAGE);
    }

    @Step("Заполняем поля email, password")
    public void fillRegisterForm(String email, String password) {
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(passwordLocator).sendKeys(password);
    }

    @Step("Кликаем на кнопку 'Войти'")
    public void clickButtonLogin() {
        driver.findElement(buttonLogin).click();
    }

    public void explicitWaitToVisibleHeaderLoginPage() {
        new WebDriverWait(driver, 3)
              .until(ExpectedConditions.visibilityOf(driver.findElement(headerLoginPage)));
    }

}
