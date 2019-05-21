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

public class BaseBot {

    /*
     * command for launch from console:
     * mvn exec:java -Dexec.mainClass="com.combats.GameBot" -Dlogin=Amlet -Dpassword=*12fylhtQ34# -DtypeOfBattle=chaos/group/single
     */

    @BeforeTest
    public static void preparation() {
        browser = "chrome";
        startMaximized = true;
//        browserSize = "1600x900";
//        headless = true;
        holdBrowserOpen = true;
        savePageSource = false;
        reportsFolder = "fails";
        timeout = 6000;

        WebDriverManager.chromedriver().setup();
    }

    @Test
    public static void game() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterToMainPage()
                .login(System.getProperty("login"), System.getProperty("password"))
                .moveInTheCity()
                .chooseBattle("")
                .fight();
    }

    @AfterTest
    public static void end() {
        WebDriverRunner.getWebDriver().close();
        waiting(120, 150);
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

