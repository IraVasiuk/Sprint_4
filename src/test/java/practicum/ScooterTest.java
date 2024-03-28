package practicum;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import practicum.pages.MainPage;
import practicum.pages.StatusPage;

public class ScooterTest {
    private final String invalidOrderNumber= "123";

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void invalidOrderNumber() {
        WebDriver driver = driverRule.getDriver();

        MainPage main = new MainPage(driver);

        main.open();
        main.clickOnOrderStatus();
        main.enterOrderNumber(invalidOrderNumber);

        StatusPage status = main.clickOnGo();
        status.checkNotFound();
    }
}
