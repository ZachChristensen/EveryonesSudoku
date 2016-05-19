package zach.christensen.everyonessudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class SudokuGame9x9 extends AppCompatActivity {
    boolean isPaused = false;
    int selectedIndex;

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

        //Initialise Screen
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        setButtons();

        //Start Timer
        timerHandler.postDelayed(timerRunnable, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handles the back button in material design.
        switch (item.getItemId()) {
            case android.R.id.home:
                //TODO Are you sure? notification
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.button);
        b.setText("start");
    }


    private void setButtons(){
        Button btnReturn = (Button)findViewById(R.id.btnReturn);
        assert btnReturn != null;
        btnReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
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

    }

    protected void outputToast(String output){
        Toast.makeText(SudokuGame9x9.this, output, Toast.LENGTH_SHORT).show();
    }
}
