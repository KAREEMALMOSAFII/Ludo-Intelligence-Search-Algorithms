package Core;

import Utilities.Color;

import java.util.*;
public class Player implements Cloneable {

    private int id;
    private String name;

    private Color color;

    public List<Token> tokens;

    public Player(int id ,String name, Color color, List<Token> tokens) {
        this.id=id;
        this.name = name;
        this.color = color;
        this.tokens = tokens;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public boolean HasWon() {
        return tokens.stream().allMatch(token -> token.getCurrentCell().isGoal());
    }

     boolean allTokensInHome() {
        return getTokens().stream().allMatch(token -> token.getCurrentCell().isHome());
    }

    @Override
    public Player clone() {
        try {
            Player cloned = (Player) super.clone();
            List<Token> clonedTokens = new ArrayList<>();
            for (Token token : this.tokens) {
                clonedTokens.add(token.clone());
            }
            cloned.tokens = clonedTokens;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
