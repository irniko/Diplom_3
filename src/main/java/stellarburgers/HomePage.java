package stellarburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage extends TestDataAndConstants {

    private WebDriver driver;
    private final By buttonToMakeOrderOnHomePage = By.xpath(".//button[text()='Оформить заказ']");

    public HomePage(WebDriver driver) {
        this.driver = driver;    }

    @Step("Открываем страницу: " + URL_HOME_PAGE)
    public void openUrl() {
        driver.get(URL_HOME_PAGE);
    }

    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Step("Кликаем на кнопку 'Личный кабинет'")
    public void clickToPersonalAccountButton() {
        driver.findElement(BUTTON_PERSONAL_ACCOUNT).click();
    }

    @Step("Проверяем, что текущая страница " + URL_HOME_PAGE)
    public boolean atPage() {
        return driver.getCurrentUrl().equals(URL_HOME_PAGE);
    }

    @Step("Кликаем на раздел")
    public void clickOnTab(String tabName) {
        driver.findElement(By.xpath(".//span[text() = '" + tabName + "']/parent::div")).click();
    }

    public boolean checkClassAttributeContains(By locator) {
        return driver.findElement(locator).getAttribute("class").contains("current");
    }

    @Step("Ожидание видимости кнопки 'Оформить заказ' на главной странице")
    public void explicitWaitToVisibleHeaderHomePage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(buttonToMakeOrderOnHomePage)));
    }

    @Step("Проверяем, что на главной странице кнопка 'Войти в аккаунт' сменилась на 'Оформить заказ'")
    public boolean isVisibleHeaderHomePage() {
        return driver.findElement(buttonToMakeOrderOnHomePage).isDisplayed();
    }

}
