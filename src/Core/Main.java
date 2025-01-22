package Core;
import java.util.*;
import Utilities.Color;

public class Main {
    public static void main(String[] args) {
//        Game game = new Game();
//        game.startGame();
        Game game = new Game(); // Initialize the game
        int depth = 3; // Set depth for AI
        LudoCo co = new LudoCo();

        int bestMove = co.computerPlay(game, depth);
        System.out.println("Best Move Evaluation: " + bestMove);
    }
}
