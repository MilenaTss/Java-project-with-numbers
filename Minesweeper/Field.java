import java.util.Random;

class Field {
    private final char[][] real_field = new char[9][9];
    protected final char[][] user_field = new char[9][9];

    int numOfMines;

    Field() {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                real_field[i][j] = '.';
                user_field[i][j] = '.';
            }
        }
    }

    public void printField() {
        System.out.print(" |123456789|\n-|---------|\n");
        for (int i = 0; i < 9; ++i) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < 9; ++j) {
                System.out.print(user_field[i][j]);
            }
            System.out.print("|\n");
        }
        System.out.println("-|---------|");
    }

    public void createMines(int num) {
        numOfMines = num;
        Random r = new Random();
        for (int i = 0; i < num; ++i) {
            int x;
            do {
                x = r.nextInt(81);
            } while (real_field[x / 9][x % 9] == 'X');

            real_field[x / 9][x % 9] = 'X';
        }
        fillFieldWithNumbers();
    }

    public boolean checkMines() {
        int numMarkedCells = 0;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (real_field[i][j] == 'X' && user_field[i][j] == '*') numMarkedCells++;
                if (user_field[i][j] == '*' && real_field[i][j] != 'X') return false;
            }
        }

        int numOpenedCells = 0;

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (user_field[i][j] != '.') numOpenedCells++;
            }
        }
        return numMarkedCells == numOfMines || numOpenedCells == 81 - numOfMines;
    }

    public char getCell(int i, int j) {
        return user_field[i][j];
    }

    public void markMine(int i, int j) {
        if (user_field[i][j] != '*') user_field[i][j] = '*';
        else user_field[i][j] = '.';
    }

    public boolean openCells(int x, int y) {
        if (real_field[x][y] == 'X') return false;
        user_field[x][y] = real_field[x][y] == '.' ? '/' : real_field[x][y];

        // if there are not mines at all we need to fill these cells with new data and call recursive with adjacent cell
        if (!shouldBeOpened(x, y)) return true;

        for (int i = x - 1; i <= x + 1; ++i) {
            for (int j = y - 1; j <= y + 1; ++j) {
                try {
                    user_field [i][j] = real_field[i][j] == '.' ? '/' : real_field[i][j];
                } catch (Exception ignored) {}
            }
        }

        for (int i = x - 1; i <= x + 1; ++i) {
            for (int j = y - 1; j <= y + 1; ++j) {
                if (i >= 0 && j >= 0 && i < 9 && j < 9) {
                    openCells(i, j);
                }
            }
        }
        return true;
    }

    private boolean shouldBeOpened(int x, int y) {
        int numOfNotMinesInReal = 0, numOfCellsAround = 0;
        int numOfEmptyCells = 0;

        //check if there are not mines around, and check if we need to full these cells
        for (int i = x - 1; i <= x + 1; ++i) {
            for (int j = y - 1; j <= y + 1; ++j) {
                try {
                    if (real_field[i][j] != 'X') numOfNotMinesInReal++;
                    if (user_field[i][j] == '.') numOfEmptyCells++;
                    numOfCellsAround++;
                } catch (Exception ignored) {}
            }
        }

        //if all cells are not empty it means that everything is writed
        if (numOfEmptyCells == 0) return false;

        //if there are not mines around then we need to open cells
        return numOfNotMinesInReal == numOfCellsAround;
    }

    private void fillFieldWithNumbers() {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (real_field[i][j] == 'X') continue;
                int num = countMinesAround(i, j);
                if (num != 0) real_field[i][j] = (char) ('0' + num);
            }
        }
    }

    private int countMinesAround(int x, int y) {
        int num = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try {
                    if (real_field[i][j] == 'X') num++;
                } catch (Exception ignored) { }
            }
        }
        return num;
    }

    public void openAllMines() {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (real_field[i][j] == 'X') {
                    user_field[i][j] = 'X';
                }
            }
        }
    }
}