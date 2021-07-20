package tictactoe;

class HardPlayer implements Player {
    char move;

    static class Move {
        int row, col;
        Move(int r, int c) {
            row = r;
            col = c;
        }
    }

    HardPlayer(char move) {
        this.move = move;
    }

    static char invertMove(char move) {
        return move == 'X' ? 'O' : 'X';
    }

    static int minimax(Field field, int depth, char move) {
        if (field.getResult().equals("X wins")) return 10;
        if (field.getResult().equals("O wins")) return -10;
        if (field.getResult().equals("Draw")) return 0;

        int best = 1000;
        if (move == 'X') best = -1000;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field.isEmptyCell(i, j)) {
                    field.setCell(move, i, j);
                    if (move == 'X') {
                        best = Math.max(best, minimax(field,
                                depth + 1, invertMove(move)));
                    } else {
                        best = Math.min(best, minimax(field,
                                depth + 1, invertMove(move)));
                    }
                    field.setCell(' ', i, j);
                }
            }
        }
        return best;
    }

    Move findMove (Field field) {
        int bestVal = -1000;
        if (move == 'O') bestVal = 1000;
        Move bestMove = new Move(-1, -1);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field.isEmptyCell(i, j)) {
                    field.setCell(move, i, j);
                    int moveVal = minimax(field, 0, invertMove(move));
                    field.setCell(' ', i, j);
                    if (move == 'X' && moveVal > bestVal
                            || move == 'O' && moveVal < bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    public void makeMove (Field field){
        System.out.println("Making move hard");
        Move m = findMove(field);
        field.setCell(move, m.row, m.col);
    }
}