package tests;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.switchTo;

import static helpers.Environment.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Magners Pear availability test in AM")
@Feature("AM search and availability check")
@Story("Check the availability of Magners Pear cider at AM site")
@Tag("am_magners_tests")
class AmMagnersPearTests extends TestBase {
    private String url;
    private Boolean isAvailable = false;
    @Test
    @AllureId("1028")
    @DisplayName("Simple search and check of the availability")
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
    @AllureId("1027")
    @DisplayName("Search and check availability at certain shop,")
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
//            $("[data-name='Magners Pear']").exists();
            $("[href='/catalog/sidr/magners_pear/']").should(exist);
        });
        step("PREP: go to the item's page ", () -> {
            $("[href='/catalog/sidr/magners_pear/']").click(); // seems to be opening 2nd window now
        });
        step("CHECK: if new tab is opened after click on an element on the page", () -> {
            if (WebDriverRunner.getWebDriver().getWindowHandles().size() > 1) {
                switchTo().window(1);
            }
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

