package practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practicum.EnvConfig;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

import static practicum.EnvConfig.EXPECTED_ANSWERS_IMPORTANT_QUESTIONS;

public class MainPage {
    private final WebDriver driver;
    //локатор для кнопки "да все привыкли"
    private final By cookie = By.id("rcc-confirm-button");
    //локатор для верхней кнопки Заказать
    private final By higherOrderButton = By.xpath(".//button[(@class ='Button_Button__ra12g' and text()='Заказать')]");
    //локатор для нижней кнопки Заказать
    private final By lowerOrderButton = By.className("Button_Middle__1CSJM");
    //локатор кнопки "Статус заказа"
    private final By orderStatusField = By.className("Header_Link__1TAG7");
    // локатор поля ввода "Введите номер заказа"
    private final By orderField = By.className("Header_Input__xIoUq");
    //локатор кнопки "Go!"
    private final By goButton = By.cssSelector("[class*=Header_Button__]");
    //локаторы кнопкок вопросов раздела "Вопросы о важном"
    private final By buttonsImportantQuestionsList_0 = By.id("accordion__heading-0");
    private final By buttonsImportantQuestionsList_1 = By.id("accordion__heading-1");
    private final By buttonsImportantQuestionsList_2 = By.id("accordion__heading-2");
    private final By buttonsImportantQuestionsList_3 = By.id("accordion__heading-3");
    private final By buttonsImportantQuestionsList_4 = By.id("accordion__heading-4");
    private final By buttonsImportantQuestionsList_5 = By.id("accordion__heading-5");
    private final By buttonsImportantQuestionsList_6 = By.id("accordion__heading-6");
    private final By buttonsImportantQuestionsList_7 = By.id("accordion__heading-7");
    //массив из локаторов кнопок раздела "Вопрсоы о важном"
    private final By[] buttonsImportantQuestionsArray = {buttonsImportantQuestionsList_0, buttonsImportantQuestionsList_1, buttonsImportantQuestionsList_2, buttonsImportantQuestionsList_3, buttonsImportantQuestionsList_4, buttonsImportantQuestionsList_5, buttonsImportantQuestionsList_6, buttonsImportantQuestionsList_7};
    //локаторы к текстовым ответам раздела "Вопросы о важном"
    private final By answersImportantQuestionsList_0 = By.xpath(".//div[@id='accordion__panel-0']/p");
    private final By answersImportantQuestionsList_1 = By.xpath(".//div[@id='accordion__panel-1']/p");
    private final By answersImportantQuestionsList_2 = By.xpath(".//div[@id='accordion__panel-2']/p");
    private final By answersImportantQuestionsList_3 = By.xpath(".//div[@id='accordion__panel-3']/p");
    private final By answersImportantQuestionsList_4 = By.xpath(".//div[@id='accordion__panel-4']/p");
    private final By answersImportantQuestionsList_5 = By.xpath(".//div[@id='accordion__panel-5']/p");
    private final By answersImportantQuestionsList_6 = By.xpath(".//div[@id='accordion__panel-6']/p");
    private final By answersImportantQuestionsList_7 = By.xpath(".//div[@id='accordion__panel-7']/p");
    //Массив локаторов из текстовых ответов раздела "Вопрсоы о важном"
    private final By[] answersImportantQuestionsArray = {answersImportantQuestionsList_0, answersImportantQuestionsList_1, answersImportantQuestionsList_2, answersImportantQuestionsList_3, answersImportantQuestionsList_4, answersImportantQuestionsList_5, answersImportantQuestionsList_6, answersImportantQuestionsList_7};

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //закрываю куки
    public void cookieClose() {
        driver.findElement(cookie).click();
    }

    public StatusPage clickOnGo() {
        driver.findElement(goButton).click();

        return new StatusPage(driver);
    }

    public void enterOrderNumber(String orderNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderField));

        driver.findElement(orderField).sendKeys(orderNumber);
    }

    public void clickOnOrderStatus() {
        driver.findElement(orderStatusField).click();
    }

    //открыть главную страницу Самоката
    public void open() {
        driver.get(EnvConfig.BASE_URL);
    }

    //пролистнуть страницу и кликнуть по кнопке в разделе "Вопросы о важном"
    public void clickButtonsImportantQuestions(int listIndex) {
        By locator = buttonsImportantQuestionsArray[listIndex];

        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        driver.findElement(locator).click();
    }

    //метод для получения текста из текстовых ответов раздела "Вопросы о важном"
    public String getAnswersImportantQuestions(int listIndex) {
        By locator = answersImportantQuestionsArray[listIndex];
        return driver.findElement(locator).getText();
    }

    //метод для сравнения текстовых ответов с ожидаемыми ответами
    public boolean contentIsDisplayed(int listIndex) {
        By locator = answersImportantQuestionsArray[listIndex];
        String expectedAnswersText = EXPECTED_ANSWERS_IMPORTANT_QUESTIONS[listIndex];

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        return driver.findElement(locator).isDisplayed() && getAnswersImportantQuestions(listIndex).equals(expectedAnswersText);
    }

    //кликнуть по верхней кнопке Заказать на главной странице
    public void higherOrderButtonClick() {
        driver.findElement(higherOrderButton).click();
    }

    //кликнуть по нижней кнопке Заказать
    public void lowerOrderButtonClick() {
        WebElement element = driver.findElement(lowerOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(lowerOrderButton).click();
    }

    //выбор кнопки Заказать на странице
    public void chooseOrderButtonAndClick(String buttonLocation) {
        if (buttonLocation.equals("up")) {
            higherOrderButtonClick();
        } else if (buttonLocation.equals("down")) {
            lowerOrderButtonClick();
        }
    }

}