/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirsttry;

import java.util.ArrayList;

/**
 *
 * @author ugurcan
 */
public class Player {
    private String PlayerName;
    private boolean extraStone;
    private ArrayList<OkeyStone> myBoard = new ArrayList<>();
    
    public String getPlayerName() {
        return PlayerName;
    }

    public boolean isExtraStone() {
        return extraStone;
    }

    

    public void setPlayerName(String PlayerName) {
        this.PlayerName = PlayerName;
    }

    public void setExtraStone(boolean extraStone) {
        this.extraStone = extraStone;
    }

    public void setMyBoard(ArrayList<OkeyStone> myBoard) {
        this.myBoard = myBoard;
    }

    public ArrayList<OkeyStone> getMyBoard() {
        return myBoard;
    }

    
   
    

    public Player(String Name) 
    {
    this.PlayerName = Name;
    }
    
    
    
 }
