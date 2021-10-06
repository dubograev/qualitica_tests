package ru.dubograev.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("UI")
@Feature("Clients page")
public class ClientsPageTests extends TestBase {

    private final static String CLIENTS_EXPECTED_TITLE = "Клиенты";
    private final static String CLIENTS_URL = "/clients";
    private final static int AMOUNT_OF_CLIENTS = 8;

    @Test
    @Description("This test verifies that the browser's tab title contains correct text")
    @DisplayName("Page title of the Clients page should be correct")
    void clientsTitleTest() {
        step("Open test url " + Configuration.baseUrl + CLIENTS_URL, () -> {
            open(CLIENTS_URL);
        });

        step("The title should be " + CLIENTS_EXPECTED_TITLE, () -> {
            String clientsActualTitle = title();

            assertThat(clientsActualTitle).isEqualTo(CLIENTS_EXPECTED_TITLE);
        });
    }

    @Test
    @DisplayName("Verify that the amount of clients is " + AMOUNT_OF_CLIENTS)
    void amountOfClients() {
        step("Open test url " + Configuration.baseUrl + CLIENTS_URL, () -> {
            open(CLIENTS_URL);
        });

        step("Amount of clients should be " + AMOUNT_OF_CLIENTS, () -> {
            $(".clients__list").$$(".clients__card").shouldHave(size(AMOUNT_OF_CLIENTS));
        });
    }

    @Test
    void filePresentationTest() throws Exception {
        open(CLIENTS_URL);
        File presentationPdf = $("a[href='/Qualitica.pdf']").download();
        PDF parsed = new PDF(presentationPdf);
        assertThat(parsed.title).isEqualTo("Qualitica_new");
        assertThat(parsed.author).isNull();
        assertThat(parsed.numberOfPages).isEqualTo(18);
        assertThat(parsed.text).contains("Повышаем качество");
    }
}