package edu.brandeis.cosi103a.ip1;
import java.util.List;
import java.util.Random;

import edu.brandeis.cosi103a.ip1.Card;
import edu.brandeis.cosi103a.ip1.Card.CardType;
import edu.brandeis.cosi103a.ip1.Player;


public class DominionGame {

    public static void main(String[] args) {
        DominionGame game = new DominionGame();
        game.play();
    }

    private Supply supply;
    private List<Card> purchasePriority;


    private Card bitcoin;
    private Card ethereum;
    private Card dogecoin;
    private Card method;
    private Card module;
    private Card framework;

    private Player player1;
    private Player player2;

    

    public void play() {
        setup();
        gameLoop();
    }

    public void setup() {
        bitcoin = new Card("Bitcoin", Card.CardType.CRYPTO, 0, 1);
        ethereum = new Card("Ethereum", Card.CardType.CRYPTO, 3, 2);
        dogecoin = new Card("Dogecoin", Card.CardType.CRYPTO, 6, 3);

        method = new Card("Method", Card.CardType.AUTOMATION, 2, 1);
        module = new Card("Module", Card.CardType.AUTOMATION, 5, 3);
        framework = new Card("Framework", Card.CardType.AUTOMATION, 8, 6);

        purchasePriority = List.of(
            framework,
            module,
            method,
            dogecoin,
            ethereum,
            bitcoin
        );        

        supply = new Supply();
        supply.addPile(bitcoin, 60);
        supply.addPile(ethereum, 40);
        supply.addPile(dogecoin, 30);
        supply.addPile(method, 14);
        supply.addPile(module, 8);
        supply.addPile(framework, 8);

        player1 = new Player();
        player2 = new Player();

        for (int i = 0; i < 7; i++) {
            player1.addToDrawPile(supply.buy(bitcoin));
            player2.addToDrawPile(supply.buy(bitcoin));
        }

        for (int i = 0; i < 3; i++) {
        player1.addToDrawPile(supply.buy(method));
        player2.addToDrawPile(supply.buy(method));
        }

        player1.shuffleDrawPile();
        player2.shuffleDrawPile();

        player1.drawHand(5);
        player2.drawHand(5);

        System.out.println("Player 1 starting hand: " + player1.getHand());
        System.out.println("Player 2 starting hand: " + player2.getHand());

    }

    public void takeTurn(Player player, Supply supply) {
        System.out.println("\n--- New Turn ---");
        System.out.println("Current player: " + (player == player1 ? "Player 1" : "Player 2"));
        System.out.println("Hand: " + player.getHand());
        
        int coins = 0;
        for (Card card : player.getHand()) {
        if (card.getType() == CardType.CRYPTO) {
            coins += card.getValue();
            }
        }

        System.out.println("Coins available this turn: " + coins);

        Card toBuy = chooseCardToBuy(coins, supply);
        if (toBuy != null) {
            player.gainCard(supply.buy(toBuy));
            System.out.println("Bought card: " + toBuy.getName());
        } else {
            System.out.println("No card bought this turn.");
        }

        cleanup(player);
    }

    private Card chooseCardToBuy(int coins, Supply supply) {
        for (Card card : purchasePriority) {
            if (coins >= card.getCost() && supply.isAvailable(card)) {
                return card;
            }
        }
        return null;
    }
    
    private void cleanup(Player player) {
        player.discardHand();
        player.drawHand(5);
        System.out.println("Hand after cleanup: " + player.getHand());

   
    System.out.println("Draw pile size: " + player.getDrawPileSize());
    System.out.println("Discard pile size: " + player.getDiscardPileSize());
    }
    
    private void gameLoop() {
        Random rand = new Random();
        Player currentPlayer = rand.nextBoolean() ? player1 : player2;
    
        while (supply.getRemaining(framework) > 0) {
            takeTurn(currentPlayer, supply);
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    
        endGame();
    }
    
    
    public int score(Player player) {
        int total = 0;
        for (Card card : player.getAllCards()) {
            if (card.getType() == CardType.AUTOMATION) {
                total += card.getValue();
            }
        }
        return total;
    }

    private void endGame() {
        System.out.println("\n--- Game Over ---");
        int score1 = score(player1);
        int score2 = score(player2);
    
        System.out.println("Player 1 score: " + score1);
        System.out.println("Player 2 score: " + score2);
    
        if (score1 > score2) {
            System.out.println("Player 1 wins!");
        } else if (score2 > score1) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
    
    

}

