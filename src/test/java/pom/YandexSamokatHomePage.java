package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

// Класс главной страницы Яндекс Самокат
public class YandexSamokatHomePage {
    private WebDriver driver;
    private String dropdownListPoint;
    // Кнопка "Заказать" в разделе "Как это работает"
    private By makeOrderButton = By.xpath("//*[@class='Home_FinishButton__1_cWm']/button");

    public YandexSamokatHomePage(WebDriver driver, String dropdownListPoint) {
        this.driver = driver;
        this.dropdownListPoint = dropdownListPoint;
    }

    // Получить элемент выпадающего списка в разделе "Вопросы о важном"
    private WebElement getDropdownListPoint() {
        return driver.findElement(By.xpath(".//*[text()='" + dropdownListPoint + "']"));
    }

    // Нажать на выпадающий список
    public void clickDropdownList() {
        getDropdownListPoint().click();
    }

    // Получить текст из выпадающего списка
    public String getDropdownListPointText() {
        By dropdownListPointText = with(By.xpath("//*[contains(@id, 'accordion__panel')]")).near(getDropdownListPoint());
        return driver.findElement(dropdownListPointText).getText();
    }

    // Нажать на кнопку "Заказать" в разделе "Как это работает"
    public void clickMakeOrderButton() {
        WebElement webElement = driver.findElement(makeOrderButton);
        webElement.click();
    }
}
