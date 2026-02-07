import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.*;
import testData.TestUser;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import static constants.ErrorMessageText.*;
import static constants.Hosts.*;
import static constants.TextConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class YandexSamokatTests {
    private WebDriver driver;
    // Главная страница
    private YandexSamokatHomePage objHomePage;
    // Шапка главной страницы
    private YandexSamokatPageHeader objPageHeader;
    // Страница создания заказа
    private YandexSamokatOrderPage objOrderPage;
    // Страница поиска заказа
    private YandexSamokatOrderTrackingPage objTrackOrderPage;
    // Секция принятия cookies
    private YandexSamokatCookiesSection objCookiesSection;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        // Google Chrome
        driver = new ChromeDriver();
        // Mozilla Firefox
//        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(MAIN_PAGE);
        objCookiesSection = new YandexSamokatCookiesSection(driver);
        objCookiesSection.acceptCookies();
    }

    @ParameterizedTest
    @MethodSource("testData.ParameterizedTestData#dropdownListData")
    @DisplayName("Тест проверяет, что когда нажимаешь на стрелочку, открывается соответствующий текст.")
    void dropdownListPointTest(String dropdownListQuestion, String expectedResult) {
        objHomePage = new YandexSamokatHomePage(driver, dropdownListQuestion);
        objHomePage.clickDropdownList();

        assertEquals(expectedResult, objHomePage.getDropdownListPointText());
    }

    @ParameterizedTest
    @MethodSource("testData.ParameterizedTestData#userTestData")
    @DisplayName("Тест делает заказ через кнопку \"Заказать\" в шапке сайта")
    void makeOrderWithHigherOrderButton(TestUser user) {
        objPageHeader = new YandexSamokatPageHeader(driver);
        objPageHeader.clickMakeOrderButton();
        objOrderPage = new YandexSamokatOrderPage(driver);
        objOrderPage.makeOrder(
                user.getName(),
                user.getLastName(),
                user.getAddress(),
                user.getPhoneNumber()
        );
        assertTrue(objOrderPage.getSuccessOrderWindowTitle().contains(ORDER_CONFIRMATION_TEXT),
                "Не найдено окно об успешном оформлении заказа");
    }

    @ParameterizedTest
    @MethodSource("testData.ParameterizedTestData#userTestData")
    @DisplayName("Тест делает заказ через кнопку \"Заказать\", расположенную внизу")
    void makeOrderWithLowerOrderButton(TestUser user) {
        objHomePage = new YandexSamokatHomePage(driver, "");
        objHomePage.clickMakeOrderButton();

        objOrderPage = new YandexSamokatOrderPage(driver);
        objOrderPage.makeOrder(
                user.getName(),
                user.getLastName(),
                user.getAddress(),
                user.getPhoneNumber()
        );

        assertTrue(objOrderPage.getSuccessOrderWindowTitle().contains(ORDER_CONFIRMATION_TEXT),
                "Не найдено окно об успешном оформлении заказа");
    }

    @Test
    @DisplayName("Если нажать на логотип Самоката, попадешь на главную страницу Самоката")
    void redirectToMainPage() {
        driver.get(ORDER_PAGE);

        objPageHeader = new YandexSamokatPageHeader(driver);
        objPageHeader.clickHeaderSamokatLogoTitle();

        assertEquals(MAIN_PAGE + "/", driver.getCurrentUrl(),
                "Переход на главную страницу не выполнен");
    }

    @Test
    @DisplayName("Если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса")
    void redirectToYandexMainPage() {
        objPageHeader = new YandexSamokatPageHeader(driver);
        objPageHeader.clickHeaderYandexLogoTitle();

        Set<String> windowIds = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(windowIds);
        driver.switchTo().window(tabs.get(1));
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains(YANDEX_PAGE));

        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains(YANDEX_PAGE),
                "Переход на главную страницу Яндекс не выполнен");
    }

    @Test
    @DisplayName("Правильное содержание текста ошибки для поля Имя")
    void checkNameTextfieldErrorMessage() {
        driver.get(ORDER_PAGE);

        objOrderPage = new YandexSamokatOrderPage(driver);
        objOrderPage.clickFurtherButton();

        assertEquals(ERROR_MESSAGE_NAME, objOrderPage.getErrorMessageText(objOrderPage.getNameTextfield()),
                "Текст ошибки не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Правильное содержание текста ошибки для поля Фамилия")
    void checkLastNameTextfieldErrorMessage() {
        driver.get(ORDER_PAGE);

        objOrderPage = new YandexSamokatOrderPage(driver);
        objOrderPage.clickFurtherButton();

        assertEquals(ERROR_MESSAGE_LAST_NAME, objOrderPage.getErrorMessageText(objOrderPage.getLastNameTextfield()),
                "Текст ошибки не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Правильное содержание текста ошибки для поля Адрес")
    void checkAddressTextfieldErrorMessage() {
        driver.get(ORDER_PAGE);

        objOrderPage = new YandexSamokatOrderPage(driver);
        objOrderPage.setAddress("1");
        objOrderPage.clickFurtherButton();

        assertEquals(ERROR_MESSAGE_ADDRESS, objOrderPage.getErrorMessageText(objOrderPage.getAddressTextfield()),
                "Текст ошибки не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Правильное содержание текста ошибки для поля Станция метро")
    void checkMetroStationTextfieldErrorMessage() {
        driver.get(ORDER_PAGE);

        objOrderPage = new YandexSamokatOrderPage(driver);
        objOrderPage.clickFurtherButton();

        assertEquals(ERROR_MESSAGE_METRO_STATION, objOrderPage.getErrorMessageText(objOrderPage.getMetroStationTextfield()),
                "Текст ошибки не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Правильное содержание текста ошибки для поля Телефон")
    void checkPhoneNumberTextfieldErrorMessage() {
        driver.get(ORDER_PAGE);

        objOrderPage = new YandexSamokatOrderPage(driver);
        objOrderPage.clickFurtherButton();

        assertEquals(ERROR_MESSAGE_PHONE_NUMBER, objOrderPage.getErrorMessageText(objOrderPage.getPhoneNumberTextfield()),
                "Текст ошибки не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Если ввести неправильный номер заказа, попадёшь на страницу ошибки")
    void checkErrorImageOnTrackingPage() {
        objPageHeader = new YandexSamokatPageHeader(driver);
        objPageHeader.clickOrderStatusButton();
        objPageHeader.clickGoButton();

        objTrackOrderPage = new YandexSamokatOrderTrackingPage(driver);

        assertTrue(objTrackOrderPage.checkErrorImage(), "Картинка \"Такого заказа нет\" не отображается");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
