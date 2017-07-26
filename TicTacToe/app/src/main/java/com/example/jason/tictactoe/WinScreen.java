package com.example.jason.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WinScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);
        ((TextView)findViewById(R.id.textView4)).setText(this.getIntent().getStringExtra("winner"));
    }

    public void onClick(View view){
        Button button = (Button)view;
        if(button.getText().equals("Yes")){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"That's too bad.",Toast.LENGTH_SHORT).show();
        }
    }
}
