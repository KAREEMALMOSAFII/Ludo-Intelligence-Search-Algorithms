package Core;
import Utilities.Type;
import Utilities.Direction;
import Utilities.Color;
public class Token {
    private int tokenId;

    private Player owner;

    private Cell currentCell;

    private Direction direction;

    public Token(int tokenId, Player owner, Cell currentCell) {
        this.tokenId = tokenId;
        this.owner = owner;
        this.currentCell = currentCell;
        if(Color.RED.equals(owner.getColor())){
            this.direction = Direction.RIGHT;// Default direction
        }
        else if(Color.YELLOW.equals(owner.getColor())){
            this.direction = Direction.LEFT;
        }

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
    boolean isThereTokenInBoard = ;
    // Check if the token can move based on the current dice roll
    public boolean canMove(int diceRoll, Board board) {
        System.out.println(currentCell.getType());
        // Rule 1: No token can move from home unless a 6 is rolled

        if (this.currentCell.getType() == Type.HOME && diceRoll == 6) {
            return true;
        }
        if (this.currentCell.getType() == Type.HOME && diceRoll != 6) {
            return false;
        }

        // Rule 2: Token must be able to move the exact number of steps
        int targetX = currentCell.getPosX() + diceRoll;
        int targetY = currentCell.getPosY();
        if (targetX >= board.getBoard().length) {
            return false;
        }
        //Rule3 and Rule4
        Cell targetCell = board.getBoard()[targetX][targetY];
        /*if (targetCell.getTokens().size() >= 2) {
            // Condition 1: If we're landing on that cell, send all tokens home
            if (targetX == currentCell.getPosX() + diceRoll) {
                // Send all tokens on that cell back to home
                for (Token token : targetCell.getTokens()) {
                    token.resetToHome();
                }

                return true;
            }
            // Condition 2: If we're passing this cell, return false (blocked)
            else if(targetX >= currentCell.getPosX() + diceRoll){
                return false;
            }
            else{
                return  true;
            }
        }*/

        return true;
    }

    public void moveToken(int steps, Board board) {
        if(Color.RED.equals(owner.getColor())) {


            if (this.currentCell.getType() == Type.HOME && steps == 6) {

                int newX = 5, newY = 1;
                Cell nextCell = board.getBoard()[newX][newY];

                // Update token position
                currentCell.removeToken(this);
                nextCell.addToken(this);
                currentCell = nextCell;

            } else {
                for (int i = 0; i < steps; i++) {
                    int posX = currentCell.getPosX();
                    int posY = currentCell.getPosY();
                    //------------------------------------------------zak

                    // Check board boundaries and adjust direction if needed
                    if (posX == 0 && direction == Direction.TOP) direction = Direction.RIGHT;
                    if (posY == 0 && direction == Direction.LEFT) direction = Direction.TOP;
                    if (posX == board.getBoard().length - 1 && direction == Direction.BOTTOM)
                        direction = Direction.LEFT;
                    if (posY == board.getBoard()[0].length - 1 && direction == Direction.RIGHT)
                        direction = Direction.BOTTOM;

                    int newX = posX, newY = posY;

                    // Determine the next cell based on direction
                    switch (direction) {

                        case LEFT:{
                            newY--;
                            break;}
                        case RIGHT:{
                            newY++;
                            break;}
                        case TOP:{
                            newX--;
                            break;}
                        case BOTTOM:{
                            newX++;
                            break;}
                    }
                    System.out.println(newX + newY);
                    // Ensure within bounds
                    if (newX < 0 || newX >= board.getBoard().length || newY < 0 || newY >= board.getBoard()[0].length) {
                        throw new IllegalStateException("Token moved out of bounds. Check logic.");
                    }

                    Cell nextCell = board.getBoard()[newX][newY];

                    // Handle cell logic based on type or color
                    if (!"\uD83D\uDFE5".equals(nextCell.getText())) { // Not a red cell
                        if ("⬛".equals(nextCell.getText())) {
                            // Handle black cell logic
                            switch (direction) {
                                case LEFT:
                                    newX--;
                                    newY++;
                                    direction = Direction.TOP;
                                    break;
                                case TOP:
                                    newX++;
                                    newY++;
                                    direction = Direction.RIGHT;
                                    break;
                                case RIGHT:
                                    newX++;
                                    newY--;
                                    direction = Direction.BOTTOM;
                                    break;
                                case BOTTOM:
                                    newX--;
                                    newY--;
                                    direction = Direction.LEFT;
                                    break;
                            }
                        } else {
                            // Handle non-black, non-red cell logic
                            switch (direction) {
                                case LEFT:
                                    newY--;
                                    direction = Direction.BOTTOM;
                                    break;
                                case TOP:
                                    newX--;
                                    direction = Direction.LEFT;
                                    break;
                                case RIGHT:
                                    newY++;
                                    direction = Direction.TOP;
                                    break;
                                case BOTTOM:
                                    newX++;
                                    direction = Direction.RIGHT;
                                    break;
                            }
                        }
                    }

                    // Update token position
                    currentCell.removeToken(this);
                    nextCell.addToken(this);
                    currentCell = nextCell;

                    // Print token move
                    System.out.printf("%s's Token %d moved to (%d, %d) in direction %s%n",
                            owner.getName(), tokenId, currentCell.getPosX(), currentCell.getPosY(), direction);

                    // Print the board
                    board.printBoard();
                }
            }
        }
        else {
            //here the yllow logic
        }
    }

    public void resetToHome() {
        // Reset token to its home cell
        currentCell.removeToken(this);
        Cell homeCell = owner.getTokens().get(0).getCurrentCell(); // Assuming the first token's cell is home
        homeCell.addToken(this);
        currentCell = homeCell;
    }
}
