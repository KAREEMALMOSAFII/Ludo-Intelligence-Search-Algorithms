package Core;

import java.util.ArrayList;
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

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

   public void startGame()
    {
        while (isOver){
            playTurn();
        }
        System.out.println("Game Over");
    }
    public void playTurn()
    {
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
                    int tokenId  = selectedThrow[0];
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

    public void checkWinCondition()
    {
        for (Player player : players) {
            if (player.HasWon()) {
                isOver = true;
                System.out.println(player.getName() + " has won the game!");
                break;
            }
        }
    }
    public void switchTurn()
    {
        currentTurn = (currentTurn + 1) % players.size();
    }

    // بجيب الخلية الهدف الهدف ع حسب الخلية يلي انا فيها وقيمة النرد
    private Cell getTargetCell(Cell currentCell, int diceRoll) {
        int currentRow = currentCell.getPosX();
        int currentCol = currentCell.getPosY();
        int targetIndex = (currentRow * 15 + currentCol + diceRoll) % (15 * 15);
        int targetRow = targetIndex / 15;
        int targetCol = targetIndex % 15;


//        if (targetPosition >= board.getCells().size()) {
//            targetPosition -= board.getCells().size();
//        }
        return board.getBoard()[targetRow][targetCol];
    }

    // Validate the move based on game rules
    private boolean isMoveValid(Token token, Cell targetCell) {
//اذا الخلبة المستهدفة امنة يمكن وضعالحجر بغض النظر عن مالك الحجر
        if (targetCell.isSafeZone()) {
            return true;
        }

        // Check if the target cell has tokens from the same player
        for (Token t : targetCell.getTokens()) {
            if (t.getOwner() == token.getOwner()) {
                return false;
            }
        }

        return true; // Valid move
    }

    // Generate possible moves based on dice roll and current state
    private List<int[]> generatePossibleMoves(Player currentPlayer, int diceRoll) {
        List<int[]> possibleMoves = new ArrayList<>();


        for (int i = 0; i < currentPlayer.tokens.size(); i++) {
            Token token = currentPlayer.tokens.get(i);

            // اذا طلعلنا 6 منركب حجر
            if (token.getCurrentCell() == null && diceRoll==6) {
                    possibleMoves.add(new int[]{i, diceRoll}); // [Token ID, Dice Roll]
            } else if (token.getCurrentCell() != null){
              //اذا فينا نتحرك جسب القواعد
                Cell targetCell = getTargetCell(token.getCurrentCell(), diceRoll);


                if (targetCell != null && isMoveValid(token, targetCell)) {
                    possibleMoves.add(new int[]{i, diceRoll});
                }
            }
        }

        return possibleMoves;
    }

}
