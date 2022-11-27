package Poker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Main class
*/
public class Poker {
    static final String file="poker.txt";//text file to read from
    static final int playerCount = 2;//number of players per round
    static final int cardsPerPlayer=5;//number of cards per player
    static ArrayList<Round> roundList = new ArrayList<Round>();//list of all rounds
    static Player player1 = new Player();
    static Player player2 = new Player();
    
    public static void main(String[] args) {
        try{
            readRoundsFromFile();
        }
        catch(IOException e){
            System.out.println("Couldn't read file.");
            System.exit(0);
        }
        
        play();
    }
    
    // Reading rounds from file and populating Round class
    static void readRoundsFromFile() throws IOException{
        BufferedReader reader=new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null){
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
    
    //Printing out rounds played, finding the winner
    static void play(){
        
        for(int i=0; i<roundList.size();i++){
            
            System.out.println("Hand "+(i+1)+"\n");
            
            Hands thisHand = new Hands(roundList.get(i).getFirstPlayersCards());
            player1.setCardsInHand(roundList.get(i).getFirstPlayersCards());
            player1.setHighestHand(thisHand.getHandName());
            
            System.out.println(player1.handToString()+"    PLAYER 1\n"+player1.getHighestHand());
            
            System.out.println("");
            
            Hands thisHand2 = new Hands(roundList.get(i).getSecondPlayersCards());
            player2.setCardsInHand(roundList.get(i).getSecondPlayersCards());
            player2.setHighestHand(thisHand2.getHandName());
            System.out.println(player2.handToString()+"    PLAYER 2\n"+player2.getHighestHand());
            
            if(thisHand.getHandRating()!=thisHand2.getHandRating()){
                if(thisHand.getHandRating()<thisHand2.getHandRating()){
                    System.out.println("\nPLAYER 2 WINS "+player2.getHighestHand());
                    player2.addToWinCount();
                    
                }
                else {
                    System.out.println("\nPLAYER 1 WINS "+player1.getHighestHand());
                    player1.addToWinCount();
                }
            }
            else System.out.println("\nTIE");
            System.out.println("\n-------------------------------");
        }
        System.out.println("Player 1 wins: "+player1.getWinCount());
        System.out.println("Player 2 wins: "+player2.getWinCount());
    }
}
