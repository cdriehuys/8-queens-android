package unc.comp433.chathan.eightqueens;


import android.graphics.Point;

import java.util.Arrays;
import java.util.Locale;

class Game {
    static final int BOARD_SIZE = 8;

    private static final int NUM_QUEENS = 8;

    private boolean[][] queens;

    private String errorMessage;

    /**
     * Create a new 8-Queens game.
     */
    Game() {
        queens = new boolean[BOARD_SIZE][BOARD_SIZE];
        for (boolean[] row: queens) {
            Arrays.fill(row, false);
        }

        errorMessage = null;
    }

    /**
     * Get the error message from the previous move.
     *
     * @return The error message from the previous move. May be null.
     */
    String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Determine if there's a queen at the given location.
     *
     * @param col The column of the cell to check for a queen in.
     * @param row The row of the cell to check for a queen in.
     *
     * @return True if there is a queen at the given location and false otherwise.
     */
    boolean getQueenAt(int col, int row) {
        return queens[col][row];
    }

    /**
     * Determine if the game has been won.
     *
     * @return True if the game is complete, false otherwise.
     */
    boolean isWon() {
        int numQueens = 0;
        for (boolean[] row: queens) {
            for (boolean cell: row) {
                if (cell) {
                    numQueens++;
                }
            }
        }

        return numQueens == NUM_QUEENS;
    }

    /**
     * Toggle the presence of a queen in a particular cell.
     *
     * If the move was unsuccessful, use {@link #getErrorMessage() getErrorMessage} to retrieve
     * information about why the move was invalid.
     *
     * @param col The column where the cell is located.
     * @param row The row where the cell is located.
     */
    void toggleQueen(int col, int row) {
        // If there was already a queen in that square, we simply remove it
        if (queens[col][row]) {
            queens[col][row] = false;
            return;
        }

        // The square was unoccupied, so we have to check if the move is valid
        Point collision = getCollision(col, row);

        if (collision != null) {
            errorMessage = String.format(
                    Locale.US,
                    "That square is attacked by the queen at (%d, %d).",
                    collision.x,
                    collision.y);
            return;
        }

        // The move was valid so we set the queen and clear any existing error message
        queens[col][row] = true;
        errorMessage = null;
    }

    /**
     * Get the location of the queen attacking a given cell.
     *
     * @param col The column the cell is located in.
     * @param row The row the cell is located in.
     *
     * @return The location of the queen attacking the given cell. Will be null if there is no queen
     *         attacking the given cell.
     */
    private Point getCollision(int col, int row) {
        // Up
        for (int y = row - 1; y >= 0; y--) {
            if (queens[col][y]) {
                return new Point(col, y);
            }
        }

        // Down
        for (int y = row + 1; y < BOARD_SIZE; y++) {
            if (queens[col][y]) {
                return new Point(col, y);
            }
        }

        // Left
        for (int x = col - 1; x >= 0; x--) {
            if (queens[x][row]) {
                return new Point(x, row);
            }
        }

        // Right
        for (int x = col + 1; x < BOARD_SIZE; x++) {
            if (queens[x][row]) {
                return new Point(x, row);
            }
        }

        // Up-left
        for (int x = col - 1, y = row - 1; x >= 0 && y >= 0; x--, y--) {
            if (queens[x][y]) {
                return new Point(x, y);
            }
        }

        // Up-right
        for (int x = col + 1, y = row - 1; x < BOARD_SIZE && y >= 0; x++, y--) {
            if (queens[x][y]) {
                return new Point(x, y);
            }
        }

        // Down-left
        for (int x = col - 1, y = row + 1; x >= 0 && y < BOARD_SIZE; x--, y++) {
            if (queens[x][y]) {
                return new Point(x, y);
            }
        }

        // Down-right
        for (int x = col + 1, y = row + 1; x < BOARD_SIZE && y < BOARD_SIZE; x++, y++) {
            if (queens[x][y]) {
                return new Point(x, y);
            }
        }

        return null;
    }
}
