package Core;
import Utilities.Type;
import Utilities.Direction;
import Utilities.Color;

import java.util.List;

public class Token {
    private int tokenId;
    private Player owner;
    private Cell currentCell;

    //i will add this to make sure that every token move 52 block
    private  int totalDiceRolSum;

    public Token(int tokenId, Cell currentCell) {
        this.tokenId = tokenId;
        this.owner = null;
        this.currentCell = currentCell;
        this.totalDiceRolSum = 0;
    }

    public int getTokenId() {
        return tokenId;
    }


    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

//    public void initializeTokensInHome(Player player) {
//
//        List<Token> tokens = player.getTokens();
//        Color playerColor = player.getColor();
//        int tokenIndex = 0;
//
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                // Assign tokens to the first four `HOME` cells of the player's color
//                if (grid[i][j].getType() == Type.HOME && grid[i][j].getColor() == playerColor) {
//                    if (tokenIndex < tokens.size()) {
//                        Token token = tokens.get(tokenIndex);
//                        token.setCurrentCell(grid[i][j]);
//                        grid[i][j].addToken(token);
//                        tokenIndex++;
//                    }
//                }
//            }
//        }
//    }


    public boolean canMove(int diceRoll, Board board) {
        if (currentCell.isHome()) {
            // Token is in home, can only move if diceRoll == 6
            return diceRoll == 6;
        }

        int currentPos = getCurrentCellIndex(board);
        int targetPos = (currentPos + diceRoll) % 52;

        // Check for end zone entry
        if (totalDiceRolSum == 52) {
//        if (isEnteringEndZone(diceRoll, board)) {
            return true;
        }

        // Check for blockages on the path
        for (int i = 1; i <= diceRoll; i++) {
            int intermediatePos = (currentPos + i) % 52;
            Cell cell = board.getBoard()[intermediatePos];
            if (cell.getTokens().stream().filter(t -> t.getOwner().equals(owner)).count() >= 2) {
                // Two tokens of the same color are blocking the path
                return false;
            }
        }

        return true;
    }
    public void moveToken(int diceRoll, Board board) {
        if (!canMove(diceRoll, board)) {
            System.out.println("Token cannot move.");
            return;
        }

        int currentPos = getCurrentCellIndex(board);
        //int targetPos = (currentPos + diceRoll);
        int targetPos = (currentPos + diceRoll) % 52;
        System.out.println("Current position: " + currentPos);
        System.out.println("Target position: " + targetPos);
        if (totalDiceRolSum == 52) {
//        if (isEnteringEndZone(diceRoll, board)) {
            // Move into end zone
            moveToEndZone(diceRoll, board);
        } else {
            // Move normally
           board.getBoard()[currentPos].removeToken(this);
            board.getBoard()[targetPos].addToken(this);
            currentCell = board.getBoard()[targetPos];
            totalDiceRolSum +=diceRoll;
            board.printBoard();
        }

        // Check for killing other tokens
        killTokensOnCell(board.getBoard()[targetPos]);
    }

//    private boolean isEnteringEndZone(int diceRoll, Board board) {
//        int currentPos = getCurrentCellIndex(board);
//        int targetPos = (currentPos + diceRoll) % 52;
//        int endZoneStart = board.playerStartPositions[owner.getId()]; // End zone starts at the player's start position
//        return currentPos < endZoneStart && targetPos >= endZoneStart;
//    }

    private void moveToEndZone(int diceRoll, Board board) {
        int currentDepth = getEndZoneDepth();
        int newDepth = currentDepth + diceRoll;

        if (newDepth <= 6) {
            // Move deeper into the end zone
            currentCell = new Cell(currentCell.getPosX(), currentCell.getPosY(), Type.GOAL, owner.getColor());
            System.out.println("Token moved into the end zone at depth: " + newDepth);
        } else {
            System.out.println("Cannot move token into the end zone.");
        }
    }

    private void killTokensOnCell(Cell cell) {
        // Remove all tokens of other colors on the cell
        cell.getTokens().removeIf(t -> !t.getOwner().equals(owner));
    }

    private int getCurrentCellIndex(Board board) {
        for (int i = 0; i < board.getBoard().length; i++) {
            if (board.getBoard()[i].equals(currentCell)) {
                return i;
            }
        }
        return -1; // Not found
    }

    private int getEndZoneDepth() {
        // Calculate the depth in the end zone (e.g., based on the cell's position)
        return 0; // Placeholder, implement as needed
    }

    public void resetToHome() {
        // Reset token to its home cell
        currentCell.removeToken(this);
        Cell homeCell = owner.getTokens().getFirst().getCurrentCell(); // Assuming the first token's cell is home
        homeCell.addToken(this);
        currentCell = homeCell;
    }



}
