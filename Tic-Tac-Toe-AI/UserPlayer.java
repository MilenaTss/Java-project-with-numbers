package tictactoe;

import java.util.Scanner;

class UserPlayer implements Player {
    Scanner s;
    char move;

    UserPlayer(Scanner s, char move) {
        this.move = move;
        this.s = s;
    }

    static int getCoordinates(Scanner s, Field field) {
        int x, y;
        while (true) {
            System.out.print("Enter the coordinates: ");
            try {
                String[] input = s.nextLine().split(" ");
                x = Integer.parseInt(input[0]);
                y = Integer.parseInt(input[1]);
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (x > 3 || y > 3 || x < 1 || y < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (!field.isEmptyCell(x - 1, y - 1)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            break;
        }
        return (x - 1) * 3 + y - 1;
    }

    public void makeMove(Field field) {
        int coordinates = getCoordinates(s, field);
        int x = coordinates / 3;
        int y = coordinates % 3;
        field.setCell(move, x, y);
    }
}