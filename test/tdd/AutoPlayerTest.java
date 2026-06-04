package tdd;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the AutoPlayer class.
 */
public class AutoPlayerTest {

    /**
     * Tests that the AutoPlayer places exactly one move per turn
     * and does not overwrite existing moves.
     */
    @Test
    public void testAutoPlayerMove() {
        AutoPlayer player = new AutoPlayer("autoX", 1, 'X');
        // Test for board sizes from 3x3 to 10x10
        for (int size = 3; size <= 10; size++) {
            Board board = new Board(size);
            for (int cell = 1; cell <= size * size; cell++) {
                int beforeMove = emptyCellCount(board);
                player.move(board);
                int afterMove = emptyCellCount(board);
                assertEquals("AutoPlayer should place exactly one move", beforeMove + 1, afterMove);
            }
        }
    }

    /**
     * Helper method to count the number of cells that are not valid moves.
     * @param board the game board
     * @return number of non-empty (taken) cells
     */
    private int emptyCellCount(Board board) {
        int count = 0;
        int boarderSize = board.getBoardSize();
        for (int cell = 1; cell <= boarderSize * boarderSize; cell++) {
            if (!board.isValidPosition(String.valueOf(cell))) {
                count++;
            }
        }
        return count;
    }
}
