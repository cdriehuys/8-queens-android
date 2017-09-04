package unc.comp433.chathan.eightqueens;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private BoardSquare[][] boardSquares;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();

        createBoard();
        drawBoard();
    }

    /**
     * Handle a click event for the reset button.
     *
     * @param view The view that was clicked.
     */
    public void handleResetClick(View view) {
        resetGame();
    }

    /**
     * Show an alert informing the user they won the game.
     */
    private void alertWin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set text content for alert
        builder.setTitle(R.string.congratulations)
                .setMessage(R.string.win_message);

        // Add button to reset board
        builder.setPositiveButton(R.string.reset, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetGame();
            }
        });

        // Add button to close dialog without resetting the board
        builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Create the initial layout of the board.
     *
     * The board is contained in a grid, and each square is a separate button.
     */
    private void createBoard() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int buttonSize = width / Game.BOARD_SIZE;

        GridLayout board = (GridLayout) findViewById(R.id.chess_grid_layout);

        boardSquares = new BoardSquare[Game.BOARD_SIZE][Game.BOARD_SIZE];

        for (int row = 0; row < Game.BOARD_SIZE; row++) {
            for (int col = 0; col < Game.BOARD_SIZE; col++) {
                BoardSquare square = new BoardSquare(this, col, row);
                square.setOnClickListener(new BoardSquareClickListener());

                GridLayout.LayoutParams cellParams = new GridLayout.LayoutParams();
                cellParams.width = buttonSize;
                cellParams.height = buttonSize;

                board.addView(square, cellParams);

                boardSquares[col][row] = square;
            }
        }
    }

    /**
     * Draw the board from the game's state.
     *
     * This method updates the board's buttons to reflect whether or not they have a queen in them.
     */
    private void drawBoard() {
        for (int row = 0; row < Game.BOARD_SIZE; row++) {
            for (int col = 0; col < Game.BOARD_SIZE; col++) {
                boardSquares[col][row].setQueenVisible(game.getQueenAt(col, row));
            }
        }

        // Retrieve the game's current error message, if any.
        TextView errorMessageTV = (TextView) findViewById(R.id.game_error_tv);
        String errorMessage = game.getErrorMessage();

        Resources res = getResources();

        // Render error messages in red and the default text in gray.
        if (errorMessage != null) {
            errorMessageTV.setText(errorMessage);
            errorMessageTV.setTextColor(
                    ResourcesCompat.getColor(res, android.R.color.holo_red_dark, null));
        } else {
            errorMessageTV.setText(res.getString(R.string.no_message));
            errorMessageTV.setTextColor(Color.parseColor("#777777"));
        }
    }

    /**
     * Toggle a queen in the given location.
     *
     * @param col The column the square to toggle is in.
     * @param row The row the square to toggle is in.
     */
    private void makeMove(int col, int row) {
        game.toggleQueen(col, row);
        drawBoard();

        if (game.isWon()) {
            alertWin();
        }
    }

    /**
     * Reset the game.
     */
    private void resetGame() {
        game = new Game();
        drawBoard();
    }

    /**
     * Click handler for squares on the chess board.
     */
    private class BoardSquareClickListener implements ImageButton.OnClickListener {

        /**
         * Handle a click event on a board square.
         *
         * @param view The view that was clicked.
         */
        @Override
        public void onClick(View view) {
            BoardSquare square = (BoardSquare) view;

            int col = square.getColumn();
            int row = square.getRow();

            Log.v(TAG, String.format("Button at (%d, %d) clicked", col, row));

            makeMove(square.getColumn(), square.getRow());
        }
    }
}
