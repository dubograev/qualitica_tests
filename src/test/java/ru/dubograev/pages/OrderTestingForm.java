package ru.dubograev.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class OrderTestingForm {

    @Step("Click on 'Отправить заявку' button")
    public void submitForm() {
        $(".form-button .button").click();
    }

    @Step("Enter message")
    public OrderTestingForm enterMessage(String message) {
        $("[name=messagetxt]").setValue(message);

        return this;
    }

    @Step("Enter phone number")
    public OrderTestingForm enterPhoneNumber(String phoneNumber) {
        $("[name=tel]").setValue(phoneNumber);

        return this;
    }

    @Step("Enter email")
    public OrderTestingForm enterEmail(String email) {
        $("[name=email]").setValue(email);

        return this;
    }

    @Step("Enter name")
    public OrderTestingForm enterName(String name) {
        $("[name=name]").setValue(name);

        return this;
    }
}