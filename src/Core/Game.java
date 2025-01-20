package Core;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentTurn;
    private Dice dice;
    private boolean isOver;

    public Game(Board board, List<Player> players, int currentTurn, Dice dice, boolean isOver) {
        this.board = board;
        this.players = players;
        this.currentTurn = currentTurn;
        this.dice = dice;
        this.isOver = isOver;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

   public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Ludo!");
        System.out.println("Choose game mode:");
        System.out.println("1. Play with another user");
        System.out.println("2. Play with the computer");

        int choice;
        do {
            System.out.print("Enter your choice (1 or 2): ");
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2);

        if (choice == 1) {
            playWithUser();
        } else {
            //playWithComputer();
        }
    }

    private void playWithUser() {
        System.out.println("Starting game with another user...");

        while (!isOver) {
            playTurn();
            checkWinCondition();
            switchTurn();
        }

        System.out.println("Game over!");
    }
/*
    private void playWithComputer() {
        System.out.println("Starting game with the computer...");

        while (!isOver) {
            if (currentTurn == 0) { // User's turn
                System.out.println(players.get(currentTurn).getName() + "'s turn.");
                playTurn();
            } else { // Computer's turn
                System.out.println("Computer's turn.");
                int diceRoll = dice.rollDice();
                System.out.println("Computer rolled: " + diceRoll);

                // Implement computer's move logic here
                computerMove(diceRoll);
            }
            checkWinCondition();
            switchTurn();
        }

        System.out.println("Game over!");
    }

    private void computerMove(int diceRoll) {
        Player computer = players.get(currentTurn);

        for (Token token : computer.getTokens()) {
            // Find a token that can move and move it
            if (token.canMove(diceRoll)) {
                token.moveToken(diceRoll);
                System.out.println("Computer moved token " + token.getTokenId());
                return;
            }
        }
        System.out.println("Computer could not make a move.");
    }*/
    //=================================================================================
    public void playTurn()
    {
        Player currentPlayer = players.get(currentTurn);
        System.out.println(currentPlayer.getName() + "'s turn.");
        System.out.print("Press Enter to roll the dice...");
        new Scanner(System.in).nextLine(); // Wait for user input

        int diceRoll = dice.rollDice();
        System.out.println(currentPlayer.getName() + " rolled: " + diceRoll);

        boolean moved = false;
        for (Token token : currentPlayer.getTokens()) {
            if (token.canMove(diceRoll,board)) {
                token.moveToken(diceRoll,board);
                moved = true;
                break;
            }
        }

        if (!moved) {
            System.out.println("No valid moves. Turn skipped.");
        }
    }
    //=================================================================================
    public void checkWinCondition()
    {
        for (Player player : players) {
            if (player.hasPlayerWon()) {
                System.out.println(player.getName() + " has won the game!");
                isOver = true;
                break;
            }
        }
    }
    //=================================================================================
    public void switchTurn()
    {
        currentTurn = (currentTurn + 1) % players.size();
    }

    int calculateNewPosition(int currentPosition, int diceRoll, int playerId) {
        int newPosition = (currentPosition + diceRoll) % 52;
        // If entering home path
        if (currentPosition < board.playerStartPositions[playerId]
                && newPosition >= board.playerStartPositions[playerId]) {
            // Enter home path
            return -1; // -1 indicates transitioning to home path
        }
        return newPosition;
    }

    void moveToHomePath(int tokenIndex, int stepsInHomePath, int playerId) {
        board.homePaths[playerId][stepsInHomePath] = tokenIndex;
    }

//    if (newPosition == -1) {
//        moveToHomePath(tokenIndex, 0, player.id); // Start in home path
//    } else {
//        player.tokens[tokenIndex] = newPosition; // Update position on the main track
//    }
}
