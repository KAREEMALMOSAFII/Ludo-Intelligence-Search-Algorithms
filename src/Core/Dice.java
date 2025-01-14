package Core;

import java.util.Random;

public class Dice {
    private int face;
    private Random random;

    private Dice(int face) {
        this.face = face;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    private int rollDice() {
        return random.nextInt(1, 6) + 1;
    }
}
