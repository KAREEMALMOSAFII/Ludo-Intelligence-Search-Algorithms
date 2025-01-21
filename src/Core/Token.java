package Core;

public class Token {
    private int tokenId;
    private Player owner;
    private Cell currentCell;

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

    public void moveToken()
    {

    }

    public void resetToHome()
    {

    }

    public void canMove()
    {

    }
}
