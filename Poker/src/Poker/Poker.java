package Poker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Main class
*/
public class Poker {
    static final String file="poker.txt";//text file to read from
    static final String resultsFile="Poker_Results.txt";//text file name to write to
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
        createResultsFile();
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
            boolean isRoundOver=false;
            System.out.println("Hand "+(i+1)+"\n");
            
            Hands thisHand = new Hands(roundList.get(i).getFirstPlayersCards());
            player1.setCardsInHand(roundList.get(i).getFirstPlayersCards());
            player1.setHighestHand(thisHand.getHandName());
            player1.setHighestCard(thisHand.getHighestCardValue());
            player1.setHighestCards(thisHand.getHighestCardValues());
            player1.setHighestBelongingValues(thisHand.getHighestBelongingValues());
            
            System.out.println(player1.handToString()+"    PLAYER 1\n"+
            thisHand.highestHandToPrint());
            
            System.out.println("");
            
            Hands thisHand2 = new Hands(roundList.get(i).getSecondPlayersCards());
            player2.setCardsInHand(roundList.get(i).getSecondPlayersCards());
            player2.setHighestHand(thisHand2.getHandName());
            player2.setHighestCard(thisHand2.getHighestCardValue());
            player2.setHighestCards(thisHand2.getHighestCardValues());
            player2.setHighestBelongingValues(thisHand2.getHighestBelongingValues());

            System.out.println(player2.handToString()+"    PLAYER 2\n"+
            thisHand2.highestHandToPrint());
            
            if(thisHand.getHandRating()!=thisHand2.getHandRating()){
                if(thisHand.getHandRating()<thisHand2.getHandRating()){
                    System.out.println("\nPLAYER 2 WINS "+player2.getHighestHand());
                    player2.addToWinCount();
                    printToFile(i+1, thisHand, thisHand2, "Player 2");
                    isRoundOver=true;
                }
                else if(isRoundOver!=true){
                    System.out.println("\nPLAYER 1 WINS "+player1.getHighestHand());
                    player1.addToWinCount();
                    printToFile(i+1, thisHand, thisHand2, "Player 1");
                    isRoundOver=true;
                }
            }
            else if(thisHand.getHandRating()==2 || thisHand.getHandRating()==3 ||
                    thisHand.getHandRating()==4 || thisHand.getHandRating()==7 ||
                    thisHand.getHandRating()==8){
                
                if(whichPlayerWins(player1.getHighestBelongingValues(),
                        player2.getHighestBelongingValues())==1 && isRoundOver!=true){
                    System.out.println("\nPLAYER 1 WINS "+player1.getHighestHand());
                    player1.addToWinCount();
                    printToFile(i+1, thisHand, thisHand2, "Player 1");
                    isRoundOver=true;
                    
                }
                else if(whichPlayerWins(player1.getHighestBelongingValues(),
                            player2.getHighestBelongingValues())==2 && isRoundOver!=true){
                        System.out.println("\nPLAYER 2 WINS "+player2.getHighestHand());
                        player2.addToWinCount();
                        printToFile(i+1, thisHand, thisHand2, "Player 2");
                        isRoundOver=true;
                    }
                if(whichPlayerWins(player1.getHighestCards(),
                        player2.getHighestCards())==1 && isRoundOver!=true){
                    System.out.println("\nPLAYER 1 WINS "+player1.getHighestHand());
                    player1.addToWinCount();
                    printToFile(i+1, thisHand, thisHand2, "Player 1");
                    isRoundOver=true;
                }    
                else if(whichPlayerWins(player1.getHighestCards(),
                        player2.getHighestCards())==2 && isRoundOver!=true){
                    System.out.println("\nPLAYER 2 WINS "+player2.getHighestHand());
                    player2.addToWinCount();
                    printToFile(i+1, thisHand, thisHand2, "Player 2");
                    isRoundOver=true;
                }
                else if(isRoundOver!=true){
                    System.out.println("\nTIE");
                    printToFile(i+1, thisHand, thisHand2, "TIE");
                }
                
            }
            else if(thisHand.getHandRating()==1 || thisHand.getHandRating()==5 ||
                    thisHand.getHandRating()==6 || thisHand.getHandRating()==9){
                if(whichPlayerWins(player1.getHighestCards(),
                        player2.getHighestCards())==1){
                    System.out.println("\nPLAYER 1 WINS "+player1.getHighestHand());
                    player1.addToWinCount();
                    printToFile(i+1, thisHand, thisHand2, "Player 1");
                }    
                else if(whichPlayerWins(player1.getHighestCards(),
                        player2.getHighestCards())==2){
                    System.out.println("\nPLAYER 2 WINS "+player2.getHighestHand());
                    player2.addToWinCount();
                    printToFile(i+1, thisHand, thisHand2, "Player 2");
                }
                else{
                    System.out.println("\nTIE");
                    printToFile(i+1, thisHand, thisHand2, "TIE");
                }
            }
            else{
                System.out.println("\nTIE");
                printToFile(i+1, thisHand, thisHand2, "TIE");
            }
                
            System.out.println("\n-------------------------------");
        }
        System.out.println("Player 1 total wins: "+player1.getWinCount());
        
        try
            {
                FileWriter file= new FileWriter(resultsFile,true);
                BufferedWriter fr = new BufferedWriter(file);
                String line;
                line = String.format("Player 1 total win count: "+player1.getWinCount());
                fr.write(line);
                fr.close();
            }
            catch(Exception e){e.printStackTrace();}
    }
    
    //Determining higher cards between players
    static int whichPlayerWins(int[] player1, int[] player2){
        for(int i= player1.length-1;i>=0;i--){
            if(player1[i]>player2[i]){
                return 1;
            }
            else if(player1[i]<player2[i]) {
                return 2;
            }
        }
        return 0;
    }
    
    //prints the top of the table to result file
    static void createResultsFile(){
        String top =
              "------------------------------------------------------------------------------------\n"
            + "  Hand                  Player 1                          Player 2          Winner  \n"
            + "------------------------------------------------------------------------------------\n";
            try
            {
                
               FileWriter file= new FileWriter(resultsFile,false);
               BufferedWriter fr = new BufferedWriter(file);
               fr.write(top);
               fr.close();
            }
            catch(Exception e){e.printStackTrace();}
    }
    
    //adds round information to table in results file
    static void printToFile(int hand, Hands player1, Hands player2, String winner){
        try
            {
                FileWriter file= new FileWriter(resultsFile,true);
                BufferedWriter fr = new BufferedWriter(file);
                String line;
                line = String.format(" %4d               %-32s  %-20s %8s  \n",
                 hand, player1.handToString(), player2.handToString(), winner);
                fr.write(line);

                line = String.format("%35s %35s",player1.highestHandToPrint(),player2.highestHandToPrint()+"\n\n");
                fr.write(line);

                fr.close();
            }
            catch(Exception e){e.printStackTrace();}
    }
}
