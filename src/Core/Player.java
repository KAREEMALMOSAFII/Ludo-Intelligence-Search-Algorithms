package Core;

import Utilities.Color;

import java.util.List;

public class Player {
    private String name;

    private Color color;

    private List<Token> tokens;

    public Player(String name, Color color, List<Token> tokens) {
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

    private boolean HasWon() {
        return tokens.stream().allMatch(token -> token.getCurrentCell().isGoal());
    }
}
