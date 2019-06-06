package com.combats;

import com.codeborne.selenide.WebDriverRunner;
import com.combats.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.codeborne.selenide.Configuration.*;
import static java.lang.Thread.sleep;

public class BaseCombatsBot {

    /*
     * command for launch not compiled from console:
     * mvn exec:java -Dexec.mainClass="com.combats.GameCombatsBot" -Dlogin=login -Dpassword=password
     * -DtypeOfBattle=chaos/group/single -DtelegramAPI=telegramAPI -Dpet=yes/no -Dheadless=true/false
     *
     * command for launch Combats.jar from console:
     * java -Dlogin=login -Dpassword=password -DtypeOfBattle=chaos/group/single -Dpet=yes/no -Dheadless=true/false
     * -jar Combats-version.jar
     *
     */
    static SimpleDateFormat parser = new SimpleDateFormat("HH");

    static void preparation(boolean headlessValue) {
        if (!headlessValue) {
            startMaximized = true;
        } else {
            browserSize = "1600x900";
        }
        browser = "chrome";
//        holdBrowserOpen = true;
        headless = headlessValue;
        savePageSource = false;
        reportsFolder = "fails";
        timeout = 20000;

        WebDriverManager.chromedriver().setup();
    }

    static void game(String login, String password, String typeOfBattle, String pet, String telegramAPI) {
        LoginPage loginPage = new LoginPage();
        loginPage.enterToMainPage()
                .login(login, password)
                .moveInTheCity()
                .chooseBattle(typeOfBattle)
                .fight(pet, telegramAPI);
    }

    static int end() {
        WebDriverRunner.getWebDriver().quit();
        waiting(60, 90);
        return Integer.parseInt(parser.format(new Date()));
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

