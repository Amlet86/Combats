package com.combats;

import com.combats.pages.LoginPage;

public class GameTest extends BaseBot {

    public static void main(String[] args) {
        endToEnd();
    }

    public static void endToEnd() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterToMainPage()
                .login(System.getProperty("login"), System.getProperty("password"))
                .moveInCity()
                .chooseBattle(System.getProperty("typeOfBattle"))
                .fight();
    }

}