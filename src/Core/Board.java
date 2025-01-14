package Core;

import Utilities.Type;

public class Board {
    Cell[][] board;

    public Board(Cell[][] board) {
        this.board = board;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    private void initilizeBoard()
    {
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                if ((i == 0 && (j >= 0 && j <= 6 || j >= 10 && j <= 16)) || (i == 6 && (j >= 0 && j <= 6 )) ||  (i == 16 && (j >= 0 && j <= 6 || j >= 10 && j <= 16)) || (j == 0 && (i >= 0 && i <= 6 || i >= 10 && i <= 16))  || (j == 16 && (i >= 0 && i <= 6 || i >= 10 && i <= 16))  )
                        new Cell(i , j , Type.NORMAL);
            }
            }
    }

    private void printBoard()
    {

    }
}
