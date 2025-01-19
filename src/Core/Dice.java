package Core;

import java.util.Random;

public class Dice {
    private int face;
    private Random random;

    private Dice(int face) {
        this.face = face;
        this.random = new Random();
    }
    //i add this to  test in main
    public static Dice createDice(int face) {
        return new Dice(face);
    }
    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

//for 17 jdk and above
/*public int rollDice() {
        return random.nextInt(1, 6) + 1;
    }*/
//for 16 jdk and earlier
public int rollDice() {
    return random.nextInt(6) + 1; // Generates a number between 1 and 6
}


}
