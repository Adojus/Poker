package Poker;


/*
 * Class for storing round cards
*/
public class Round {
    private String[] firstPlayersCards;//dealt cards for player 1
    private String[] secondPlayersCards;//dealt cards for player 2

    //constructor
    public Round(String[] firstPlayersCards, String[] secondPlayersCards){
        this.firstPlayersCards=firstPlayersCards;
        this.secondPlayersCards=secondPlayersCards;
    }
    
    //getter for 1st players cards
    public String[] getFirstPlayersCards(){return firstPlayersCards;}
    
    //getter for 2nd players cards
    public String[] getSecondPlayersCards(){return secondPlayersCards;}
    
}
