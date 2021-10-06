package ru.dubograev.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.dubograev.helpers.DriverUtils;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("UI")
@Feature("Main Page")
public class MainPageTests extends TestBase {

    private final static String EXPECTED_TITLE = "Заказать тестирование программного обеспечения. " +
            "Стоимость услуг по тестированию в агентстве Qualitica";
    private final static String HEADER = "Повышаемкачество программных продуктов";
    private final static String DESCRIPTION = "Пишем тестовую документацию, тестируем сайты, мобильные приложения, " +
            "корпоративное программное обеспечение";

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that the header and the description contain correct text")
    @DisplayName("Header and description should be correct")
    void generatedTest() {
        step("Open url " + Configuration.baseUrl, () -> {
            open("");
        });

        step("Check that the header is " + HEADER, () -> {
            $("h1 .bezbrp").shouldHave(text(HEADER));
        });
        step("Check that the description below the header is " + DESCRIPTION, () -> {
            $(".top_txt").shouldHave(text(DESCRIPTION));
        });
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that the browser's tab title contains correct text")
    @DisplayName("Page title should be correct")
    void titleTest() {
        step("Open url " + Configuration.baseUrl, () ->
                open(""));

        step("Page title should have text " + EXPECTED_TITLE, () -> {
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(EXPECTED_TITLE);
        });
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open url " + Configuration.baseUrl, () ->
                open(""));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}