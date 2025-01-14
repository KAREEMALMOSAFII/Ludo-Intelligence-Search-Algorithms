package Core;

import java.util.List;

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

   public void startGame()
    {

    }
    public void playTurn()
    {

    }
    public void checkWinCondition()
    {

    }
    public void switchTurn()
    {

    }
}
