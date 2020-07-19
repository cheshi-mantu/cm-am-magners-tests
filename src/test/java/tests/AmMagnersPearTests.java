package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static helpers.Environment.*;
import static io.qameta.allure.Allure.step;

@Epic("Magners Pear availability test in AM")
@Feature("Direct search from URL")
@Story("Go to search URL and check the availability")
@Tag("am_magners_tests")
class AmMagnersPearTests extends TestBase {
    private String url;
    @Test
    @DisplayName("Search and check availability")
    @Description("1) build search string, \n 2) directly open search \n 3) check availability")
    void searchAndCheckAvailiability() {
        step("PREP: building the search string", () -> {
            url = baseSearchUrl + searchString.replaceAll(" ", "+");
            System.out.println(url);
        });
        step ("ACT: Open " + url, () -> {
            open(url);
        });
        step("PREP: Confirm we are older that 18", () -> {
            $(byText("Подтверждаю, мне есть 18 лет")).click();
        });
        step("PREP: Yes, we are from Moscow", () -> {
            $(".dropdown__content.header-contacts__city-content").shouldHave(text("Ваш город Москва?"));
            $(byText("Да, верно")).click();
        });
        step("CHECK: Checking search results page against search string", () -> {
            $("div.catalog-list-item__container").shouldNotHave(text(searchString));
        });
    }

}

