package unc.comp433.chathan.eightqueens;

import android.content.Context;


/**
 * A class representing a single square on a chess board.
 */
public class BoardSquare extends android.support.v7.widget.AppCompatImageButton {
    private static final String TAG = BoardSquare.class.getSimpleName();

    private int column;
    private int row;

    /**
     * Create a new board square.
     *
     * @param context The context passed to the square's parent Button.
     * @param column The column the square is located in.
     * @param row The row the square is located in.
     */
    public BoardSquare(Context context, int column, int row) {
        super(context);

        this.column = column;
        this.row = row;
    }

    /**
     * Get the column the board is in.
     *
     * @return The index of the column the square is in.
     */
    public int getColumn() { return column; }

    /**
     * Get the row the board is in.
     *
     * @return The index of the row the square is in.
     */
    public int getRow() { return row; }

    /**
     * Set if the square has a queen in it.
     *
     * @param queenVisible A boolean indicating if a queen should be drawn in the square.
     */
    public void setQueenVisible(boolean queenVisible) {
        if (queenVisible) {
            setImageResource(R.drawable.ic_queen);
        } else {
            setImageResource(0);
        }
    }
}
