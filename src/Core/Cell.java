package Core;

import Utilities.Color;
import Utilities.Type;

import java.util.List;

import static Utilities.Color.*;

public class Cell {
    private int posX;

    private int posY;

    private List<Token> tokens;

    private Type type;

    private String text;

    public Cell(int posX, int posY, Type type , Color color) {
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        switch (color) {
            case RED:
                text = "\uD83D\uDFE5";
                break;
            case YELLOW:
                text = "\uD83D\uDFE8";
                break;
            case BLUE:
                text = "\uD83D\uDFE6";
                break;
            case GREEN:
                text = "\uD83D\uDFE9";
                break;
            case BLACK:
                text = "⬛";
                break;
            case BROWN:
                text = "\uD83D\uDFEB";
                break;
            default:
                text = "⬜";
        }
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
