import java.util.Scanner;

class Game {
    Field field;

    Game(int n) {
        field = new Field();
        field.createMines(n);
        field.printField();
    }

    public void play(Scanner s) {
        boolean win = true;

        while (!field.checkMines()) {
            System.out.print("Set/unset mines marks or claim a cell as free: ");

            int column = s.nextInt();
            int row = s.nextInt();
            String mode = s.next();
            column--; row--;

            if (field.getCell(row, column) != '*' && field.getCell(row, column) != '.') {
                System.out.print("There is a number here!");
                continue;
            }

            if (mode.equals("mine")) {
                field.markMine(row, column);
            }

            if (mode.equals("free")) {
                if (!field.openCells(row, column)) {
                    win = false;
                    field.openAllMines();
                    field.printField();
                    System.out.println("You stepped on a mine and failed!");
                    break;
                }
            }

            field.printField();
        }

        if (win) {
            System.out.print("Congratulations! You found all the mines!");
        }
    }
}