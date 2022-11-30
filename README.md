# Poker

**1000 Poker Games**


1.    Every line in Poker.txt is a game round and is loaded to the Round class, the first 5 cards are the first player's, the latter are second player's. Then for every round, we load the cards to class Hands variables thisHand and thisHand2. The Hands class constructor calls methods, to load the possible hands mapped to rank, card values mapped to respective integer value. And methods to determine: 
        * the rating (from 10 to 1, the higher the better) of the best hand that's made up from player's cards:
            * 10 - Royal flush
                * If every card's symbol is the same and if every cards value is a letter (T, J, Q, K, A)
            * 9 - Straight flush
                * If every card's symbol is the same and if there is a gap of 5 cards in an array, from which are removed all the values, that are present in player's hand.
            * 8 - Four of a kind
                * If there is maximum 4 cards with the same value.
            * 7 - Full house
                * If there is maximum 3 cards with the same value and there are only 2 different value families in hand.
            * 6 - Flush
                * If every card's symbol is the same
            * 5 - Straight
                * If there is a gap of 5 cards in an array, from which are removed all the values, that are present in player's hand.
            * 4 - Three of a kind
                * If there is maximum 3 cards with the same value.
            * 3 - Two Pairs
                * If there is maximum 2 cards with the same value and there are 3 different value families in hand.
            * 2 - One pair
                * If there is maximum 2 cards with the same value.
            * 1 - Highest card
                * If none of the above conditions were satisfied.
        * the highest value of highest valued card in player's hand, sorted (from lowest to highest) integer array of card values in hand.

        The player with higher rated hand wins. If the hand rating is the same, arrays that are sorted by occurences of value (high to low) and then by value (high to low) are compared while iterating throught their elements. Which player has higher card sooner in the array wins. If the card values in the arrays were the same for both players, the game is a tie.
2.    This solution uses the oop ideas of abstraction and encapsulation.
3.    Technology that I used and was new to me was JUnit for testing the solution.
