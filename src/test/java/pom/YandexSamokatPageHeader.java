package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Класс шапки главной страницы Яндекс Самокат
public class YandexSamokatPageHeader {
        private WebDriver driver;
        // Надпись "Яндекс" в лого
        private By yandexLogoTitle = By.className("Header_LogoYandex__3TSOI");
        // Надпись "Самокат" в лого
        private By samokatLogoTitle = By.className("Header_LogoScooter__3lsAR");
        // Кнопка "Заказать"
        private By makeOrderButton = By.className("Button_Button__ra12g");
        // Кнопка "Статус заказа"
        private By orderStatusButton = By.className("Header_Link__1TAG7");
        // Кнопка "Go!"
        private By goButton = By.xpath("//button[contains(@class,'Header_Button__28dPO')]");
        // Текстовое поле "Введите номер заказа"
        private By orderNumberTextfield = By.tagName("input");

        public YandexSamokatPageHeader(WebDriver driver) {
            this.driver = driver;
        }

        // Нажать на нопку "Заказать"
        public void clickMakeOrderButton() {
            WebElement webElement = driver.findElement(makeOrderButton);
            webElement.click();
        }

        // Нажать на надпись "Яндекс" в лого
        public void clickHeaderYandexLogoTitle() {
            driver.findElement(yandexLogoTitle).click();
        }

        // Нажать на надпись "Самокат" в лого
        public void clickHeaderSamokatLogoTitle() {
            driver.findElement(samokatLogoTitle).click();
        }

        // Нажать на кнопку "Статус заказа"
        public void clickOrderStatusButton() {
            driver.findElement(orderStatusButton).click();
        }

        // Нажать на кнопку "Go!" после ввода номера заказа
        public void clickGoButton() {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(goButton));
            enterOrderNumber(12345);
            driver.findElement(goButton).click();
        }

        // Ввести номер заказа
        private void enterOrderNumber(int orderNumber) {
            driver.findElement(orderNumberTextfield).clear();
            driver.findElement(orderNumberTextfield).sendKeys(String.valueOf(orderNumber));
        }
}

