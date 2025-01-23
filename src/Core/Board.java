package Core;

import Utilities.Color;
import Utilities.Type;
public class Board {

    Cell[] board;
    public static final int[] playerStartPositions = {0, 13, 26, 40};
    public static  Cell[][] homePaths;

    public Cell[] getBoard() {
        return board;
    }

    public void setBoard(Cell[] board) {
        this.board = board;
    }

    public int getCellIndex(int x , int y) {
        for (int i =0;i < board.length;i++) {
            if (board[i].getPosX() == x && board[i].getPosY() == y)
                return i;
        }
        System.out.println("No cell found for position x=" + x + ", y=" + y);
        return -1;

    }

    public void initializeBoard() {

        board = new Cell[52];
        homePaths = new Cell[4][6];

        for (int i = 0; i < 52; i++) {
            if (i == 0)
                board[i] = new Cell(13, 6, Type.SAFEZONE, Color.BLUE,null);
            else if (i == 1)
                board[i] = new Cell(12, 6, Type.NORMAL, Color.WHITE, null);
            else if (i == 2)
                board[i] = new Cell(11, 6, Type.NORMAL, Color.WHITE, null);
            else if (i == 3)
                board[i] = new Cell(10, 6, Type.NORMAL, Color.WHITE, null);
            else if (i == 4)
                board[i] = new Cell(9, 6, Type.NORMAL, Color.WHITE, null);
            else if (i == 5)
                board[i] = new Cell(8, 5, Type.NORMAL, Color.WHITE, null);
            else if (i == 6)
                board[i] = new Cell(8, 4, Type.NORMAL, Color.WHITE, null);
            else if (i == 7)
                board[i] = new Cell(8, 3, Type.NORMAL, Color.WHITE, null);
            else if (i == 8)
                board[i] = new Cell(8, 2, Type.SAFEZONE, Color.BROWN, null);
            else if (i == 9)
                board[i] = new Cell(8, 1, Type.NORMAL, Color.WHITE, null);
            else if (i == 10)
                board[i] = new Cell(8, 0, Type.NORMAL, Color.WHITE, null);
            else if (i == 11)
                board[i] = new Cell(7, 0, Type.NORMAL, Color.WHITE, null);
            else if (i == 12)
                board[i] = new Cell(6, 0, Type.NORMAL, Color.WHITE, null);
            else if (i == 13)
                board[i] = new Cell(6, 1, Type.SAFEZONE, Color.RED, null);
            else if (i == 14)
                board[i] = new Cell(6, 2, Type.NORMAL, Color.WHITE, null);
            else if (i == 15)
                board[i] = new Cell(6, 3, Type.NORMAL, Color.WHITE, null);
            else if (i == 16)
                board[i] = new Cell(6, 4, Type.NORMAL, Color.WHITE, null);
            else if (i == 17)
                board[i] = new Cell(6, 5, Type.NORMAL, Color.WHITE, null);
            else if (i == 18)
                board[i] = new Cell(5, 6, Type.NORMAL, Color.WHITE, null);
            else if (i == 19)
                board[i] = new Cell(4, 6, Type.NORMAL, Color.WHITE, null);
            else if (i == 20)
                board[i] = new Cell(3, 6, Type.NORMAL, Color.WHITE, null);
            else if (i == 21)
                board[i] = new Cell(2, 6, Type.SAFEZONE, Color.BROWN, null);
            else if (i == 22)
                board[i] = new Cell(1, 6, Type.NORMAL, Color.WHITE, null);
            else if (i == 23)
                board[i] = new Cell(0, 6, Type.NORMAL, Color.WHITE, null);
            else if (i == 24)
                board[i] = new Cell(0, 7, Type.NORMAL, Color.WHITE, null);
            else if (i == 25)
                board[i] = new Cell(0, 8, Type.NORMAL, Color.WHITE, null);
            else if (i == 26)
                board[i] = new Cell(1, 8, Type.SAFEZONE, Color.GREEN, null);
            else if (i == 27)
                board[i] = new Cell(2, 8, Type.NORMAL, Color.WHITE, null);
            else if (i == 28)
                board[i] = new Cell(3, 8, Type.NORMAL, Color.WHITE, null);
            else if (i == 29)
                board[i] = new Cell(4, 8, Type.NORMAL, Color.WHITE, null);
            else if (i == 30)
                board[i] = new Cell(5, 8, Type.NORMAL, Color.WHITE, null);
            else if (i == 31)
                board[i] = new Cell(6, 9, Type.NORMAL, Color.WHITE, null);
            else if (i == 32)
                board[i] = new Cell(6, 10, Type.NORMAL, Color.WHITE, null);
            else if (i == 33)
                board[i] = new Cell(6, 11, Type.NORMAL, Color.WHITE, null);
            else if (i == 34)
                board[i] = new Cell(6, 12, Type.SAFEZONE, Color.BROWN,null);
            else if (i == 35)
                board[i] = new Cell(6, 13, Type.NORMAL, Color.WHITE, null);
            else if (i == 36)
                board[i] = new Cell(6, 14, Type.NORMAL, Color.WHITE, null);
            else if (i == 37)
                board[i] = new Cell(7, 14, Type.NORMAL, Color.WHITE, null);
            else if (i == 38)
                board[i] = new Cell(8, 14, Type.NORMAL, Color.WHITE, null);
            else if (i == 39)
                board[i] = new Cell(8, 13, Type.SAFEZONE, Color.YELLOW,null);
            else if (i == 40)
                board[i] = new Cell(8, 12, Type.NORMAL, Color.WHITE, null);
            else if (i == 41)
                board[i] = new Cell(8, 11, Type.NORMAL, Color.WHITE, null);
            else if (i == 42)
                board[i] = new Cell(8, 10, Type.NORMAL, Color.WHITE, null);
            else if (i == 43)
                board[i] = new Cell(8, 9, Type.NORMAL, Color.WHITE, null);
            else if (i == 44)
                board[i] = new Cell(9, 8, Type.NORMAL, Color.WHITE, null);
            else if (i == 45)
                board[i] = new Cell(10, 8, Type.NORMAL, Color.WHITE, null);
            else if (i == 46)
                board[i] = new Cell(11, 8, Type.NORMAL, Color.WHITE, null);
            else if (i == 47)
                board[i] = new Cell(12, 8, Type.SAFEZONE, Color.BROWN ,null);
            else if (i == 48)
                board[i] = new Cell(13, 8, Type.NORMAL, Color.WHITE, null);
            else if (i == 49)
                board[i] = new Cell(14, 8, Type.NORMAL, Color.WHITE, null);
            else if (i == 50)
             board[i] = new Cell(14, 7, Type.NORMAL, Color.WHITE, null);
            else
                board[i] = new Cell(14, 6, Type.NORMAL, Color.WHITE, null);
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == 0) {
                    if (j == 0)
                        homePaths[i][j] = new Cell(13, 8, Type.SAFEZONE, Color.BLUE,null);

                    else if (j == 1)
                        homePaths[i][j] = new Cell(12, 8, Type.SAFEZONE, Color.BLUE,null);

                    else if (j == 2)
                        homePaths[i][j] = new Cell(11, 8, Type.SAFEZONE, Color.BLUE,null);
                    else if (j == 3)
                        homePaths[i][j] = new Cell(10, 8, Type.SAFEZONE, Color.BLUE,null);
                    else if (j == 4)
                        homePaths[i][j] = new Cell(9, 8, Type.SAFEZONE, Color.BLUE,null);
                    else
                        homePaths[i][j] = new Cell(8, 7, Type.GOAL, Color.BLACK,null);
                }
                if (i == 1) {
                    if (j == 0)
                        homePaths[i][j] = new Cell(7, 1, Type.SAFEZONE, Color.RED,null);

                    else if (j == 1)
                        homePaths[i][j] = new Cell(7, 2, Type.SAFEZONE, Color.RED,null);

                    else if (j == 2)
                        homePaths[i][j] = new Cell(7, 3, Type.SAFEZONE, Color.RED,null);
                    else if (j == 3)
                        homePaths[i][j] = new Cell(7, 4, Type.SAFEZONE, Color.RED,null);
                    else if (j == 4)
                        homePaths[i][j] = new Cell(7, 5, Type.SAFEZONE, Color.RED,null);
                    else
                        homePaths[i][j] = new Cell(7, 6, Type.GOAL, Color.BLACK,null);
                }
                if (i == 2) {
                    if (j == 0)
                        homePaths[i][j] = new Cell(1, 7, Type.SAFEZONE, Color.GREEN,null);

                    else if (j == 1)
                        homePaths[i][j] = new Cell(2, 7, Type.SAFEZONE, Color.GREEN,null);

                    else if (j == 2)
                        homePaths[i][j] = new Cell(3, 7, Type.SAFEZONE, Color.GREEN,null);
                    else if (j == 3)
                        homePaths[i][j] = new Cell(4, 7, Type.SAFEZONE, Color.GREEN,null);
                    else if (j == 4)
                        homePaths[i][j] = new Cell(5, 7, Type.SAFEZONE, Color.GREEN,null);
                    else
                        homePaths[i][j] = new Cell(6, 7, Type.GOAL, Color.BLACK,null);
                }
                if (i == 3) {
                    if (j == 0)
                        homePaths[i][j] = new Cell(7, 13, Type.SAFEZONE, Color.YELLOW,null);

                    else if (j == 1)
                        homePaths[i][j] = new Cell(7, 12, Type.SAFEZONE, Color.YELLOW,null);

                    else if (j == 2)
                        homePaths[i][j] = new Cell(7, 11, Type.SAFEZONE, Color.YELLOW,null);
                    else if (j == 3)
                        homePaths[i][j] = new Cell(7, 10, Type.SAFEZONE, Color.YELLOW,null);
                    else if (j == 4)
                        homePaths[i][j] = new Cell(7, 9, Type.SAFEZONE, Color.YELLOW,null);
                    else
                        homePaths[i][j] = new Cell(7, 8, Type.GOAL, Color.BLACK,null);
                }
            }
        }
    }

    public void printBoard() {
        int rows = 15;
        int cols = 15;
        Cell[][] grid = new Cell[rows][cols];
        Cell[][] homPathGrid = new Cell[4][6];
        // Initialize the grid with default NORMAL cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j, Type.NORMAL, Color.WHITE,null);
            }
        }

        // Place Ludo path cells from board array
        for (int i = 0; i < 52; i++) {
            System.out.print(" "+ board[i].getText()+" ");
            int x = board[i].getPosX();
            int y = board[i].getPosY();
            grid[x][y] = board[i];
        }

        System.out.println(" ");
        System.out.println(" ");
        // Add additional zones (safe zones, home areas, goals)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Safe Zones
                if ((i == 6 && j == 1) || (i == 7 && j == 1) || (i == 7 && j == 2) ||
                        (i == 7 && j == 3) || (i == 7 && j == 4) || (i == 7 && j == 5)) {
                    grid[i][j] = new Cell(i, j, Type.SAFEZONE, Color.RED, null);
                } else if ((i == 13 && j == 7) || (i == 12 && j == 7) || (i == 11 && j == 7) ||
                        (i == 10 && j == 7) || (i == 9 && j == 7) || (i == 13 && j == 6)) {
                    grid[i][j] = new Cell(i, j, Type.SAFEZONE, Color.BLUE,null);
                } else if ((i == 8 && j == 13) || (i == 7 && j == 13) || (i == 7 && j == 12) ||
                        (i == 7 && j == 11) || (i == 7 && j == 10) || (i == 7 && j == 9)) {
                    grid[i][j] = new Cell(i, j, Type.SAFEZONE, Color.YELLOW,null);
                } else if ((i == 1 && j == 8) || (i == 1 && j == 7) || (i == 2 && j == 7) ||
                        (i == 3 && j == 7) || (i == 4 && j == 7) || (i == 5 && j == 7)) {
                    grid[i][j] = new Cell(i, j, Type.SAFEZONE, Color.GREEN,null);
                }
                // Goal Area
                else if ((i == 6 && j == 6) || (i == 6 && j == 7) || (i == 6 && j == 8) ||
                        (i == 7 && j == 6) || (i == 7 && j == 7) || (i == 7 && j == 8) ||
                        (i == 8 && j == 6) || (i == 8 && j == 7) || (i == 8 && j == 8)) {
                    grid[i][j] = new Cell(i, j, Type.GOAL, Color.BLACK,null);
                }
                // Home Areas
                else if (i < 6 && j < 6) { // Red Home
                    if ((i == 1 && j >= 1 && j <= 4) || (i == 2 && (j == 1 || j == 4)) ||
                            (i == 3 && (j == 1 || j == 4)) || (i == 4 && (j >= 1 && j <= 4))) {
                        grid[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE,null);
                    } else {
                        if (i == 0 && j < 6 || j == 0 && i < 6 || j == 5 && i < 6 || i == 5 && j < 6)
                            grid[i][j] = new Cell(i, j, Type.EMPTY, Color.RED,null);
                        else
                            grid[i][j] = new Cell(i, j, Type.HOME, Color.RED,Color.RED);
                    }
                } else if (i < 6 && j > 8) { // Green Home
                    if ((i == 1 && j >= 10 && j <= 13) || (i == 2 && (j == 10 || j == 13)) ||
                            (i == 3 && (j == 10 || j == 13)) || (i == 4 && (j >= 10 && j <= 13))) {
                        grid[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE,null);
                    } else {
                        if (i == 0 && j >= 9 && j < 15 || j == 14 && i < 6 || j == 9 && i < 6 || i == 5 && j >= 9 && j < 15)
                            grid[i][j] = new Cell(i, j, Type.EMPTY, Color.GREEN,null);
                        else
                            grid[i][j] = new Cell(i, j, Type.HOME, Color.GREEN,Color.GREEN);
                    }
                } else if (i > 8 && j < 6) { // Blue Home
                    if ((i == 10 && (j >= 1 && j <= 4)) || (i == 13 && (j >= 1 && j <= 4)) ||
                            (j == 4 && (i == 11 || i == 12)) || (j == 1 && (i == 11 || i == 12))) {
                        grid[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE,null);
                    } else {
                        if (i == 9 && j < 6 || j == 0 && i >= 9 && i < 14 || j == 5 && i >= 9 && i < 14 || i == 14 && j < 6)
                            grid[i][j] = new Cell(i, j, Type.EMPTY, Color.BLUE,null);
                        else
                            grid[i][j] = new Cell(i, j, Type.HOME, Color.BLUE,Color.BLUE);
                    }
                } else if (i > 8 && j > 8) { // Yellow Home
                    if ((i == 10 && j >= 10 && j <= 13) || (i == 11 && (j == 10 || j == 13)) ||
                            (i == 12 && (j == 10 || j == 13)) || (i == 13 && j >= 10 && j <= 13)) {
                        grid[i][j] = new Cell(i, j, Type.EMPTY, Color.WHITE,null);
                    } else {
                        if (i == 9 && j >= 9 && j < 14 || j == 9 && i >= 9 && i < 14 || j == 14 && i >= 9 && i < 14 || i == 14 && j >= 9 && j < 15)
                            grid[i][j] = new Cell(i, j, Type.EMPTY, Color.YELLOW,null);
                        else
                            grid[i][j] = new Cell(i, j, Type.HOME, Color.YELLOW,Color.YELLOW);
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

        System.out.println("Home Path Array");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(homePaths[i][j].getText() + " ");
            }
            System.out.println();
        }
    }

}
