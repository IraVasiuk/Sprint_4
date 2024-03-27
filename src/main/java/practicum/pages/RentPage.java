package practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import practicum.EnvConfig;

import java.time.Duration;

public class RentPage {
    private final WebDriver driver;

    //локатор для поля Когда привезти самокат
    private final By orderDataWhenDelivery = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //локатор для поля Срок аренды
    private final By rentalPeriod = By.className("Dropdown-placeholder");
    //локатор для поля Срок аренды - сутки
    private final By period_1day = By.xpath(".//*[@class='Dropdown-option'][1]");
    //локатор для поля Срок аренды - 2 суток
    private final By period_2days = By.xpath(".//*[@class='Dropdown-option'][2]");
    //локатор для поля Срок аренды - 3 суток
    private final By period_3days = By.xpath(".//*[@class='Dropdown-option'][3]");
    //локатор для поля Срок аренды - 4 суток
    private final By period_4days = By.xpath(".//*[@class='Dropdown-option'][4]");
    //локатор для поля Срок аренды - 5 суток
    private final By period_5days = By.xpath(".//*[@class='Dropdown-option'][5]");
    //локатор для поля Срок аренды - 6 суток
    private final By period_6days = By.xpath(".//*[@class='Dropdown-option'][6]");
    //локатор для поля Срок аренды - 7 суток
    private final By period_7days = By.xpath(".//*[@class='Dropdown-option'][7]");
    //Цвет самоката "Черный жемчуг"
    private final By blackPearl = By.xpath(".//input[@id='black']");
    //Цвет самоката "Серая безысходность"
    private final By grayHopelessness = By.xpath(".//input[@id='grey']");
    //поле Комментарий для курьера
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //кнопка "Заказать"
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод для выбора даты в поле Когда привезти самокат
    public void enterDate(String newDate) {
        driver.findElement(orderDataWhenDelivery).sendKeys(newDate);
        driver.findElement(orderDataWhenDelivery).sendKeys(Keys.ENTER);
    }

    //метод для выборя количества дней аренды
    public void choosePeriod(int days) {
        driver.findElement(rentalPeriod).click();
        if (days == 1) {
            driver.findElement(period_1day).click();
        } else if (days == 2) {
            driver.findElement(period_2days).click();
        } else if (days == 3) {
            driver.findElement(period_3days).click();
        } else if (days == 4) {
            driver.findElement(period_4days).click();
        } else if (days == 5) {
            driver.findElement(period_5days).click();
        } else if (days == 6) {
            driver.findElement(period_6days).click();
        } else if (days == 7) {
            driver.findElement(period_7days).click();
        }
    }

    //выбор цвета самоката Черный жемчуг
    public void clickBlackPearl() {
        driver.findElement(blackPearl).click();
    }

    //выбор цвета самоката Серая безысходность
    public void clickGrayHopelessness() {
        driver.findElement(grayHopelessness).click();
    }

    //метод выбора цвета
    public void chooseColor(String newColor) {
        if (newColor.equals("black")) {
            clickBlackPearl();
        } else if (newColor.equals("grey")) {
            clickGrayHopelessness();
        }
    }

    //метод дял ввода комментария для курьера
    public void enterComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
    }

    //метод для нажатия кнопки Заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //общий метод для ввода даты, выбора периода, выбора цвета, ввода комментария и нажатия на кнопку Заказать на странице Про аренду
    public void enterAllDataRentOrder(String newDate, int days, String newColor, String newComment) {
        enterDate(newDate);
        choosePeriod(days);
        chooseColor(newColor);
        enterComment(newComment);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT));
        clickOrderButton();
    }
}
