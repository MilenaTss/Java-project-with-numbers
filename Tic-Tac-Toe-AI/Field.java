package tictactoe;

import java.util.Arrays;

class Field {
    private final char[][] field = new char[3][3];
    private int num_x = 0, num_o = 0;

    Field () {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                field[i][j] = ' ';
            }
        }
    }

    public void printField() {
        System.out.println("---------");
        for (int i = 0; i < 3; ++i) {
            System.out.print("| ");
            for (int j = 0; j < 3; ++j) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public void setCell(char xo, int x, int y) {
        if (field[x][y] == 'X') num_x--;
        if (field[x][y] == 'O') num_o--;
        if (xo == 'X') num_x++;
        if (xo == 'O') num_o++;
        field[x][y] = xo;
    }

    public boolean isEmptyCell(int x, int y) {
        return field[x][y] == ' ';
    }

    public String getResult() {
        for (int i = 0; i < 3; ++i) {
            if (Arrays.equals(field[i], new char[]{'X', 'X', 'X'})) return "X wins";
            if (Arrays.equals(field[i], new char[]{'O', 'O', 'O'})) return "O wins";
        }

        for (int i = 0; i < 3; ++i) {
            if (field[0][i] == 'X' && field[1][i] == 'X' && field[2][i] == 'X') return "X wins";
            if (field[0][i] == 'O' && field[1][i] == 'O' && field[2][i] == 'O') return "O wins";
        }

        if (field[0][0] == 'X' && field[1][1] == 'X' && field[2][2] == 'X') return "X wins";
        if (field[0][0] == 'O' && field[1][1] == 'O' && field[2][2] == 'O') return "O wins";
        if (field[0][2] == 'X' && field[1][1] == 'X' && field[2][0] == 'X') return "X wins";
        if (field[0][2] == 'O' && field[1][1] == 'O' && field[2][0] == 'O') return "O wins";

        if (num_x + num_o == 9) {
            return "Draw";
        }

        return "Game not finished";
    }
}