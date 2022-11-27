package Poker;

/*
 * Class for storing players information
*/
public class Player {
    private String[] cardsInHand;//dealt cards
    private String highestCard;//card with highest value
    private String highestHand;//highest hand in deck
    private int winCount = 0;//number of wins
    
    //constructor
    public Player(String[] cardsInHand){
        this.cardsInHand=cardsInHand;
    }
    
    public Player(){}
    
    //getter for cards
    public String[] getCardsInHand(){return cardsInHand;}
    
    //setter for cards
    public void setCardsInHand(String[] cardsInHand){this.cardsInHand=cardsInHand;}
    
    //getter for highest card
    public String getHighestCard(){return highestCard;}
    
    //setter for highest card
    public void setHighestCard(String highestCard){this.highestCard=highestCard;}
    
    //getter for highest hand
    public String getHighestHand(){return highestHand;}
    
    //setter for highest hand
    public void setHighestHand(String highestHand){this.highestHand=highestHand;}
    
    //getter for win number
    public int getWinCount(){return winCount;}
    
    //method to increase winCount by 1
    public void addToWinCount(){winCount=winCount+1;}
    
    public String handToString(){
    String line;
    line = String.format("%s %s %s %s %s", cardsInHand);
    return line;
    }
}

