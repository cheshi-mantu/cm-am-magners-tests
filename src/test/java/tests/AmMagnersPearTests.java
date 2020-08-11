package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static helpers.Environment.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Magners Pear availability test in AM")
@Feature("Direct search from URL")
@Story("Go to search URL and check the availability")
@Tag("am_magners_tests")
class AmMagnersPearTests extends TestBase {
    private String url;
    private Boolean isAvailable = false;
    @Test
    @AllureId("1031")
    @DisplayName("Search and check availability")
    @Description("1) build search string, \n 2) directly open search \n 3) check availability")
    void searchAndCheckGeneralAvailiability() {
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
        step("CHECK and PREP next text: isAvailable", () -> {
            if ($("[data-name='Magners Pear']").exists()){
                isAvailable = true;
            }
        });
        step("CHECK and PREP next text: isAvailable", () -> {
            assertTrue(isAvailable);
        });
    }
    @Test
    @AllureId("1032")
    @DisplayName("Search and check availability at certain shop")
    @Description("1) build search string, " +
            "2) directly open search " +
            "3) check availability" +
            "4) click the product card" +
            "5) ")
    void searchTargetShop() {
        step("PREP: building the search string", () -> {
            url = baseSearchUrl + searchString.replaceAll(" ", "+");
            System.out.println(url);
        });
        step ("PREP: Open " + url, () -> {
            open(url);
        });
        step("PREP: Confirm we are older that 18", () -> {
            $(byText("Подтверждаю, мне есть 18 лет")).click();
        });
        step("PREP: Yes, we are from Moscow", () -> {
            $(".dropdown__content.header-contacts__city-content").shouldHave(text("Ваш город Москва?"));
            $(byText("Да, верно")).click();
        });
        step("CHECK: search item is on the page", () -> {
            $("[data-name='Magners Pear']").exists();
        });
        step("PREP: go to the item's page ", () -> {
            $(".catalog-list-item__title.js-product-detail-link").click();
        });
        step("PREP: click availability button", () -> {
            $(".btn.btn-primary.btn-outlined.catalog-element-info__shops.js-product-detail-stores-link").shouldHave(text("В наличии")).click();
        });
        step("PREP: enter search street name and Enter", () -> {
            $(".search__input.js-store-search-input").setValue(streetName).pressEnter();
            $("span.store-item__quantity").shouldHave(text("В наличии:"));
        });
    }

}

