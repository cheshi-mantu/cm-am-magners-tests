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
        step ("Open " + url, () -> {
            open(url);
        });
        step("Confirm we are older that 18", () -> {
            $(byText("Подтверждаю, мне есть 18 лет")).click();
        });
        step("Yes, we are from Moscow", () -> {
            $(".dropdown__content.header-contacts__city-content").shouldHave(text("Ваш город Москва?"));
            $(byText("Да, верно")).click();
        });
//        step("Click \'Change your shipping country\'", () -> {
//            $("[data-action='ShippingSwitcher']").click();
//        });
//        step("Click country selector, select country by list value => RU" +
//                "Check if Currency is set to RUB", () -> {
//            $(byId("gle_selectedCountry")).click();
//            $(by("value", "RU")).click();
//            $(byId("gle_selectedCurrency")).shouldHave(value("RUB"));
//        });
//        step("Click SAVE on country selector" +
//                "check that country flag in the upper right corner is set" +
//                "to Russian", () -> {
//            $("[data-key='SavenClose']").click();
//            $("#shippingSwitcherLink").shouldHave(cssClass("flag-ru"));
//            $("ul.nav-container").shouldHave(text("Bags"));
//        });
//        step("Check if navigation bar has /'Bags/'", () -> {
//            $("ul.nav-container").shouldHave(text("Bags"));
//        });
//        step("Hover the main menu over \'Bags\' laptop should be present in the drop down", () -> {
//            $$("ul.nav-container li").findBy(text("Bags")).hover();
//            $$("div.columns-4.sub-cat-menu-item.sub-cat-menu-list").find(text("laptop bags"));
//        });
//        step("Go to laptop bags collection, Vega should be present on the page", () -> {
//            $("[data-gtm-link='bags | laptop bags']").click();
//            $$(".product-card--name").find(value("Vega"));
//        });
//        step("Go Vega Brief page, price should be " + checkPrice, () -> {
//            $(byText("Vega 2.0 Transit Brief")).click();
//            $(".desc").shouldHave(text(checkPrice));
//        });
//
    }

}

