package practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import practicum.pages.MainPage;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ImportantQuestionTest {
    private final int listIndex;

    @Rule
    public DriverRule driverRule = new DriverRule();

    //конструктор тест-класса Вопросы о важном
    public ImportantQuestionTest(int listIndex) {
        this.listIndex = listIndex;
    }

    //набор тестовых данных
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
        };
    }

    @Test
    public void IsVisibleTextWhenClickedButton() {

        WebDriver driver = driverRule.getDriver();

        //создаю объект класса главной страницы
        MainPage main = new MainPage(driver);

        //открываю главную страницу
        main.open();

        //кликаю по кнопке с вопросом в зависимости от индекса
        main.clickButtonsImportantQuestions(listIndex);

        //сравниваю результаты
        assertTrue(main.contentIsDisplayed(listIndex));
    }
}
