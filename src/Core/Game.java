package Core;

import Utilities.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentTurn;
    private Dice dice;
    private boolean isOver;

    public Game() {
        board= new Board();
        players= new ArrayList<>();
        currentTurn=0;
        dice = new Dice();
        isOver = false;
    }

    public Game(Board board, List<Player> players, int currentTurn, Dice dice, boolean isOver) {
        this.board = board;
        this.players = players;
        this.currentTurn = currentTurn;
        this.dice = dice;
        this.isOver = false;
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

    public boolean getIsOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public void startGame() {
        board.initializeBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose Number Of Players , 2 Or 4 Ai Users ! ");
        Integer numOfPlayers= scanner.nextInt();
        System.out.println("Choose Players Number");
        switch (numOfPlayers) {
            case 1:
                Player player = new Player("Player 1", Color.BLUE, new ArrayList<>());
                Player computer = new Player("Player 2", Color.GREEN, new ArrayList<>());
                for (int j = 0; j < 4; j++) {
                    player.getTokens().add(new Token(j, player, null));
                    computer.getTokens().add(new Token(j, player, null));
                }
                players.add(player);
                players.add(computer);
                break;

            case 2:
                Player computer1 = new Player("Player 1", Color.BLUE, new ArrayList<>());
                Player computer2 = new Player("Player 2", Color.RED, new ArrayList<>());
                Player computer3 = new Player("Player 3", Color.GREEN, new ArrayList<>());
                Player computer4 = new Player("Player 4", Color.YELLOW, new ArrayList<>());
                for (int j = 0; j < 4; j++) {
                    computer1.getTokens().add(new Token(j, computer1, null));
                    computer2.getTokens().add(new Token(j, computer2, null));
                    computer3.getTokens().add(new Token(j, computer3, null));
                    computer4.getTokens().add(new Token(j, computer4, null));
                }
                players.add(computer1);
                players.add(computer2);
                players.add(computer3);
                players.add(computer4);
        }
        while (!getIsOver()) {
           playTurn();
        }
        System.out.println("Game Over");
    }

    public void playTurn() {
        Player currentPlayer = players.get(currentTurn);
        int diceRoll = dice.rollDice();
        System.out.println(currentPlayer.getName() + "rolled a" + diceRoll);
        actionSolo(currentPlayer, diceRoll);
        checkWinCondition();
        if (diceRoll != 6) { // إذا لم يحصل اللاعب على 6، يتم الانتقال للدور التالي
            switchTurn();
        } else {
            System.out.println(currentPlayer.getName() + " gets another turn!");
        }
    }

    private void actionSolo(Player currentPlayer, int diceRoll) {
        List<int[]> throwsList = generatePossibleMoves(currentPlayer, diceRoll);

        while (!throwsList.isEmpty()) {
            System.out.println("Throws List: " + throwsList);

            if (!currentPlayer.tokens.isEmpty()) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the ID of the stone to move (0 to " + (currentPlayer.tokens.size() - 1) + "):");
                int stoneId = scanner.nextInt();
                if (stoneId < 0 || stoneId >= currentPlayer.tokens.size()) {
                    System.out.println("Invalid stone ID.");
                    continue;
                }
                System.out.println("Enter the index of the throw to use (0 to " + (throwsList.size() - 1) + "):");
                int throwIndex = scanner.nextInt();
                if (throwIndex < 0 || throwIndex >= throwsList.size()) {
                    System.out.println("Invalid throw index.");
                    continue;
                }

                if (throwIndex < throwsList.size()) {
                    int[] selectedThrow = throwsList.get(throwIndex);
                    int tokenId = selectedThrow[0];
                    int numberOfMoves = selectedThrow[1];

                    boolean moveSuccessful = moveStone(currentPlayer, stoneId, numberOfMoves);
                    board.printBoard();

                    if (moveSuccessful) {
                        throwsList.remove(throwIndex);
                    } else {
                        System.out.println("Invalid move.");
                    }
                } else {
                    System.out.println("Invalid throw index.");
                }
            } else {
                System.out.println("No stones available to move.");
                break;
            }
        }
    }

    private boolean moveStone(Player currentPlayer, int stoneId, int numberOfMoves) {
        // Mock stone movement
        System.out.println("Moving stone " + stoneId + " by " + numberOfMoves + " steps.");
        return true;
    }

    public void checkWinCondition() {
        for (Player player : players) {
            if (player.HasWon()) {
                setOver(true);
                System.out.println(player.getName() + " has won the game!");
                break;
            }
        }
    }

    public void switchTurn() {
        currentTurn = (currentTurn + 1) % players.size();
    }
    // Generate possible moves based on dice roll and current state
    private List<int[]> generatePossibleMoves(Player currentPlayer, int diceRoll) {
        List<int[]> possibleMoves = new ArrayList<>();

        for (int i = 0; i < currentPlayer.tokens.size(); i++) {
            Token token = currentPlayer.tokens.get(i);

            // اذا طلعلنا 6 منركب حجر
            if (token.getCurrentCell() == null && diceRoll == 6) {
                possibleMoves.add(new int[]{i, diceRoll}); // [Token ID, Dice Roll]
            } else if (token.getCurrentCell() != null) {
                //اذا فينا نتحرك جسب القواعد
//                Cell targetCell = getTargetCell(token.getCurrentCell(), diceRoll);
//
//
//                if (targetCell != null && isMoveValid(token, targetCell)) {
//                    possibleMoves.add(new int[]{i, diceRoll});
//                }
            }
        }

        return possibleMoves;
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

//    void moveToHomePath(int tokenIndex, int stepsInHomePath, int playerId) {
//        board.homePaths[playerId][stepsInHomePath] = tokenIndex;
//    }

//    if (newPosition == -1) {
//        moveToHomePath(tokenIndex, 0, player.id); // Start in home path
//    } else {
//        player.tokens[tokenIndex] = newPosition; // Update position on the main track
//    }

}


