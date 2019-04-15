package com.combats;

import com.combats.pages.LoginPage;
import org.testng.annotations.Test;

public class GameTest extends BaseTest {

    @Test
    public void endToEnd() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterToMainPage()
                .login(System.getProperty("login"), System.getProperty("password"))
                .moveInCity()
                .chooseBattle(System.getProperty("typeOfBattle"))
                .fight();
    }

}