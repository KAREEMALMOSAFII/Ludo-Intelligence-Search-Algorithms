package Core;

import Utilities.Type;

import java.util.List;

public class Cell {
    private int posX;

    private int posY;

    private List<Token> tokens;

    private Type type;


    public Cell(int posX, int posY, Type type) {
        this.posX = posX;
        this.posY = posY;
        this.type = type;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void addToken(Token token)
    {
        tokens.add(token);
    }

    public void removeToken(Token token)
    {
        tokens.remove(token);
    }
    public boolean isHome()
    {
        return type.name().equals("HOME");
    }

    public boolean isSafeZone()
    {
        return type.name().equals("SAFEZONE");
    }

    public boolean isGoal()
    {
        return type.name().equals("GOAL");
    }

}
