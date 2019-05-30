package com.combats;

import com.codeborne.selenide.WebDriverRunner;
import com.combats.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Configuration.*;
import static java.lang.Thread.sleep;

public class BaseCombatsBot {

    /*
     * command for launch not compiled from console:
     * mvn exec:java -Dexec.mainClass="com.combats.GameCombatsBot" -Dlogin=login -Dpassword=password
     * -DtypeOfBattle=chaos/group/single -DtelegramAPI=telegramAPI -Dpet=yes/no
     *
     * command for launch Combats.jar from console:
     * java -Dlogin=login -Dpassword=password -DtypeOfBattle=chaos/group/single -Dpet=yes/no -jar Combats-version.jar
     *
     */

    @BeforeTest
    public static void preparation() {
        browser = "chrome";
        browserSize = "1600x900";
//        startMaximized = true;
//        holdBrowserOpen = true;
        headless = true;
        savePageSource = false;
        reportsFolder = "fails";
        timeout = 8000;

        WebDriverManager.chromedriver().setup();
    }

    @Test
    public static void game(String login, String password, String typeOfBattle, String pet, String telegramAPI) {
        LoginPage loginPage = new LoginPage();
        loginPage.enterToMainPage()
                .login(login, password)
                .moveInTheCity()
                .chooseBattle(typeOfBattle)
                .fight(pet, telegramAPI);
    }

    @AfterTest
    public static void end() {
        WebDriverRunner.getWebDriver().close();
        waiting(30, 60);
    }

    private static int getRandomMultiplyThousand(int from, int to) {
        return new Random().nextInt(to * 1000 - from * 1000) + from * 1000;
    }

    public static int getRandomInt(int from, int to) {
        return new Random().nextInt(to - from) + from;
    }

    public static void waiting(int from, int to) {
        try {
            sleep(getRandomMultiplyThousand(from, to));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

