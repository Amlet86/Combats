package com.combats;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Random;

import static com.codeborne.selenide.Configuration.*;
import static java.lang.Thread.sleep;

public class BaseTest {

    /*
     *
     * command for launch from console:
     * mvn test -Dlogin=login -Dpassword=password -DtypeOfBattle=chaos/group/single
     */

    public void beforeTest() {
        browser = "chrome";
        browserSize = "1600x900";
        headless = true;
//        startMaximized = true;
        savePageSource = false;
        timeout = 10000;

        WebDriverManager.chromedriver().setup();
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

