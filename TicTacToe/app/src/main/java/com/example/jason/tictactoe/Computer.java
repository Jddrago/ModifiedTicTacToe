package com.example.jason.tictactoe;

import java.util.Random;

/**
 * Created by Jason on 7/27/2017.
 */

public class Computer extends GamePiece {

    public Computer(int playersPiece){
        name = "Computer";
        imageID = selectPiece(playersPiece);
    }

    private int selectPiece(int pieceId){
        Random rand = new Random();
        int id,selection = -1;
        do{
            id = rand.nextInt(3);
            switch (id){
                case 0: selection = R.drawable.x; break;
                case 1: selection = R.drawable.o; break;
                case 2: selection = R.drawable.y; break;
            }

        }while(selection == pieceId);
        return selection;
    }

    @Override
    public int chooseLocation(){
        return new Random().nextInt(9);
    }
}
