package Poker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Justis
 */
public class Poker {
    static final String file="poker.txt";
    static final int playerCount = 2;
    static final int cardsPerPlayer=5;
    static ArrayList<Round> roundList = new ArrayList<Round>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            readCardsFromFile();
        }
        catch(IOException e){
            System.out.println("Couldn't read file.");
        }
        
        
        
    }
    
    static void readCardsFromFile() throws IOException{
                BufferedReader reader=new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null)
                {
                    String[] parts = line.split(" ");
                    String[] cardsInHand = new String[cardsPerPlayer];
                    String[] cardsInHand2 = new String[cardsPerPlayer];
                    for(int i=0;i<playerCount*cardsPerPlayer;i++){
                        if(i<cardsPerPlayer)
                            cardsInHand[i]=parts[i];
                        else
                            cardsInHand2[i-5]=parts[i];
                    }
                    
                    Round playersCards = new Round(cardsInHand,cardsInHand2);
                    roundList.add(playersCards);
                    
                }
                reader.close();
    }
}
