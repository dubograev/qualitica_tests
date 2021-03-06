package ru.dubograev.tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.dubograev.pages.OrderTestingForm;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("UI")
@Feature("Order Testing form")
public class FormTests extends TestBase {

    OrderTestingForm form = new OrderTestingForm();

    @Test
    @AllureId("5077")
    @Disabled
    @DisplayName("Verify that user is able to submit the form")
    void formTest() {
        step("Open main page", () -> {
            open("");
        });

        step("Click on 'Заказать тестирование' button", () -> {
            clickOrderTestingButton();
        });

        step("Fill the form", () -> {
            form.enterName("Jack Daniel's")
                    .enterEmail("jackdaniels@mailinator.com")
                    .enterPhoneNumber("8002000000")
                    .enterMessage("Извините, просто хочу протестировать форму")
                    .submitForm();
        });

        step("Verify that a pop-up with text 'Письмо отправлено' should appear", () -> {
            $("#win2").shouldHave(text("Письмо отправлено"));
        });
    }

    private void clickOrderTestingButton() {
        $(".abs-button").$(byText("Заказать тестирование")).click();
    }
}