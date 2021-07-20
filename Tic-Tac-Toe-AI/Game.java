package tictactoe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

class Game {
    Player player1;
    Player player2;
    Field field;
    boolean isPlayed = true;
    static HashSet<String> types =
            new HashSet<>(Arrays.asList("user", "easy", "medium", "hard"));

    Game (Scanner s, Field f) {
        field = f;
        String type1, type2;

        while (true) {
            System.out.print("Input command: ");
            String[] input = s.nextLine().split(" ");
            try {
                if (input[0].equals("exit")) {
                    isPlayed = false;
                    return;
                }
                type1 = input[1];
                type2 = input[2];

                if (!types.contains(type1)) {
                    System.out.println("Bad parameters!");
                }
                if (!types.contains(type2)) {
                    System.out.println("Bad parameters!");
                }
                break;
            } catch (Exception e) {
                System.out.println("Bad parameters!");
            }
        }

        player1 =  PlayerFactory.make(type1, s, 'X');
        player2 = PlayerFactory.make(type2, s, 'O');
    }

    void play() {
        field.printField();
        while (field.getResult().equals("Game not finished")) {
            player1.makeMove(field);
            field.printField();

            if (field.getResult().equals("Game not finished")) {
                player2.makeMove(field);
            } else {
                break;
            }

            field.printField();
        }
        System.out.println(field.getResult());
    }
}