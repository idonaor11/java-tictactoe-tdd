
package tdd;
import java.util.Scanner;

/**
 * Main class that starts the Tic-Tac-Toe game.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int playersNumber;
        while (true) {
            System.out.print("How many players [0-2]? ");
            String players = scanner.nextLine();
            if (players.matches("[0-2]")) {
                playersNumber = Integer.parseInt(players);
                break;
            }
        }
        TicTacToe game = getTicTacToe(playersNumber);
        game.play();
    }

    /**
     * Creates a TicTacToe instance with appropriate players.
     * @param playersNumber number of human players (0, 1, or 2)
     * @return a TicTacToe instance
     */
    private static TicTacToe getTicTacToe(int playersNumber) {
        Player player1, player2;
        // Both players are automated
        if (playersNumber == 0) {
            player1 = new AutoPlayer("AutoPlayerX", 1, 'X');
            player2 = new AutoPlayer("AutoPlayerO", 2, 'O');
         // One human, one automated
        } else if (playersNumber == 1) {
            player1 = new Player("PLAYER-X", 1, 'X');
            player2 = new AutoPlayer("AutoPlayer", 2, 'O');
            // Both are human players
        } else {
            player1 = new Player("PLAYER-X", 1, 'X');
            player2 = new Player("PLAYER-O", 2, 'O');
        }
        return new TicTacToe(player1, player2);
    }
}
