package ru.dubograev.tests;

import com.codeborne.pdftest.PDF;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("UI")
public class ClientsPageTests extends TestBase {

    private final static String CLIENTS_EXPECTED_TITLE = "Клиенты";

    @Test
    @Description("The test verifies that the Clients page has a correct title")
    @DisplayName("Page title should have header text")
    void clientsTitleTest() {
        open("https://qualitica.ru/clients/");
        String clientsActualTitle = title();

        assertThat(clientsActualTitle).isEqualTo(CLIENTS_EXPECTED_TITLE);
    }

    @Test
    @DisplayName("Verify that the amount of clients is 8")
    void amountOfClients() {
        open("https://qualitica.ru/clients/");
        $(".clients__list").$$(".clients__card").shouldHave(size(8));
    }

    @Test
    void filePresentationTest() throws Exception {
        open("https://qualitica.ru/clients/");
        File presentationPdf = $("a[href='/Qualitica.pdf']").download();
        PDF parsed = new PDF(presentationPdf);
        assertThat(parsed.title).isEqualTo("Qualitica_new");
        assertThat(parsed.author).isNull();
        assertThat(parsed.numberOfPages).isEqualTo(18);
        assertThat(parsed.text).contains("Повышаем качество");
    }
}