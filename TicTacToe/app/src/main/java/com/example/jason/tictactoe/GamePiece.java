package com.example.jason.tictactoe;

import java.io.Serializable;

/**
 * Created by Jason on 7/18/2017.
 */

public abstract class GamePiece implements Serializable{

    public String name;
    public int imageID;

    public GamePiece(){

    }

    public abstract int chooseLocation();
}
