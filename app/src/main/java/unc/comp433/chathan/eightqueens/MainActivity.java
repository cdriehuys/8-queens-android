package unc.comp433.chathan.eightqueens;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    private static final int DARK_SQUARE = Color.parseColor("#755000");
    private static final int LIGHT_SQUARE = Color.parseColor("#edc674");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBoard();
    }

    private void createBoard() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int buttonSize = width / 8;

        GridLayout board = (GridLayout) findViewById(R.id.chess_grid_layout);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                BoardSquare square = new BoardSquare(getApplicationContext(), col, row);

                if ((row + col) % 2 == 0) {
                    square.setBackgroundColor(DARK_SQUARE);
                } else {
                    square.setBackgroundColor(LIGHT_SQUARE);
                }

                GridLayout.LayoutParams cellParams = new GridLayout.LayoutParams();
                cellParams.width = buttonSize;
                cellParams.height = buttonSize;

                board.addView(square, cellParams);
            }
        }
    }
}
