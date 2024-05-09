package stellarburgers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browsers {

    public static WebDriver createDriver() {
        WebDriver driver = null;
        // при запуске в конфигурации теста или консоли указать ключ -Dbrowser=chrome или -Dbrowser=yandex, по умолчанию запустится chrome
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
            driver = new ChromeDriver();
        } else {
            System.out.println("Неподдерживаемый браузер: " + browser);
        }
        return driver;
    }

}