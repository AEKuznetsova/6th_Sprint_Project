package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YandexSamokatCookiesSection {
    private WebDriver driver;
    // Кнопка принятия cookie "Да все привыкли"
    private By cookieButton = By.className("App_CookieButton__3cvqF");

    public YandexSamokatCookiesSection(WebDriver driver) {
        this.driver = driver;
    }

    // Принять cookies
    public void acceptCookies() {
        WebElement webElement = driver.findElement(cookieButton);
        webElement.click();
    }
}
