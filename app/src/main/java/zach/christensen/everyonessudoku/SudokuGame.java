package zach.christensen.everyonessudoku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import zach.christensen.everyonessudoku.Model.Controller;

/**
 * Created by Zach on 24/05/2016.
 */
public class SudokuGame extends AppCompatActivity {
    Controller myCont;
    int selectedIndex;
    boolean isSelectedDark;
    Button selectedButton;

    boolean isPaused = false;
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

    @Override
    public void onResume() {
        super.onResume();
        timerHandler.post(timerRunnable);
    }

    protected void resetSelectedCell(){
        if (isSelectedDark){
            selectedButton.setBackgroundResource(R.drawable.grid_button_dark);
        }
        else{
            selectedButton.setBackgroundResource(R.drawable.grid_button);
        }
        selectedIndex = -1;
    }



    protected void changeCell(int newNum){
        if (selectedButton == null){
            return;
        }

        //update model
        myCont.updateCellModel(selectedIndex, newNum);
        if (myCont.isComplete()){
            outputToast("Game Complete! Great Job.");
            timerHandler.removeCallbacks(timerRunnable);
        }
        resetSelectedCell();
        selectedButton = null;
    }

    public void outputToast(String output){
        Toast.makeText(SudokuGame.this, output, Toast.LENGTH_LONG).show();
    }

    public void outputToast(Integer output){
        Toast.makeText(SudokuGame.this, Integer.toString(output), Toast.LENGTH_SHORT).show();
    }
}
