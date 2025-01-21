package Core;
import java.util.*;
import Utilities.Color;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // Initialize board
        Board board = new Board();
        board.initializeBoard();

        // Initialize players
        Player player1 = new Player("Player 1", Color.RED);
        Player player2 = new Player("Player 2", Color.BLUE);

        // Initialize tokens and assign them to players
        player1.setTokens(initializeTokens(4, player1));
        player2.setTokens(initializeTokens(4, player2));

        // Assign unique IDs to players (optional but helps with logic)
        player1.setId(1);
        player2.setId(2);
        // Place tokens in their home areas

        // Assign tokens to their owners
        for (Token token : player1.getTokens()) {
            token.setOwner(player1);

        }
        for (Token token : player2.getTokens()) {
            token.setOwner(player2);
        }

        Player currentPlayer = player1;
        while (true) {
            System.out.println(currentPlayer.getName() + "'s turn. Press Enter to roll the dice...");
            scanner.nextLine();

            int diceRoll = rollDice();
            System.out.println("You rolled a " + diceRoll + "!");

            if (diceRoll == 6) {

                if (allTokensInHome(currentPlayer)) {
                    // If all tokens are in home, move one to the board
                    Token tokenToMove = currentPlayer.getTokens().get(0); // Choose the first token //TODO :: CHANGE get(0)
                    board.placeTokenAtStart(tokenToMove, currentPlayer);
                    System.out.println("Token moved from home to the board!");
                    board.printBoard();
                } else {
                    // Ask which token to move
                    System.out.println("You have tokens on the board. Choose one to move:");
                    for (int i = 0; i < currentPlayer.getTokens().size(); i++) {
                        Token token = currentPlayer.getTokens().get(i);
                        if(!token.getCurrentCell().isHome()){
                            System.out.println((i + 1) + ": Token at position " + (token.getCurrentCell() != null ? token.getCurrentCell().getPosX() : "Home"));
                        }
                        board.printBoard();
                    }
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Token selectedToken = currentPlayer.getTokens().get(choice - 1);
                    selectedToken.moveToken(diceRoll, board);
                    board.printBoard();
                }
            } else {
                // Regular move
                if (!anyTokenCanMove(currentPlayer, diceRoll, board)) {
                    System.out.println("No token can move. Turn skipped.");
                } else {
                    System.out.println("Choose a token to move:");
                    for (int i = 0; i < currentPlayer.getTokens().size(); i++) {
                        Token token = currentPlayer.getTokens().get(i);
                        System.out.println((i + 1) + ": Token at position " + (token.getCurrentCell() != null ? token.getCurrentCell().getPosX() : "Home"));
                    }
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Token selectedToken = currentPlayer.getTokens().get(choice - 1);
                    selectedToken.moveToken(diceRoll, board);
                    board.printBoard();
                }
            }

            // Check for win condition
            if (currentPlayer.hasPlayerWon()) {
                System.out.println(currentPlayer.getName() + " has won the game!");
                break;
            }

            // Switch to the next player
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        scanner.close();
    }

    // Helper methods
    private static int rollDice() {
        return new Random().nextInt(6) + 1;
    }

    private static boolean allTokensInHome(Player player) {
        return player.getTokens().stream().allMatch(token -> token.getCurrentCell() == null || token.getCurrentCell().isHome());
    }

    private static boolean anyTokenCanMove(Player player, int diceRoll, Board board) {
        return player.getTokens().stream().anyMatch(token -> token.canMove(diceRoll, board));
    }

    private static List<Token> initializeTokens(int count, Player owner) {
        List<Token> tokens = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tokens.add(new Token(i,null)); // Tokens will be assigned an owner later
            tokens.get(i).setOwner(owner);     // Assign the owner later
        }
        return tokens;
    }
}
