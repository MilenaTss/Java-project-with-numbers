package tictactoe;

import java.util.Random;
import java.util.Scanner;

interface Player {
    void makeMove(Field field);
}

class PlayerFactory {
    public static Player make(String type, Scanner s, char move) {
        switch (type) {
            case "easy":
                return new EasyPlayer(move);
            case "user":
                return new UserPlayer(s, move);
            case "medium":
                return new MediumPlayer(move);
            case "hard":
                return new HardPlayer(move);
        }
        return null;
    }
}

class EasyPlayer implements Player {
    Random random = new Random();
    char move;

    EasyPlayer(char move) {
        this.move = move;
    }

    public void makeMove(Field field) {
        System.out.println("Making move level \"easy\"");

        int coordinates;
        do {
            coordinates = random.nextInt(9);
        } while (!field.isEmptyCell(coordinates / 3, coordinates % 3));

        field.setCell(move, coordinates / 3, coordinates % 3);
    }
}