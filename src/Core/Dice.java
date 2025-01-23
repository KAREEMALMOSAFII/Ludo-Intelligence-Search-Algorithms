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

    private final int[] testRolls = {6, 6, 3, 6, 6};  // Sample test sequence
    private int rollIndex = 0;
    void rollDice() {

        if (rollIndex < testRolls.length) {
            setFace(testRolls[rollIndex]);
            rollIndex++;
        } else {
            Random random = new Random();
            setFace(random.nextInt(6) + 1);  // Roll a random number between 1 and 6
        }
    }
//    void rollDice() {
//        setFace(random.nextInt(6) + 1);
//
//    }
}
