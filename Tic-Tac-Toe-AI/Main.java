package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (true) {
            Field field = new Field();
            Game game = new Game(s, field);
            if (!game.isPlayed) break;
            game.play();
            System.out.println();
        }
    }
}