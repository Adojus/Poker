/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poker;

/**
 *
 * @author Justis
 */
public class Player {
    private String[] cardsInHand;
    private String highestCard;
    private String highestHand;
    private int winCount=0;
    
    public Player(String[] cardsInHand){
        this.cardsInHand=cardsInHand;
    }
    
    public String[] getCardsInHand(){return cardsInHand;}
    public void setCardsInHand(String[] cardsInHand){this.cardsInHand=cardsInHand;}
    
    public String getHighestCard(){return highestCard;}
    private void setHighestCard(String highestCard){this.highestCard=highestCard;}
    
    public String getHighestHand(){return highestHand;}
    private void setHighestHand(String highestHand){this.highestHand=highestHand;}
    
    public int getWinCount(){return winCount;}
    public void addToWinCount(){winCount=winCount+1;}
    
    
}

