import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;
public class StepsTest extends Preparation {

    private  static final String REPOSITORY = "eroshenkoam/allure-example";
    private  static final int issue = 80;
        @Test
        public void testLambdaStep() {
            SelenideLogger.addListener("allure", new AllureSelenide());

            step("Отткрываем главную страницу", () -> {
                open("https://github.com");
            });
step("Ищем репозиторий" + REPOSITORY, () -> {
    $(".mr-2.color-fg-muted").click();
    $("#query-builder-test").shouldBe(Condition.visible, Duration.ofSeconds(30));
    $("#query-builder-test").setValue(REPOSITORY).pressEnter();
} );

step("Кликаем по ссылке репозитория" + REPOSITORY , () -> {
    $(linkText(REPOSITORY)).click();
});
step("Открываем таб issues" , () -> {
            $("#issues-tab").click();
        });
step("Проверяем наличие issue с номером " + issue , () -> {;
            $(withText("#" + issue)).should(Condition.exist);
});
    }

    @Test
    public  void testAnnotatedStep(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
steps.openMainPage();
steps.searchForRepository(REPOSITORY);
steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(issue);
    }
}
