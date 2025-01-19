package Core;

import Utilities.Color;
import Utilities.Type;

public class Board {

    Cell[] board;
    public  final  int[] playerStartPositions = {0, 13, 26, 40};
    int[][] homePaths ;


    public Cell[] getBoard() {
        return board;
    }

    public void setBoard(Cell[] board) {
        this.board = board;
    }


    public void initializeBoard() {

        board = new Cell[52];
        homePaths = new int[4][6];

        for (int i = 0; i < 52; i++) {
            if (i == 0)
                board[i] = new Cell(13, 6, Type.SAFEZONE, Color.BLUE);
            else if (i == 1)
                board[i] = new Cell(12, 6, Type.NORMAL, Color.WHITE);
            else if (i == 2)
                board[i] = new Cell(11, 6, Type.NORMAL, Color.WHITE);
            else if (i == 3)
                board[i] = new Cell(10, 6, Type.NORMAL, Color.WHITE);
            else if (i == 4)
                board[i] = new Cell(9, 6, Type.NORMAL, Color.WHITE);
            else if (i == 5)
                board[i] = new Cell(8, 5, Type.NORMAL, Color.WHITE);
            else if (i == 6)
                board[i] = new Cell(8, 4, Type.NORMAL, Color.WHITE);
            else if (i == 7)
                board[i] = new Cell(8, 3, Type.NORMAL, Color.WHITE);
            else if (i == 8)
                board[i] = new Cell(8, 2, Type.SAFEZONE, Color.BROWN);
            else if (i == 9)
                board[i] = new Cell(8, 1, Type.NORMAL, Color.WHITE);
            else if (i == 10)
                board[i] = new Cell(8, 0, Type.NORMAL, Color.WHITE);
            else if (i == 11)
                board[i] = new Cell(7, 0, Type.NORMAL, Color.WHITE);
            else if (i == 12)
                board[i] = new Cell(6, 0, Type.NORMAL, Color.WHITE);
            else if (i == 13)
                board[i] = new Cell(6, 1, Type.SAFEZONE, Color.RED);
            else if (i == 14)
                board[i] = new Cell(6, 2, Type.NORMAL, Color.WHITE);
            else if (i == 15)
                board[i] = new Cell(6, 3, Type.NORMAL, Color.WHITE);
            else if (i == 16)
                board[i] = new Cell(6, 4, Type.NORMAL, Color.WHITE);
            else if (i == 17)
                board[i] = new Cell(6, 5, Type.NORMAL, Color.WHITE);
            else if (i == 18)
                board[i] = new Cell(5, 6, Type.NORMAL, Color.WHITE);
            else if (i == 19)
                board[i] = new Cell(4, 6, Type.NORMAL, Color.WHITE);
            else if (i == 20)
                board[i] = new Cell(3, 6, Type.NORMAL, Color.WHITE);
            else if (i == 21)
                board[i] = new Cell(2, 6, Type.SAFEZONE, Color.BROWN);
            else if (i == 22)
                board[i] = new Cell(1, 6, Type.NORMAL, Color.WHITE);
            else if (i == 23)
                board[i] = new Cell(0, 6, Type.NORMAL, Color.WHITE);
            else if (i == 24)
                board[i] = new Cell(0, 7, Type.NORMAL, Color.WHITE);
            else if (i == 25)
                board[i] = new Cell(0, 8, Type.NORMAL, Color.WHITE);
            else if (i == 26)
                board[i] = new Cell(1, 8, Type.SAFEZONE, Color.GREEN);
            else if (i == 27)
                board[i] = new Cell(2, 8, Type.NORMAL, Color.WHITE);
            else if (i == 28)
                board[i] = new Cell(2, 8, Type.NORMAL, Color.WHITE);
            else if (i == 29)
                board[i] = new Cell(3, 8, Type.NORMAL, Color.WHITE);
            else if (i == 30)
                board[i] = new Cell(4, 8, Type.NORMAL, Color.WHITE);
            else if (i == 31)
                board[i] = new Cell(5, 8, Type.NORMAL, Color.WHITE);
            else if (i == 32)
                board[i] = new Cell(6, 9, Type.NORMAL, Color.WHITE);
            else if (i == 33)
                board[i] = new Cell(6, 10, Type.NORMAL, Color.WHITE);
            else if (i == 34)
                board[i] = new Cell(6, 11, Type.NORMAL, Color.WHITE);
            else if (i == 35)
                board[i] = new Cell(6, 12, Type.SAFEZONE, Color.BROWN);
            else if (i == 36)
                board[i] = new Cell(6, 13, Type.NORMAL, Color.WHITE);
            else if (i == 37)
                board[i] = new Cell(6, 14, Type.NORMAL, Color.WHITE);
            else if (i == 38)
                board[i] = new Cell(7, 14, Type.NORMAL, Color.WHITE);
            else if (i == 39)
                board[i] = new Cell(8, 14, Type.NORMAL, Color.WHITE);
            else if (i == 40)
                board[i] = new Cell(8, 13, Type.SAFEZONE, Color.YELLOW);
            else if (i == 41)
                board[i] = new Cell(8, 12, Type.NORMAL, Color.WHITE);
            else if (i == 42)
                board[i] = new Cell(8, 11, Type.NORMAL, Color.WHITE);
            else if (i == 43)
                board[i] = new Cell(8, 10, Type.NORMAL, Color.WHITE);
            else if (i == 44)
                board[i] = new Cell(8, 9, Type.NORMAL, Color.WHITE);
            else if (i == 45)
                board[i] = new Cell(9, 8, Type.NORMAL, Color.WHITE);
            else if (i == 46)
                board[i] = new Cell(10, 8, Type.NORMAL, Color.WHITE);
            else if (i == 47)
                board[i] = new Cell(11, 8, Type.NORMAL, Color.WHITE);
            else if (i == 48)
                board[i] = new Cell(12, 8, Type.SAFEZONE, Color.BROWN);
            else if (i == 49)
                board[i] = new Cell(13, 8, Type.NORMAL, Color.WHITE);
            else if (i == 50)
                board[i] = new Cell(14, 8, Type.NORMAL, Color.WHITE);

            else board[i] = new Cell(14, 8, Type.NORMAL, Color.WHITE);
        }
    }

