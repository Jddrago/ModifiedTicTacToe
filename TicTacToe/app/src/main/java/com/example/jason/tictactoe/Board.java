package com.example.jason.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Board extends AppCompatActivity {

    private ImageView[] images = new ImageView[9];
    private SharedPreferences shared;
    private ArrayList<GamePiece> pieces = new ArrayList<>();
    private GamePiece currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        images[0] = (ImageView)findViewById(R.id.imageView4);
        images[1] = (ImageView)findViewById(R.id.imageView5);
        images[2] = (ImageView)findViewById(R.id.imageView6);
        images[3] = (ImageView)findViewById(R.id.imageView7);
        images[4] = (ImageView)findViewById(R.id.imageView8);
        images[5] = (ImageView)findViewById(R.id.imageView9);
        images[6] = (ImageView)findViewById(R.id.imageView10);
        images[7] = (ImageView)findViewById(R.id.imageView11);
        images[8] = (ImageView)findViewById(R.id.imageView12);
        for (ImageView i: images) {
            i.setTag(R.drawable.blank);
        }
        shared = this.getSharedPreferences("com.example.jason.tictactoe", Context.MODE_PRIVATE);
        try {
            pieces = (ArrayList<GamePiece>) ObjectSerializer.deserialize(
                    shared.getString("game",
                            ObjectSerializer.serialize(new ArrayList<GamePiece>())));
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
        currentPlayer = pieces.get(0);
        ((TextView)findViewById(R.id.textView3)).setText(currentPlayer.name + "'s turn");
    }

    public void onClick(View view){
        ImageView image = (ImageView)view;
        if(Integer.parseInt(image.getTag().toString()) == R.drawable.blank){
            image.setImageResource(currentPlayer.imageID);
            image.setTag(currentPlayer.imageID);
            if(!checkForWin() && checkTie() < 9) {
                if (currentPlayer == pieces.get(0)) {
                    currentPlayer = pieces.get(1);
                    ComputersTurn();
                } else {
                    currentPlayer = pieces.get(0);
                }
            }
            else{
                if(checkForWin()){
                    Intent intent = new Intent(this, WinScreen.class);
                    intent.putExtra("winner",  currentPlayer.name + " wins");
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(this, WinScreen.class);
                    intent.putExtra("winner","It's a tie");
                    startActivity(intent);
                }
            }
        }
        ((TextView)findViewById(R.id.textView3)).setText(currentPlayer.name + "'s turn");
    }

    public void ComputersTurn(){
        Log.i("Computers:","turn");
        int compsChoice = currentPlayer.chooseLocation();
        boolean validSelection = false;
        while(!validSelection) {
            Log.i("Computers selection:", compsChoice + "");
            if (Integer.parseInt(images[compsChoice].getTag().toString()) == R.drawable.blank) {
                images[compsChoice].setImageResource(currentPlayer.imageID);
                images[compsChoice].setTag(currentPlayer.imageID);
                validSelection = true;
            }
            else{
                compsChoice = currentPlayer.chooseLocation();
            }
        }

        if(!checkForWin() && checkTie() < 9) {
            currentPlayer = pieces.get(0);
        }
        else{
            if(checkForWin()){
                Intent intent = new Intent(this, WinScreen.class);
                intent.putExtra("winner",  currentPlayer.name + " wins");
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, WinScreen.class);
                intent.putExtra("winner","It's a tie");
                startActivity(intent);
            }
        }
    }

    public boolean checkForWin(){
        boolean victory = false;
        if(checkCondition(0,1,2)){
            victory = true;
        }
        else if(checkCondition(3,4,5)){
            victory = true;
        }
        else if(checkCondition(6,7,8)){
            victory = true;
        }
        else if(checkCondition(0,3,6)){
            victory = true;
        }
        else if(checkCondition(1,4,7)){
            victory = true;
        }
        else if(checkCondition(2,5,8)){
            victory = true;
        }
        else if(checkCondition(0,4,8)){
            victory = true;
        }
        else if(checkCondition(2,4,6)){
            victory = true;
        }
        return victory;
    }

    public boolean checkCondition(int index1, int index2, int index3){
        return Integer.parseInt(images[index1].getTag().toString()) == Integer.parseInt(images[index2].getTag().toString())
                && Integer.parseInt(images[index1].getTag().toString()) == Integer.parseInt(images[index3].getTag().toString())
                && ((Integer.parseInt(images[index1].getTag().toString()) == pieces.get(0).imageID) || (Integer.parseInt(images[index1].getTag().toString()) == pieces.get(1).imageID));
    }

    public int checkTie(){
        int count = 0;
        for (ImageView image: images) {
            if(Integer.parseInt(image.getTag().toString()) != R.drawable.blank){
                count++;
            }
        }
        return count;
    }
}
