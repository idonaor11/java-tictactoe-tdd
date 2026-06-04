package tdd;
import java.util.Scanner;

/**
 * Represents the Tic-Tac-Toe game logic.
 */
final public class TicTacToe {
    private static Scanner sc;
    private Player player1 = new Player("PLAYER-X", 1, 'X');
    private Player player2 = new Player("PLAYER-O", 2, 'O');
    private Board board;

    /**
     * Constructs a TicTacToe game with default players.
     */
    public TicTacToe() {
        sc = new Scanner(System.in);
    }

    /**
     * Constructs a TicTacToe game with custom players.
     * @param player1 the first player
     * @param player2 the second player
     */
    public TicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        sc = new Scanner(System.in);
    }

    /**
     * Starts the gameplay loop.
     */
    public void play() {
        Player currentPlayer = this.player1;
        if (this.playAgain()) {
            this.gameOver();
            return;
        }

        while (true) {
            currentPlayer.move(this.board);
            if (this.board.checkWin(currentPlayer.getMarker())) {
                this.handleWinner(currentPlayer);
                if (this.playAgain()) {
                    this.gameOver();
                    return;
                }
            } else if(this.board.isFull()) {
                System.out.println("The board is full. It's a tie!");
                if (this.playAgain()) {
                    this.gameOver();
                    return;
                } else {
                    continue;
                }
            }

            currentPlayer = currentPlayer == this.player1 ? this.player2 : this.player1;
        }
    }

    // Ends the game and prints final results
    private void gameOver() {
        this.printResults();
        sc.close();
        this.player1.closeSC();
        this.player2.closeSC();
    }

    /**
     * Handles when a player wins a round.
     * @param winner the player who won the round
     */
    public void handleWinner(Player winner) {
        System.out.println(winner.getName() + " has won this round!");
        winner.incrementNumberOfWins();
    }

    // Prints welcome message for starting new round
    private void welcome() {
        System.out.println("Hit \"y/Y\" to start a new game. Or hit any other key to exit.");
    }

    // Prompts the user for board size and validates input
    private int getBoardSize() {
        while (true) {
            System.out.print("Please enter your preferred SIZE of the board");
            System.out.println(" (from 3 to 10. 3 -> 3x3; 4 -> 4x4; 10 -> 10x10, etc): ");

            if (sc.hasNextLine()) {
                String userInput = sc.nextLine();
                if(this.verifyBoardSize(userInput)) {
                    return Integer.parseInt(userInput);
                }
            }
        }
    }

    /**
     * Validates the board size entered by the user.
     * @param boardSize string representation of the size
     * @return true if the size is between 3 and 10
     */
    public boolean verifyBoardSize(String boardSize) {
        if (!boardSize.matches("-?\\d+")){
            return false;
        }
        int size = Integer.parseInt(boardSize);
        return size >= 3 && size <= 10;
    }

    /**
     * Prompts whether to play again and resets the board if yes.
     * @return true if a new game should start
     */
    private boolean playAgain() {
        this.welcome();
        sc = new Scanner((System.in));
        String userDecision = sc.nextLine();

        if (userDecision.equalsIgnoreCase("Y")) {
            int boardSize = this.getBoardSize();
            this.board = new Board(boardSize);
            return false;
        }

        return true;
    }


    /**
     * Prints final win statistics for both players.
     */
    public void printResults() {
        System.out.println("Player " + this.player1.getName() + " has won: "
                + this.player1.getNumberOfWins() + " time(s).");
        System.out.println("Player " + this.player2.getName() + " has won: "
                + this.player2.getNumberOfWins() + " time(s).");

        if (this.player1.getNumberOfWins() == this.player2.getNumberOfWins()) {
            System.out.println("Its a tie!");
        } else {
            String winner = this.player1.getNumberOfWins() > this.player2.getNumberOfWins()
                    ? this.player1.getName() : this.player2.getName();
            System.out.println("The final winner is: " + winner + "!!!");
        }

        System.out.println();
    }
}
