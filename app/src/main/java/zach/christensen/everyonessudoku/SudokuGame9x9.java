package zach.christensen.everyonessudoku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import zach.christensen.everyonessudoku.Model.Controller;
import java.util.ArrayList;

public class SudokuGame9x9 extends AppCompatActivity {
    boolean isPaused = false;
    Controller myCont;
    int selectedIndex;
    boolean isSelectedDark;
    Button selectedButton;
    private static final int[] GRIDBUTTONS = {
            R.id.button0,
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.button10,
            R.id.button11,
            R.id.button12,
            R.id.button13,
            R.id.button14,
            R.id.button15,
            R.id.button16,
            R.id.button17,
            R.id.button18,
            R.id.button19,
            R.id.button20,
            R.id.button21,
            R.id.button22,
            R.id.button23,
            R.id.button24,
            R.id.button25,
            R.id.button26,
            R.id.button27,
            R.id.button28,
            R.id.button29,
            R.id.button30,
            R.id.button31,
            R.id.button32,
            R.id.button33,
            R.id.button34,
            R.id.button35,
            R.id.button36,
            R.id.button37,
            R.id.button38,
            R.id.button39,
            R.id.button40,
            R.id.button41,
            R.id.button42,
            R.id.button43,
            R.id.button44,
            R.id.button45,
            R.id.button46,
            R.id.button47,
            R.id.button48,
            R.id.button49,
            R.id.button50,
            R.id.button51,
            R.id.button52,
            R.id.button53,
            R.id.button54,
            R.id.button55,
            R.id.button56,
            R.id.button57,
            R.id.button58,
            R.id.button59,
            R.id.button60,
            R.id.button61,
            R.id.button62,
            R.id.button63,
            R.id.button64,
            R.id.button65,
            R.id.button66,
            R.id.button67,
            R.id.button68,
            R.id.button69,
            R.id.button70,
            R.id.button71,
            R.id.button72,
            R.id.button73,
            R.id.button74,
            R.id.button75,
            R.id.button76,
            R.id.button77,
            R.id.button78,
            R.id.button79,
            R.id.button80
    };

    private final static int[] SETTERBUTTONS = {
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9,
    };

    //Timer Objects
    TextView timerTextView;
    int timeSeconds = 0;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isPaused) {
                int seconds = timeSeconds;
                int minutes = seconds / 60;
                seconds = seconds % 60;

                timerTextView.setText(String.format("%d:%02d", minutes, seconds));

                timeSeconds += 1;
            }
            //Run this method one second from now.
            timerHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_game9x9);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myCont = new Controller(this);
        myCont.loadTest9x9();
        setGrid(myCont.getGrid());

        //Screen size
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        outputToast(Integer.toString(metrics.heightPixels));
        outputToast(Integer.toString(metrics.widthPixels));

        //Initialise Screen
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        setButtons();
        setGrid();

        //Start Timer
        timerHandler.postDelayed(timerRunnable, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handles the back button in material design.
        switch (item.getItemId()) {
            case android.R.id.home:
                new AlertDialog.Builder(this)
                        .setTitle("Quit Game")
                        .setMessage("Are you sure you want to quit?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                finish();
                            }}).show();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }

    private void resetSelectedCell(){
        if (isSelectedDark){
            selectedButton.setBackgroundResource(R.drawable.grid_button_dark);
        }
        else{
            selectedButton.setBackgroundResource(R.drawable.grid_button);
        }
    }

    private void setButtons(){

        Button btnUndo = (Button)findViewById(R.id.btnUndo);
        assert btnUndo != null;
        btnUndo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //TODO
                SudokuGame9x9.this.finish();
            }
        });

        Button btnPause = (Button)findViewById(R.id.btnPause);
        assert btnPause != null;
        btnPause.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Button btnPause = (Button)findViewById(R.id.btnPause);
                if (!isPaused){
                    btnPause.setText("Unpause");
                    isPaused = true;
                }
                else{
                    btnPause.setText("Pause");
                    isPaused = false;
                }
            }
        });

        //Setter Buttons
        for (int b : SETTERBUTTONS){
            Button btn = (Button)findViewById(b);
            assert btn != null;
            btn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Button clickedBtn = (Button)v;
                    if (selectedButton != null){
                        changeCell(Integer.parseInt(clickedBtn.getText().toString()));
                    }
                }
            });
        }
    }

    private void setGrid(){
        for (int b : GRIDBUTTONS){
            Button btn = (Button)findViewById(b);
            assert btn != null;
            btn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Button clickedBtn = (Button)v;
                    if (selectedButton != null){
                        resetSelectedCell();
                        if (selectedButton == clickedBtn){
                            selectedButton = null;
                            return;
                        }
                    }
                    selectedButton = clickedBtn;

                    isSelectedDark = selectedButton.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.grid_button_dark).getConstantState());
                    selectedButton.setBackgroundResource(R.drawable.grid_button_selected);

                }
            });
        }
    }

    public void setGrid(int[] newGrid){

    }

    public void setCell(int index, int value){

    }

    private void changeCell(int newNum){
        if (selectedButton == null){
            return;
        }
        selectedButton.setText(Integer.toString(newNum));
        resetSelectedCell();
        selectedButton = null;
    }

    public void outputToast(String output){
        Toast.makeText(SudokuGame9x9.this, output, Toast.LENGTH_SHORT).show();
    }
}
