import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class Preparation {
    @BeforeAll
    public static void setUp() {
        com.codeborne.selenide.Configuration.browserSize = "1920 x 1080";
        com.codeborne.selenide.Configuration.pageLoadStrategy = "eager";
        com.codeborne.selenide.Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000;

    }
}



