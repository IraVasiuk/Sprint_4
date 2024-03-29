package practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import practicum.pages.*;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderScooterTest {

    private final String buttonLocation;
    private final String name;
    private final String surname;
    private final String address;
    private final String telephone;
    private final String newDate;
    private final int days;
    private final String newColor;
    private final String newComment;

    //конструктор тест-класса
    public OrderScooterTest(String buttonLocation, String name, String surname, String address, String telephone, String newDate, int days, String newColor, String newComment) {
        this.buttonLocation = buttonLocation;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephone = telephone;
        this.newDate = newDate;
        this.days = days;
        this.newColor = newColor;
        this.newComment = newComment;
    }

    //тестовые данные
    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"up", "Иннокентий", "Пуговкин", "Московская", "89001111111", "29.03.2024", 7, "black", "Осторожно, дома злая собака!"},
                {"down", "Эльвира", "Завидло", "Ленинградская", "89001111112", "31.03.2024", 2, "grey", "Хочу самый быстрый"},
        };
    }

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void enterOrderAllDataTest() {

        WebDriver driver = driverRule.getDriver();

        //создаю объект главной страницы
        MainPage main = new MainPage(driver);
        //открываю главную страницу
        main.open();
        //закрываю куки
        main.cookieClose();
        //выбираю кнопку Заказать для клика
        main.chooseOrderButtonAndClick(buttonLocation);
        //создаю объект страницы "Для кого самокат"
        OrderPage order = new OrderPage(driver);
        //вызываю метод для ввода данных на странице "Для кого самокат"
        order.enterOrderAllData(name, surname, address, telephone);
        //создаю объект страницы Про аренду
        RentPage rent = new RentPage(driver);
        //вызываю метод для ввода данных на странице Про аренду
        rent.enterAllDataRentOrder(newDate, days, newColor, newComment);
        //создаю объект страницы Хотите оформить заказ?
        WishOrder wish = new WishOrder(driver);
        //нажимаю кнопку Да в окне Хотите оформить заказ?
        wish.clickOkButton();
        //создаю объект страницы Заказ оформлен
        OrderIsProcessed objOrderIsProcessed = new OrderIsProcessed(driver);
        //проверяю, что поле "Заказ оформлен". В приложении есть баг, который не даёт оформить заказ. Он воспроизводится только в Chrome
        assertTrue(objOrderIsProcessed.orderIsProcessedTextIsDisplayed());
    }
}
