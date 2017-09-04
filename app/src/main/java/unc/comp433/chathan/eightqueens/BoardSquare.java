package unc.comp433.chathan.eightqueens;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * A class representing a single square on a chess board.
 */
public class BoardSquare extends android.support.v7.widget.AppCompatButton implements Button.OnClickListener {
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

        this.setOnClickListener(this);
    }

    /**
     * Handle a click event for the square.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        Log.d(TAG, String.format("Square at (%d, %d) toggled", column, row));
    }
}
