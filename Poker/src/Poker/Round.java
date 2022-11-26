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
public class Round {
    private String[] firstPlayersCards;
    private String[] secondPlayersCards;
    
    public Round(String[] firstPlayersCards, String[] secondPlayersCards){
        this.firstPlayersCards=firstPlayersCards;
        this.secondPlayersCards=secondPlayersCards;
    }
}
