/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirsttry;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author ugurcan
 */
public class GameManager 
{
    private ArrayList<OkeyStone> StoneList = new ArrayList<>();
    private Color Colors[] = new Color[5];
    private ArrayList<Player> Players = new ArrayList<>();

    private OkeyStone Okey[]= new OkeyStone[2];

    public int getOkeyValue() {
        return Okey[0].getDeger();
    }
    
    public GameManager() 
    {
        Colors[0]=new Color("Sarı");
        Colors[1]=new Color("Mavi");
        Colors[2]=new Color("Siyah");
        Colors[3]=new Color("Kırmızı");
        Colors[4]=new Color("Sahte");
    }
    
    public void AddPlayer(Player player)
    {
        if(Players.size()<4)
        Players.add(player);
        else
            System.out.println("Room is currently full");
        
    }
    public void RemovePlayer(Player player)
    {
        Players.remove(player);
        System.out.println(player.getPlayerName()+" Removed from game");
    }
    public void SetUpGame(int extraStonePlayer)
    {
        if(Players.size()==4)
        {
        
         for(int i=0;i<4;i++)
        {
            for(int j=1;j<=13;j++)
            {
                
                StoneList.add(new OkeyStone(Colors[i], j));
                StoneList.add(new OkeyStone(Colors[i], j));
            }
            
            
        }
        StoneList.add(new OkeyStone(Colors[4], 0));
        StoneList.add(new OkeyStone(Colors[4], 0));
        
        Collections.shuffle(StoneList);
        
        this.Okey = SelectOkey();
        DistributeTheStones(extraStonePlayer);
        }
        else
        {
            System.out.println("Not enough players to start the game!");
           
        }
          
        
        
            
    }
    private OkeyStone[] SelectOkey()
    {
        
       
        //Creating a random integer between 0 and 106(Maximum is excluded)
        int randomNum = ThreadLocalRandom.current().nextInt(0, 105 + 1);
        //Assigning found "stone" to our "temp" stone variable 
        OkeyStone temp = StoneList.get(randomNum);
        
        //if we found a "FakeOkey" we are searching again,until we find a "Normal Stone"
        while(temp.getTasRengi()== Colors[4])
        {
        randomNum = ThreadLocalRandom.current().nextInt(0, 105 + 1);
        temp = StoneList.get(randomNum);
        }
        
        StoneList.remove(temp);
        
        
        OkeyStone RealOkey[]= new OkeyStone[2];
        
        
        //if our temp stone's value = 13 then our "Okey" must be 1, otherwise we can just take one bigger valued stone as "Okey"
        if(temp.getDeger()==13)
        {
            RealOkey = findStone(temp.getTasRengi(), 1);
            
        }
        else
        {
             RealOkey = findStone(temp.getTasRengi(), temp.getDeger()+1);
            
        }
        RealOkey[0].setIsOkey(true);
        RealOkey[1].setIsOkey(true);
        
       return RealOkey;
        
    }
    
    //I implemented a function to find required stones quickly from "Stone List" with specific properties
    private OkeyStone[] findStone(Color stoneColor,int stoneValue)
    {
         OkeyStone RealOkey[] = new OkeyStone[2];
         int i=0;
         for(Iterator<OkeyStone> tas=StoneList.iterator();tas.hasNext();)
            {
                RealOkey[i] = tas.next();
                if(RealOkey[i].getTasRengi() == stoneColor&&RealOkey[i].getDeger()==stoneValue)
                {
                    RealOkey[i] = tas.next();
                    i++;
                   
                }
                
            }
         return RealOkey;
        
    }
    
    //A fumction to distribute the stones to players
    public void DistributeTheStones(int playerNumber)
    {
        String extraStonePlayer = Players.get(playerNumber).getPlayerName();
        for(Iterator<Player> player = Players.iterator();player.hasNext();)
        {
            
            if(player.next().getPlayerName() == extraStonePlayer)
            {
                player.next().setMyBoard(giveStone(15));
                
            }
            else
            {
                player.next().setMyBoard(giveStone(14));
            }
            
        }
        
        
        
    }
    //This function returns 15 okeystones from stone arraylist also it deletes already distributed stones from StoneList array. 
    private ArrayList<OkeyStone> giveStone(int stoneCount)
    {
        ArrayList<OkeyStone> temp = new ArrayList<>();
        for(int i=0;i<stoneCount+1;i++)
                {
                    OkeyStone tempStone = StoneList.get(i);
                    temp.add(tempStone);
                    StoneList.remove(tempStone);
                }
        
        return temp;
        
    }
            
    
   
    
    
    
    
}
