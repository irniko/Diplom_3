package stellarburgers;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

@DisplayName("Главная страница")
@RunWith(Parameterized.class)
public class HomePageTest extends TestDataAndConstants {

    private WebDriver driver;
    HomePage homePage;
    final String tabName;
    private final By tabLocator;

    public HomePageTest(String tabName, By tabLocator) {
        this.tabName = tabName;
        this.tabLocator = tabLocator;
    }

    @Before
    public void setUp() {
        driver = Browsers.createDriver();
        homePage = new HomePage(driver);
        homePage.openUrl();
        homePage.implicitWait();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Parameterized.Parameters(name = "Таб: {0}")
    public static Object [][] getData() {
        return getTestDataToTabMovingTest();
    }

    @Test
    @DisplayName("Проверка перехода к разделу")
    public void checkMovingToTab() {
        Allure.parameter("Таб", tabName);
        if (!homePage.checkClassAttributeContains(tabLocator)) {
            homePage.clickOnTab(tabName);
        }
        Allure.step("Проверяем, что раздел '" + tabName + "' является текущим");
        assertTrue(homePage.checkClassAttributeContains(tabLocator));
    }

}