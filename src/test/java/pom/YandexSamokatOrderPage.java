package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

// Класс страницы создания заказа Яндекс Самокат
public class YandexSamokatOrderPage {
    private WebDriver driver;

    // Поле "Имя"
    private By nameTexfield = By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'Имя')]");
    // Поле "Фамилия"
    private By lastNameTextfield = By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'Фамилия')]");
    // Поле "Адрес"
    private By addressTextfield = By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'Адрес')]");
    // Поле "Станция метро"
    private By metroStationTextfield = By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'метро')]");
    // Первая станция в выпадающем списке "Станция метро"
    private By firstMetroStation = By.xpath("//*[@class='select-search__select']//button[@value='1']");
    // Поле "Телефон"
    private By phoneNumberTextfield = By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'Телефон')]");
    // Кнопка "Далее"
    private By furtherButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]");
    // Поле "Когда привезти самокат"
    private By dateTextfield = By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'Когда привезти самокат')]");
    // Текущая дата в календаре поля "Когда привезти самокат"
    private By todayDate = By.xpath("//*[contains(@class,'react-datepicker__day--today')]");
    // Поле "Срок аренды"
    private By rentTimeTextfield = By.className("Dropdown-placeholder");
    // Срок "сутки" в выпадающем списке "Срок аренды"
    private By dayRentPeriod = By.xpath("//*[@class='Dropdown-option' and contains(text(),'сутки')]");
    // Кнопка "Заказать"
    private By makeOrderButton = By.xpath("//*[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");
    // Кнопка "Да" (окно "Хотите оформить заказ?")
    private By orderConfirmButton = By.xpath("//*[@class='Order_Modal__YZ-d3']//button[contains(text(),'Да')]");
    // Заголовок окна подтверждения заказа
    private By successOrderWindowTitle = By.className("Order_ModalHeader__3FDaJ");

    public YandexSamokatOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Получить текст ошибок валидации полей формы оформления заказа
    public String getErrorMessageText(By fieldPath) {
        By errorMessageText = with(By.xpath("//*[contains(@class,'Error')]")).near(fieldPath);
        return driver.findElement(errorMessageText).getText();
    }

    // Заполнить поля на странице оформления заказа
    private void clearTextfield(By textfieldName) {
        driver.findElement(textfieldName).isEnabled();
        driver.findElement(textfieldName).clear();
    }

    // Заполнить поле "Имя" параметром
    public void setName(String newName) {
        clearTextfield(nameTexfield);
        driver.findElement(nameTexfield).sendKeys(newName);
    }

    // Заполнить поле "Фамилия" параметром
    public void setLastName(String newLastName) {
        clearTextfield(lastNameTextfield);
        driver.findElement(lastNameTextfield).sendKeys(newLastName);
    }

    // Заполнить поле "Адрес" параметром
    public void setAddress(String newAddress) {
        clearTextfield(addressTextfield);
        driver.findElement(addressTextfield).sendKeys(newAddress);
    }

    // Выбрать станцию метро
    public void setMetroStation() {
        WebElement webElement = driver.findElement(metroStationTextfield);
        webElement.click();
        driver.findElement(firstMetroStation).click();
    }

    // Заполнить поле "Телефон" параметром
    public void setPhoneNumber(String newPhoneNumber) {
        clearTextfield(phoneNumberTextfield);
        driver.findElement(phoneNumberTextfield).sendKeys(newPhoneNumber);
    }

    // Нажать кнопку "Далее"
    public void clickFurtherButton() {
        driver.findElement(furtherButton).click();
    }

    // Выбрать текущую дату
    public void setOrderDate() {
        driver.findElement(dateTextfield).click();
        driver.findElement(todayDate).click();
    }

    // Выбрать срок аренды "сутки"
    public void setDayRentPeriod() {
        driver.findElement(rentTimeTextfield).click();
        driver.findElement(dayRentPeriod).click();
    }

    // Нажать кнопку "Заказать"
    public void clickMakeOrderButton() {
        driver.findElement(makeOrderButton).click();
    }

    // Нажать кнопку "Да"
    public void clickOrderConfirmButton() {
        driver.findElement(orderConfirmButton).click();
    }

    // Получить текст заголовка окна подтверждения заказа
    public String getSuccessOrderWindowTitle() {
        return driver.findElement(successOrderWindowTitle).getText();
    }

    // Заполнить поля первой формы оформления заказа
    public void fillFirstFormTextfields(
            String newName,
            String newLastName,
            String newAddress,
            String newPhoneNumber
    ) {
        setName(newName);
        setLastName(newLastName);
        setAddress(newAddress);
        setMetroStation();
        setPhoneNumber(newPhoneNumber);
    }

    // Заказать самокат через кнопку "Заказать"
    public void makeOrder(
            String newName,
            String newLastName,
            String newAddress,
            String newPhoneNumber
    ) {
        fillFirstFormTextfields(
                newName,
                newLastName,
                newAddress,
                newPhoneNumber
        );
        clickFurtherButton();
        setOrderDate();
        setDayRentPeriod();
        clickMakeOrderButton();
        clickOrderConfirmButton();
    }
}
