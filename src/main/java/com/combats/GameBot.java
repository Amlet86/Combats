package com.combats;

public class GameBot extends BaseBot {

    public static void main(String[] args) {
        for (int i = 100; i > 0; i--) {
            preparation();
            game();
            end();
        }
    }

}