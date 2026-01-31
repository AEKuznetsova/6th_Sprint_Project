package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс страницы поиска заказа
public class YandexSamokatOrderTrackingPage {
    private WebDriver driver;
    private By errorImage = By.xpath("//*[@class='Track_NotFound__6oaoY']/img[@alt='Not found']");

    public YandexSamokatOrderTrackingPage(WebDriver driver) {
        this.driver = driver;
    }

    // Нажать на кнопку "Go!" после ввода номера заказа
    public boolean checkErrorImage() {
        return driver.findElement(errorImage).isDisplayed();
    }
}