    public void printBoard() {
        int rows = 15;
        int cols = 15;
        Cell[][] grid = new Cell[rows][cols];

        // Initialize the grid with default NORMAL cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j, Type.NORMAL, Color.WHITE);
            }
        }

        // Place Ludo path cells from board array
        for (int i = 0; i < 52; i++) {
            int x = board[i].getPosX();
            int y = board[i].getPosY();
            grid[x][y] = board[i];
        }

        // Add additional zones (safe zones, home areas, goals)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Safe Zones
                if ((i == 6 && j == 1) || (i == 7 && j == 1) || (i == 7 && j == 2) ||
                        (i == 7 && j == 3) || (i == 7 && j == 4) || (i == 7 && j == 5)) {
                    grid[i][j] = new Cell(i, j, Type.SAFEZONE, Color.RED);
                } else if ((i == 13 && j == 7) || (i == 12 && j == 7) || (i == 11 && j == 7) ||
                        (i == 10 && j == 7) || (i == 9 && j == 7) || (i == 13 && j == 6)) {
                    grid[i][j] = new Cell(i, j, Type.SAFEZONE, Color.BLUE);
                } else if ((i == 8 && j == 13) || (i == 7 && j == 13) || (i == 7 && j == 12) ||
                        (i == 7 && j == 11) || (i == 7 && j == 10) || (i == 7 && j == 9)) {
                    grid[i][j] = new Cell(i, j, Type.SAFEZONE, Color.YELLOW);
                } else if ((i == 1 && j == 8) || (i == 1 && j == 7) || (i == 2 && j == 7) ||
                        (i == 3 && j == 7) || (i == 4 && j == 7) || (i == 5 && j == 7)) {
                    grid[i][j] = new Cell(i, j, Type.SAFEZONE, Color.GREEN);
                }
                // Goal Area
                else if ((i == 6 && j == 6) || (i == 6 && j == 7) || (i == 6 && j == 8) ||
                        (i == 7 && j == 6) || (i == 7 && j == 7) || (i == 7 && j == 8) ||
                        (i == 8 && j == 6) || (i == 8 && j == 7) || (i == 8 && j == 8)) {
                    grid[i][j] = new Cell(i, j, Type.GOAL, Color.BLACK);
                }
                // Home Areas
                else if (i < 6 && j < 6) { // Red Home
                    if ((i == 1 && j >= 1 && j <= 4) || (i == 2 && (j == 1 || j == 4)) ||
                            (i == 3 && (j == 1 || j == 4)) || (i == 4 && (j >= 1 && j <= 4))) {
                        grid[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE);
                    } else {
                        grid[i][j] = new Cell(i, j, Type.HOME, Color.RED);
                    }
                } else if (i < 6 && j > 8) { // Green Home
                    if ((i == 1 && j >= 10 && j <= 13) || (i == 2 && (j == 10 || j == 13)) ||
                            (i == 3 && (j == 10 || j == 13)) || (i == 4 && (j >= 10 && j <= 13))) {
                        grid[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE);
                    } else {
                        grid[i][j] = new Cell(i, j, Type.HOME, Color.GREEN);
                    }
                } else if (i > 8 && j < 6) { // Blue Home
                    if ((i == 10 && (j >= 1 && j <= 4)) || (i == 13 && (j >= 1 && j <= 4)) ||
                            (j == 4 && (i == 11 || i == 12)) || (j == 1 && (i == 11 || i == 12))) {
                        grid[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE);
                    } else {
                        grid[i][j] = new Cell(i, j, Type.HOME, Color.BLUE);
                    }
                } else if (i > 8 && j > 8) { // Yellow Home
                    if ((i == 10 && j >= 10 && j <= 13) || (i == 11 && (j == 10 || j == 13)) ||
                            (i == 12 && (j == 10 || j == 13)) || (i == 13 && j >= 10 && j <= 13)) {
                        grid[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE);
                    } else {
                        grid[i][j] = new Cell(i, j, Type.HOME, Color.YELLOW);
                    }
                }
            }
        }

        // Print the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].getText() + " ");
            }
            System.out.println();
        }
    }

}
