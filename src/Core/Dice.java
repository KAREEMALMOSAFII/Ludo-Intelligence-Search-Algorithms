package Core;

import java.util.Random;

public class Dice {
    private int face;
    private Random random;

    public Dice() {
        face = 0;
        random = new Random();
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    void rollDice() {
        setFace(random.nextInt(6) + 1);
    }

}
