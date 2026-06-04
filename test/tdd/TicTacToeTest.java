package tdd;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the TicTacToe game logic and board validation.
 */
public class TicTacToeTest {
    /**
     * Tests all possible win conditions (row, column, diagonals) for various board sizes.
     */
    @Test
    public void checkWinnerTest() {
        for (int size = 3; size < 10; size++) {
            Board board = new Board(size);
            // Row win
            for (int j = 1; j <= size; j++) {
                board.placeTheMove('X', j);
            }
            assertTrue("Expected win on row", board.checkWin('X'));

            // Vertical win
            board = new Board(size);
            int col = 1;
            for (int j = 0; j < size; j++) {
                board.placeTheMove('O', col + j * size);
            }
            assertTrue("Expected win on column", board.checkWin('O'));

            // Diagonal win (top-left to bottom-right)
            board = new Board(size);
            for (int j = 0; j < size; j++) {
                board.placeTheMove('X', j + 1 + j * size);
            }
            assertTrue("Expected win on diagonal (top-left to bottom-right)", board.checkWin('X'));

            // Diagonal win (top-right to bottom-left)
            board = new Board(size);
            for (int j = 0; j < size; j++) {
                board.placeTheMove('O', size + j * size - j);
            }
            assertTrue("Expected win on diagonal (top-right to bottom-left)", board.checkWin('O'));
        }
    }

    /**
     * Tests if the handleWinner method correctly increments win counters.
     */
    @Test
    public void handleWinnerTest() {
        Player playerX = new Player("PLAYER-X", 1, 'X');
        Player playerO = new Player("PLAYER-O", 2, 'O');
        TicTacToe ticTacToe = new TicTacToe(playerX, playerO);

        int numOfWinsX = playerX.getNumberOfWins();
        int numOfWinsO = playerO.getNumberOfWins();
        // Player X wins
        ticTacToe.handleWinner(playerX);
        assertEquals("Player X should have 1 more win", numOfWinsX + 1, playerX.getNumberOfWins());
        assertEquals("Player O should have same wins", numOfWinsO, playerO.getNumberOfWins());

        // Player O wins
        ticTacToe.handleWinner(playerO);
        assertEquals("Player O should have 1 more win", numOfWinsO + 1, playerO.getNumberOfWins());
        assertEquals("Player X should remain at +1", numOfWinsX + 1, playerX.getNumberOfWins());
    }

    /**
     * Tests the validation of board positions (legal and illegal inputs).
     */
    @Test
    public void isValidPositionTest() {
        Board board = new Board(3);
        assertTrue(board.isValidPosition("1"));
        assertTrue(board.isValidPosition("2"));
        assertTrue(board.isValidPosition("3"));
        assertFalse(board.isValidPosition("10"));
        assertFalse(board.isValidPosition("0"));
        assertFalse(board.isValidPosition("-1"));
        assertFalse(board.isValidPosition("a"));
        assertFalse(board.isValidPosition("b"));
        assertFalse(board.isValidPosition("c"));
        assertFalse(board.isValidPosition("abc"));
        board.placeTheMove('X', 1);
        assertFalse(board.isValidPosition("1"));
    }

    /**
     * Tests whether the board is correctly identified as full after all moves.
     */
    @Test
    public void isFullTest() {
        for (int size = 3; size <= 10; size++) {
            Board board = new Board(size);
            int maxPos = size * size;
            for (int i = 1; i <= maxPos; i++) {
                assertFalse("Board should not be full yet at move " + i + "size: " + size,  board.isFull());
                if (i % 2 == 0) {
                    board.placeTheMove('X', i);
                }
                else {
                    board.placeTheMove('O', i);
                }
            }
            assertTrue("Board should be full", board.isFull());
        }
    }

    /**
     * Tests board size verification logic.
     */
    @Test
    public void verifyBoardSizeTest() {
        Player playerX = new Player("PLAYER-X", 1, 'X');
        Player playerO = new Player("PLAYER-O", 2, 'O');
        TicTacToe ticTacToe = new TicTacToe(playerX, playerO);
        // if valid size
        for (int size = 3; size <= 10; size++) {
            assertTrue("Expected valid size", ticTacToe.verifyBoardSize(String.valueOf(size)));
        }
        assertFalse(ticTacToe.verifyBoardSize("11"));
        assertFalse(ticTacToe.verifyBoardSize("2"));
        assertFalse(ticTacToe.verifyBoardSize("a"));
        assertFalse(ticTacToe.verifyBoardSize("b"));
        assertFalse(ticTacToe.verifyBoardSize(""));
    }
}
