package zach.christensen.everyonessudoku;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setWelcomeMsg();
        setButtons();
    }

    private void setButtons(){
        Button nine = (Button)findViewById(R.id.btn9x9);
        assert nine != null;
        nine.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this, SudokuGame9x9.class);
                HomeActivity.this.startActivity(myIntent);
            }
        });

        Button six = (Button)findViewById(R.id.btn6x6);
        assert six != null;
        six.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this, SudokuGame6x6.class);
                HomeActivity.this.startActivity(myIntent);
            }
        });

        Button four = (Button)findViewById(R.id.btn4x4);
        assert four != null;
        four.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this, SudokuGame4x4.class);
                HomeActivity.this.startActivity(myIntent);
            }
        });

        Button extra = (Button)findViewById(R.id.btnExtras);
        assert extra != null;
        extra.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this, ExtraActivity.class);
                HomeActivity.this.startActivity(myIntent);
            }
        });
    }

    private void setWelcomeMsg(){
        Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        TextView welcome = (TextView)findViewById(R.id.txtWelcome);
        if (hours > 3 & hours < 12){
            welcome.setText("Good Morning");
        }
        else{
            welcome.setText("Good Evening");
        }
    }
}
