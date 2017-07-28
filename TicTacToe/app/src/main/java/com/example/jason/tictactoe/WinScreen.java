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
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void endGame(View view){
        finishAffinity();
        System.exit(0);
    }
}
