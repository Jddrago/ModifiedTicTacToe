package com.example.jason.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;
    public ArrayList<GamePiece> pieces = new ArrayList<>();
    private boolean player1Selected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.example.jason.tictactoe", Context.MODE_PRIVATE);
        pieces.add(new Player());
        (findViewById(R.id.imageView)).setTag(R.drawable.x);
        (findViewById(R.id.imageView2)).setTag(R.drawable.o);
        (findViewById(R.id.imageView3)).setTag(R.drawable.y);
    }

    public void OnClick(View view){
        ImageView image = (ImageView)view;
        Log.i("Image","image clicked");
            if (Integer.parseInt(image.getTag().toString()) == R.drawable.x) {
                pieces.get(0).imageID = R.drawable.x;
            } else if (Integer.parseInt(image.getTag().toString()) == R.drawable.o) {
                pieces.get(0).imageID = R.drawable.o;
            } else if (Integer.parseInt(image.getTag().toString()) == R.drawable.y) {
                pieces.get(0).imageID = R.drawable.y;
            }
            player1Selected = true;
            Log.i("player 1 selected", pieces.get(0).imageID + "");
            pieces.add(1,new Computer(pieces.get(0).imageID));
            Log.i("player 2 selected", pieces.get(1).imageID + "");
    }

    public void Ready(View view){
        pieces.get(0).name = ((EditText)findViewById(R.id.player1Name)).getText().toString();
        //pieces.get(1).name = ((EditText)findViewById(R.id.player2Name)).getText().toString();
        try {
            sharedPreferences.edit().putString("game",ObjectSerializer.serialize(pieces)).apply();
            sharedPreferences.edit().commit();
            Intent intent = new Intent(this,Board.class);
            startActivity(intent);
        } catch(Exception e) {
            Log.e("exception serializing", e.toString());
        }
    }
}
