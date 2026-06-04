package tdd;

import java.util.Scanner;

/**
 * Represents a human player in the Tic-Tac-Toe game.
 */
public class Player {
    private final int id;
    private final String name;
    private final char marker;
    private final Scanner sc;
    private int numberOfWins;

    /**
     * Constructs a Player.
     * @param name the name of the player
     * @param id the player's unique ID
     * @param marker the symbol used by the player
     */
    public Player(String name, int id, char marker) {
        this.id = id;
        this.name = name;
        this.numberOfWins = 0;
        this.marker = marker;
        sc = new Scanner(System.in);
    }

    /**
     * @return the player's ID
     */
    public int getId() {
        return id;
    }

    /**
     * @return the player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the player's marker
     */
    public char getMarker() {
        return this.marker;
    }

    /**
     * @return the Scanner instance for input
     */
    public Scanner getSC() {
        return this.sc;
    }

    /**
     * Closes the Scanner
     */
    public void closeSC() {
        this.sc.close();
    }

    /**
     * @return the number of wins the player has
     */
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    /**
     * Increments the number of wins for this player by 1
     */
    public void incrementNumberOfWins() {
        this.numberOfWins++;
    }

    /**
     * Resets the number of wins to zero
     */
    public void resetNumberOfWins() {
        this.numberOfWins = 0;
    }

    /**
     * Reads and performs a move from the user input.
     * Ensures the move is valid before placing it on the board.
     * @param board the game board
     */
    public void move(Board board) {
        String movePos;

        while (true) {
            System.out.println("Player " + this.name + ", please enter your move. (enter a value from 1 - "
                    + board.getBoardSize() * board.getBoardSize() + ")");
            board.print();

            if (sc.hasNextLine()) {
                movePos = sc.nextLine();

                if (!board.isValidPosition(movePos)) {
                    System.out.println("Invalid move. Please try again.");
                } else {
                    break;
                }
            }
        }

        board.placeTheMove(this.marker, Integer.parseInt(movePos));
    }
}
