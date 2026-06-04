package tdd;
/**
 * Represents a game board for Tic-Tac-Toe.
 */
public class Board {
    private int boardSize;
    private char[][] board;

    private Board() {}

    /**
     * Constructs a new game board with the specified size.
     * @param boardSize the size of the board.
     */
    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.setBoard();
    }

    /**
     * Sets the size of the board.
     * @param boardSize new size for the board
     */
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    /**
     * @return the current board size
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    // Initializes the board as a 2D array filled with empty spaces
    private void setBoard() {
        this.board = new char[this.boardSize][this.boardSize];

        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Prints the current state of the board to the console.
     */
    public void print() {
        StringBuilder topBottomBoundary = new StringBuilder();

        topBottomBoundary.append("+---".repeat(Math.max(0, this.boardSize)));
        topBottomBoundary.append("+");

        for (char[] row : this.board) {
            System.out.println(topBottomBoundary);

            for (char cell : row) {
                System.out.print("| " + cell + " ");
            }
            System.out.println("|");
        }
        System.out.println(topBottomBoundary);
        System.out.println();
    }

    /**
     * Places a marker on the board at a specified position.
     * @param checkMark the marker to place
     * @param movePosition position from 1 to boardSize*boardSize
     */
    public void placeTheMove(char checkMark, int movePosition) {
        int i = (movePosition - 1) / this.board.length;
        int j = (movePosition - 1) % this.board.length;
        this.board[i][j] = checkMark;
    }

    /**
     * Checks if the given player has won the game.
     * @param player the marker of the player to check
     * @return true if player has a full row, column, or diagonal
     */
    public boolean checkWin(char player) {
        for (int i = 0; i < boardSize; i++) {
            boolean colWin = true;
            boolean rowWin = true;
            for (int j = 0; j < boardSize; j++) {
                if (this.board[i][j] != player) {
                    colWin = false;
                }
                if (this.board[j][i] != player) {
                    rowWin = false;
                }
            }
            if (colWin || rowWin) {
                return true;
            }
        }
        boolean topDiagonalWin = true;
        boolean lowerDiagonalWin = true;
        for (int i = 0; i < boardSize; i++) {
            if (this.board[i][i] != player) {
                topDiagonalWin = false;
                break;
            }
        }
        for (int i = 0; i < boardSize; i++) {
            if (this.board[i][boardSize - i - 1] != player) {
                lowerDiagonalWin = false;
                break;
            }
        }
        return topDiagonalWin || lowerDiagonalWin;
    }

    /**
     * Checks whether the given string is a valid move.
     * @param position the position as a string
     * @return true if it's within range and the cell is empty
     */
    public boolean isValidPosition(String position) {
        int boardSize = this.board.length;
        int maxPosition = boardSize * boardSize;
        if (!position.matches("-?\\d+")){
            return false;
        }
        int pos = Integer.parseInt(position);
        if (pos < 1 || pos > maxPosition) {
            return false;
        }
        int row = (pos - 1) / boardSize;
        int col = (pos - 1) % boardSize;

        return this.board[row][col] == ' ';
    }

    /**
     * Checks if the board is completely filled.
     * @return true if there are no empty cells
     */
    public boolean isFull() {
        int boardSize = this.board.length;
        int maxPosition = boardSize * boardSize;
        for (int i = 1; i <= maxPosition; i++) {
            int row = (i - 1) / boardSize;
            int col = (i - 1) % boardSize;
            // check if position is empty.
            if (this.board[row][col] == ' ') {
                return false;
            }
        }
        return true;
    }
}
