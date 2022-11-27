package Poker;

import java.util.*;
import java.util.HashMap;

/*
 * Class for determining players hand strength
*/
public class Hands{
    //Map for storing hand name as key and rating as value
    private Map<String,Integer> possibleHands = new HashMap<>();
    
    //Map for storing cards values as key and their integer counterpart as value
    private Map<Character, Integer> possibleCardValues = new HashMap<>();
    
    //array storing all possible card values
    private int[] possibleValues={2,3,4,5,6,7,8,9,10,11,12,13,14};
    private int playerHandRating=0;//players hand rating-the higher, the better
    private String handName;//players hands name
    private String[] playerHand;//players cards
    
    //constructor
    public Hands(){
        addPossibleHands();
        addPossibleCardValues();
    }
    
    //constructor
    public Hands(String[] playerHand){
        this.playerHand = playerHand;
        addPossibleHands();
        addPossibleCardValues();
        determineRating();
    }
    
    //setter for players card array
    public void setPlayerHand(String[] playerHand){
        this.playerHand = playerHand;
        determineRating();
    }
    
    //getter for hands rating
    public int getHandRating(){return playerHandRating;}
    
    //getter for hands name
    public String getHandName(){return handName;}
    
    //method that takes hand name and returns its key in map possibleHands
    public <K, V> String getKey(Integer handName)
    {
        for (Map.Entry<String, Integer> entry: possibleHands.entrySet())
        {
            if (handName.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    //populates possibleHands map
    private void addPossibleHands(){
        possibleHands.put("Highest Card",1);
        possibleHands.put("One Pair", 2);
        possibleHands.put("Two Pairs", 3);
        possibleHands.put("Three of a Kind",4);
        possibleHands.put("Straight",5);
        possibleHands.put("Flush",6);
        possibleHands.put("Full House",7);
        possibleHands.put("Four of a Kind",8);
        possibleHands.put("Straight Flush",9);
        possibleHands.put("Royal Flush",10);
    }
    
    //populates possibleCardValues map
    private void addPossibleCardValues(){
        possibleCardValues.put('2', 2);
        possibleCardValues.put('3', 3);
        possibleCardValues.put('4', 4);
        possibleCardValues.put('5', 5);
        possibleCardValues.put('6', 6);
        possibleCardValues.put('7', 7);
        possibleCardValues.put('8', 8);
        possibleCardValues.put('9', 9);
        possibleCardValues.put('T', 10);
        possibleCardValues.put('J', 11);
        possibleCardValues.put('Q', 12);
        possibleCardValues.put('K', 13);
        possibleCardValues.put('A', 14);
    }
    
    //resets possibleValues array
    private void resetPossibleValuesArray(){
        int[] tempValues = {2,3,4,5,6,7,8,9,10,11,12,13,14};
        possibleValues=tempValues;
    }
    
    //determines rating and name of hand
    private void determineRating(){
        if(isRoyalFlush()){
            playerHandRating = possibleHands.get("Royal Flush");
            handName = getKey(playerHandRating);
        }
        else if(isStraightFlush()){
            playerHandRating = possibleHands.get("Straight Flush");
            handName = getKey(playerHandRating);
        }
        else if(isFourOfAKind()){
            playerHandRating = possibleHands.get("Four of a Kind");
            handName = getKey(playerHandRating);
        }
        else if(isFullHouse()){
            playerHandRating = possibleHands.get("Full House");
            handName = getKey(playerHandRating);
        }
        else if(isFlush()){
            playerHandRating = possibleHands.get("Flush");
            handName = getKey(playerHandRating);
        }
        else if(isStraight()){
            resetPossibleValuesArray();
            playerHandRating = possibleHands.get("Straight");
            handName = getKey(playerHandRating);
        }
        else if(isThreeOfAKind()){
            playerHandRating = possibleHands.get("Three of a Kind");
            handName = getKey(playerHandRating);
        }
        else if(isTwoPairs()){
            playerHandRating = possibleHands.get("Two Pairs");
            handName = getKey(playerHandRating);
        }
        else if(isOnePair()){
            playerHandRating = possibleHands.get("One Pair");
            handName = getKey(playerHandRating);
        }
        else{
            playerHandRating = possibleHands.get("Highest Card");
            handName = getKey(playerHandRating);
        }
    }
    
    //returns true if hands conditions are suitable for royal flush
    private boolean isRoyalFlush(){
        return isFlush() && isRoyal();
    }
    
    //returns true if hands conditions are suitable for sraight flush
    private boolean isStraightFlush(){
        return isFlush() && isStraight();
    }
    
    //returns true if hands conditions are suitable for four of a kind
    private boolean isFourOfAKind(){
        return maxSameValueCards()==4;
    }
    
    //returns true if hands conditions are suitable for full house
    private boolean isFullHouse(){
        return maxSameValueCards()==3 && numberOfSameValueCardsFamilies()==2;
    }
    
    //returns true if hands conditions are suitable for flush
    private boolean isFlush(){
        char cardSymbol='E';
        for(int i=0; i<playerHand.length; i++){
            if(i==0){
                cardSymbol = playerHand[i].charAt(1);
            }
            else{
                if(cardSymbol != playerHand[i].charAt(1)){
                    return false;
                }
            }
        }
        return true;
    }
    
    //returns true if hands conditions are suitable for straight
    private boolean isStraight(){
        for(String card : playerHand){
            char cardValue = card.charAt(0);
            int intValue = possibleCardValues.get(cardValue);
            removeValue(intValue);
        }
        return possibleValues[0]==7 || isThereGap();
    }
    
    //returns true if hands conditions are suitable for three of a kind
    private boolean isThreeOfAKind(){
        return maxSameValueCards()==3;
    }
    
    //returns true if hands conditions are suitable for two pairs
    private boolean isTwoPairs(){
        return maxSameValueCards()==2 && numberOfSameValueCardsFamilies()==3;
    }
    
    //returns true if hands conditions are suitable for one pair
    private boolean isOnePair(){
        return maxSameValueCards()==2;
    }
    
    //returns true if every cards first character is a letter
    private boolean isRoyal(){
        for(String card : playerHand){
            if(Character.isDigit(card.charAt(0))){
                return false;
            }
        }
        return true;
    }
    
    //removes given value from possibleValues array
    private void removeValue(int value){
        int[] newValues=new int[possibleValues.length-1];
        int counter =0;
        for(int i=0; i<possibleValues.length-1; i++){
            if(possibleValues[i]!=value){
                newValues[counter]=possibleValues[i];
                counter++;
            }
        }
        possibleValues = newValues;
    }
    
    //checks if there is a gap of 5 values in the middle of possibleValues array
    private boolean isThereGap(){
        //int maxGap=0;
        for(int i=0; i<possibleValues.length-1; i++){
            //int currentGap=0;
            if(possibleValues[i]-possibleValues[i+1]<-5){
                return true;
            }
        }
        return false;
    }
    
    //calculates and returns the number of different values present in hand
    private int numberOfSameValueCardsFamilies(){
        char[] families={0,0,0,0,0};
        int counter=0;
        for(String card : playerHand){
            for(int i=0; i<families.length; i++){
                
                if(card.charAt(0)==families[i]){
                    break;
                }
                else if(families[i]==0){
                    families[i]=card.charAt(0);
                    counter++;
                    break;
                }
            }
        }
        return counter;
    }

    //calculates and returns number of identical values in hand
    private int maxSameValueCards(){
        
        int maxSameCards=0;
        for(int i=0; i< playerHand.length; i++){
            int temp=1;
            char cardValue = playerHand[i].charAt(0);
            for(int j=0; j< playerHand.length; j++){
                if(i!=j && cardValue == playerHand[j].charAt(0)){
                    temp++;
                }
            }
            if(temp>maxSameCards){
                maxSameCards=temp;
            }
        }
        return maxSameCards;
    }
    
}
