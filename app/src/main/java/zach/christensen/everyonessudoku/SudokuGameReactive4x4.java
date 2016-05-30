package zach.christensen.everyonessudoku;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;

public class SudokuGameReactive4x4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button myButton = new Button(this);
        myButton.setText("Press me");
        myButton.setBackgroundColor(Color.YELLOW);

        GridView myGrid = new GridView(this);
        myGrid.setNumColumns(4);
        myGrid.setColumnWidth(45);
        myGrid.setMinimumWidth(200);
        myGrid.setMinimumHeight(200);
        myGrid.setBackgroundColor(Color.LTGRAY);

        GridView grid = new GridView(this);

        //grid.setId();
        grid.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.MATCH_PARENT));
        grid.setBackgroundColor(Color.WHITE);
        grid.setNumColumns(3);
        grid.setColumnWidth(GridView.AUTO_FIT);
        grid.setVerticalSpacing(5);
        grid.setHorizontalSpacing(5);
        grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        RelativeLayout myLayout = new RelativeLayout(this);
        myLayout.setBackgroundColor(Color.BLUE);

        //myLayout.addView(myButton);
        myLayout.addView(grid);
        setContentView(myLayout);
    }
}
