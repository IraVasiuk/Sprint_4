package practicum;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import practicum.pages.MainPage;
import practicum.pages.StatusPage;

public class ScooterTest {
    private final String INVALID_ORDER_NUMBER = "123";

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void invalidOrderNumber() {
        WebDriver driver = driverRule.getDriver();

        MainPage main = new MainPage(driver);

        main.open();
        main.clickOnOrderStatus();
        main.enterOrderNumber(INVALID_ORDER_NUMBER);

        StatusPage status = main.clickOnGo();
        status.checkNotFound();
    }
}
