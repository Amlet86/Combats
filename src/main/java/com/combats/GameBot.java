package com.combats;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameBot extends BaseBot {

    public static void main(String[] args) {

        String login = System.getProperty("login");
        String password = System.getProperty("password");
        String typeOfBattle = (System.getProperty("typeOfBattle") != null) ? System.getProperty("typeOfBattle") : "chaos";

        SimpleDateFormat parser = new SimpleDateFormat("HH");
        int now = Integer.parseInt(parser.format(new Date()));

        while (7 <= now && now <= 23) {
            preparation();
            game(login, password, typeOfBattle);
            end();
        }
    }

}