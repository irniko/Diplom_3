package stellarburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class PersonalAccountPage extends TestDataAndConstants {

    private WebDriver driver;
    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    @Step("Кликаем на кнопку 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(BUTTON_CONSTRUCTOR).click();
    }
    @Step("Кликаем на логотип 'Stellar Burgers'")
    public void clickLogoButton() {
        driver.findElement(BUTTON_LOGO).click();
    }
    @Step("Кликаем на кнопку 'Выйти'")
    public void clickExitButton() {
        driver.findElement(BUTTON_EXIT).click();
    }
}
