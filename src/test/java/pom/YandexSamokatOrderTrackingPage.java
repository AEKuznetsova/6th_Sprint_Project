package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// Класс страницы поиска заказа
public class YandexSamokatOrderTrackingPage {
    private WebDriver driver;
    private By errorImage = By.xpath("//*[@class='Track_NotFound__6oaoY']/img[@alt='Not found']");

    public YandexSamokatOrderTrackingPage(WebDriver driver) {
        this.driver = driver;
    }

    // Нажать на кнопку "Go!" после ввода номера заказа
    public boolean checkErrorImage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(errorImage));
        return driver.findElement(errorImage).isDisplayed();
    }
}
