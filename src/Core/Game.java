package Core;

import Utilities.Color;
import Utilities.Type;

import java.util.*;


public class Game {
    private Board board;
    private List<Player> players;
    private int currentTurn;
    private Dice dice;
    private boolean isOver;

    public final static Scanner scanner = new Scanner(System.in);

    public Game() {
        board = new Board();
        players = new ArrayList<>();
        currentTurn = 0;
        dice = new Dice();
        isOver = false;
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
                Player player = new Player(0, "Player ", Color.BLUE, new ArrayList<>());
                Player computer = new Player(1, "Computer ", Color.RED, new ArrayList<>());

                initializeTokens(player);
                initializeTokens(computer);

                players.add(player);
                players.add(computer);
                break;

            case 2:
                Player computer1 = new Player(0, "Computer 1", Color.BLUE, new ArrayList<>());
                Player computer2 = new Player(1, "Computer 2", Color.RED, new ArrayList<>());
                Player computer3 = new Player(2, "Computer 3", Color.GREEN, new ArrayList<>());
                Player computer4 = new Player(3, "Computer 4", Color.YELLOW, new ArrayList<>());

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
        if(numOfPlayers==1)
        {
        while (!getIsOver()) {
            System.out.println("Player Turn");
            playerMove();
            checkWinCondition();
            switchTurn();
            board.printBoard();

            for (int i = 0; i < players.size() - 1; i++) {
                System.out.println("Computer Turn");
                playerMove();
                //  computerMove();
                checkWinCondition();
                switchTurn();
                board.printBoard();
            }
        }
        }
        else {
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

    public void playerMove() {
        Player currentPlayer = players.get(currentTurn);
        int consecutiveSixes = 0;

        dice.rollDice();
        int rolledValue = dice.getFace();
        System.out.println(currentPlayer.getName() + " rolled a " + rolledValue);

        if (rolledValue != 6 && currentPlayer.allTokensInHome()) {
            System.out.println("No available moves.");
            return;
        }

       else if (rolledValue == 6 && currentPlayer.allTokensInHome()) {
            currentPlayer.getTokens().stream()
                    .filter(token -> token.getCurrentCell().isHome())
                    .forEach(token ->
                            System.out.println("The Available Token To Move From Home: X = " + token.getCurrentCell().getPosX()
                                    + ", Y = " + token.getCurrentCell().getPosY())
                    );
            int posX = scanner.nextInt();
            int posY = scanner.nextInt();
            currentPlayer.tokens.stream()
                    .filter(token -> token.getCurrentCell().getPosX() == posX && token.getCurrentCell().getPosY() == posY)
                    .findFirst().ifPresent(token -> token.moveToken(rolledValue, board, posX, posY, true));
           // board.printBoard();
            return;
        }

      else if (rolledValue == 6 && !currentPlayer.allTokensInHome() && currentPlayer.tokens.stream().anyMatch(s -> s.getCurrentCell().isHome())) {
            System.out.println("Press 0 to move token from home to start or 1 to move on Of : ");

            currentPlayer.getTokens().stream()
                    .filter(token -> token.getCurrentCell().isNormal() || token.getCurrentCell().isSafeZone())
                    .forEach(token ->
                            System.out.println(" X = " + token.getCurrentCell().getPosX()
                                    + ", Y = " + token.getCurrentCell().getPosY())
                    );

            int choice = scanner.nextInt();
            if (choice == 0) {
                currentPlayer.tokens.stream().filter(s -> s.getCurrentCell().isHome()).findFirst()
                        .ifPresent(token -> token.moveToken(rolledValue, board, null, null, true));
           //     board.printBoard();
            } else {
                currentPlayer.getTokens().stream()
                        .filter(token -> token.getCurrentCell().isNormal() ||  token.getCurrentCell().isSafeZone())
                        .forEach(token ->
                                System.out.println("Available Token To Move From Board: X = " + token.getCurrentCell().getPosX()
                                        + ", Y = " + token.getCurrentCell().getPosY())
                        );
                int posX = scanner.nextInt();
                int posY = scanner.nextInt();
                currentPlayer.tokens.stream()
                        .filter(token -> token.getCurrentCell().getPosX() == posX && token.getCurrentCell().getPosY() == posY)
                        .findFirst().ifPresent(token -> token.moveToken(rolledValue, board, posX, posY, false));
             //   board.printBoard();
            }

        }
      else
        {
            currentPlayer.getTokens().stream()
                    .filter(token -> token.getCurrentCell().isNormal() || token.getCurrentCell().isSafeZone())
                    .forEach(token ->
                            System.out.println("Available Token To Move From Board: X = " + token.getCurrentCell().getPosX()
                                    + ", Y = " + token.getCurrentCell().getPosY())
                    );
            int posX = scanner.nextInt();
            int posY = scanner.nextInt();
            currentPlayer.tokens.stream()
                    .filter(token -> token.getCurrentCell().getPosX() == posX && token.getCurrentCell().getPosY() == posY)
                    .findFirst().ifPresent(token -> token.moveToken(rolledValue, board, posX, posY, false));
        }


        System.out.println("Finish Player Move");
    }



    public void computerMove() {
//        Player currentPlayer = players.get(currentTurn);
//        int consecutiveSixes = 0;
//
//        while (true) {
//            dice.rollDice();
//            System.out.println("Computer rolled a " + dice.getFace());
//
//            if (dice.getFace() == 6) {
//                consecutiveSixes++;
//                if (consecutiveSixes == 3) {
//                    System.out.println("Computer rolled three 6s in a row. Turn forfeited!");
//                    return;
//                }
//            } else {
//                consecutiveSixes = 0; // Reset counter if not a 6
//            }
//
//            if (dice.getFace() != 6 && currentPlayer.allTokensInHome()) {
//                System.out.println("No available moves for the computer.");
//                return; // Exit if no valid moves
//            }
//
//            Token bestToken = null;
//            Cell bestMove = null;
//            double bestScore = Double.NEGATIVE_INFINITY;
//
//            // Evaluate all possible moves using ExpectiMinMax algorithm
//            for (Token token : currentPlayer.tokens) {
//                if (token.getCurrentCell().isHome() && dice.getFace() != 6) continue; // Skip home tokens if not rolling a 6
//
//                Cell targetCell = board.getCell(token.getPosX() + dice.getFace(), token.getPosY());
//                if (targetCell != null) {
//                    double score = expectiMinMax(board, currentPlayer, 3, true);
//                    if (score > bestScore) {
//                        bestScore = score;
//                        bestToken = token;
//                        bestMove = targetCell;
//                    }
//                }
//            }
//
//            // Make the best move
//            if (bestToken != null && bestMove != null) {
//                bestToken.moveToken(bestMove, dice.getFace());
//                System.out.println("Computer moved token to position: " + bestMove.getX() + ", " + bestMove.getY());
//            } else {
//                System.out.println("Computer has no valid moves.");
//                return;
//            }
//
//            checkWinCondition();
//
//            if (dice.getFace() != 6) {
//                break; // End the turn if dice rolled a number other than 6
//            }
//        }
//    }
//
//    private double expectiMinMax(Board board, Player currentPlayer, int depth, boolean maximizingPlayer) {
//        if (depth == 0 || checkWinCondition()) {
//            return evaluateBoard(board, currentPlayer);
//        }
//
//        if (maximizingPlayer) {
//            double maxEval = Double.NEGATIVE_INFINITY;
//            for (Token token : currentPlayer.tokens) {
//                Cell nextMove = board.getCell(token.getPosX() + dice.getFace(), token.getPosY());
//                if (nextMove != null) {
//                    token.moveToken(nextMove, dice.getFace());
//                    double eval = expectiMinMax(board, currentPlayer, depth - 1, false);
//                    maxEval = Math.max(maxEval, eval);
//                    token.undoMove();
//                }
//            }
//            return maxEval;
//        } else {
//            double expectedValue = 0;
//            for (int i = 1; i <= 6; i++) {
//                dice.setFace(i); // Simulate all possible dice rolls
//                expectedValue += (1.0 / 6) * expectiMinMax(board, currentPlayer, depth - 1, true);
//            }
//            return expectedValue;
//        }
//    }
//    private double evaluateBoard(Board board, Player currentPlayer) {
//        double score = 0;
//        for (Token token : currentPlayer.tokens) {
//            score += board.getCell(token.getCurrentCell().getPosX(), token.getCurrentCell().getPosY()).getScoreValue();
//        }
//        return score;

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



// ================================================================================= ALGORITHIM ===================================================================================
private void playComputerMove() {
    int bestScore = Integer.MIN_VALUE;
    int bestRow = -1;
    int bestCol = -1;
    Player currentPlayer = players.get(currentTurn);

    dice.rollDice();
    System.out.println(currentPlayer.getName() + " rolled a " + dice.getFace());

    for (Token token : currentPlayer.getTokens()) {

        if (token.canMove(dice.getFace(),board,)) {
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

            token.moveToken(dice.getFace(),board,token.getCurrentCell().getPosX(),token.getCurrentCell().getPosY(),false);

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
                if (token.canMove(dice.getFace(),board,)) {
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

            int position = board.getCellIndex(token.getCurrentCell().getPosX(), token.getCurrentCell().getPosY());
                if (token.canMove(dice.getFace(),board,position)) {
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
                    token.moveToken(dice.getFace(),board,token.getCurrentCell().getPosX(),token.getCurrentCell().getPosY(),false);


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



