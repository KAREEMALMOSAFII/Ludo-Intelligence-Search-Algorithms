package Core;

import Utilities.Color;
import Utilities.Type;

import java.util.ArrayList;
import java.util.List;

import static Utilities.Color.*;

public class Cell implements Cloneable{
    private int posX;

    private int posY;

    private List<Token> tokens;

    private Type type;

    private String text;

    private Color baseColor;

    public Cell(int posX, int posY, Type type, Color baseColor, Color tempColor) {
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        this.baseColor=baseColor;
        this.tokens = new ArrayList<>();

        Color finalColor = (tempColor != null) ? tempColor : baseColor;

       if (tempColor!=null) {
            switch (finalColor) {
                case YELLOW:
                    text = "\uD83D\uDC66";
                    break;
                case RED:
                    text = "\uD83D\uDC69\uD83C\uDFFC";
                    break;
                case BLUE:
                    text = "\uD83D\uDC68\uD83C\uDFFF\u200D\uD83E\uDDB0";
                    break;
                case GREEN:
                    text = "\uD83D\uDC68\uD83C\uDFFD\u200D\uD83E\uDDB3";
                    break;
                default:
                    text = "⬜";
            }
        } else {
            switch (finalColor) {
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

    public Color getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
    }

    public void setText( Color color) {
        Color finalColor = (color != null) ? color : baseColor;

        if (color!=null) {
            switch (finalColor) {
                case YELLOW:
                    text = "\uD83D\uDC66";
                    break;
                case RED:
                    text = "\uD83D\uDC69\uD83C\uDFFC";
                    break;
                case BLUE:
                    text = "\uD83D\uDC68\uD83C\uDFFF\u200D\uD83E\uDDB0";
                    break;
                case GREEN:
                    text = "\uD83D\uDC68\uD83C\uDFFD\u200D\uD83E\uDDB3";
                    break;
                default:
                    text = "⬜";
            }
        } else {
            switch (finalColor) {
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
    }
    public void addToken(Token token) {
        System.out.println("Before Add TOKEN " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getType() : "null") + " " +
                "Cell Color " + (token.getCurrentCell() != null ? token.getCurrentCell().getText() : "null") + " " +
                "Token Owner Color " + (token.getOwner() != null ? token.getOwner().getColor() : "null") + " PosX " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getPosX() : "null") + " PosY " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getPosY() : "null") + " Cell tokens size " +
                (token.getCurrentCell() != null && token.getCurrentCell().getTokens() != null ?
                        token.getCurrentCell().getTokens().size() : "null") + " ");

        tokens.add(token);
        this.setText(token.getOwner().getColor());
        token.setCurrentCell(this);



        System.out.println("After Add TOKEN " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getType() : "null") + " " +
                "Cell Color " + (token.getCurrentCell() != null ? token.getCurrentCell().getText() : "null") + " " +
                "Token Owner Color " + (token.getOwner() != null ? token.getOwner().getColor() : "null") + " PosX " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getPosX() : "null") + " PosY " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getPosY() : "null") + " Cell tokens size " +
                (token.getCurrentCell() != null && token.getCurrentCell().getTokens() != null ?
                        token.getCurrentCell().getTokens().size() : "null") + " ");
    }

    public void removeToken(Token token) {

        System.out.println("Before REMOVE TOKEN " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getType() : "null") + " " +
                "Cell Color " + (token.getCurrentCell() != null ? token.getCurrentCell().getText() : "null") + " " +
                "Token Owner Color " + (token.getOwner() != null ? token.getOwner().getColor() : "null") + " PosX " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getPosX() : "null") + " PosY " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getPosY() : "null") + " Cell tokens size " +
                (token.getCurrentCell() != null && token.getCurrentCell().getTokens() != null ?
                        token.getCurrentCell().getTokens().size() : "null") + " ");

        if (tokens != null) {
            tokens.remove(token);
            if (tokens != null && !tokens.isEmpty())
            this.setText(tokens.getFirst().getOwner().getColor());
            else
                this.setText(null);

        } else {
            throw new IllegalStateException("Token list is not initialized in this cell.");
        }
        System.out.println("After REMOVE TOKEN " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getType() : "null") + " " +
                "Cell Color " + (token.getCurrentCell() != null ? token.getCurrentCell().getText() : "null") + " " +
                "Token Owner Color " + (token.getOwner() != null ? token.getOwner().getColor() : "null") + " PosX " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getPosX() : "null") + " PosY " +
                (token.getCurrentCell() != null ? token.getCurrentCell().getPosY() : "null") + " Cell tokens size " +
                (token.getCurrentCell() != null && token.getCurrentCell().getTokens() != null ?
                        token.getCurrentCell().getTokens().size() : "null") + " ");
    }

    public boolean isHome() {
        return type.name().equals("HOME");
    }

    public boolean isSafeZone() {
        return type.name().equals("SAFEZONE");
    }

    public boolean isGoal() {
        return type.name().equals("GOAL");
    }

    public boolean isNormal() {
        return type.name().equals("NORMAL");
    }
/*================ZAK===============*/
@Override
public Cell clone() {
    try {
        return (Cell) super.clone();
    } catch (CloneNotSupportedException e) {
        throw new AssertionError("Cloning not supported", e);
    }
}
}
