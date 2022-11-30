package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import Poker.Hands;

public class HandsTest {
    String[] cards = {"AD","KD","QD","JD","TD"};
    Hands hand=new Hands(cards);

    String[] cards2 = {"9D","9S","QD","QS","TD"};
    Hands hand2=new Hands(cards2);

    @Test
    public void testGetValueToPrintAce(){
        assertEquals("Ace",hand.getValueToPrint(14));
    }

    @Test
    public void testGetValueToPrintTwo(){
        assertEquals("Two",hand.getValueToPrint(2));
    }

    @Test
    public void testHandToString(){
        assertEquals("AD KD QD JD TD",hand.handToString());
    }

    @Test
    public void testHighestHandToPrint(){
        assertEquals("Royal Flush W/ Diamonds",hand.highestHandToPrint());
    }

    @Test
    public void testGetHandRating(){
        assertEquals(10,hand.getHandRating());
    }

    @Test
    public void testGetHandName(){
        assertEquals("Royal Flush",hand.getHandName());
    }

    @Test
    public void testGetHighestCardValue(){
        assertEquals(14,hand.getHighestCardValue());
    }

    

    @Test
    public void testHandToString2(){
        assertEquals("9D 9S QD QS TD",hand2.handToString());
    }

    @Test
    public void testHighestHandToPrint2(){
        assertEquals("Two Pairs",hand2.highestHandToPrint());
    }

    @Test
    public void testGetHandRating2(){
        assertEquals(3,hand2.getHandRating());
    }

    @Test
    public void testGetHandName2(){
        assertEquals("Two Pairs",hand2.getHandName());
    }

    @Test
    public void testGetHighestCardValue2(){
        assertEquals(12,hand2.getHighestCardValue());
    }

}
