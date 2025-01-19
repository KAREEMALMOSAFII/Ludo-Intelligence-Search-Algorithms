package Core;

import Utilities.Color;
import Utilities.Type;

public class Board {
    Cell[][] board;

    public Board(Cell[][] board) {
        this.board = board;
    }

    public Board() {
        this.board = new Cell[15][15];
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public void initilizeBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {

                if (i == 6 && j == 1 || i == 7 && j == 1 || i == 7 && j == 2 || i == 7 && j == 3 || i == 7 && j == 4 || i == 7 && j == 5)
                    board[i][j] = new Cell(i, j, Type.SAFEZONE, Color.RED);
                else if (i == 13 && j == 7 || i == 12 && j == 7 || i == 11 && j == 7 || i == 10 && j == 7 || i == 9 && j == 7 || i == 13 && j == 6)
                    board[i][j] = new Cell(i, j, Type.SAFEZONE, Color.BLUE);

                else if (i == 8 && j == 13 || i == 7 && j == 13 || i == 7 && j == 12 || i == 7 && j == 11 || i == 7 && j == 10 || i == 7 && j == 9)
                    board[i][j] = new Cell(i, j, Type.SAFEZONE, Color.YELLOW);

                else if (i == 1 && j == 8 || i == 1 && j == 7 || i == 2 && j == 7 || i == 3 && j == 7 || i == 4 && j == 7 || i == 5 && j == 7)
                    board[i][j] = new Cell(i, j, Type.SAFEZONE, Color.GREEN);

                else if (i == 6 && j == 6 || i == 6 && j == 7 || i == 6 && j == 8 || i == 7 && j == 6 || i == 7 && j == 7 || i == 7 && j == 8 || i == 8 && j == 6 || i == 8 && j == 7 || i == 8 && j == 8)
                    board[i][j] = new Cell(i, j, Type.GOAL, Color.BLACK);

                else if (i < 6 && j < 6) {
                    if ((i == 1 && j >= 1 && j <= 4) || (i == 2 && (j == 1 || j == 4)) ||
                            (i == 3 && (j == 1 || j == 4)) || (i == 4 && (j == 1 || j == 2 || j == 3 || j == 4))) {
                        board[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE);

                    } else {
                        board[i][j] = new Cell(i, j, Type.HOME, Color.RED);

                    }
                } else if (i < 6 && j > 8) {
                    if ((i == 1 && j >= 10 && j <= 13) || (i == 2 && (j == 10 || j == 13)) ||
                            (i == 3 && (j == 10 || j == 13)) || (i == 4 && (j == 10 || j == 11 || j == 12 || j == 13))) {
                        board[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE);
                    } else {
                        board[i][j] = new Cell(i, j, Type.HOME, Color.GREEN);
                    }
                } else if (i > 8 && j < 6) {
                    if (i == 10 && (j == 1 || j == 2 || j == 3 || j == 4) || i == 13 && (j == 1 || j == 2 || j == 3 || j == 4) || j == 4 && (i == 11 || i == 12) || j == 1 && (i == 11 || i == 12)) {
                        board[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE);
                    } else {
                        board[i][j] = new Cell(i, j, Type.HOME, Color.BLUE);
                    }
                } else if (i > 8 && j > 8) {
                    if ((i == 10 && j == 10) || (i == 10 && j == 11) || (i == 10 && j == 12) || (i == 10 && j == 13) || (i == 11 && (j == 10 || j == 13)) ||
                            (i == 12 && (j == 10 || j == 13)) || (i == 13 && j == 10) || (i == 13 && j == 11) || (i == 13 && j == 12) || (i == 13 && j == 13)) {
                        board[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE);
                    } else {
                        board[i][j] = new Cell(i, j, Type.HOME, Color.YELLOW);
                    }
                } else {
                    board[i][j] = new Cell(i, j, Type.NORMAL, Color.WHITE);
                }
            }
        }
    }

   /* public void printBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(board[i][j].getText());
            }
            System.out.println();
        }

    }*/
   public void printBoard() {
       for (int i = 0; i < board.length; i++) {
           for (int j = 0; j < board[i].length; j++) {
               // If a token is present on this cell, display the token's visual representation
               if (!board[i][j].getTokens().isEmpty()) {
                   System.out.print("T" + board[i][j].getTokens().get(0).getOwner().getColor().name().charAt(0) + " ");
               } else {
                   System.out.print(board[i][j].getText() + " ");
               }
           }
           System.out.println(); // Newline after each row
       }
   }
}
