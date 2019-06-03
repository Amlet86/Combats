package com.combats.pages;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public LoginPage enterToMainPage() {
        return open("http://www.google.com/", LoginPage.class);
    }

    public CityPage login(String login, String password) {
        $("[name=login]").setValue(login);
        $("[name=psw]").setValue(password);
        $("[value=' Войти ']").click();
        return page(CityPage.class);
    }

}
