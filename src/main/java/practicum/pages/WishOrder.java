package practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishOrder {
    private final WebDriver driver;

    //Кнопка Да в окне Хотите оформить заказ?
    private final By okButton = By.xpath(".//*[text()='Да']");

    public WishOrder(WebDriver driver) {
        this.driver = driver;
    }

    //метод нажатия на кнопку Да
    public void clickOkButton() {
        driver.findElement(okButton).click();
    }
}
