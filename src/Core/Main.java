package Core;
import Utilities.Type;
import Utilities.Color;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize board
        Cell[][] cells = new Cell[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cells[i][j] = new Cell(i, j, Type.EMPTY,Color.WHITE);
            }
        }
        Board board = new Board(cells);
        board.initilizeBoard();
        //board.printBoard();





        // Initialize players and tokens
        List<Token> player1Tokens = new ArrayList<>();
        Player player1 = new Player("Player 1", Color.RED, player1Tokens);
        int[][] initialRedPositions = {
                {2, 2}, // First token
                {2, 3}, // Second token
                {3, 2}, // Third token
                {3, 3}  // Fourth token
        };

        for (int i = 0; i < 4; i++) {
            int row = initialRedPositions[i][0];
            int col = initialRedPositions[i][1];
            Token redToken = new Token(i + 1, player1, cells[row][col]);
            player1Tokens.add(redToken);
            cells[row][col].addToken(redToken);
        }
        /*Cell player1Home = cells[0][0];
        for (int i = 1; i <= 4; i++) {
            Token token = new Token(i, player1, player1Home);
            player1Tokens.add(token);
            player1Home.addToken(token);
        }*/

        List<Token> player2Tokens = new ArrayList<>();
        Player player2 = new Player("Player 2", Color.YELLOW, player2Tokens);
        int[][] initialYllowPositions = {
                {11, 11}, // First token
                {11, 12}, // Second token
                {12, 11}, // Third token
                {12, 12}  // Fourth token
        };

        for (int i = 0; i < 4; i++) {
            int row = initialYllowPositions[i][0];
            int col = initialYllowPositions[i][1];
            Token yllowToken = new Token(i + 1, player2, cells[row][col]);
            player2Tokens.add(yllowToken);
            cells[row][col].addToken(yllowToken);
        }

        /*Cell player2Home = cells[0][0];
        player2Home.setType(Type.HOME);
        for (int i = 1; i <= 4; i++) {
            Token token = new Token(i, player2, player2Home);
            player2Tokens.add(token);
            player2Home.addToken(token);
        }*/

        // Simulate dice rolls
        Dice dice = Dice.createDice(6);

        // Simulate a few turns
        for (int turn = 1; turn <= 5; turn++) {
            System.out.println("Turn " + turn);

            int diceRoll = dice.rollDice();
            System.out.println("Player 1 rolls: " + diceRoll);

            for (Token token : player1Tokens) {
                System.out.println(token.canMove(diceRoll, board));
                /*System.out.println("===============================");
                board.printBoard();
                System.out.println("===============================");*/

                if (token.canMove(diceRoll, board)) {

                    token.moveToken(diceRoll, board);
                    System.out.println("Player 1's Token " + token.getTokenId() + " moved to (" + token.getCurrentCell().getPosX() + ", " + token.getCurrentCell().getPosY() + ")");
                    board.printBoard();
                    break;
                }
            }

            diceRoll = dice.rollDice();
            System.out.println("Player 2 rolls: " + diceRoll);

            for (Token token : player2Tokens) {
                if (token.canMove(diceRoll, board)) {
                    token.moveToken(diceRoll, board);
                    System.out.println("Player 2's Token " + token.getTokenId() + " moved to (" + token.getCurrentCell().getPosX() + ", " + token.getCurrentCell().getPosY() + ")");
                    board.printBoard();
                    break;
                }
            }
        }

        // Print final positions
        System.out.println("Final positions:");
        for (Token token : player1Tokens) {
            System.out.println("Player 1's Token " + token.getTokenId() + " is at (" + token.getCurrentCell().getPosX() + ", " + token.getCurrentCell().getPosY() + ")");
        }
        for (Token token : player2Tokens) {
            System.out.println("Player 2's Token " + token.getTokenId() + " is at (" + token.getCurrentCell().getPosX() + ", " + token.getCurrentCell().getPosY() + ")");
        }

        // Example of moving a token and printing the board
        /*Token sampleToken = new Token(1, player1, cells[0][0]);
        cells[0][0].addToken(sampleToken);*/

        // Initial board with token at home
        //System.out.println("Moving token...");

        /*sampleToken.moveToken(3, board); // Move the token*/
        //board.printBoard(); // Updated board with token moved
    }
}