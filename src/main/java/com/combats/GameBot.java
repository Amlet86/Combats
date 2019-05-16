package com.combats;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameBot extends BaseBot {

    public static void main(String[] args) {

        SimpleDateFormat parser = new SimpleDateFormat("HH");
        int now = Integer.parseInt(parser.format(new Date()));

        while (8 <= now && now <= 23) {
            preparation();
            game();
            end();
        }
    }

}