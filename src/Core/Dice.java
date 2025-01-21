package Core;

import java.util.Random;

public class Dice {
    private int face;
    private Random random;

    public Dice() {
        face = 0;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    int rollDice() {
        int face= random.nextInt(1, 6) + 1;;
        setFace(face);
        return face;

    }
}
