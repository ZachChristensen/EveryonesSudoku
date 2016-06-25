package zach.christensen.everyonessudoku;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import zach.christensen.everyonessudoku.Model.Controller;

public class SudokuGame6x6 extends SudokuGame implements InterfaceSudokuGame{
    protected Integer[] GRIDBUTTONS = {
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
            R.id.button35
    };

    protected int[] SETTERBUTTONS = {
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_game6x6);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myCont = new Controller(this);


        //Initialise Screen
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        txtMoves = (TextView) findViewById(R.id.txtMoves);
        setButtons();
        setGridButtons();

        myCont.loadTest6x6();
        updateGrid(myCont.loadFile());

        //Start Timer
        timerHandler.postDelayed(timerRunnable, 0);
    }

    protected void setButtons(){
        Button btnUndo = (Button)findViewById(R.id.btnUndo);
        assert btnUndo != null;
        btnUndo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                myCont.undo();
            }
        });

        Button btnRestart = (Button)findViewById(R.id.btnRestart);
        assert btnRestart != null;
        btnRestart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                myCont.restart();
                updateGrid(myCont.loadFile());
                timeSeconds = 0;
            }
        });

        Button btnHint = (Button)findViewById(R.id.btnHint);
        assert btnHint != null;
        btnHint.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (selectedButton == null) {
                    return;
                }
                outputToast(myCont.getHint(selectedIndex));
                resetSelectedCell();
                selectedButton = null;
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

    protected void setGridButtons(){
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
                    selectedIndex = Arrays.asList(GRIDBUTTONS).indexOf(selectedButton.getId());
                    isSelectedDark = selectedButton.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.grid_button_dark).getConstantState());
                    selectedButton.setBackgroundResource(R.drawable.grid_button_selected);
                }
            });
        }
    }

    public void updateGrid(int[] newGrid){
        for (int i = 0; i < newGrid.length; i++){
            Button btn = (Button)findViewById(GRIDBUTTONS[i]);
            assert btn != null;
            btn.setText(newGrid[i] == 0 ? " " : Integer.toString(newGrid[i]));
        }
    }

    public void updateCell(int index, int value){
        Button btn = (Button)findViewById(GRIDBUTTONS[index]);
        assert btn != null;
        if (value == 0){
            btn.setText("");
        }
        else {
            btn.setText(Integer.toString(value));
        }
    }
}
