package zach.christensen.everyonessudoku;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class ExtraActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button extra = (Button)findViewById(R.id.btnR4x4);
        assert extra != null;
        extra.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(ExtraActivity.this, SudokuGameReactive4x4.class);
                ExtraActivity.this.startActivity(myIntent);
            }
        });

    }
}
