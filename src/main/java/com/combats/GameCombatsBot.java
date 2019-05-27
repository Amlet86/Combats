package com.combats;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameCombatsBot extends BaseCombatsBot {

    public static void main(String[] args) {

        String login = System.getProperty("login");
        String password = System.getProperty("password");
        String typeOfBattle = (System.getProperty("typeOfBattle") != null) ? System.getProperty("typeOfBattle") : "chaos";
        String telegramAPI = (System.getProperty("telegramAPI") != null) ? System.getProperty("telegramAPI") : "null";

        SimpleDateFormat parser = new SimpleDateFormat("HH");
        int now = Integer.parseInt(parser.format(new Date()));

        while (7 <= now && now <= 23) {
            preparation();
            game(login, password, typeOfBattle, telegramAPI);
            end();
        }
    }

}