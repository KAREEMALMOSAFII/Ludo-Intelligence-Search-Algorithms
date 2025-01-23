package Core;

import Utilities.Type;
import Utilities.Direction;
import Utilities.Color;

import java.util.List;
import java.util.Optional;

import static Core.Board.homePaths;
import static Core.Board.playerStartPositions;
import static java.lang.Math.abs;

public class Token {
    private int tokenId;
    private Player owner;
    private Cell currentCell;

    //i will add this to make sure that every token move 52 block

    public Token(int tokenId, Player owner, Cell currentCell) {
        this.tokenId = tokenId;
        this.owner = owner;
        this.currentCell = currentCell;
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


    public boolean canMove(int diceRoll, Board board,int pos) {
//        if (owner.allTokensInHome() && diceRoll != 6) {
//            return false;
//        }


       int currentPos = pos;
        int targetPos = (currentPos + diceRoll) % 52;

        //TODO WE DIDI REACH IT
        // Check for blockages on the path
        for (int i = 1; i <= diceRoll; i++) {
            int intermediatePos = (currentPos + i) % 52;
            Cell cell = board.getBoard()[intermediatePos];
            if (cell.getTokens() != null &&
                    cell.getTokens().stream().filter(t -> t.getOwner()!=owner).count() >= 2) {
                return false;
            }
        }

        return true;
    }

    public void moveToken(int diceRoll, Board board,Integer posX,Integer posY, boolean isHome) {

        // Move token from home if all tokens are in home and dice roll is 6
        if (isHome) {
            System.out.println("START LOGIC: Moving token from home to start.");
            moveTokenFromHomeToStart(board);
            return;
        }


        int currentPos = board.getCellIndex(posX,posY);
        int playerId = owner.getId();
        int nearestSafeZone = (playerStartPositions[playerId] + 50) % 52; // Corrected nearest safe zone calculation


        // If the token is near a safe zone and can move directly into the home path
        if ((nearestSafeZone - currentPos + 52) % 52 <= diceRoll) {
            System.out.println("Moving to safe zone...");
            int stepsIntoSafeZone = diceRoll - ((nearestSafeZone - currentPos + 52) % 52);
            moveToSafeZone(this, stepsIntoSafeZone);
            return;
        }

        // If the token is already in the safe zone, move inside home path
        if (isInSafeZonePath()) {
            System.out.println("Token is in safe zone, moving within home path.");
            int newHomeIndex = getSafeZoneIndex() + diceRoll;

            if (newHomeIndex >= homePaths[playerId].length) {
                System.out.println("Token has reached the final goal!");
                tokenReachedGoal();
            } else {
                moveToSafeZone(this, newHomeIndex);
            }
            return;
        }

        // Normal movement logic if not near a safe zone
        if (!canMove(diceRoll, board,currentPos)) {
            System.out.println("Token cannot move.");
            return;
        }


        // Calculate target position on the board (handling circular movement)
        int targetPos = (currentPos + diceRoll) % 52;

        System.out.println("Current position: " + currentPos);
        System.out.println("Target position: " + targetPos);

        // Perform the movement
        board.getBoard()[currentPos].removeToken(this);
        board.getBoard()[targetPos].addToken(this);
        currentCell = board.getBoard()[targetPos];

       // board.printBoard();
    }

    private int getCurrentCellIndex(Board board) {
        System.out.println(currentCell.getPosX() + "   " + currentCell.getPosY());
        for (int i = 0; i < board.getBoard().length; i++) {
            if (board.getCellIndex(currentCell.getPosX(), currentCell.getPosY()) != -1) {
                System.out.println("FOUNED  " + board.getCellIndex(currentCell.getPosX(), currentCell.getPosY()));
                return board.getCellIndex(currentCell.getPosX(), currentCell.getPosY());
            }
        }
        System.out.println("NOTTFOUNED  ");
        return -1; // Not found
    }


    public void resetToHome() {
        // Reset token to its home cell
        currentCell.removeToken(this);
        //todo Get First Home Cell doesnot have Token
        Cell homeCell = owner.getTokens().getFirst().getCurrentCell(); // Assuming the first token's cell is home
        homeCell.addToken(this);
        currentCell = homeCell;
    }

    public void moveTokenFromHomeToStart(Board board) {
        Optional<Token> homeToken = owner.getTokens().stream()
                .filter(token -> token.getCurrentCell().isHome())
                .findFirst();

        if (homeToken.isPresent()) {
            Token token = homeToken.get();
            System.out.println("tokenID " + token.getTokenId());
            token.getCurrentCell().removeToken(this); // Remove the correct token
            int playerStartPosition = playerStartPositions[owner.getId()];
            Cell startCell = board.getBoard()[playerStartPosition];

            if (startCell != null) {
                startCell.addToken(this);
                startCell.setText(owner.getColor());
                token.setCurrentCell(startCell);  // Update the correct token's position
                System.out.println("new Cell Type " + token.getCurrentCell().getType());
                startCell.getTokens().forEach(s-> System.out.println("token pos x " + s.getCurrentCell().getPosX()+ " token pos y " + s.getCurrentCell().getPosY()));

            } else {
                throw new IllegalStateException("Start cell is null for player " + owner.getId());
            }
        }
    }

    public void moveToSafeZone(Token token, int homePathIndex) {
        int playerId = token.getOwner().getId();

        // Ensure the home path index is within bounds
        if (homePathIndex >= homePaths[playerId].length) {
            System.out.println("Token has reached the final goal!");
            tokenReachedGoal();
            return;
        }

        // Remove token from current board position
        token.getCurrentCell().removeToken(token);

        // Move to the corresponding home path cell (safe zone)
        homePaths[playerId][homePathIndex].addToken(token);

        // Update token's current position
        token.currentCell = homePaths[playerId][homePathIndex];

        System.out.println("Token moved to safe zone at index: " + homePathIndex);
    }


    private void killTokensOnCell(Cell cell) {
        // Remove all tokens of other colors on the cell
        cell.getTokens().removeIf(t -> !t.getOwner().equals(owner));
    }


    private boolean isInSafeZonePath() {
        for (Cell cell : homePaths[owner.getId()]) {
            if (!cell.getTokens().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private int getSafeZoneIndex() {
        int playerId = owner.getId();
        for (int i = 0; i < homePaths[playerId].length; i++) {
            if (!homePaths[playerId][i].getTokens().isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    private void tokenReachedGoal() {
        System.out.println("Token successfully reached the goal!");
    }
}
