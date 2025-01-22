package Core;
import java.util.*;
public class LudoCo {

    private static final int INFINITY = Integer.MAX_VALUE;
    private static final int NEG_INFINITY = Integer.MIN_VALUE;

    // Define possible outcomes of dice rolls
    private static final int[] POSSIBLE_OUTCOMES = {1, 2, 3, 4, 5, 6};

    public static int computerPlay(Game game, int depth) {
        Dice dice = game.getDice();
        int outcome = dice.rollDice(); // Roll the dice
        game.setDice(dice);

        // Call expectiminimax to find the best move for the computer
        return maxMove(game, depth);
    }

    private static int maxMove(Game game, int depth) {
        if (depth == 0 || game.getIsOver()) {
            return heuristic(game);
        }

        int maxEval = NEG_INFINITY;
        List<Game> nextStates = game.getNextStates1(); // Generate possible states
        for (Game nextState : nextStates) {
            int eval = chanceMove(nextState, depth - 1, false); // Pass user (false) as the next player
            maxEval = Math.max(maxEval, eval);
        }
        return maxEval;
    }

    private static int minMove(Game game, int depth) {
        if (depth == 0 || game.getIsOver()) {
            return heuristic(game);
        }

        int minEval = INFINITY;
        List<Game> nextStates = game.getNextStates1(); // Generate possible states
        for (Game nextState : nextStates) {
            int eval = chanceMove(nextState, depth - 1, true); // Pass computer (true) as the next player
            minEval = Math.min(minEval, eval);
        }
        return minEval;
    }

    private static int chanceMove(Game game, int depth, boolean isComputer) {
        if (depth == 0 || game.getIsOver()) {
            return heuristic(game);
        }

        double expectedEval = 0.0;
        Dice dice = game.getDice();
        for (int outcome : POSSIBLE_OUTCOMES) {
            dice.setFace(outcome); // Set the dice outcome
            int eval;
            if (isComputer) {
                eval = maxMove(game, depth - 1);
            } else {
                eval = minMove(game, depth - 1);
            }
            double probability = game.probability(outcome); // Get probability of the outcome
            expectedEval += probability * eval;
        }
        return (int) expectedEval;
    }

    private static int heuristic(Game game) {
        // Define a heuristic to evaluate the game's state
        // Example: Use the progress of tokens, tokens near the home, or blocking opponent tokens
        return game.evaluateState();
    }
}
