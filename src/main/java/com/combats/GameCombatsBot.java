package com.combats;

import java.util.Date;

import static java.lang.Boolean.*;

public class GameCombatsBot extends BaseCombatsBot {

    public static void main(String[] args) {

        String login = System.getProperty("login");
        String password = System.getProperty("password");
        String typeOfBattle = (System.getProperty("typeOfBattle") != null) ? System.getProperty("typeOfBattle") : "chaos";
        String telegramAPI = (System.getProperty("telegramAPI") != null) ? System.getProperty("telegramAPI") : "null";
        String pet = (System.getProperty("pet") != null) ? System.getProperty("pet") : "no";
        boolean headless = (System.getProperty("headless") != null) ? parseBoolean(System.getProperty("headless")) : true;

        int now = Integer.parseInt(parser.format(new Date()));;

        while (now >= 7 && now <= 23) {
            preparation(headless);
            game(login, password, typeOfBattle, pet, telegramAPI);
            now = end();
        }
    }

}