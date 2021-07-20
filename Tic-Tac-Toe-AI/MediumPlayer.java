package tictactoe;

import java.util.Random;

class MediumPlayer implements Player {
    Random random = new Random();
    char move;
    char opponent_move;

    MediumPlayer(char move) {
        this.move = move;
        opponent_move = move == 'X' ? 'O' : 'X';
    }

    int checkWin(Field field, char move) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (field.isEmptyCell(i, j)) {
                    field.setCell(move, i, j);
                    System.out.println(field.getResult());
                    if (field.getResult().equals(move + " wins")) {
                        return i * 3 + j;
                    }
                    field.setCell(' ', i, j);
                }
            }
        }
        return -1;
    }

    public void makeMove(Field field) {
        System.out.println("Making move level \"medium\"");

        int make_move = checkWin(field, this.move);
        if (make_move != -1) {
            field.setCell(move, make_move / 3, make_move % 3);
            return;
        }

        make_move = checkWin(field, this.opponent_move);
        if (make_move != -1) {
            field.setCell(move, make_move / 3, make_move % 3);
            return;
        }

        int coordinates;
        do {
            coordinates = random.nextInt(9);
        } while (!field.isEmptyCell(coordinates / 3, coordinates % 3));
        field.setCell(move, coordinates / 3, coordinates % 3);
    }
}