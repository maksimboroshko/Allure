import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps extends Preparation {

@Step ("Отткрываем главную страницу")
    public void openMainPage(){
    open("https://github.com");
    }
@Step ("Ищем репозиторий + {repo}")
    public void searchForRepository(String repo) {
    $(".mr-2.color-fg-muted").click();
    $("#query-builder-test").shouldBe(Condition.visible, Duration.ofSeconds(30));
    $("#query-builder-test").setValue(repo).pressEnter();
}

@Step ("Кликаем по ссылке репозитория + {repo}")
public void clickOnRepositoryLink(String repo) {
    $(linkText(repo)).click();
}

@Step ("Открываем таб issues")
public void openIssueTab(){
    $("#issues-tab").click();
}

@Step("Проверяем наличие issue с номером  + {issue}")
public void shouldSeeIssueWithNumber(int issue) {
    $(withText("#" + issue)).should(Condition.exist);
}

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".mr-2.color-fg-muted").click();
        $("#query-builder-test").shouldBe(Condition.visible, Duration.ofSeconds(30));
        $("#query-builder-test").setValue("eroshenkoam/allure-example").pressEnter();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#80")).should(Condition.exist);
    }

}

