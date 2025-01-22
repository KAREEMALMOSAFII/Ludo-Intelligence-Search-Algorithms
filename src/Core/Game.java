package Core;

import Utilities.Color;
import Utilities.Type;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.*;


public class Game implements Cloneable{
    private Board board;
    private List<Player> players;
    private int currentTurn;
    private Dice dice;
    private boolean isOver;

    public final static Scanner scanner = new Scanner(System.in);

    public Game() {
        board= new Board();
        players= new ArrayList<>();
        currentTurn=0;
        dice = new Dice();
        isOver = false;
    }
//==================ZAK NEW======================
@Override
public Game clone() {
    try {
        // Perform shallow copy
        Game cloned = (Game) super.clone();

        // Deep copy of the board
        cloned.board = this.board.clone();

        // Deep copy of the players
        List<Player> clonedPlayers = new ArrayList<>();
        for (Player player : this.players) {
            clonedPlayers.add(player.clone());
        }
        cloned.players = clonedPlayers;

        // Deep copy of the dice
        cloned.dice = this.dice;

        // Return the fully cloned Game object
        return cloned;
    } catch (CloneNotSupportedException e) {
        throw new AssertionError("Cloning not supported", e);
    }
}

    public Game(Board board, List<Player> players, int currentTurn, Dice dice, boolean isOver) {
        this.board = board;
        this.players = players;
        this.currentTurn = currentTurn;
        this.dice = dice;
        this.isOver = false;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public boolean getIsOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public void startGame() {
        board.initializeBoard();
        board.printBoard();
        System.out.println("Choose Number Of Players: 1 (Player vs AI) or 2 (4 AI players)");

        int numOfPlayers;
        while (true) {
            try {
                numOfPlayers = scanner.nextInt();
                if (numOfPlayers == 1 || numOfPlayers == 2) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        switch (numOfPlayers) {
            case 1:
                Player player = new Player(0,"Player 1", Color.BLUE, new ArrayList<>());
                Player computer = new Player(2,"Computer", Color.GREEN, new ArrayList<>());

                initializeTokens(player);
                initializeTokens(computer);

                players.add(player);
                players.add(computer);
                break;

            case 2:
                Player computer1 = new Player(0,"Computer 1", Color.BLUE, new ArrayList<>());
                Player computer2 = new Player(1,"Computer 2", Color.RED, new ArrayList<>());
                Player computer3 = new Player(2,"Computer 3", Color.GREEN, new ArrayList<>());
                Player computer4 = new Player(3,"Computer 4", Color.YELLOW, new ArrayList<>());

                initializeTokens(computer1);
                initializeTokens(computer2);
                initializeTokens(computer3);
                initializeTokens(computer4);

                players.add(computer1);
                players.add(computer2);
                players.add(computer3);
                players.add(computer4);
                break;
        }
        while (!getIsOver()) {
//          playerMove();
//           checkWinCondition();
//           switchTurn();
//            board.printBoard();
            for (int i = 0; i < players.size(); i++) {
               // computerMove();
                playComputerMove();
                checkWinCondition();
                switchTurn();
                board.printBoard();
            }
        }
        System.out.println("Game Over");
    }

    public void playerMove()
    {
        Player currentPlayer = players.get(currentTurn);
        int consecutiveSixes = 0;

        while (true) {
            dice.rollDice();
            System.out.println(currentPlayer.getName() + " rolled a " + dice.getFace());

            if (dice.getFace() == 6) {
                consecutiveSixes++;
                if (consecutiveSixes == 3) {
                    System.out.println("You rolled three 6s in a row. Turn forfeited!");
                    return;
                }
            } else {
                consecutiveSixes = 0; // Reset counter if not a 6
            }

            System.out.println("currentPlayer.allTokensInHome() " + currentPlayer.allTokensInHome());
            if (dice.getFace() != 6 && currentPlayer.allTokensInHome()) {
                System.out.println("No available moves.");
                return; // Exit if no valid moves
            }

            if (dice.getFace() == 6 && currentPlayer.allTokensInHome()) {
                currentPlayer.tokens.getFirst().moveToken(dice.getFace(),board);
                return; // Exit if no valid moves
            }

            System.out.println("Choose the position of the token you want to move:");
//            currentPlayer.getTokens()
//                    .forEach(token ->
//                            System.out.println("The Available Token To Move: X = "
//                                    + token.getCurrentCell().getPosX()
//                                    + ", Y = "
//                                    + token.getCurrentCell().getPosY()
//                            + "TOKEN TYPE" + token.getCurrentCell().getType())
//                    );

            currentPlayer.getTokens().stream()
                    .filter(token -> !token.getCurrentCell().isHome())
                    .forEach(token ->
                            System.out.println("The Available Token To Move: X = "
                                    + token.getCurrentCell().getPosX()
                                    + ", Y = "
                                    + token.getCurrentCell().getPosY())
                    );
            int PosX = scanner.nextInt();
            int PosY = scanner.nextInt();

            if (board.getCellIndex(PosX, PosY) != -1) {
                currentPlayer.tokens.getFirst().moveToken(dice.getFace(),board);
            } else {
                System.out.println("Invalid position. Try again.");
                continue; // Ask for input again if invalid position
            }

            checkWinCondition();

            if (dice.getFace() != 6) {
                break; // End the turn if dice rolled a number other than 6
            }

        }
    }

    public void checkWinCondition() {
        for (Player player : players) {
            if (player.HasWon()) {
                setOver(true);
                System.out.println(player.getName() + " has won the game!");
                break;
            }
        }
    }

    public void switchTurn() {
        currentTurn = (currentTurn + 1) % players.size();
    }

    int calculateNewPosition(int currentPosition, int diceRoll, int playerId) {
        int newPosition = (currentPosition + diceRoll) % 52;
        // If entering home path
        if (currentPosition < Board.playerStartPositions[playerId]
                && newPosition >= board.playerStartPositions[playerId]) {
            // Enter home path
            return -1; // -1 indicates transitioning to home path
        }
        return newPosition;
    }

    private void initializeTokens(Player player) {
        if (player.getColor().equals(Color.BLUE)) {
            initializeColorTokens(player, Color.BLUE, 11, 2);
        } else if (player.getColor().equals(Color.RED)) {
            initializeColorTokens(player, Color.RED, 2, 2);
        } else if (player.getColor().equals(Color.GREEN)) {
            initializeColorTokens(player, Color.GREEN, 2, 11);
        } else if (player.getColor().equals(Color.YELLOW)) {
            initializeColorTokens(player, Color.YELLOW, 11, 11);
        }
    }

    private void initializeColorTokens(Player player, Color color, int startX, int startY) {
        for (int i = 0; i < 4; i++) {
            int offsetX = i < 2 ? 0 : 1;  // Different offset for different rows
            int offsetY = i % 2 == 0 ? 0 : 1;  // Alternate columns

            Cell cell = new Cell(startX + offsetX, startY + offsetY, Type.HOME, color, color);
            player.getTokens().add(new Token(i, player, cell));
        }
    }



    // ==========================================

    private int expectiminimax(Board state, int depth, boolean isMaximizingPlayer) {
        System.out.println("heheheehehhehe");
        if (depth == 0 || isOver) {
            return heuristic(state);
        }

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Board nextState : getNextStates(state)) {
                int eval = expectiminimax(nextState, depth - 1, false);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int expectedEval = 0;
            int[] possibleOutcomes = { 1, 2, 3, 4, 5, 6 }; // Dice outcomes
            for (int outcome : possibleOutcomes) {
                dice.setFace(outcome); // Set the dice face
                int eval = expectiminimax(state, depth - 1, true);
                expectedEval += probability(outcome) * eval;
            }
            return expectedEval;
        }
    }

    // Computer move logic for automated decision-making
    public void computerMove() {
        Player currentPlayer = players.get(currentTurn);
        int bestMove = -1;
        int maxEval = Integer.MIN_VALUE;
        int consecutiveSixes = 0;
            dice.rollDice();
            System.out.println(currentPlayer.getName() + " rolled a " + dice.getFace());

//            if (dice.getFace() == 6) {
//                consecutiveSixes++;
//                if (consecutiveSixes == 3) {
//                    System.out.println("You rolled three 6s in a row. Turn forfeited!");
//                    return;
//                }
//            } else {
//                consecutiveSixes = 0; // Reset counter if not a 6
//            }
//
//            System.out.println("currentPlayer.allTokensInHome() " + currentPlayer.allTokensInHome());
//            if (dice.getFace() != 6 && currentPlayer.allTokensInHome()) {
//                System.out.println("No available moves.");
//                return; // Exit if no valid moves
//            }
//
//            if (dice.getFace() == 6 && currentPlayer.allTokensInHome()) {
//                currentPlayer.tokens.getFirst().moveToken(dice.getFace(),board);
//                return; // Exit if no valid moves
//            }
//
//            System.out.println("Choose the position of the token you want to move:");
////            currentPlayer.getTokens()
////                    .forEach(token ->
////                            System.out.println("The Available Token To Move: X = "
////                                    + token.getCurrentCell().getPosX()
////                                    + ", Y = "
////                                    + token.getCurrentCell().getPosY()
////                            + "TOKEN TYPE" + token.getCurrentCell().getType())
////                    );
//        int[] position = new int[2];
//
//            currentPlayer.getTokens().stream()
//                    .filter(token -> !token.getCurrentCell().isHome())
//                    .forEach(token ->{
//                        System.out.println("The Available Token To Move: X = "
//                                + token.getCurrentCell().getPosX()
//                                + ", Y = "
//                                + token.getCurrentCell().getPosY());
//                        position[0] = token.getCurrentCell().getPosX();
//                        position[1] = token.getCurrentCell().getPosY();
//                            }
//
//                    );
//
//
//            if (board.getCellIndex(position[0],  position[1]) != -1) {
//                currentPlayer.tokens.getFirst().moveToken(dice.getFace(),board);
//            } else {
//                System.out.println("Invalid position. Try again.");
////                continue; // Ask for input again if invalid position
//            }
//
//            checkWinCondition();

//            if (dice.getFace() != 6) {
//                break; // End the turn if dice rolled a number other than 6
//            }
        for (Token token : currentPlayer.getTokens()) {

            System.out.println("==========================================================");
            System.out.println(currentPlayer.getName() + "can't Move");
            System.out.println("==========================================================");

//            if (token.canMove(dice.getFace(), board)) {
//                System.out.println("==========================================================");
//                System.out.println(currentPlayer.getName() + "can Move");
//                System.out.println("==========================================================");
//
////                token.moveToken(dice.getFace(), board);
//
//
//            }
            int eval = expectiminimax(board, 3, false); // Depth of 3 for decision-making
            if (eval > maxEval) {
                maxEval = eval;
                bestMove = token.getTokenId();
            }
        }

        if (bestMove != -1) {
            currentPlayer.getTokens().get(bestMove).moveToken(dice.getFace(), board);
            System.out.println(currentPlayer.getName() + " moved token " + bestMove);
        } else {
            System.out.println(currentPlayer.getName() + " has no valid moves.");
        }
    }

    // Heuristic function to evaluate the game state
    private int heuristic(Board state) {
        int score = 0;
        for (Player player : players) {
            for (Token token : player.getTokens()) {
                int position = board.getCellIndex(token.getCurrentCell().getPosX(), token.getCurrentCell().getPosY());
                if (position != -1) {
                    score += position; // Higher position = better progress
                }
                if (token.getCurrentCell().isHome()) {
                    score += 100; // Bonus for tokens in HOME
                }
                if (token.getCurrentCell().isGoal()) {
                    score += 500; // Higher bonus for tokens reaching the goal
                }
            }
        }
        return score;
    }

    // Get all possible next states based on the current game state
    public List<Board> getNextStates(Board currentState) {
        List<Board> nextStates = new ArrayList<>();
        Player currentPlayer = players.get(currentTurn);
        for (Token token : currentPlayer.getTokens()) {
            if (token.canMove(dice.getFace(), board)) {
//                Board clonedState = currentState;
                token.moveToken(dice.getFace(), board);
                nextStates.add(board);
            }
        }
        return nextStates;
    }
    // Generate possible next states (update from private to public)
    public List<Game> getNextStates1() {
        if (players.isEmpty()) {
            throw new IllegalStateException("No players available in the game.");
        }

        if (currentTurn < 0 || currentTurn >= players.size()) {
            throw new IllegalStateException("Invalid currentTurn index: " + currentTurn);
        }

        Player currentPlayer = players.get(currentTurn);

        List<Game> nextStates = new ArrayList<>();

        // Ensure the player has tokens to move
        if (currentPlayer.getTokens().isEmpty()) {
            System.out.println("No tokens available for player: " + currentPlayer);
            return nextStates; // Return an empty list
        }

        for (Token token : currentPlayer.getTokens()) {
            if (dice == null || dice.getFace() == 0) {
                throw new IllegalStateException("Dice has not been rolled or has an invalid face value.");
            }

            if (token.canMove(dice.getFace(), board)) {
                Game nextState = clone(); // Create a copy of the current game state
                token.moveToken(dice.getFace(), board); // Simulate a move
                nextStates.add(nextState);
            }
        }

        return nextStates;
    }


    // Probability of a particular dice outcome (uniform distribution)
    public double probability(int outcome) {
        return 1.0 / 6.0; // Equal probability for each dice face
    }

/*===============Zak new=====================*/
    // Evaluate the game state using a heuristic
    public int evaluateState() {
        Player currentPlayer = players.get(currentTurn);
        int score = 0;
        for (Token token : currentPlayer.getTokens()) {
            score += token.getProgress(); // Example heuristic: Sum of token progress
        }
        return score;
    }
//==================Zak new2=================
private void playComputerMove() {
    int bestScore = Integer.MIN_VALUE;
    int bestRow = -1;
    int bestCol = -1;
    Player currentPlayer = players.get(currentTurn);

    int outcome =dice.rollDice();
    dice.setFace(outcome);
    System.out.println(currentPlayer.getName() + " rolled a " + dice.getFace());

    for (Token token : currentPlayer.getTokens()) {

        if (token.canMove(dice.getFace(),board)) {
            System.out.println("can move======================");

if(currentPlayer.allTokensInHome() && dice.getFace()==6){
//    board.getBoard()[].addToken(token);
    token.moveTokenFromHomeToStart(board);
}
else{
    int position = board.getCellIndex(token.getCurrentCell().getPosX(), token.getCurrentCell().getPosY());
    //if this position cell didn't in the 1th array
if(position == -1){
    continue;
}
else {
    board.getBoard()[position].addToken(token);
}

}

            token.moveToken(dice.getFace(),board);

            System.out.println("FROM COMPUTER MOVE");
            board.printBoard();
            int score = expectiminimax1(0, false,token);
            int position = board.getCellIndex(token.getCurrentCell().getPosX(), token.getCurrentCell().getPosY());
            board.getBoard()[position].removeToken(token);
            if (score > bestScore) {
                System.out.println("bestRow " + bestRow + " bestCol " + bestCol);

                System.out.println("SCORE Updated , Last Score " + bestScore + " new score " + score);

                bestScore = score;
                bestRow = token.getCurrentCell().getPosX();
                bestCol = token.getCurrentCell().getPosY();
            }

            board.getBoard()[position].addToken(token);
        }


    }



    System.out.println("Block Setted At Potition  " + bestRow + " " + bestCol + " With Score : " + bestScore);

}

    private int expectiminimax1(int depth, boolean maximizingPlayer, Token token) {
        Player currentPlayer = players.get(currentTurn);
        if (isOver || depth == 2) {
            return evaluate(token);
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
                if (token.canMove(dice.getFace(),board)) {
                    if(currentPlayer.allTokensInHome() && dice.getFace()==6){
//    board.getBoard()[].addToken(token);
                        token.moveTokenFromHomeToStart(board);
                    }
                    else {
                        // Only proceed if the token is not out of bounds
                        int position = board.getCellIndex(token.getCurrentCell().getPosX(), token.getCurrentCell().getPosY());
                        if (position >= 0 && position < board.getBoard().length) {
                            board.getBoard()[position].addToken(token);
                        } else {
                            System.out.println("Invalid position: " + position);
                            return Integer.MIN_VALUE; // Handle invalid position if needed
                        }
                    }

                    //token.moveToken(dice.getFace(),board);

                    System.out.println("FROM MAX");
                    int position = board.getCellIndex(token.getCurrentCell().getPosX(), token.getCurrentCell().getPosY());
                    board.printBoard();
                    int eval = expectiminimax1(depth + 1, false,token);
                    board.getBoard()[position].removeToken(token);
                    maxEval = Math.max(maxEval, eval);

                }


            return maxEval;

        } else {
            int minEval = Integer.MAX_VALUE;

                if (token.canMove(dice.getFace(),board)) {
                    int position = board.getCellIndex(token.getCurrentCell().getPosX(), token.getCurrentCell().getPosY());
                    System.out.println("ttttttt"+position);

                    if(currentPlayer.allTokensInHome() && dice.getFace()==6){
//    board.getBoard()[].addToken(token);
                        token.moveTokenFromHomeToStart(board);
                    }
                    else {
                        // Only proceed if the token is not out of bounds

                        if (position >= 0 && position < board.getBoard().length) {
                            board.getBoard()[position].addToken(token);
                        } else {
                            System.out.println("Invalid position: " + position);
                            return Integer.MIN_VALUE; // Handle invalid position if needed
                        }
                    }
                    token.moveToken(dice.getFace(),board);


                    System.out.println("FROM MIN");
                    board.printBoard();
                    int eval = expectiminimax1(depth + 1, true,token);

                    board.getBoard()[position].removeToken(token);
                    minEval = Math.min(minEval, eval);

                }


            return minEval;
        }

    }
    private int evaluate(Token token) {
        int score = 0;

        Player owner = token.getOwner();

        Cell currentCell = token.getCurrentCell();

        if (currentCell.isGoal()) {

            score += 100;
        } else if (currentCell.isHome()) {
            score += 50;
        } else {

            score += getScore(currentCell.getPosX(), currentCell.getPosY());
        }

        // Penalize if the opponent's token is nearby or on the same cell
        for (Player opponent : players) {
            if (opponent != owner) {
                for (Token opponentToken : opponent.getTokens()) {
                    int position1 = board.getCellIndex(opponentToken.getCurrentCell().getPosX(), opponentToken.getCurrentCell().getPosY());
                    int position2 = board.getCellIndex(token.getCurrentCell().getPosX(), token.getCurrentCell().getPosY());
                    if (position1 == position2) {
                        score -= 30;
                    }
                }
            }
        }

        if (!currentCell.getType().equals(Type.SAFEZONE)) {
            score -= 20;  // Penalize unsafe position
        }

        return score;
    }

    // A method that evaluates a position on the Ludo board and assigns a score
    private int getScore(int row, int col) {
        int score = 0;
        // check if the current position is near the goal (higher score for tokens closer to the goal)
        score += evaluateProximityToGoal(row, col);
        // check if the position is safe (some positions on the board are safe zones)
        score += evaluateSafety(row, col);
        // penalize if an opponent is near or can capture the token
        score += evaluateOpponentProximity(row, col);

        return score;
    }
    // evaluates the proximity to the goal area (higher score for tokens near the goal)
    private int evaluateProximityToGoal(int row, int col) {
        int score = 0;
        int goalRow = 0; // Example goal row
        int goalCol = board.getBoard().length - 1;


        int distanceToGoal = Math.abs(goalRow - row) + Math.abs(goalCol - col);

        // Closer to goal, higher score
        if (distanceToGoal == 0) {
            score += 100;
        } else {
            score += (board.getBoard().length - 1 - distanceToGoal);
        }

        return score;
    }

    // evaluates if the token is in a safe position (safe zones are predefined)
    private int evaluateSafety(int row, int col) {
        int score = 0;
        if (board.getBoard()[board.getCellIndex(row,col)].isSafeZone()) {
            score += 50;
        }

        return score;
    }


    // check all positions within a range
    private int evaluateOpponentProximity(int row, int col) {
        int score = 0;
        int currentCellIndex = board.getCellIndex(row, col);
        int boardLength = board.getBoard().length;


        for (int i = currentCellIndex - 6; i <= currentCellIndex + 6; i++) {
            if (i >= 0 && i < boardLength) {
                if (!board.getBoard()[i].getTokens().isEmpty()) {
                    score -= 30;
                }
            }
        }
        return score;
    }
}



